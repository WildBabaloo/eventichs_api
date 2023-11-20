package eventichs.api.eventichs_api.DAO

import eventichs.api.eventichs_api.Modèle.Organisation
import eventichs.api.eventichs_api.Modèle.OrganisationMembres
import eventichs.api.eventichs_api.Modèle.Utilisateur

interface OrganisationMembersDAO: DAO<OrganisationMembres> {
    fun ajouterParticipant(codeOrganisation: Int, IdParticipant: Int)

    fun enleverParticipant(codeOrganisation: Int)
}