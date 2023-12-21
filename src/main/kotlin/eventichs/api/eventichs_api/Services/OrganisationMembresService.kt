package eventichs.api.eventichs_api.Services

import eventichs.api.eventichs_api.DAO.OrganisationMembersDAO
import eventichs.api.eventichs_api.Exceptions.DroitAccèsInsuffisantException
import eventichs.api.eventichs_api.Modèle.OrganisationMembres
import org.springframework.stereotype.Service

@Service
class OrganisationMembresService(val dao: OrganisationMembersDAO) {
    fun chercherTous(): List<OrganisationMembres> = dao.chercherTous()
    fun ajouterParticipant(codeOrganisation: Int, codeUtilisateur: String): OrganisationMembres? {
        if (!dao.validerUtilisateur(codeOrganisation ,codeUtilisateur)){ throw DroitAccèsInsuffisantException("L'utilisateur n'as pas le droit de consulter cette organisation") }

        return dao.ajouterParticipant(codeOrganisation, codeUtilisateur)
    }
    fun enleverParticipant(codeOrganisation: Int, codeUtilisateur: String) {
        if (!dao.validerUtilisateur(codeOrganisation, codeUtilisateur)){ throw DroitAccèsInsuffisantException("L'utilisateur n'as pas le droit de consulter cette organisation") }

        dao.enleverParticipant(codeOrganisation, codeUtilisateur)
    }
    fun chercherParParticipantID(codeUtilisateur: String): List<OrganisationMembres> = dao.chercherParUtilisateurID(codeUtilisateur)
    fun chercherParOrganisationID(codeOrganisation: Int, codeUtilisateur: String): List<OrganisationMembres> {
        if (!dao.validerUtilisateur(codeOrganisation, codeUtilisateur)){ throw DroitAccèsInsuffisantException("L'utilisateur n'as pas le droit de consulter cette organisation") }

        return dao.chercherParOrganisationID(codeOrganisation)
    }
}