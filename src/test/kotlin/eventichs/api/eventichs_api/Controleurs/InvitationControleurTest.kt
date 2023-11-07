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


    //Demander à joindre une organisation
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


    //Inviter un autre participant à un événement publique (Participant)
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

    // Consulter ses invitations et ses demandes (Participant + Organisation)
    @Test
    fun `Étant donné l'utilisateur dont l'id est 1, lorsqu'on effectue une requete GET de recherche d'invitations et demandes par id utilisateur, on obtient un fichier JSON qui contient la liste des invitations dont l'id de l'invité est 1 et un code de retour 200`(){
        TODO("Méthode non-implémentée")
    }

    @Test
    fun `Étant donné l'utilisateur dont l'id est 1 et qui n'est pas inscrit au service, lorsqu'on effectue une requete GET de recherche d'invitations par id utilisateur, on obtient le code de retour 404`(){
        TODO("Méthode non-implémentée")
    }

    //Accepter la demande de joindre l'organisation par le participant

    @Test
    fun `Etant donne l'organisation dont l'id est 1, lorsqu'on effectue une requête POST pour accepter la demande d'invitation de l'utilisateur dont l'id est 2, on obtient un JSON qui contient l'id de l'utilisateur, l'id de l'organisation et un code de retour 201 `(){
        TODO("Methode non-implementee")
    }

    @Test
    fun `Étant donné l'organisation dont l'id est 1, lorsqu'on effectue une requête POST pour accepter une demande non éxistante de l'utilisateur dont l'id est 2, on obtient un code de retour 409 `(){
        TODO("Methode non-implementee")
    }
}