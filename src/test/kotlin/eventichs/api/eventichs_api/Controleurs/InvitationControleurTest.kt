package eventichs.api.eventichs_api.Controleurs

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping

@SpringBootTest
@AutoConfigureMockMvc
class InvitationControleurTest {

//    @MockBean
//    lateinit var service: InvitationService

    @Autowired
    private lateinit var mockMvc: MockMvc

    //Éffacer une invitation (Participant + Organisation)
    @Test
    // @DeleteMapping("/invitations/{code}")
    fun `Étant donné un utilisateur qui a invité un participant lorsqu'on effectue une requête DELETE sur une invitation qui existe alors on obtient un JSON qui contient l'invitation supprimé et un code de retour 200` (){
        TODO("Méthode non-implémentée")
    }
    @Test
    // @DeleteMapping("/invitations/{code}")
    fun `Étant donné un utilisateur lorsqu'on effectue une requête DELETE sur une invitation qui n'existe pas alors on obtient un code de retour 409` (){
        TODO("Méthode non-implémentée")
    }

    //Alex



}