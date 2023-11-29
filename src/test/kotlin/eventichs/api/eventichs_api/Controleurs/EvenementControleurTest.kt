package eventichs.api.eventichs_api.Controleurs

import com.fasterxml.jackson.databind.ObjectMapper
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
class EvenementControleurTest {

//    @MockBean
//    lateinit var service: EvenementService

    @Autowired
    private lateinit var mapper: ObjectMapper
    @Autowired
    private lateinit var mockMvc: MockMvc

    // Consulter événements (général)
    @Test
    fun `Étant donné une requête GET de tous les événements disponibles, on reçoit un JSON qui contient la liste d'événements disponibles et un code retour 200`() {
        TODO("Méthode non-implémentée")
    }
    @Test
    fun `Étant donné une requête GET d'un événement disponible selon son ID, on reçoit un JSON qui contient l'événement demandé et un code retour 200`() {
        TODO("Méthode non-implémentée")
    }

    // Consulter événements (participant)
    @Test
    fun `Étant donné un utilisateur, lorsqu'on effectue une méthode GET d'événements disponibles, on reçoit un JSON qui contient la liste d'événements disponibles et un code retour 200`() {
        TODO("Méthode non-implémentée")
    }
    @Test
    fun `Étant donné un utilisateur qui n'est pas inscrit au service, lorsqu'on effectue une méthode GET d'événements disponibles, on reçoit un code d'erreur 404`() {
        TODO("Méthode non-implémentée")
    }

    // Consulter la liste des événements d'une organisation
    @Test
    fun `Étant donné une organisation, lorsqu'on effectue une méthode GET de ses événements, on reçoit un JSON qui contient la liste complète des événements et un code retour 200`() {
        TODO("Méthode non-implémentée")
    }
    @Test
    fun `Étant donné une organisation qui n'a pas d'événements, lorsqu'on effectue une méthode GET de ses événements, on reçoit un code d'erreur 404`() {
        TODO("Méthode non-implémentée")
    }

    // S'inscrire à un événement (participant)
    @Test
    fun `Étant donné un participant, lorsqu'on effectue une méthode POST pour se joindre à un événement, on obtient un JSON qui contient l'événement et un code retour 200`() {
        TODO("Méthode non-implémentée")
    }
    @Test
    fun `Étant donné un participant qui n'est pas inscrit au service, lorsqu'on effectue une méthode POST pour se joindre à un événement, on reçoit un code d'erreur 404`() {
        TODO("Méthode non-implémentée")
    }

    // Modifier sa participation à un événement (participant)
    @Test
    fun `Étant donné un participant, lorsqu'on effectue une méthode POST pour modifier sa participation à un événement, on obtient un JSON contenant l'événement et un code retour 200 `() {
        TODO("Méthode non-implémentée")
    }
    @Test
    fun `Étant donné un participant qui n'est pas inscrit à un événement, lorsqu'on effectue une méthode POST pour modifier sa participation à un événement, on reçoit un code d'erreur 404`() {
        TODO("Méthode non-implémentée")
    }

    // Supprimer sa participation à un événement (participant)
    @Test
    fun `Étant donné un participant, lorsqu'on effectue une méthode DELETE pour supprimer sa participation à un événement, on obtient un JSON contenant l'événement et un code retour 200 `() {
        TODO("Méthode non-implémentée")
    }
    @Test
    fun `Étant donné un participant qui n'est pas inscrit à un événement, lorsqu'on effectue une méthode DELETE pour supprimer sa participation à un événement, on reçoit un code d'erreur 404`() {
        TODO("Méthode non-implémentée")
    }

    // Consulter les événements auxquels l'utilisateur participe (participant)
    fun `Étant donné un participant, lorsqu'on effectue une méthode GET pour consulter la liste de ses événements, on obtient un JSON contenant la liste des événements et un code retour 200`() {
        TODO("Méthode non-implémentée")
    }
    fun `Étant donné un participant qui n'est pas inscrit au service, lorsqu'on effectue une méthode GET pour consulter la liste de ses événements, on reçoit un code d'erreur 404`() {
        TODO("Méthode non-implémentée")
    }

    // Créer un événement privé/public (organisation)
    fun `Étant donné une organisation, lorsqu'on effectue une méthode POST pour créer un événement, on obtient un JSON contenant l'événement et un code retour 200`() {
        TODO("Méthode non-implémentée")
    }
    fun `Étant donné une organisation qui n'est pas inscrit à un service, lorsqu'on effectue une méthode POST pour créer un événement, on reçoit un code d'erreur 404`() {
        TODO("Méthode non-implémentée")
    }

    // Modifier événement (organisation)
    fun `Étant donné une organisation, lorsqu'on effectue une méthode POST pour modifier un événement, on obtient un JSON contenant l'événement et un code retour 200`() {
        TODO("Méthode non-implémentée")
    }
    fun `Étant donnée une organisation qui n'est pas inscrit à un service, lorsqu'one effectue une méthode POST pour modifier un événement, on reçoit un code d'erreur 404`() {
        TODO("Méthode non-implémentée")
    }
    fun `Étant donnée une organisation, lorsqu'on effectue une méthode POST pour modifier un événement qui n'existe pas, on reçoit un code d'erreur 404`() {
        TODO("Méthode non-implémentée")
    }

    // Supprmier événement (organisation)
    fun `Étant donné un organisation, lorsqu'on effectue une méthode DELETE pour supprimer un événement, on obtient un JSON contenant l'événement supprimé et un code retour 200 `() {
        TODO("Méthode non-implémentée")
    }
    fun `Étant donné un organisation qui n'est pas inscrit à un service, lorsqu'on effectue une méthode DELETE pour supprimer un événement, on reçoit un code d'erreur 404`() {
        TODO("Méthode non-implémentée")
    }
    fun `Étant donnée une organisation, lorsqu'on effectue une méthode DELETE pour supprimer un événement qui n'existe pas, un reçoit un code d'erreur 404`() {
        TODO("Méthode non-implémentée")
    }





}
