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
import org.springframework.web.bind.annotation.*


import org.springframework.http.MediaType
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import com.fasterxml.jackson.databind.ObjectMapper
import eventichs.api.eventichs_api.Exceptions.ConflitAvecUneRessourceExistanteException
import org.hamcrest.CoreMatchers.containsString


@SpringBootTest
@AutoConfigureMockMvc
//@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class InvitationOrganisationControleurTest {

    @Autowired
    private lateinit var mapper: ObjectMapper

    @MockBean
    lateinit var service: InvitationOrganisationService

    @Autowired
    private lateinit var mockMvc: MockMvc

    // -----------------------------
    // CHANGEMENT DE FONCTIONALITÉ 2 Décembre
    // -----------------------------
  /*

    // -----------------------------------------------------------------------------------------------------------------
    //@GetMapping("/organisations/invitations/{id}")
    @Test  //Test réussi
    fun `1 Étant donné une invitation avec l'id 1 lorsqu'on effectue une requête GET de recherche par id alors on obtient un JSON qui contient une invitation dont l'id est 1 et un code de retour 200` (){
        val invitation = InvitationOrganisation(1, 1, 1, null, "envoyé")

        Mockito.`when`(service.chercherParID(1)).thenReturn(invitation)

        mockMvc.perform(get("/organisations/invitations/1"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(1))
    }


    @Test  //Test réussi
    fun `2 Étant donné l'invitation dont l'id est 8 et qui n'est pas inscrit au service lorsqu'on effectue une requête GET de recherche par id  alors on obtient un code de retour 404 et le message d'erreur « L'invitation à une organisation 8 n'est pas inscrit au service »`() {
        Mockito.`when`(service.chercherParID(8)).thenReturn(null)

        mockMvc.perform(get("/organisations/invitations/8")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound)
            .andExpect { résultat ->
                assertTrue(résultat.resolvedException is RessourceInexistanteException)
                assertEquals(" L'invitation à une organisation 8 n'est pas inscrit au service ", résultat.resolvedException?.message)
            }
    }


    // -----------------------------------------------------------------------------------------------------------------
    //@PostMapping("/organisations/{idOrganisation}/invitations/{idParticipant}")
    //Cas d'utilisation: 1.Demander à joindre une organisation (Participant)
    @Test  //Test réussi
    fun `3 Étant donnée une invitation à une organisation dont l'id de l'organisation est 1 et celui du participant est 2 et qui n'est pas inscrite au service lorsqu'on effectue une requête POST pour l'ajouter alors on obtient un code de retour 409 et et le message ' Il y existe déjà une invitation à l'organisation 1 assigné au participant 2 inscrit au service '` () {
        val invitation = InvitationOrganisation(0, 2, 1, null, "envoyé")
        Mockito.`when`(service.demandeJoindreOrganisation(invitation.idOrganisation, invitation.idDestinataire!!))
            .thenThrow(ConflitAvecUneRessourceExistanteException(" Il y existe déjà une invitation à l'organisation 1 assigné au participant 2 inscrit au service "))

        mockMvc.perform(
            post("/organisations/1/invitations/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(invitation))
        )
            .andExpect(status().isConflict)
            .andExpect { résultat ->
                assertTrue(résultat.resolvedException is ConflitAvecUneRessourceExistanteException)
               /* assertEquals(
                    " Il y existe déjà une invitation à l'organisation 1 assigné au participant 2 inscrit au service ",
                    résultat.resolvedException?.message
                )*/

            }
    }

    @Test  //Test réussi
    fun `4 Étant donnée une invitation à une organisation dont l'id de l'organisation est 1 et celui du participant est 3 lorsqu'on effectue une requête POST pour l'ajouter alors on obtient un un code de retour 200 et un JSON qui contient l'invitation créé`(){
        val invitation = InvitationOrganisation(3, 3, 1, null, "envoyé")
        Mockito.`when`(service.demandeJoindreOrganisation(invitation.idOrganisation,invitation.idDestinataire!!)).thenReturn(invitation)

        mockMvc.perform(post("/organisations/1/invitations/3")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(invitation)))
            .andExpect(status().isCreated)
            .andExpect(header().string("Location",containsString("/organisations/invitations/3" )))
            .andExpect(jsonPath("$.id").value("3"))

    }

    // -----------------------------------------------------------------------------------------------------------------
    //@DeleteMapping("/organisations/invitations/{idInvitationOrganisation}")
    //Cas d'utilisation: 7.Éffacer une invitation (Participant + Organisation)
    @Test //Test réussi
    fun `5 Étant donné une invitation à une organisation avec l'id 3 lorsque effectue une requête DELETE selon l'id 3 alors on obtient un JSON qui contient l'invitation effacé et un code de retour 200` (){
        val invitation = InvitationOrganisation(3, 3, 1, null, "envoyé")
        Mockito.`when`(service.effacerInvitation(3)).thenReturn(invitation)

        mockMvc.perform(delete("/organisations/invitations/3"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(3))
    }

    // -----------------------------------------------------------------------------------------------------------------
    //@GetMapping("/organisations/{idOrganisation}/invitations")
    //Cas d'utilisation: 3.Consulter ses invitations(Organisation)
    @Test
    fun `6 Étant donné une organisation qui a une invitation lorsqu'on effectue une requête GET de recherche par de ces invitations selon l'id 1 alors on obtient un JSON qui contient une liste d'une InvitationOrganisation ayant l'id 1 et un code de retour 200` (){
        val listeInvitations : List<InvitationOrganisation> = listOf( InvitationOrganisation(1, 1, 1, null, "envoyé"))

        Mockito.`when`(service.chercherParOrganisation(1)).thenReturn(listeInvitations)

        mockMvc.perform(get("/organisations/1/invitations"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(1))
    }
*/
    /*
    @Test
    fun `Étant donné l'invitation dont l'id est 8 et qui n'est pas inscrit au service lorsqu'on effectue une requête GET de recherche par id  alors on obtient un code de retour 404 et le message d'erreur « L'invitation à une organisation 8 n'est pas inscrit au service »`() {
        Mockito.`when`(service.chercherParID(8)).thenReturn(null)

        mockMvc.perform(get("/organisations/invitations/8")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound)
            .andExpect { résultat ->
                assertTrue(résultat.resolvedException is RessourceInexistanteException)
                assertEquals(" L'invitation à une organisation 8 n'est pas inscrit au service ", résultat.resolvedException?.message)
            }
    }*/



    // -----------------------------------------------------------------------------------------------------------------
    //@GetMapping("/utilisateurs/{idParticipant}/invitations")
    //Cas d'utilisation: 3.Consulter ses invitations(Participant)

    // -----------------------------------------------------------------------------------------------------------------
    //@PutMapping("/organisations/invitations/{id}/status/{status}")
    //Cas d'utilisation: 4.Accepter la demande de joindre l'organisation par le participant (Organisation)

    // -----------------------------------------------------------------------------------------------------------------
    //@PutMapping("/organisations/jetons/{jeton}/{idUtilisateur}")
    //Cas d'utilisation: 5.Entrer un jeton d'invitation (Participant)

    // -----------------------------------------------------------------------------------------------------------------
    //@PostMapping("/organisations/{idOrganisation}/jetons")
    //Cas d'utilisation: 6.Générer son jeton d'invitation (Organisation)




    /*@Test
fun `Étant donné lorsque alors` (){
    TODO("Méthode non-implémentée")
}*/
}