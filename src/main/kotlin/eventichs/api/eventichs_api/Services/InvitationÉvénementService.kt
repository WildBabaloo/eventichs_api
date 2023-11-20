package eventichs.api.eventichs_api.Services

import eventichs.api.eventichs_api.DAO.InvitationÉvénementDAO
import eventichs.api.eventichs_api.Modèle.InvitationÉvénement
import org.springframework.stereotype.Service

@Service
class InvitationÉvénementService(val dao: InvitationÉvénementDAO) {

    fun chercherTous(): List<InvitationÉvénement> = dao.chercherTous()

    fun chercherInvitationsParIdUtilisateur(id: Int): List<InvitationÉvénement> = dao.chercherParIdUtilisateur(id)

}