package eventichs.api.eventichs_api.DAO

import eventichs.api.eventichs_api.Modèle.Organisation
import eventichs.api.eventichs_api.Modèle.OrganisationMembres
import eventichs.api.eventichs_api.Modèle.Utilisateur

interface OrganisationMembersDAO: DAO<OrganisationMembres> {
    override fun ajouterParticipant(codeOrganisation: Organisation, unParticipant: Utilisateur): Organisation?

    override fun enleverParticipant(codeOrganisation: Organisation, unParticipant: Utilisateur): Organisation?
}