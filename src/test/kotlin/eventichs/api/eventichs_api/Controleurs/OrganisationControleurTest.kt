package test.kotlin.eventichs.api.eventichs_api.Controleurs

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
@SpringBootTest
@AutoConfigureMockMvc
class OrganisationControleurTest {

// TODO

//    @MockBean
//    lateinit var service: OrganisationService

    @Autowired
    private lateinit var mockMvc: MockMvc

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