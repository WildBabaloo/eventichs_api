package eventichs.api.eventichs_api.Controleurs

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc

@SpringBootTest
@AutoConfigureMockMvc
class UtilisateurControleurTest {

//    @MockBean
//    lateinit var service: InvitationService

    @Autowired
    private lateinit var mockMvc: MockMvc

    //S'inscrire (Participant & Organisation)
    @Test
    // @PostMapping("/utilisateurs")
    fun `Étant donné un utilisateur sans compte lorsqu'on effectue une requête POST pour se créer un compte avec un id inexistant dans le service on obtient un JSON d'un utilisateur et un code de retour 201` (){
        TODO("Méthode non-implémentée")
    }
    @Test
    // @PostMapping("/utilisateurs")
    fun `Étant donné un utilisateur ayant un compte lorsqu'on effectue une requête POST pour se créer un compte avec un id existant dans le service on obtient un code de retour 409 et le message "Impossible de créer cet utilisateur"` (){
        TODO("Méthode non-implémentée")
    }

    //Se connecter (Participant & Organisation)
    @Test
    // @GetMapping("/utilisateurs/{id}")
    fun `Étant donné un utilisateur possedant un compte lorsqu'on effectue une requête GET pour se connecter à un compte avec un id existant dans le service on obtient un JSON d'un utilisateur et un code de retour 200` (){
        TODO("Méthode non-implémentée")
    }
    @Test
    // @GetMapping("/utilisateurs/{id}")
    fun `Étant donné un utilisateur ne possedant pas un compte lorsqu'on effectue une requête GET pour se connecter à un compte avec un id inexistant dans le service obtient un code de retour 409 et le message "Cet utilisateur n'existe pas"` (){
        TODO("Méthode non-implémentée")
    }

    //Modifier son compte (Participant & Organisation)
    @Test
    // @PutMapping("/utilisateurs/{id}")
    fun `Étant donné un utilisateur ayant un compte lorsqu'on effectue une requête POST pour modifier son compte avec un id existant dans le service on obtient un JSON de l'utilisateur modifié et un code de retour 200` (){
        TODO("Méthode non-implémentée")
    }
    @Test
    // @PutMapping("/utilisateurs/{id}")
    fun `Étant donné un utilisateur sans compte lorsqu'on effectue une requête POST pour modifier un compte avec un id inexistant dans le service on obtient un code de retour 409 et le message "Cet utilisateur n'existe pas"` (){
        TODO("Méthode non-implémentée")
    }

    //Supprimer son compte (Participant & Organisation)
    @Test
    // @DeleteMapping("/utilisateurs/{id}")
    fun `Étant donné un utilisateur ayant un compte lorsqu'on effectue une requête DELETE pour effacer son compte avec un id existant dans le service on obtient un JSON de l'utilisateur effacé et un code de retour 200` (){
        TODO("Méthode non-implémentée")
    }
    @Test
    // @DeleteMapping("/utilisateurs/{id}")
    fun `Étant donné un utilisateur sans compte lorsqu'on effectue une requête DELETE pour effacer un compte avec un id inexistant dans le service on obtient un code de retour 409` (){
        TODO("Méthode non-implémentée")
    }
}