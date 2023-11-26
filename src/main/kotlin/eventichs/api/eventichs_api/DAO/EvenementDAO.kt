package eventichs.api.eventichs_api.DAO

import eventichs.api.eventichs_api.Modèle.Événement

interface EvenementDAO : DAO<Événement> {
    override fun chercherTous(): List<Événement>
    override fun chercherParID(id: Int): Événement?

    fun chercherParOrganisation(organisation_id: Int): List<Événement>
    fun chercherParType(type : String) : List<Événement>
    override fun supprimerParID(id : Int) : Événement?

    override fun modifier(element: Événement): Événement?

    override fun ajouter(element: Événement): Événement?

}
