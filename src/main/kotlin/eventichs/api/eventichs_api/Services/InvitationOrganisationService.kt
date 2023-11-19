package eventichs.api.eventichs_api.Services

import eventichs.api.eventichs_api.DAO.InvitationOrganisationDAO
import eventichs.api.eventichs_api.Modèle.InvitationOrganisation
import org.springframework.stereotype.Service

@Service
class InvitationOrganisationService(val dao : InvitationOrganisationDAO){
    fun chercherTous(): List<InvitationOrganisation> = dao.chercherTous()
    fun chercherParID(id: Int): InvitationOrganisation? = dao.chercherParID(id)

}