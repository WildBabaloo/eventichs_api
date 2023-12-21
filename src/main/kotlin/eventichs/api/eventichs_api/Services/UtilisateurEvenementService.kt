package eventichs.api.eventichs_api.Services

import eventichs.api.eventichs_api.DAO.UtilisateurEvenementDAO
import eventichs.api.eventichs_api.Modèle.Participant
import eventichs.api.eventichs_api.Modèle.UtilisateurÉvénement
import eventichs.api.eventichs_api.Modèle.Événement
import org.springframework.stereotype.Service

@Service
class UtilisateurEvenementService(val dao : UtilisateurEvenementDAO) {

    fun chercherTous(name : String) : List<UtilisateurÉvénement> = dao.chercherTous()

    fun chercherEvenementsParUtilisateur(name : String): List<Événement> = dao.chercherParUtilisateurID(name)

    fun chercherUtilisateursParEvenement(id: Int, name : String) : List<Participant> = dao.chercherParEvenementID(id)

    fun ajouter(ÉvénementId: Int, name : String): UtilisateurÉvénement? = dao.ajouter(UtilisateurÉvénement(name, ÉvénementId))

    fun supprimerParUtilisateur(name : String) : UtilisateurÉvénement? = dao.supprimerParUtilisateurID(name)

    fun supprimerParEvenement(id : Int, name : String) : UtilisateurÉvénement? = dao.supprimerParEvenementID(id)
}