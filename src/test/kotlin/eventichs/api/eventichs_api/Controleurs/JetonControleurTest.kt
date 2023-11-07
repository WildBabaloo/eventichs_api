package eventichs.api.eventichs_api.Controleurs

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc

@SpringBootTest
@AutoConfigureMockMvc
class JetonControleurTest {

//    @MockBean
//    lateinit var service: InvitationService

    @Autowired
    private lateinit var mockMvc: MockMvc

    //Entrer un jeton d'invitation (Participant)
    @Test
    // @PostMapping("/jetons/{utilisateur_id}")
    fun `Étant donné un participant qui a un jeton associé à une organisation lorsqu'on effectue une requête POST pour rejoindre l'organisation alors on obtient un JSON qui contient l'organisation assigné au jeton et un code de retour 200` (){
        TODO("Méthode non-implémentée")
    }
    @Test
    // @PostMapping("/jetons/{utilisateur_id}")
    fun `Étant donné un participant qui a un jeton associé à aucune organisation lorsqu'on effectue une requête POST pour rejoindre une organisation alors on obtient un code de retour 409` (){
        TODO("Méthode non-implémentée")
    }

    //Générer son jeton d'invitation (Organisation)
    @Test
    // @GetMapping("/jetons/{id}/{quantité}")
    fun `Étant donné une organisation qui cherche à inviter des participants lorsqu'on effectue une requête GET pour obtenir 5 jetons alors on obtient un JSON qui contient 5 jetons ainsi qu'un code de retour 201` (){
        TODO("Méthode non-implémentée")
    }
    @Test
    // @GetMapping("/jetons/{id}/{quantité}")
    fun `Étant donné une organisation qui cherche à inviter des participant lorsqu'on effectue une requête GET avec une organisation qui n'existe pas pour obtenir 5 jetons alors on obtient un code de retour 409` (){
        TODO("Méthode non-implémentée")
    }
}