package eventichs.api.eventichs_api.Controleurs

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc

@SpringBootTest
@AutoConfigureMockMvc
class InvitationControleurTest {

//    @MockBean
//    lateinit var service: InvitationService

    @Autowired
    private lateinit var mockMvc: MockMvc

    //Julien
    @Test
    fun `Étant donné lorsque alors` (){
        TODO("Méthode non-implémentée")
    }

    //Alex

    @Test
    fun `Étant donné l'utilisateur dont l'id est 1, qui ne fait pas partie d'aucune organisation, lorsqu'on effectue une requete POST pour ajouter une demande d'invitation à l'organisation éxistante dont l'id est 2, on obtient un JSON qui contient un évènement qui contient l'id utilisateur, l'id organisation et l'état de la demande, ainsi d'un code de retour 201` (){
        TODO("Méthode non-implémentée")
    }

    @Test
    fun `Étant donné l'utilisateur dont l'id est 1 qui fait déja partie de l'organisation dont l'id est 2, lorsqu'on effectue une requete POST pour ajouter une demande d'invitation à l'organisation éxistante dont l'id est 2, on obtient le code de retour 409` (){
        TODO("Méthode non-implémentée")
    }

    @Test
    fun `Étant donné l'utilisateur dont l'id est 1 lorsqu'on effectue une requete POST pour ajouter une demande d'invitation à une organisation non-éxistente dont l'id est -1, on obtient le code de retour 400` (){
        TODO("Méthode non-implémentée")
    }

    @Test
    fun `Étant donné l'utilisateur dont l'id est 1, qui est inscrit à l'évènement dont l'id est 100 et le type = publique, lorsqu'on effectue une requete POST pour envoyer une invitation à l'utilisateur 2 qui n'est pas inscrit à l'évènement avec l'id 100, on obtient un JSON qui contient l'invitation avec un id utilisateur-invitateur, un id utilisateur-invité, un id évènement, l'état de l'invitation ainsi qu'un code de retour 201` (){
        TODO("Méthode non-implémentée")
    }

    @Test
    fun `Étant donné l'utilisateur dont l'id est 1, qui n'est pas inscrit à l'évènement dont l'id est 100 et le type = publique, lorsqu'on effectue une requete POST pour envoyer une invitation à l'utilisateur 2 qui n'est pas inscrit à l'évènement avec l'id 100, on obtient un code de retour 400` (){
        TODO("Méthode non-implémentée")
    }

    @Test
    fun `Étant donné l'utilisateur dont l'id est 1, est inscrit à l'évènement dont l'id est 100 et le type = publique, lorsqu'on effectue une requete POST pour envoyer une invitation à l'utilisateur 2 qui est déja inscrit à l'évènement avec l'id 100, on obtient un code de retour 409` (){
        TODO("Méthode non-implémentée")
    }

    @Test
    fun `Étant donné l'utilisateur dont l'id est 1, est inscrit à l'évènement dont l'id est 100 et le type = privé, lorsqu'on effectue une requete POST pour envoyer une invitation à l'utilisateur 2 n'est pas déja inscrit à l'évènement avec l'id 100, on obtient un code de retour 409` (){
        TODO("Méthode non-implémentée")
    }

    @Test
    fun `Étant donné l'utilisateur dont l'id est 1, lorsqu'on effectue une requete GET de recherche d'invitations par id utilisateur, on obtient un fichier JSON qui contient la liste des invitations dont l'id de l'invité est 1 et un code de retour 200`(){
        TODO("Méthode non-implémentée")
    }

    @Test
    fun `Étant donné l'utilisateur dont l'id est 1 et qui n'est pas inscrit au service, lorsqu'on effectue une requete GET de recherche d'invitations par id utilisateur, on obtient le code de retour 404`(){
        TODO("Méthode non-implémentée")
    }



}