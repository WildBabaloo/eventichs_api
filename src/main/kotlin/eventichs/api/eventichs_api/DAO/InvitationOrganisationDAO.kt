package eventichs.api.eventichs_api.DAO

import eventichs.api.eventichs_api.Modèle.InvitationOrganisation

interface InvitationOrganisationDAO : DAO<InvitationOrganisation>  {
    override fun chercherTous(): List<InvitationOrganisation> {
        TODO("Not yet implemented")
    }

    override fun chercherParID(id: Int): InvitationOrganisation? {
        TODO("Not yet implemented")
    }

    override fun ajouter(element: InvitationOrganisation): InvitationOrganisation? {
        TODO("Not yet implemented")
    }
}