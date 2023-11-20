package eventichs.api.eventichs_api.Services

import eventichs.api.eventichs_api.DAO.OrganisationMembersDAO
import eventichs.api.eventichs_api.Modèle.OrganisationMembres
import org.springframework.stereotype.Service

@Service
class OrganisationMembresService(val dao: OrganisationMembersDAO) {
    fun ajouterParticipant(codeOrganisation: Int, idParticipant: Int) = dao.ajouterParticipant(codeOrganisation, idParticipant)
    fun enleverParticipant(codeOrganisation: Int) = dao.enleverParticipant(codeOrganisation)
    fun chercherParUtilisateurID(id: Int): List<OrganisationMembres> = dao.chercherParUtilisateurID(id)
    fun chercherParParticipantID(id: Int): List<OrganisationMembres> = dao.chercherParOrganisationID(id)
}