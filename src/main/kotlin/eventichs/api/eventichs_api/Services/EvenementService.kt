package eventichs.api.eventichs_api.Services

import org.springframework.stereotype.Service
import eventichs.api.eventichs_api.DAO.EvenementDAO
import eventichs.api.eventichs_api.Modèle.Événement
import java.security.Principal

@Service
class EvenementService(val dao : EvenementDAO) {
    fun chercherTous(name: String): List<Événement> = dao.chercherTous()
    fun chercherParID(id: Int, name: String): Événement? = dao.chercherParID(id)
    fun chercherParOrganisation(id: Int, name: Any) : List<Événement> = dao.chercherParOrganisation(id)
    fun chercherEvenementPublic() : List<Événement> = dao.chercherEvenementPublic()
    fun supprimerParID(id:Int) : Événement? = dao.supprimerParID(id)
    fun modifierEvenement(id: Int, evenement: Événement, principal: Principal?) : Événement? = dao.modifier(id,evenement)
    fun ajouterEvenement(evenement: Événement, principal: Any?) : Événement? = dao.ajouter(evenement)
}
