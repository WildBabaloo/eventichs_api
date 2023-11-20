package eventichs.api.eventichs_api.DAO

import eventichs.api.eventichs_api.Modèle.UtilisateurÉvénement

interface UtilisateurEvenementDAO : DAO<UtilisateurÉvénement> {

    override fun chercherTous(): List<UtilisateurÉvénement>
    fun chercherParUtilisateurID(id: Int): List<UtilisateurÉvénement>
    fun chercherParEvenementID(id: Int): List<UtilisateurÉvénement>

    override fun supprimerParID(id : Int) : UtilisateurÉvénement?
    fun supprimerParUtilisateurID(id : Int) : UtilisateurÉvénement?
    fun supprimerParEvenementID(id : Int) : UtilisateurÉvénement?
    override fun ajouter(element: UtilisateurÉvénement): UtilisateurÉvénement?
}
