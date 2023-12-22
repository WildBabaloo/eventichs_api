package eventichs.api.eventichs_api.Services

import eventichs.api.eventichs_api.DAO.InvitationOrganisationDAOImplMémoire
import eventichs.api.eventichs_api.Exceptions.DroitAccèsInsuffisantException
import eventichs.api.eventichs_api.Modèle.InvitationOrganisation
import eventichs.api.eventichs_api.Modèle.Organisation
import eventichs.api.eventichs_api.Modèle.Utilisateur
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock

class InvitationOrganisationServiceTest {

    val mockDAO = mock( InvitationOrganisationDAOImplMémoire::class.java )
    val service = InvitationOrganisationService(mockDAO)
    
    @Test //fun chercherParID(id: Int, code_util: String): InvitationOrganisation?
    fun `S-1 Étant donné l'utilisateur Joe qui gère l'organisaion ayant l'id 1 lorsqu'on tente d'obtenir une invitation à une organisation selon son id, alors son droit d'accès est validé et le l'invitation est obtenu`() {
        // Étant donné
        Mockito.`when`(mockDAO.validerUtilisateur(1, "Joe")).thenReturn(true)

        // lorsque
        service.chercherParID(1, "Joe")

        // alors
        Mockito.verify(mockDAO).chercherParID(1)
    }

    @Test //Cas d'utilisation: 3.Consulter ses invitations(Organisation). chercherParOrganisation(idOrganisation: Int, code_Util : String) : List<InvitationOrganisation>
    fun `S-2 Étant donné l'utilisateur Joe qui gère l'organisaion ayant l'id 1 lorsqu'on tente d'obtenir toutes les invitations de l'organisation selon son id, alors son droit d'accès est validé et les invitations sont obtenu`() {
        // Étant donné
        Mockito.`when`(mockDAO.validerOrganisation(1, "Joe")).thenReturn(true)

        // lorsque
        service.chercherParOrganisation(1, "Joe")

        // alors
        Mockito.verify(mockDAO).chercherParOrganisation(1)
    }

    @Test //Cas d'utilisation: 3.Consulter ses invitations(Participant). chercherParParticipant(code_Util: String) : List<InvitationOrganisation>
    fun `S-4 Étant donné l'utilisateur Kieran lorsqu'on tente d'obtenir toutes les invitations à des organisations du participant Kieran, alors les invitations sont obtenu`() {
        // lorsque
        service.chercherParParticipant( "Kieran")

        // alors
        Mockito.verify(mockDAO).chercherParParticipant("Kieran")
    }

    @Test //Cas d'utilisation: 4.Accepter la demande de joindre l'organisation par le participant (Organisation). changerStatus(invitation : InvitationOrganisation, status : String, code_Util: String) : InvitationOrganisation?
    fun `S-5 Étant donné l'utilisateur Joe qui gère l'organisation ayant l'id 1 lorsqu'on tente de modifier le status d'une invitation pour accepté un participant, alors le status de l'invitation est changé`() {
        val invitation = InvitationOrganisation(
            8,
            Utilisateur(
                "auth0|656d3ecc4178aefc03429538",
                "email",
                "nomUtil",
                "prénom"),
            Organisation(
                1,
                "Joe",
                "nomOrg",
                1,
                true),
            null,
            "envoyé")

        // Étant donné
        Mockito.`when`(mockDAO.validerOrganisationInvitation(8, "Joe")).thenReturn(true)

        // lorsque
        service.changerStatus(invitation, "accepté", "Joe")

        // alors
        Mockito.verify(mockDAO).changerStatus(8, "accepté")
    }

    @Test //Cas d'utilisation: 6.Générer son jeton d'invitation (Organisation). créerJeton(organisation : Organisation, code_Util: String) : InvitationOrganisation?
    fun `S-6 Étant donné l'utilisateur Joe qui gère l'organisation ayant l'id 8 lorsqu'on tente de générer un jeton, alors son droit d'accès est validé et un jeton est généré`() {
        val organisation = Organisation(
            8,
            "Joe",
            "nomOrg",
            1,
            true)

        // Étant donné
        Mockito.`when`(mockDAO.validerOrganisation(8, "Joe")).thenReturn(true)

        // lorsque
        service.créerJeton(organisation, "Joe")

        // alors
        Mockito.verify(mockDAO).créerJeton(8)
    }

    @Test //Cas d'utilisation: 7.Éffacer une invitation (Participant + Organisation). effacerInvitation(id : Int, code_Util: String) : InvitationOrganisation?
    fun `S-7 Étant donné l'utilisateur Joe qui gère l'organisation ayant l'id 8 lorsqu'on tente de supprimer cette invitation, alors son droit d'accès est validé et l'invitation est supprimé`() {
        // Étant donné
        Mockito.`when`(mockDAO.validerUtilisateur(8, "Joe")).thenReturn(true)

        // lorsque
        service.effacerInvitation(8, "Joe")

        // alors
        Mockito.verify(mockDAO).supprimerParID(8)
    }
}