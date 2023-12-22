package eventichs.api.eventichs_api.DAO

import eventichs.api.eventichs_api.Modèle.OrganisationMembres

interface OrganisationMembersDAO: DAO<OrganisationMembres> {

    override fun chercherTous(): List<OrganisationMembres>

    fun ajouterParticipant(codeOrganisation: Int, IdParticipant: String)

    fun enleverParticipant(codeOrganisation: Int, idParticipant: String)

    fun chercherParUtilisateurID(id: String): List<OrganisationMembres>

    fun chercherParOrganisationID(id: Int): List<OrganisationMembres>

}