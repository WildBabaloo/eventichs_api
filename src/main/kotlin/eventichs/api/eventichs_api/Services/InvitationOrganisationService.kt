package eventichs.api.eventichs_api.Services

import eventichs.api.eventichs_api.DAO.InvitationOrganisationDAO
import eventichs.api.eventichs_api.Exceptions.DroitAccèsInsuffisantException
import eventichs.api.eventichs_api.Modèle.InvitationOrganisation
import eventichs.api.eventichs_api.Modèle.Organisation
import eventichs.api.eventichs_api.Modèle.Utilisateur
import org.springframework.stereotype.Service

@Service
class InvitationOrganisationService(val dao : InvitationOrganisationDAO){
    fun chercherTous(): List<InvitationOrganisation> = dao.chercherTous()
    fun chercherParID(id: Int, code_util: String): InvitationOrganisation? {
        if (dao.validerUtilisateur(id, code_util) == false) {
            throw DroitAccèsInsuffisantException("L'utilisateur n'as pas le droit de consulter cette invitation")
        }
        return dao.chercherParID(id)
    }

    //Cas d'utilisation: 1.Demander à joindre une organisation (Participant)
    fun demandeJoindreOrganisation(invitation: InvitationOrganisation, code_Util : String) : InvitationOrganisation? {
        if (invitation.Utilisateur?.code != code_Util || invitation.Organisation?.codeUtilisateur == code_Util) {
            throw DroitAccèsInsuffisantException("L'utilisateur n'as pas le droit de consulter cette invitation")
        }
        return dao.ajouter(invitation)
    }

    //Cas d'utilisation: 3.Consulter ses invitations(Participant+Organisation)
    fun chercherParOrganisation(idOrganisation: Int, code_Util : String) : List<InvitationOrganisation> {
        if (dao.validerOrganisation(idOrganisation, code_Util) == false) {
            throw DroitAccèsInsuffisantException("L'utilisateur n'as pas le droit de consulter cette invitation")
        }
        return dao.chercherParOrganisation(idOrganisation)
    }

    fun chercherParParticipant(code_Util: String) : List<InvitationOrganisation> {
        return dao.chercherParParticipant(code_Util)
    }


    //Cas d'utilisation: 4.Accepter la demande de joindre l'organisation par le participant (Organisation)
    fun changerStatus(invitation : InvitationOrganisation, status : String, code_Util: String) : InvitationOrganisation? {
        if (dao.validerOrganisationInvitation(invitation.Organisation.id, code_Util) == false) {
            throw DroitAccèsInsuffisantException("L'utilisateur n'as pas le droit de consulter cette invitation")
        }

        return dao.changerStatus(invitation.id, status)
    }


    //Cas d'utilisation: 5.Entrer un jeton d'invitation (Participant)
    fun saisirJeton(jeton : String, code_Util: String) : InvitationOrganisation? {


        return dao.saisirJeton(jeton, code_Util)
    }

    //Cas d'utilisation: 6.Générer son jeton d'invitation (Organisation)
    fun crééJeton(organisation : Organisation, code_Util: String) : InvitationOrganisation? {
        if (dao.validerOrganisation(organisation.id, code_Util) == false) {
            throw DroitAccèsInsuffisantException("L'utilisateur n'as pas le droit de consulter cette invitation")
        }

        return dao.crééJeton(organisation.id)
    }


    //Cas d'utilisation: 7.Éffacer une invitation (Participant + Organisation)
    fun effacerInvitation(id : Int, code_Util: String) : InvitationOrganisation? {
        if (dao.validerUtilisateur(id, code_Util) == false) {
            throw DroitAccèsInsuffisantException("L'utilisateur n'as pas le droit de consulter cette invitation")
        }

        return dao.supprimerParID(id)
    }
}