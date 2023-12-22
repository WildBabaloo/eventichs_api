package eventichs.api.eventichs_api.DAO

import eventichs.api.eventichs_api.Modèle.OrganisationMembres

interface OrganisationMembersDAO: DAO<OrganisationMembres> {

    override fun chercherTous(): List<OrganisationMembres>

    fun ajouterParticipant(codeOrganisation: Int, codeUtilisateur: String): OrganisationMembres?

    fun enleverParticipant(codeOrganisation: Int, codeUtilisateur: String)

    fun chercherParUtilisateurID(codeUtilisateur: String): List<OrganisationMembres>

    fun chercherParOrganisationID(id: Int): List<OrganisationMembres>

    fun validerUtilisateur(id: Int, codeUtilisateur: String): Boolean

}