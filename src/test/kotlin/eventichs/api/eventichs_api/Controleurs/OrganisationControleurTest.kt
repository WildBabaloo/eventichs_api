package eventichs.api.eventichs_api.Controleurs

import eventichs.api.eventichs_api.Modèle.Organisation
import eventichs.api.eventichs_api.Services.OrganisationService
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*


@SpringBootTest
@AutoConfigureMockMvc
class OrganisationControleurTest {


@MockBean
lateinit var service: OrganisationService

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun `Étant donné un administrateur qui effectue une requete GET pour afficher toute les organisations lorsqu on exécute la requête on obtient un fichier JSON qui reqgroupe les organisations et un code de retour 200 `(){
        var listorganisation: MutableList<Organisation> = mutableListOf(Organisation(1,1,"Illuminati",1,false))
        //listorganisation?.add(Organisation(1,1,"Illuminati",1,false))

        Mockito.`when`(service.chercherTous()).thenReturn(listorganisation)

        mockMvc.perform(get("/organisations"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$[0].id").value(1))
            .andExpect(jsonPath("$[0].idUtilisateur").value(1))
            .andExpect(jsonPath("$[0].catégorie_id").value(1))
            .andExpect(jsonPath("$[0].nomOrganisation").value("Illuminati"))
            .andExpect(jsonPath("$[0].estPublic").value(false))

    }


    @Test
    fun `Étant donné une organisation qui effectue une recherche pour un participant non-exisatant et de l ajouter on obtient un code de retour 404`(){
        TODO("Méthode non-implémentée")
    }

    @Test
    fun `Étant donné une organisation qui effectue une recherche pour un participant exisatant et de l ajouter on obtient un JSON qui contient le code du participant et un code de retour 200`(){
        TODO("Méthode non-implémentée")
    }

    @Test
    fun `Étant donné une organisation qui effectue unee requête PUT pour enlever un participant non-exisatant on obtient un code de retour 404`(){
        TODO("Méthode non-implémentée")
    }

    @Test
    fun `Étant donné une organisation qui effectue une reqûete PUT pour enlever un participant exisatant on obtient un code de retour 200`(){
        TODO("Méthode non-implémentée")
    }

    @Test
    fun `Étant donné une organisation qui effectue une requête PUT pour modifier les informations de son profil non-existant on obtient un code de retour 404`(){
        TODO("Méthode non-implémentée")
    }

    @Test
    fun `Étant donné une organisation qui effectue une reqûete PUT pour modifier les informations de son profil un JSON qui contient son organisation modifié et un code de retour 200`(){
        TODO("Méthode non-implémentée")
    }

    @Test
    fun `Étant donné une organisation qui effectue une requête PUT pour modifier la visibilté de son profil on obtient un code de retour 404`(){
        TODO("Méthode non-implémentée")
    }

    @Test
    fun `Étant donné une organisation qui effectue une reqûete PUT pour modifier la visibilité de son profil on obteint un JSON qui contient son organisation modifié et un code de retour 200`(){
        TODO("Méthode non-implémentée")
    }

        @Test
    fun `Étant donné un participant qui effectue une requete GET pour consulter la liste des organisations publiques on obteint un JSON qui contient la liste des organisation publiques et un code de retour 200`(){
        TODO("Méthode non-implémentée")
    }

    @Test
    fun `Étant donné un participant qui effectue une requete GET pour consulter la liste des organisations publiques on obteint un code de retour 404`(){
        TODO("Méthode non-implémentée")
    }

    @Test
    fun `Étant donné un participant qui effectue une requete GET pour filtrer la liste des organisations publiques selon leurs goûts on obtient un code de retour 404`(){
        TODO("Méthode non-implémentée")
    }

    @Test
    fun `Étant donné un participant qui effectue une requete GET pour filtrer la liste des organisations publiques selon leur goûts on obteint un JSON qui contient la liste filtrée des organisation publiques et un code de retour 200`(){
        TODO("Méthode non-implémentée")
    }

    @Test
    fun `Étant donné un participant qui effectue une requete GET pour consulter la liste organisations rejointes on obtient un code de retour 404`(){
        TODO("Méthode non-implémentée")
    }

    @Test
    fun `Étant donné un participant qui effectue une requete GET pour consulter la liste des organisations rejointes on obteint un JSON qui contient la liste des organisation rejointes et un code de retour 200`(){
        TODO("Méthode non-implémentée")
    }

    @Test
    fun `Étant donné un participant qui effectue une requete POST pour quitter une organisation rejointe qui n'existe pas, on obtient un code de retour 404`(){
        TODO("Méthode non-implémentée")
    }

    @Test
    fun `Étant donné un participant qui effectue une requete POST pour quitter une organisation on obteint un code de retour 200`(){
        TODO("Méthode non-implémentée")
    }

}