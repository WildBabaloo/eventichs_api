package eventichs.api.eventichs_api.Services

import eventichs.api.eventichs_api.DAO.InvitationÉvénementDAO
import eventichs.api.eventichs_api.Exceptions.DroitAccèsInsuffisantException
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
    fun chercherInvitationÉvénementParId(id: Int, name: String): InvitationÉvénement? = dao.chercherParID(id)

    fun créerInvitationÉvénement(invitation: InvitationÉvénement, name: String) = dao.ajouter(invitation)

    fun modifierInvitationÉvénement(invitation: InvitationÉvénement, name: String) = dao.modifier(invitation)
    fun chercherInvitationsÉvénementsParIdExpediteur(id: String, name: String): List<InvitationÉvénement> = dao.chercherParIdExpediteur(id)

    fun supprimerInvitationsÉvénementsParId(id: Int, name: String) = dao.supprimerParID(id)


    fun entrerJetonEvenement(id: String, jeton: String) = dao.entrerJetonEvenement(id, jeton)

    fun genererJetonsEvenement(id: Int) = dao.creerJeton(id)

}