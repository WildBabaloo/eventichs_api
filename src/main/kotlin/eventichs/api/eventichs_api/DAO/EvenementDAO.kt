package eventichs.api.eventichs_api.DAO

import eventichs.api.eventichs_api.Modele.Evenement


interface EvenementDAO : DAO<Evenement> {
    override fun chercherTous(): List<Evenement>
    override fun chercherParCode(code: Int): Evenement?

    fun chercherParOrganisation(code: Int): List<Evenement>

}