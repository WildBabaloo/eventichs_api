package eventichs.api.eventichs_api.Services

import eventichs.api.eventichs_api.DAO.UtilisateurEvenementDAO
import eventichs.api.eventichs_api.Modèle.Participant
import eventichs.api.eventichs_api.Modèle.UtilisateurÉvénement
import eventichs.api.eventichs_api.Modèle.Événement
import org.springframework.stereotype.Service

@Service
class UtilisateurEvenementService(val dao : UtilisateurEvenementDAO) {

    fun chercherTous() : List<UtilisateurÉvénement> = dao.chercherTous()

    fun chercherEvenementsParUtilisateur(id: Int): List<Événement> = dao.chercherParUtilisateurID(id)

    fun chercherUtilisateursParEvenement(id: Int) : List<Participant> = dao.chercherParEvenementID(id)

    fun ajouter(utilisateurÉvénement: UtilisateurÉvénement): UtilisateurÉvénement? = dao.ajouter(utilisateurÉvénement)

    fun supprimerParUtilisateur(id : Int) : UtilisateurÉvénement? = dao.supprimerParUtilisateurID(id)

    fun supprimerParEvenement(id : Int) : UtilisateurÉvénement? = dao.supprimerParEvenementID(id)
}