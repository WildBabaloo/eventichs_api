package eventichs.api.eventichs_api.Services

import eventichs.api.eventichs_api.DAO.InvitationÉvénementDAO
import eventichs.api.eventichs_api.Exceptions.DroitAccèsInsuffisantException
import eventichs.api.eventichs_api.Modèle.InvitationÉvénement
import org.springframework.stereotype.Service

@Service
class InvitationÉvénementService(val dao: InvitationÉvénementDAO) {

    fun chercherTous(): List<InvitationÉvénement> = dao.chercherTous()

    fun chercherInvitationsÉvénementsParIdDestinataire(id: Int, code_util: String): List<InvitationÉvénement> {
        if (dao.validerUtilisateur(id, code_util) == false){
            throw DroitAccèsInsuffisantException("L'utilisateur n'a pas le droit de consulter les invitations d'un autre utilisateur.")
        }
        return dao.chercherParIdDestinataire(id)
    }
    fun chercherInvitationÉvénementParId(id: Int): InvitationÉvénement? = dao.chercherParID(id)

    fun créerInvitationÉvénement(invitation: InvitationÉvénement) = dao.ajouter(invitation)

    fun modifierInvitationÉvénement(invitation: InvitationÉvénement) = dao.modifier(invitation)
    fun chercherInvitationsÉvénementsParIdExpediteur(id: Int): List<InvitationÉvénement> = dao.chercherParIdExpediteur(id)

    fun supprimerInvitationsÉvénementsParId(id: Int) = dao.supprimerParID(id)


    fun entrerJetonEvenement(id: Int, jeton: String) = dao.entrerJetonEvenement(id, jeton)

    fun genererJetonsEvenement(id: Int) = dao.creerJeton(id)

}