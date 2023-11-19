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

    fun chercherParOrganisation(idOrganisation: Int) : List<InvitationOrganisation> {
        TODO("Not yet implemented")
    }

    fun chercherParParticipant(idParticipant: Int) : List<InvitationOrganisation> {
        TODO("Not yet implemented")
    }

    fun changerStatus(idInvitationOrganisation : Int, status : String) : InvitationOrganisation? {
        TODO("Not yet implemented")
    }
}