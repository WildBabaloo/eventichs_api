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

    override fun modifier(element: InvitationOrganisation): InvitationOrganisation? {
        TODO("Not yet implemented")
    }

    override fun supprimerParID(id: Int): InvitationOrganisation?

    fun chercherParOrganisation(idOrganisation: Int) : List<InvitationOrganisation>

    fun chercherParParticipant(idParticipant: Int) : List<InvitationOrganisation>

    fun changerStatus(idInvitationOrganisation : Int, status : String) : InvitationOrganisation?

    fun crééJeton(idOrganisation : Int) : InvitationOrganisation?

    fun saisirJeton(jeton : String, idUtilisateur : Int) : InvitationOrganisation?
}