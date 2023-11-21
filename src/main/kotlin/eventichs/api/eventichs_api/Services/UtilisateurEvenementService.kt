package eventichs.api.eventichs_api.Services

import eventichs.api.eventichs_api.DAO.UtilisateurEvenementDAO
import eventichs.api.eventichs_api.Modèle.UtilisateurÉvénement
import org.springframework.stereotype.Service

@Service
class UtilisateurEvenementService(val dao : UtilisateurEvenementDAO) {

    fun chercherTous() : List<UtilisateurÉvénement> = dao.chercherTous()

    fun chercherParUtilisateur(id: Int): UtilisateurÉvénement? = dao.chercherParID(id)

    fun chercherParEvenement(id: Int) : UtilisateurÉvénement? = dao.supprimerParID(id)

    fun ajouter(utilisateurÉvénement: UtilisateurÉvénement): UtilisateurÉvénement? = dao.ajouter(utilisateurÉvénement)

    fun supprimerParUtilisateur(id : Int) : UtilisateurÉvénement? = dao.supprimerParUtilisateurID(id)

    fun supprimerParEvenement(id : Int) : UtilisateurÉvénement? = dao.supprimerParEvenementID(id)
}