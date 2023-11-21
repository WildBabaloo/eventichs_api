package eventichs.api.eventichs_api.DAO

import eventichs.api.eventichs_api.Modèle.OrganisationMembres

interface OrganisationMembersDAO: DAO<OrganisationMembres> {

    override fun chercherTous(): List<OrganisationMembres>

    fun ajouterParticipant(codeOrganisation: Int, IdParticipant: Int)

    fun enleverParticipant(codeOrganisation: Int, idParticipant: Int)

    fun chercherParUtilisateurID(id: Int): List<OrganisationMembres>

    fun chercherParOrganisationID(id: Int): List<OrganisationMembres>

}