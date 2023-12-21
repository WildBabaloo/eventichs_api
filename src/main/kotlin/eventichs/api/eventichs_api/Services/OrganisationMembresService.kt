package eventichs.api.eventichs_api.Services

import eventichs.api.eventichs_api.DAO.OrganisationMembersDAO
import eventichs.api.eventichs_api.Modèle.OrganisationMembres
import org.springframework.stereotype.Service

@Service
class OrganisationMembresService(val dao: OrganisationMembersDAO) {
    fun chercherTous(): List<OrganisationMembres> = dao.chercherTous()
    fun ajouterParticipant(codeOrganisation: Int, codeUtilisateur: String) = dao.ajouterParticipant(codeOrganisation, codeUtilisateur)
    fun enleverParticipant(codeOrganisation: Int, codeUtilisateur: String) = dao.enleverParticipant(codeOrganisation, codeUtilisateur)
    fun chercherParParticipantID(codeUtilisateur: String): List<OrganisationMembres> = dao.chercherParUtilisateurID(codeUtilisateur)
    fun chercherParOrganisationID(id: Int): List<OrganisationMembres> = dao.chercherParOrganisationID(id)
}