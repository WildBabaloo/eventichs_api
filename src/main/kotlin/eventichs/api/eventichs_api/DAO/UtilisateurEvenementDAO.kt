package eventichs.api.eventichs_api.DAO

import eventichs.api.eventichs_api.Modèle.UtilisateurÉvénement

interface UtilisateurEvenementDAO : DAO<UtilisateurÉvénement> {

    override fun chercherTous(): List<UtilisateurÉvénement>
    override fun chercherParID(id: Int): UtilisateurÉvénement?

    fun chercherParOrganisation(organisation_id: Int): List<UtilisateurÉvénement>
    override fun supprimerParID(id : Int) : UtilisateurÉvénement?

    override fun modifier(element: UtilisateurÉvénement): UtilisateurÉvénement?

    override fun ajouter(element: UtilisateurÉvénement): UtilisateurÉvénement?
}
