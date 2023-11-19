package eventichs.api.eventichs_api.DAO

import eventichs.api.eventichs_api.Modèle.Événement

interface EvenementDAO : DAO<Événement> {
    override fun chercherTous(): List<Événement>
    override fun chercherParID(id: Int): Événement?

    fun chercherParOrganisation(organisation_id: Int): List<Événement>

}
