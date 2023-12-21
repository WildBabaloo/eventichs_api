package eventichs.api.eventichs_api.Controleurs

import eventichs.api.eventichs_api.Exceptions.RessourceInexistanteException
import eventichs.api.eventichs_api.Modèle.InvitationOrganisation
import eventichs.api.eventichs_api.Services.InvitationOrganisationService
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc


import org.springframework.http.MediaType
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import com.fasterxml.jackson.databind.ObjectMapper
import eventichs.api.eventichs_api.Exceptions.ConflitAvecUneRessourceExistanteException
import eventichs.api.eventichs_api.Exceptions.DroitAccèsInsuffisantException
import eventichs.api.eventichs_api.Exceptions.PasConnectéException
import eventichs.api.eventichs_api.Modèle.Organisation
import eventichs.api.eventichs_api.Modèle.Utilisateur
import org.hamcrest.CoreMatchers.containsString
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders


@SpringBootTest
@AutoConfigureMockMvc
class InvitationOrganisationControleurTest {

    @Autowired
    private lateinit var mapper: ObjectMapper

    @MockBean
    lateinit var service: InvitationOrganisationService

    @Autowired
    private lateinit var mockMvc: MockMvc



    // -----------------------------------------------------------------------------------------------------------------
    //
    // @GetMapping("/organisations/invitations/{id}")
    //
    // -----------------------------------------------------------------------------------------------------------------
    @WithMockUser("Joe")
    @Test // obtenirInvitationsParId() 200 OK
    fun `1- Étant donné une invitation ayant l'id 1 qui existe dans le service lorsqu'on effectue une requête GET de recherche par id alors on obtient un JSON qui contient l'invitation dont l'id est 1 et un code de retour 200` (){
        val invitation = InvitationOrganisation(
            1,
            Utilisateur(
                "auth0|656d3ecc4178aefc03429538",
                "email",
                "nomUtil",
                "prénom"),
            Organisation(
                1,
                "auth0|656d2dbea19599c9209a4f01",
                "nomOrg",
                1,
                true),
            null,
            "envoyé")

        Mockito.`when`(service.chercherParID(1,"Joe")).thenReturn(invitation)

        mockMvc.perform(get("/organisations/invitations/1").with(csrf()))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.utilisateur.code").value("auth0|656d3ecc4178aefc03429538"))
            .andExpect(jsonPath("$.utilisateur.nom").value("nomUtil"))
            .andExpect(jsonPath("$.utilisateur.prénom").value("prénom"))
            .andExpect(jsonPath("$.utilisateur.email").value("email"))
            .andExpect(jsonPath("$.organisation.id").value("1"))
            .andExpect(jsonPath("$.organisation.codeUtilisateur").value("auth0|656d2dbea19599c9209a4f01"))
            .andExpect(jsonPath("$.organisation.nomOrganisation").value("nomOrg"))
            .andExpect(jsonPath("$.organisation.catégorie_id").value("1"))
            .andExpect(jsonPath("$.organisation.estPublic").value(true))
            .andExpect(jsonPath("$.jeton").value(null))
            .andExpect(jsonPath("$.status").value("envoyé"))
    }

    @WithMockUser("Anonym")
    @Test  // obtenirInvitationsParId() 401 UNAUTHORIZED
    fun `1,1- Étant donné l'invitation dont l'id est 8 et qui un utilisateur non connecté lorsqu'on effectue une requête GET de recherche par id  alors on obtient un code de retour 401 et le message d'erreur «L'utilisateur n'est pas connecté,»`() {
        Mockito.`when`(service.chercherParID(8,"Anonym")).thenThrow(PasConnectéException("L'utilisateur n'est pas connecté."))

        mockMvc.perform(get("/organisations/invitations/8")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isUnauthorized)
            .andExpect { résultat ->
                assertTrue(résultat.resolvedException is PasConnectéException)
                assertEquals("L'utilisateur n'est pas connecté.", résultat.resolvedException?.message)
            }
    }

    @WithMockUser(username = "Sam")
    @Test  // obtenirInvitationsParId() 403 FORBIDDEN
    fun `1,3- Étant donné l'invitation dont l'id est 8 et qui un utilisateur n'a pas les droits d'accès lorsqu'on effectue une requête GET de recherche par id  alors on obtient un code de retour 403 et le message d'erreur «L'utilisateur n'as pas le droit de consulter cette invitation»`() {
        Mockito.`when`(service.chercherParID(8,"Sam")).thenThrow(DroitAccèsInsuffisantException("L'utilisateur n'as pas le droit de consulter cette invitation"))

        mockMvc.perform(get("/organisations/invitations/8").with(csrf())
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isForbidden)
            .andExpect { résultat ->
                assertTrue(résultat.resolvedException is DroitAccèsInsuffisantException)
                assertEquals("L'utilisateur n'as pas le droit de consulter cette invitation", résultat.resolvedException?.message)
            }
    }

    @WithMockUser("Joe")
    @Test  // obtenirInvitationsParId() 404 isNotFound
    fun `2- Étant donné l'invitation dont l'id est 8 et qui n'est pas inscrite au service lorsqu'on effectue une requête GET de recherche par id  alors on obtient un code de retour 404 et le message d'erreur «L'invitation 8 à une organisation n'est pas inscrit au service»`() {
        Mockito.`when`(service.chercherParID(8,"Joe")).thenReturn(null)

        mockMvc.perform(get("/organisations/invitations/8").with(csrf())
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound)
            .andExpect { résultat ->
                assertTrue(résultat.resolvedException is RessourceInexistanteException)
                assertEquals("L'invitation 8 à une organisation n'est pas inscrit au service", résultat.resolvedException?.message)
            }
    }


    // -----------------------------------------------------------------------------------------------------------------
    //
    // @PostMapping("/organisations/invitations/")
    // Cas d'utilisation: 1.Demander à joindre une organisation (Participant)
    //
    // -----------------------------------------------------------------------------------------------------------------
    @WithMockUser("Joe")
    @Test  //demandeJoindreOrganisation() 201 isCreated
    fun `3- Étant donnée une invitation à une organisation dont l'id de l'organisation est 1 et celui du participant est Joe lorsqu'on effectue une requête POST pour l'ajouter alors on obtient un code de retour 201 et un JSON qui contient l'invitation créé`(){
        val invitation = InvitationOrganisation(
            1,
            Utilisateur(
                "auth0|656d3ecc4178aefc03429538",
                "email",
                "nomUtil",
                "prénom"),
            Organisation(
                1,
                "auth0|656d2dbea19599c9209a4f01",
                "nomOrg",
                1,
                true),
            null,
            "envoyé")

        Mockito.`when`(service.demandeJoindreOrganisation(invitation,"Joe")).thenReturn(invitation)

        mockMvc.perform(post("/organisations/invitations").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(invitation)))
            .andExpect(status().isCreated)
            .andExpect(
                header().string("Location", containsString("/organisations/invitations/1")))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.utilisateur.code").value("auth0|656d3ecc4178aefc03429538"))
                .andExpect(jsonPath("$.utilisateur.nom").value("nomUtil"))
                .andExpect(jsonPath("$.utilisateur.prénom").value("prénom"))
                .andExpect(jsonPath("$.utilisateur.email").value("email"))
                .andExpect(jsonPath("$.organisation.id").value("1"))
                .andExpect(jsonPath("$.organisation.codeUtilisateur").value("auth0|656d2dbea19599c9209a4f01"))
                .andExpect(jsonPath("$.organisation.nomOrganisation").value("nomOrg"))
                .andExpect(jsonPath("$.organisation.catégorie_id").value("1"))
                .andExpect(jsonPath("$.organisation.estPublic").value(true))
                .andExpect(jsonPath("$.jeton").value(null))
                .andExpect(jsonPath("$.status").value("envoyé"))

        Mockito.verify(service).demandeJoindreOrganisation(invitation, "Joe")

    }

    @WithMockUser("Anonym")
    @Test  //demandeJoindreOrganisation() 401 UNAUTHORIZED
    fun `3,1 - Étant donnée une invitation à une organisation dont l'id de l'organisation est 1 et le participant n'est pas connecté lorsqu'on effectue une requête POST pour l'ajouter alors on obtient un code de retour 401 et le message d'erreur «L'utilisateur n'est pas connecté,»`(){
        val invitation = InvitationOrganisation(
            1,
            Utilisateur(
                "auth0|656d3ecc4178aefc03429538",
                "email",
                "nomUtil",
                "prénom"),
            Organisation(
                1,
                "auth0|656d2dbea19599c9209a4f01",
                "nomOrg",
                1,
                true),
            null,
            "envoyé")

        Mockito.`when`(service.demandeJoindreOrganisation(invitation,"Anonym")).thenThrow(PasConnectéException("L'utilisateur n'est pas connecté."))

        mockMvc.perform(post("/organisations/invitations").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(invitation)))
            .andExpect(status().isUnauthorized)
            .andExpect { résultat ->
                assertTrue(résultat.resolvedException is PasConnectéException)
                assertEquals("L'utilisateur n'est pas connecté.", résultat.resolvedException?.message)
            }
    }

    @WithMockUser("Sam")
    @Test  //demandeJoindreOrganisation() 403 FORBIDDEN
    fun `3,3 - Étant donnée une invitation à une organisation dont l'id de l'organisation est 1 et celui du participant est Sam n'ayant pas les droits d'accès lorsqu'on effectue une requête POST pour l'ajouter alors on obtient un code de retour 403 et le message d'erreur «L'utilisateur n'as pas le droit de consulter cette invitation»`(){
        val invitation = InvitationOrganisation(
            1,
            Utilisateur(
                "auth0|656d3ecc4178aefc03429538",
                "email",
                "nomUtil",
                "prénom"),
            Organisation(
                1,
                "auth0|656d2dbea19599c9209a4f01",
                "nomOrg",
                1,
                true),
            null,
            "envoyé")

        Mockito.`when`(service.demandeJoindreOrganisation(invitation,"Sam")).thenThrow(DroitAccèsInsuffisantException("L'utilisateur n'as pas le droit de consulter cette invitation"))

        mockMvc.perform(post("/organisations/invitations").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(invitation)))
            .andExpect(status().isForbidden)
            .andExpect { résultat ->
                assertTrue(résultat.resolvedException is DroitAccèsInsuffisantException)
                assertEquals("L'utilisateur n'as pas le droit de consulter cette invitation", résultat.resolvedException?.message)
            }
    }
    @WithMockUser("Joe")
    @Test  //demandeJoindreOrganisation() 404 notFound (organisation inxesitante)
    fun `4- Étant donnée une invitation à une organisation dont l'id de l'organisation est 18 mais celle-ci n'est pas inscrite au service lorsqu'on effectue une requête POST pour l'ajouter alors on obtient un code de retour 404 et le message d'erreur «L'organisation 18 n'existe pas»`(){
        val invitation = InvitationOrganisation(
            1,
            Utilisateur(
                "auth0|656d3ecc4178aefc03429538",
                "email",
                "nomUtil",
                "prénom"),
            Organisation(
                18,
                "auth0|656d2dbea19599c9209a4f01",
                "nomOrg",
                1,
                true),
            null,
            "envoyé")
        Mockito.`when`(service.demandeJoindreOrganisation(invitation,"Joe")).thenThrow(RessourceInexistanteException("L'organisation 18 n'existe pas"))

        mockMvc.perform(post("/organisations/invitations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(invitation)))
            .andExpect(status().isNotFound)
            .andExpect { résultat ->
                assertTrue(résultat.resolvedException is RessourceInexistanteException)
                assertEquals("L'organisation 18 n'existe pas", résultat.resolvedException?.message)
            }
    }

    @WithMockUser("Joe")
    @Test  //demandeJoindreOrganisation() 404 notFound (participant inxesitant)
    fun `5- Étant donnée une invitation à une organisation dont l'id du participant est 23 mais celui-ci n'est pas inscrit au service lorsqu'on effectue une requête POST pour l'ajouter alors on obtient un code de retour 404 et le message d'erreur «Le participant 23 n'existe pas»`(){
        val invitation = InvitationOrganisation(
            1,
            Utilisateur(
                "auth0|656d3ecc4178aefc03429538",
                "email",
                "nomUtil",
                "prénom"),
            Organisation(
                3,
                "auth0|656d2dbea19599c9209a4f01",
                "nomOrg",
                1,
                true),
            null,
            "envoyé")
        Mockito.`when`(service.demandeJoindreOrganisation(invitation,"Joe")).thenThrow(RessourceInexistanteException("Le participant 23 n'existe pas"))

        mockMvc.perform(post("/organisations/invitations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(invitation)))
            .andExpect(status().isNotFound)
            .andExpect { résultat ->
                assertTrue(résultat.resolvedException is RessourceInexistanteException)
                assertEquals("Le participant 23 n'existe pas", résultat.resolvedException?.message)
            }
    }


    @WithMockUser("Joe")
    @Test  //demandeJoindreOrganisation() 409 isConflict
    fun `6- Étant donnée une invitation à une organisation dont l'id de l'organisation est 3 et celui du participant est 1 et qui est déjà inscrite au service lorsqu'on effectue une requête POST pour l'ajouter alors on obtient un code de retour 409 et et le message «Il y existe déjà une invitation à l'organisation nomOrg assigné au participant prénom nomUtil inscrit au service»` () {
        val invitation = InvitationOrganisation(
            1,
            Utilisateur(
                "auth0|656d3ecc4178aefc03429538",
                "email",
                "nomUtil",
                "prénom"),
            Organisation(
                3,
                "auth0|656d2dbea19599c9209a4f01",
                "nomOrg",
                1,
                true),
            null,
            "envoyé")
        Mockito.`when`(service.demandeJoindreOrganisation(invitation,"Joe"))
            .thenThrow(ConflitAvecUneRessourceExistanteException("Il y existe déjà une invitation à l'organisation nomOrg assigné au participant prénom nomUtil inscrit au service"))

        mockMvc.perform(post("/organisations/invitations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(invitation)))
            .andExpect(status().isConflict)
            .andExpect { résultat ->
                assertTrue(résultat.resolvedException is ConflitAvecUneRessourceExistanteException)
                assertEquals(
                    "Il y existe déjà une invitation à l'organisation nomOrg assigné au participant prénom nomUtil inscrit au service",
                    résultat.resolvedException?.message
                )
            }
    }


    // -----------------------------------------------------------------------------------------------------------------
    //
    // @DeleteMapping("/organisations/invitations/{idInvitationOrganisation}")
    // Cas d'utilisation: 7.Éffacer une invitation (Participant + Organisation)
    //
    // -----------------------------------------------------------------------------------------------------------------
    @WithMockUser("Joe")
    @Test //effacerInvitation() 200 ok
    fun `7- Étant donné une invitation avec l'id 3 à une organisation inscrite au service lorsqu'on effectue une requête DELETE selon l'id 3 alors on obtient un JSON qui contient l'invitation effacé et un code de retour 200` (){
        val invitation = InvitationOrganisation(
            3,
            Utilisateur(
                "auth0|656d3ecc4178aefc03429538",
                "email",
                "nomUtil",
                "prénom"),
            Organisation(
                3,
                "auth0|656d2dbea19599c9209a4f01",
                "nomOrg",
                1,
                true),
            null,
            "envoyé")
        Mockito.`when`(service.effacerInvitation(3,"Joe")).thenReturn(invitation)

        mockMvc.perform(delete("/organisations/invitations/3"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(3))
            .andExpect(jsonPath("$.utilisateur.code").value("auth0|656d3ecc4178aefc03429538"))
            .andExpect(jsonPath("$.utilisateur.nom").value("nomUtil"))
            .andExpect(jsonPath("$.utilisateur.prénom").value("prénom"))
            .andExpect(jsonPath("$.utilisateur.email").value("email"))
            .andExpect(jsonPath("$.organisation.id").value("3"))
            .andExpect(jsonPath("$.organisation.codeUtilisateur").value("auth0|656d2dbea19599c9209a4f01"))
            .andExpect(jsonPath("$.organisation.nomOrganisation").value("nomOrg"))
            .andExpect(jsonPath("$.organisation.catégorie_id").value("1"))
            .andExpect(jsonPath("$.organisation.estPublic").value(true))
            .andExpect(jsonPath("$.jeton").value(null))
            .andExpect(jsonPath("$.status").value("envoyé"))
    }

    @WithMockUser("Anonym")
    @Test //effacerInvitation() 401 UNAUTHORIZED
    fun `8,1- Étant donné une invitation avec l'id 3 à une organisation pas inscrite au service et un utilisateur Anonym pas connecté lorsqu'on effectue une requête DELETE selon l'id 3 alors on obtient un code de retour 401 et le message d'erreur «L'utilisateur n'est pas connecté,»` (){
        Mockito.`when`(service.effacerInvitation(3,"Anonym")).thenThrow(PasConnectéException("L'utilisateur n'est pas connecté."))

        mockMvc.perform(delete("/organisations/invitations/3"))
            .andExpect(status().isUnauthorized)
            .andExpect { résultat ->
                assertTrue(résultat.resolvedException is PasConnectéException)
                assertEquals("L'utilisateur n'est pas connecté.", résultat.resolvedException?.message)
            }
    }

    @WithMockUser("Sam")
    @Test //effacerInvitation() 403 FORBIDDEN
    fun `8,3- Étant donné une invitation avec l'id 3 à une organisation pas inscrite au service et un utilisateur Sam qui n'a pas les droits d'accès lorsqu'on effectue une requête DELETE selon l'id 3 alors on obtient un code de retour 403 et le message d'erreur «L'utilisateur n'as pas le droit de consulter cette invitation»` (){
        Mockito.`when`(service.effacerInvitation(3,"Sam")).thenThrow(DroitAccèsInsuffisantException("L'utilisateur n'as pas le droit de consulter cette invitation"))

        mockMvc.perform(delete("/organisations/invitations/3"))
            .andExpect(status().isForbidden)
            .andExpect { résultat ->
                assertTrue(résultat.resolvedException is DroitAccèsInsuffisantException)
                assertEquals("L'utilisateur n'as pas le droit de consulter cette invitation", résultat.resolvedException?.message)
            }
    }

    @WithMockUser("Joe")
    @Test //effacerInvitation() 404 notFound
    fun `8- Étant donné une invitation avec l'id 3 à une organisation pas inscrite au service lorsqu'on effectue une requête DELETE selon l'id 3 alors on obtient un code de retour 404 et le message d'erreur «L'invitation 3 à une organisation n'est pas inscrit au service»` (){
        Mockito.`when`(service.effacerInvitation(3,"Joe")).thenThrow(RessourceInexistanteException("L'invitation 3 à une organisation n'est pas inscrit au service"))

        mockMvc.perform(delete("/organisations/invitations/3"))
            .andExpect(status().isNotFound)
            .andExpect { résultat ->
                assertTrue(résultat.resolvedException is RessourceInexistanteException)
                assertEquals("L'invitation 3 à une organisation n'est pas inscrit au service", résultat.resolvedException?.message)
            }
    }

    // -----------------------------------------------------------------------------------------------------------------
    //
    // @GetMapping("/utilisateurs/invitations/organisations")
    // Cas d'utilisation: 3.Consulter ses invitations(Participant)
    //
    // -----------------------------------------------------------------------------------------------------------------
    @WithMockUser("Joe")
    @Test // obtenirInvitationParticipant() 200 ok
    fun `9- Étant donné un participant ayant le code auth0|656d3ecc4178aefc03429538 inscrit au service qui a une invitation lorsqu'on effectue une requête GET de recherche par participant selon l'id 1 alors on obtient un JSON d'une liste qui contient une InvitationOrganisation ayant l'id 5 et un code de retour 200` (){
        val listeInvitations : List<InvitationOrganisation> = listOf(InvitationOrganisation(
            5,
            Utilisateur(
                "auth0|656d3ecc4178aefc03429538",
                "email",
                "nomUtil",
                "prénom"),
            Organisation(
                3,
                "auth0|656d2dbea19599c9209a4f01",
                "nomOrg",
                1,
                true),
            null,
            "envoyé"))

        Mockito.`when`(service.chercherParParticipant("Joe")).thenReturn(listeInvitations)

        mockMvc.perform(get("/utilisateurs/invitations/organisations"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$[0].id").value(5))
            .andExpect(jsonPath("$[0].utilisateur.code").value("auth0|656d3ecc4178aefc03429538"))
            .andExpect(jsonPath("$[0].utilisateur.nom").value("nomUtil"))
            .andExpect(jsonPath("$[0].utilisateur.prénom").value("prénom"))
            .andExpect(jsonPath("$[0].utilisateur.email").value("email"))
            .andExpect(jsonPath("$[0].organisation.id").value("3"))
            .andExpect(jsonPath("$[0].organisation.codeUtilisateur").value("auth0|656d2dbea19599c9209a4f01"))
            .andExpect(jsonPath("$[0].organisation.nomOrganisation").value("nomOrg"))
            .andExpect(jsonPath("$[0].organisation.catégorie_id").value("1"))
            .andExpect(jsonPath("$[0].organisation.estPublic").value(true))
            .andExpect(jsonPath("$[0].jeton").value(null))
            .andExpect(jsonPath("$[0].status").value("envoyé"))
    }

    @WithMockUser("Anonym")
    @Test // obtenirInvitationParticipant() 401 UNAUTHORIZED
    fun `10,1- Étant donné un participant ayant le code Joe qui n'est pas inscrit au service qui a une invitation et qui n'est pas connecté lorsqu'on effectue une requête GET de recherche par participant selon l'id 1 alors on obtient un code de retour 401 et le message d'erreur «L'utilisateur n'est pas connecté,»` (){
        Mockito.`when`(service.chercherParParticipant("Anonym")).thenThrow(PasConnectéException("L'utilisateur n'est pas connecté."))

        mockMvc.perform(get("/utilisateurs/invitations/organisations"))
            .andExpect(status().isUnauthorized)
            .andExpect { résultat ->
                assertTrue(résultat.resolvedException is PasConnectéException)
                assertEquals("L'utilisateur n'est pas connecté.", résultat.resolvedException?.message)
            }
    }

    @WithMockUser("Sam")
    @Test // obtenirInvitationParticipant() 403 FORBIDDEN
    fun `10,3- Étant donné un participant ayant le code Sam qui n'est pas inscrite au service qui a une invitation et qui n'a pas les droit d'accès lorsqu'on effectue une requête GET de recherche par participant selon l'id 1 alors on obtient un code de retour 403 et le message d'erreur «L'utilisateur n'as pas le droit de consulter cette invitation»` (){
        Mockito.`when`(service.chercherParParticipant("Sam")).thenThrow(DroitAccèsInsuffisantException("L'utilisateur n'as pas le droit de consulter cette invitation"))

        mockMvc.perform(get("/utilisateurs/invitations/organisations"))
            .andExpect(status().isForbidden)
            .andExpect { résultat ->
                assertTrue(résultat.resolvedException is DroitAccèsInsuffisantException)
                assertEquals("L'utilisateur n'as pas le droit de consulter cette invitation", résultat.resolvedException?.message)
            }
    }
    @WithMockUser("Joe")
    @Test // obtenirInvitationParticipant() 404 notFound
    fun `10- Étant donné un participant ayant l'id 1 qui n'est pas inscrite au service qui a une invitation lorsqu'on effectue une requête GET de recherche par participant selon l'id 1 alors on obtient un code de retour 404 et le message d'erreur «Le participant 1 n'existe pas»` (){
        Mockito.`when`(service.chercherParParticipant("Joe")).thenThrow(RessourceInexistanteException("Le participant 1 n'existe pas"))

        mockMvc.perform(get("/utilisateurs/invitations/organisations"))
            .andExpect(status().isNotFound)
            .andExpect { résultat ->
                assertTrue(résultat.resolvedException is RessourceInexistanteException)
                assertEquals("Le participant 1 n'existe pas", résultat.resolvedException?.message)
            }
    }

    // -----------------------------------------------------------------------------------------------------------------
    //
    // @GetMapping("/organisations/{idOrganisation}/invitations")
    // Cas d'utilisation: 3.Consulter ses invitations(Organisation)
    //
    // -----------------------------------------------------------------------------------------------------------------
    @WithMockUser("Joe")
    @Test // obtenirInvitationOrganisation() 200 ok
    fun `11- Étant donné une organisation ayant l'id 1 inscrit au service qui a une invitation lorsqu'on effectue une requête GET de recherche par organisation selon l'id 1 alors on obtient un JSON d'une liste qui contient une InvitationOrganisation ayant l'id 5 et un code de retour 200` (){
        val listeInvitations : List<InvitationOrganisation> = listOf(InvitationOrganisation(
            5,
            Utilisateur(
                "auth0|656d3ecc4178aefc03429538",
                "email",
                "nomUtil",
                "prénom"),
            Organisation(
                3,
                "auth0|656d2dbea19599c9209a4f01",
                "nomOrg",
                1,
                true),
            null,
            "envoyé"))

        Mockito.`when`(service.chercherParOrganisation(1,"Joe")).thenReturn(listeInvitations)

        mockMvc.perform(get("/organisations/1/invitations"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$[0].id").value(5))
            .andExpect(jsonPath("$[0].utilisateur.code").value("auth0|656d3ecc4178aefc03429538"))
            .andExpect(jsonPath("$[0].utilisateur.nom").value("nomUtil"))
            .andExpect(jsonPath("$[0].utilisateur.prénom").value("prénom"))
            .andExpect(jsonPath("$[0].utilisateur.email").value("email"))
            .andExpect(jsonPath("$[0].organisation.id").value("3"))
            .andExpect(jsonPath("$[0].organisation.codeUtilisateur").value("auth0|656d2dbea19599c9209a4f01"))
            .andExpect(jsonPath("$[0].organisation.nomOrganisation").value("nomOrg"))
            .andExpect(jsonPath("$[0].organisation.catégorie_id").value("1"))
            .andExpect(jsonPath("$[0].organisation.estPublic").value(true))
            .andExpect(jsonPath("$[0].jeton").value(null))
            .andExpect(jsonPath("$[0].status").value("envoyé"))
    }

    @WithMockUser("Anonym")
    @Test // obtenirInvitationOrganisation() 401 UNAUTHORIZED
    fun `12,1- Étant donné une organisation ayant l'id 1 et que l'utilisateur n'est pas connecté lorsqu'on effectue une requête GET de recherche par organisation selon l'id 1 alors on obtient un code de retour 401 et le message d'erreur «L'utilisateur n'est pas connecté,»` (){
        Mockito.`when`(service.chercherParOrganisation(1,"Anonym")).thenThrow(PasConnectéException("L'utilisateur n'est pas connecté."))

        mockMvc.perform(get("/organisations/1/invitations"))
            .andExpect(status().isUnauthorized)
            .andExpect { résultat ->
                assertTrue(résultat.resolvedException is PasConnectéException)
                assertEquals("L'utilisateur n'est pas connecté.", résultat.resolvedException?.message)
            }
    }

    @WithMockUser("Sam")
    @Test // obtenirInvitationOrganisation() 403 FORBIDDEN
    fun `12,3- Étant donné une organisation ayant l'id 1 et que l'utilisateur n'a pas les droits d'accès lorsqu'on effectue une requête GET de recherche par organisation selon l'id 1 alors on obtient un code de retour 403 et le message d'erreur «L'utilisateur n'as pas le droit de consulter cette invitation»` (){
        Mockito.`when`(service.chercherParOrganisation(1,"Sam")).thenThrow(DroitAccèsInsuffisantException("L'utilisateur n'as pas le droit de consulter cette invitation"))

        mockMvc.perform(get("/organisations/1/invitations"))
            .andExpect(status().isForbidden)
            .andExpect { résultat ->
                assertTrue(résultat.resolvedException is DroitAccèsInsuffisantException)
                assertEquals("L'utilisateur n'as pas le droit de consulter cette invitation", résultat.resolvedException?.message)
            }
    }

    @WithMockUser("Joe")
    @Test // obtenirInvitationOrganisation() 404 notFound
    fun `12- Étant donné une organisation ayant l'id 1 qui n'est pas inscrite au service qui a une invitation lorsqu'on effectue une requête GET de recherche par organisation selon l'id 1 alors on obtient un code de retour 404 et le message d'erreur «L'organisation 1 n'existe pas»` (){
        Mockito.`when`(service.chercherParOrganisation(1,"Joe")).thenThrow(RessourceInexistanteException("L'organisation 1 n'existe pas"))

        mockMvc.perform(get("/organisations/1/invitations"))
            .andExpect(status().isNotFound)
            .andExpect { résultat ->
                assertTrue(résultat.resolvedException is RessourceInexistanteException)
                assertEquals("L'organisation 1 n'existe pas", résultat.resolvedException?.message)
            }
    }

    // -----------------------------------------------------------------------------------------------------------------
    //
    // @PutMapping("/organisations/invitations/status/{status}")
    // Cas d'utilisation: 4.Accepter la demande de joindre l'organisation par le participant (Organisation)
    //
    // -----------------------------------------------------------------------------------------------------------------
    @WithMockUser("Joe")
    @Test // changerStatus() 200 ok -> vert
    fun `13- Étant donnée une invitation à une organisation ayant l'id 8 inscrite au service lorsqu'on effectue une requête PUT pour changer son status à "accepté" alors on obtient un code de retour 200 et un JSON qui contient l'invitation modifié`(){
        val invitation = InvitationOrganisation(
            8,
            Utilisateur(
                "auth0|656d3ecc4178aefc03429538",
                "email",
                "nomUtil",
                "prénom"),
            Organisation(
                1,
                "auth0|656d2dbea19599c9209a4f01",
                "nomOrg",
                1,
                true),
            null,
            "accepté")

        Mockito.`when`(service.changerStatus(invitation, "accepté","Joe")).thenReturn(invitation)

        mockMvc.perform(put("/organisations/invitations/status/accepté")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(invitation)))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.id").value(8))
            .andExpect(jsonPath("$.utilisateur.code").value("auth0|656d3ecc4178aefc03429538"))
            .andExpect(jsonPath("$.utilisateur.nom").value("nomUtil"))
            .andExpect(jsonPath("$.utilisateur.prénom").value("prénom"))
            .andExpect(jsonPath("$.utilisateur.email").value("email"))
            .andExpect(jsonPath("$.organisation.id").value("1"))
            .andExpect(jsonPath("$.organisation.codeUtilisateur").value("auth0|656d2dbea19599c9209a4f01"))
            .andExpect(jsonPath("$.organisation.nomOrganisation").value("nomOrg"))
            .andExpect(jsonPath("$.organisation.catégorie_id").value("1"))
            .andExpect(jsonPath("$.organisation.estPublic").value(true))
            .andExpect(jsonPath("$.jeton").value(null))
            .andExpect(jsonPath("$.status").value("accepté"))
    }

    @WithMockUser("Anonym")
    @Test // changerStatus() 401 UNAUTHORIZED
    fun `14,1- Étant donnée une invitation à une organisation ayant l'id 8 et un utilisateur pas connecté lorsqu'on effectue une requête PUT pour changer son status à "accepté" alors on obtient un code de retour 401 et le message d'erreur «L'utilisateur n'est pas connecté,»`(){
        val invitation = InvitationOrganisation(
            3,
            Utilisateur(
                "auth0|656d3ecc4178aefc03429538",
                "email",
                "nomUtil",
                "prénom"),
            Organisation(
                3,
                "auth0|656d2dbea19599c9209a4f01",
                "nomOrg",
                1,
                true),
            null,
            "envoyé")

        Mockito.`when`(service.changerStatus(invitation, "accepté","Anonym")).thenThrow(PasConnectéException("L'utilisateur n'est pas connecté."))

        mockMvc.perform(put("/organisations/invitations/status/accepté")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(invitation)))
            .andExpect(status().isUnauthorized)
            .andExpect { résultat ->
                assertTrue(résultat.resolvedException is PasConnectéException)
                assertEquals("L'utilisateur n'est pas connecté.", résultat.resolvedException?.message)
            }
    }

    @WithMockUser("Sam")
    @Test // changerStatus() 403 FORBIDDEN
    fun `14,3- Étant donnée une invitation à une organisation ayant l'id 8 et un utilisateur Sam n'ayant pas les droits d'accès lorsqu'on effectue une requête PUT pour changer son status à "accepté" alors on obtient un code de retour 403 et le message d'erreur «L'utilisateur n'as pas le droit de consulter cette invitation»`(){
        val invitation = InvitationOrganisation(
            3,
            Utilisateur(
                "auth0|656d3ecc4178aefc03429538",
                "email",
                "nomUtil",
                "prénom"),
            Organisation(
                3,
                "auth0|656d2dbea19599c9209a4f01",
                "nomOrg",
                1,
                true),
            null,
            "envoyé")

        Mockito.`when`(service.changerStatus(invitation, "accepté","Sam")).thenThrow(DroitAccèsInsuffisantException("L'utilisateur n'as pas le droit de consulter cette invitation"))

        mockMvc.perform(put("/organisations/invitations/status/accepté")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(invitation)))
            .andExpect(status().isForbidden)
            .andExpect { résultat ->
                assertTrue(résultat.resolvedException is DroitAccèsInsuffisantException)
                assertEquals("L'utilisateur n'as pas le droit de consulter cette invitation", résultat.resolvedException?.message)
            }
    }

    @WithMockUser("Joe")
    @Test // changerStatus() 404 notFound
    fun `14- Étant donnée une invitation à une organisation ayant l'id 8 qui n'est pas inscrite au service lorsqu'on effectue une requête PUT pour changer son status à "accepté" alors on obtient un code de retour 404 et le message d'erreur «L'invitation 8 à une organisation n'est pas inscrit au service»`(){
        val invitation = InvitationOrganisation(
            3,
            Utilisateur(
                "auth0|656d3ecc4178aefc03429538",
                "email",
                "nomUtil",
                "prénom"),
            Organisation(
                3,
                "auth0|656d2dbea19599c9209a4f01",
                "nomOrg",
                1,
                true),
            null,
            "envoyé")

        Mockito.`when`(service.changerStatus(invitation, "accepté","Joe")).thenThrow(RessourceInexistanteException("L'invitation 8 à une organisation n'est pas inscrit au service"))

        mockMvc.perform(put("/organisations/invitations/status/accepté")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(invitation)))
            .andExpect(status().isNotFound)
            .andExpect { résultat ->
                assertTrue(résultat.resolvedException is RessourceInexistanteException)
                assertEquals("L'invitation 8 à une organisation n'est pas inscrit au service", résultat.resolvedException?.message)
            }
    }

    // -----------------------------------------------------------------------------------------------------------------
    //
    // @PutMapping("/organisations/jetons/{jeton}")
    // Cas d'utilisation: 5.Entrer un jeton d'invitation (Participant)
    //
    // -----------------------------------------------------------------------------------------------------------------
    @WithMockUser("Joe")
    @Test // saisirJeton() 200 ok
    fun `15- Étant donnée une invitation à une organisation ayant le jeton 'VF5S6H55' inscrite au service lorsqu'on effectue une requête PUT pour changer le status à "accepté" de l'invitation ayant le jeton 'VF5S6H55' alors on obtient un code de retour 200 et un JSON qui contient l'invitation modifié`(){
        val invitation = InvitationOrganisation(
            8,
            Utilisateur(
                "auth0|656d3ecc4178aefc03429538",
                "email",
                "nomUtil",
                "prénom"),
            Organisation(
                1,
                "auth0|656d2dbea19599c9209a4f01",
                "nomOrg",
                1,
                true),
            "VF5S6H55",
            "accepté")

        Mockito.`when`(service.saisirJeton("VF5S6H55","Joe")).thenReturn(invitation)

        mockMvc.perform(put("/organisations/jetons/VF5S6H55")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(invitation.Utilisateur!!)))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.id").value(8))
            .andExpect(jsonPath("$.utilisateur.code").value("auth0|656d3ecc4178aefc03429538"))
            .andExpect(jsonPath("$.utilisateur.nom").value("nomUtil"))
            .andExpect(jsonPath("$.utilisateur.prénom").value("prénom"))
            .andExpect(jsonPath("$.utilisateur.email").value("email"))
            .andExpect(jsonPath("$.organisation.id").value("1"))
            .andExpect(jsonPath("$.organisation.codeUtilisateur").value("auth0|656d2dbea19599c9209a4f01"))
            .andExpect(jsonPath("$.organisation.nomOrganisation").value("nomOrg"))
            .andExpect(jsonPath("$.organisation.catégorie_id").value("1"))
            .andExpect(jsonPath("$.organisation.estPublic").value(true))
            .andExpect(jsonPath("$.jeton").value("VF5S6H55"))
            .andExpect(jsonPath("$.status").value("accepté"))
    }

    @WithMockUser("Anonym")
    @Test // saisirJeton() 401 UNAUTHORIZED
    fun `16,1- Étant donnée une invitation à une organisation ayant un jeton 'VF5S6H55' qui n'est pas inscrite au service et un utilisateur non connecté lorsqu'on effectue une requête PUT pour changer son status à "accepté" de l'invitation ayant le jeton 'VF5S6H55' alors on obtient un code de retour 401 et le message d'erreur «L'utilisateur n'est pas connecté,»`(){
        val invitation = InvitationOrganisation(
            8,
            Utilisateur(
                "auth0|656d3ecc4178aefc03429538",
                "email",
                "nomUtil",
                "prénom"),
            Organisation(
                1,
                "auth0|656d2dbea19599c9209a4f01",
                "nomOrg",
                1,
                true),
            "VF5S6H55",
            "accepté")

        Mockito.`when`(service.saisirJeton("VF5S6H55","Anonym")).thenThrow(PasConnectéException("L'utilisateur n'est pas connecté."))

        mockMvc.perform(put("/organisations/jetons/VF5S6H55")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(invitation.Utilisateur!!)))
            .andExpect(status().isUnauthorized)
            .andExpect { résultat ->
                assertTrue(résultat.resolvedException is PasConnectéException)
                assertEquals("L'utilisateur n'est pas connecté.", résultat.resolvedException?.message)
            }
    }

    @WithMockUser("Joe")
    @Test // saisirJeton() 404 notFound
    fun `16- Étant donnée une invitation à une organisation ayant un jeton 'VF5S6H55' qui n'est pas inscrite au service lorsqu'on effectue une requête PUT pour changer son status à "accepté" de l'invitation ayant le jeton 'VF5S6H55' alors on obtient un code de retour 404 et le message d'erreur «Aucune invitation inscrit dans le service contient le jeton VF5S6H55»`(){
        val invitation = InvitationOrganisation(
            8,
            Utilisateur(
                "auth0|656d3ecc4178aefc03429538",
                "email",
                "nomUtil",
                "prénom"),
            Organisation(
                1,
                "auth0|656d2dbea19599c9209a4f01",
                "nomOrg",
                1,
                true),
            "VF5S6H55",
            "accepté")

        Mockito.`when`(service.saisirJeton("VF5S6H55","Joe")).thenThrow(RessourceInexistanteException("Aucune invitation inscrit dans le service contient le jeton VF5S6H55"))

        mockMvc.perform(put("/organisations/jetons/VF5S6H55")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(invitation.Utilisateur!!)))
            .andExpect(status().isNotFound)
            .andExpect { résultat ->
                assertTrue(résultat.resolvedException is RessourceInexistanteException)
                assertEquals("Aucune invitation inscrit dans le service contient le jeton VF5S6H55", résultat.resolvedException?.message)
            }
    }

    // -----------------------------------------------------------------------------------------------------------------
    //
    // @PostMapping("/organisations/jetons")
    // Cas d'utilisation: 6.Générer son jeton d'invitation
    //
    // -----------------------------------------------------------------------------------------------------------------
    @WithMockUser("Joe")
    @Test // créerJeton() 200 ok
    fun `17- Étant donné une organisation ayant l'id 3 inscrit au service qui a une invitation lorsqu'on effectue une requête POST pour créer une invitation assigné à aucun utilisateur et avec un jeton généré pour l'organisation avec l'id 3 alors on obtient un JSON d'une liste qui contient une InvitationOrganisation ayant un jeton généré et assigné à aucun utilisateur  et un code de retour 201` (){
        val invitation = InvitationOrganisation(
            54,
            null,
            Organisation(
                3,
                "auth0|656d2dbea19599c9209a4f01",
                "nomOrg",
                1,
                true),
            "OYX4J399",
            "généré")

        Mockito.`when`(service.créerJeton(invitation.Organisation,"Joe")).thenReturn(invitation)

        mockMvc.perform(post("/organisations/jetons")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(invitation.Organisation)))
            .andExpect(status().isCreated)
            .andExpect(header().string("Location",containsString("/organisations/invitations/54")))
            .andExpect(jsonPath("$.id").value(54))
            .andExpect(jsonPath("$.utilisateur").value(null))
            .andExpect(jsonPath("$.organisation.id").value("3"))
            .andExpect(jsonPath("$.organisation.codeUtilisateur").value("auth0|656d2dbea19599c9209a4f01"))
            .andExpect(jsonPath("$.organisation.nomOrganisation").value("nomOrg"))
            .andExpect(jsonPath("$.organisation.catégorie_id").value("1"))
            .andExpect(jsonPath("$.organisation.estPublic").value(true))
            .andExpect(jsonPath("$.jeton").value("OYX4J399"))
            .andExpect(jsonPath("$.status").value("généré"))
    }

    @WithMockUser("Anonym")
    @Test // créerJeton() 401 UNAUTHORIZED
    fun `18,1- Étant donné une organisation ayant l'id 36 qui n'est pas inscrite au service et un utilisateur qui n'est pas connecté lorsqu'on effectue une requête POST pour créer une invitation assigné à aucun utilisateur et avec un jeton généré pour l'organisation avec l'id 36 alors on obtient un code de retour 401 et le message d'erreur «L'utilisateur n'est pas connecté,»` (){
        val organisation = Organisation(
            1,
            "auth0|656d2dbea19599c9209a4f01",
            "nomOrg",
            1,
            true)

        Mockito.`when`(service.créerJeton(organisation,"Anonym")).thenThrow(PasConnectéException("L'utilisateur n'est pas connecté."))

        mockMvc.perform(post("/organisations/jetons")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(organisation)))
            .andExpect(status().isUnauthorized)
            .andExpect { résultat ->
                assertTrue(résultat.resolvedException is PasConnectéException)
                assertEquals("L'utilisateur n'est pas connecté.", résultat.resolvedException?.message)
            }
    }

    @WithMockUser("Sam")
    @Test // créerJeton() 403 FORBIDDEN
    fun `18,3- Étant donné une organisation ayant l'id 36 qui n'est pas inscrite au service et que l'utilisateur n'a pas le droit d'accès lorsqu'on effectue une requête POST pour créer une invitation assigné à aucun utilisateur et avec un jeton généré pour l'organisation avec l'id 36 alors on obtient un code de retour 403 et le message d'erreur «L'utilisateur n'as pas le droit de consulter cette invitation»` (){
        val organisation = Organisation(
            1,
            "auth0|656d2dbea19599c9209a4f01",
            "nomOrg",
            1,
            true)

        Mockito.`when`(service.créerJeton(organisation,"Sam")).thenThrow(DroitAccèsInsuffisantException("L'utilisateur n'as pas le droit de consulter cette invitation"))

        mockMvc.perform(post("/organisations/jetons")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(organisation)))
            .andExpect(status().isForbidden)
            .andExpect { résultat ->
                assertTrue(résultat.resolvedException is DroitAccèsInsuffisantException)
                assertEquals("L'utilisateur n'as pas le droit de consulter cette invitation", résultat.resolvedException?.message)
            }
    }

    @WithMockUser("Joe")
    @Test // créerJeton() 404 notFound
    fun `18- Étant donné une organisation ayant l'id 36 qui n'est pas inscrite au service lorsqu'on effectue une requête POST pour créer une invitation assigné à aucun utilisateur et avec un jeton généré pour l'organisation avec l'id 36 alors on obtient un code de retour 404 et le message d'erreur «L'organisation 36 n'existe pas»` (){
        val organisation = Organisation(
            1,
            "auth0|656d2dbea19599c9209a4f01",
            "nomOrg",
            1,
            true)

        Mockito.`when`(service.créerJeton(organisation,"Joe")).thenThrow(RessourceInexistanteException("L'organisation 36 n'existe pas"))

        mockMvc.perform(post("/organisations/jetons")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(organisation)))
            .andExpect(status().isNotFound)
            .andExpect { résultat ->
                assertTrue(résultat.resolvedException is RessourceInexistanteException)
                assertEquals("L'organisation 36 n'existe pas", résultat.resolvedException?.message)
            }
    }

}