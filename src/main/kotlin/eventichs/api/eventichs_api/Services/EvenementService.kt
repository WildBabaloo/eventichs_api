package eventichs.api.eventichs_api.Services

import org.springframework.stereotype.Service
import eventichs.api.eventichs_api.DAO.EvenementDAO
import eventichs.api.eventichs_api.Modèle.Événement

@Service
class EvenementService(val dao : EvenementDAO) {
    fun chercherTous(): List<Événement> = dao.chercherTous()
    fun chercherParID(id: Int): Événement? = dao.chercherParID(id)
    fun chercherParOrganisation(id: Int) : List<Événement> = dao.chercherParOrganisation(id)
    fun chercherEvenementPublic() : List<Événement> = dao.chercherEvenementPublic()
    fun supprimerParID(id:Int) : Événement? = dao.supprimerParID(id)
    fun modifierEvenement(id:Int, evenement : Événement ) : Événement? = dao.modifier(id,evenement)
    fun ajouterEvenement(evenement : Événement ) : Événement? = dao.ajouter(evenement)
}
