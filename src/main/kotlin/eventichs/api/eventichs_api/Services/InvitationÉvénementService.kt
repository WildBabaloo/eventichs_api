package eventichs.api.eventichs_api.Services

import eventichs.api.eventichs_api.DAO.InvitationÉvénementDAO
import eventichs.api.eventichs_api.Modèle.InvitationÉvénement
import org.springframework.stereotype.Service

@Service
class InvitationÉvénementService(val dao: InvitationÉvénementDAO) {

    fun chercherTous(): List<InvitationÉvénement> = dao.chercherTous()

    fun chercherInvitationsÉvénementsParIdDestinataire(id: Int): List<InvitationÉvénement> = dao.chercherParIdDestinataire(id)
    fun chercherInvitationÉvénementParId(id: Int): InvitationÉvénement? = dao.chercherParID(id)

    fun créerInvitationÉvénement(invitation: InvitationÉvénement) = dao.ajouter(invitation)

    fun modifierInvitationÉvénement(invitation: InvitationÉvénement) = dao.modifier(invitation)
    fun chercherInvitationsÉvénementsParIdExpediteur(id: Int): List<InvitationÉvénement> = dao.chercherParIdExpediteur(id)

    fun supprimerInvitationsÉvénementsParId(id: Int) = dao.supprimerParID(id)


    fun entrerJetonEvenement(id: Int, jeton: String) = dao.entrerJetonEvenement(id, jeton)

    fun genererJetonsEvenement(id: Int) = dao.creerJeton(id)

}