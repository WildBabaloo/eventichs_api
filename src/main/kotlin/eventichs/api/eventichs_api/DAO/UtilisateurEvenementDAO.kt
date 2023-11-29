package eventichs.api.eventichs_api.DAO

import eventichs.api.eventichs_api.Modèle.Participant
import eventichs.api.eventichs_api.Modèle.Utilisateur
import eventichs.api.eventichs_api.Modèle.UtilisateurÉvénement
import eventichs.api.eventichs_api.Modèle.Événement

interface UtilisateurEvenementDAO : DAO<UtilisateurÉvénement> {

    override fun chercherTous(): List<UtilisateurÉvénement>
    fun chercherParUtilisateurID(id: Int): List<Événement>
    fun chercherParEvenementID(id: Int): List<Participant>

    override fun supprimerParID(id : Int) : UtilisateurÉvénement?
    fun supprimerParUtilisateurID(id : Int) : UtilisateurÉvénement?
    fun supprimerParEvenementID(id : Int) : UtilisateurÉvénement?
    override fun ajouter(element: UtilisateurÉvénement): UtilisateurÉvénement?
}
