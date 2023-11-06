package test.kotlin.eventichs.api.eventichs_api.Controleurs

class OrganisationControleurTest {


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

}