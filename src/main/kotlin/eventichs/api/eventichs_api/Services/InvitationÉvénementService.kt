package eventichs.api.eventichs_api.Services

import eventichs.api.eventichs_api.DAO.EvenementDAO
import eventichs.api.eventichs_api.DAO.InvitationÉvénementDAO
import eventichs.api.eventichs_api.Exceptions.DroitAccèsInsuffisantException
import eventichs.api.eventichs_api.Exceptions.RessourceInexistanteException
import eventichs.api.eventichs_api.Modèle.InvitationÉvénement
import org.springframework.stereotype.Service

@Service
class InvitationÉvénementService(val dao: InvitationÉvénementDAO) {

    fun chercherTous(): List<InvitationÉvénement> = dao.chercherTous()

    fun chercherInvitationsÉvénementsParIdDestinataire(id: String, code_util: String): List<InvitationÉvénement> {
        if (dao.validerUtilisateur(id, code_util) == false){
            throw DroitAccèsInsuffisantException("L'utilisateur n'a pas le droit de consulter les invitations d'un autre utilisateur.")
        }
        return dao.chercherParIdDestinataire(id)
    }

    fun chercherInvitationsÉvénementsParIdExpediteur(id: String, code_util: String): List<InvitationÉvénement> {
        if (!dao.validerUtilisateur(id, code_util)){
            throw DroitAccèsInsuffisantException("L'utilisateur n'a pas le droit de consulter les invitations d'un autre utilisateur.")
        }
        return dao.chercherParIdExpediteur(id)
    }

    fun chercherInvitationÉvénementParId(id: Int, code_util: String): InvitationÉvénement? {
        val invitation = dao.chercherParID(id)?: return null
        if(code_util != invitation.idDestinataire || code_util != invitation.idExpéditeur){
            throw DroitAccèsInsuffisantException("L'utilisateur n'a pas le droit de consulter un évènement dont il n'est ni l'expéditeur ou le destinataire.")
        }
        return invitation
    }

    fun créerInvitationÉvénement(invitation: InvitationÉvénement, code_util: String):InvitationÉvénement? {
        if (invitation.idDestinataire == code_util || invitation.idExpéditeur != code_util){
            throw DroitAccèsInsuffisantException("L'utilisateur n'a pas le droit de créer une invitation")
        }
        return dao.ajouter(invitation)
    }
    fun modifierInvitationÉvénement(invitation: InvitationÉvénement, code_util: String): InvitationÉvénement? {
        val invitation = dao.chercherParID(invitation.id)?: return null
        if (code_util != invitation.idDestinataire || code_util != invitation.idExpéditeur) {
            throw DroitAccèsInsuffisantException("L'utilisateur n'a pas le droit de consulter un évènement dont il n'est ni l'expéditeur ou le destinataire.")
        }
        return dao.modifier(invitation)
    }

    fun supprimerInvitationsÉvénementsParId(id: Int, code_util: String): InvitationÉvénement? {
        if (dao.validerUtilisateur(dao.chercherParID(id)?.idExpéditeur?: "", code_util)){
            throw DroitAccèsInsuffisantException("L'utilisateur ne peut pas supprimer cet évènement.")
        }
        return dao.supprimerParID(id)
    }

    fun entrerJetonEvenement(id: String, jeton: String) = dao.entrerJetonEvenement(id, jeton)

    fun genererJetonsEvenement(id: Int, code_util: String) = dao.creerJeton(id)

}