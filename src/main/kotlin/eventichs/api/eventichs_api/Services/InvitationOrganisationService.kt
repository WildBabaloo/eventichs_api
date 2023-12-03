package eventichs.api.eventichs_api.Services

import eventichs.api.eventichs_api.DAO.InvitationOrganisationDAO
import eventichs.api.eventichs_api.Modèle.InvitationOrganisation
import eventichs.api.eventichs_api.Modèle.Utilisateur
import org.springframework.stereotype.Service

@Service
class InvitationOrganisationService(val dao : InvitationOrganisationDAO){
    fun chercherTous(): List<InvitationOrganisation> = dao.chercherTous()
    fun chercherParID(id: Int): InvitationOrganisation? = dao.chercherParID(id)

    //Cas d'utilisation: 1.Demander à joindre une organisation (Participant)
    fun demandeJoindreOrganisation(invitation: InvitationOrganisation) : InvitationOrganisation? = dao.ajouter(invitation)

    //Cas d'utilisation: 3.Consulter ses invitations(Participant+Organisation)
    fun chercherParOrganisation(idOrganisation: Int) : List<InvitationOrganisation> = dao.chercherParOrganisation(idOrganisation)
    fun chercherParParticipant(idParticipant: Int) : List<InvitationOrganisation> = dao.chercherParParticipant(idParticipant)

    //Cas d'utilisation: 4.Accepter la demande de joindre l'organisation par le participant (Organisation)
    fun changerStatus(idInvitationOrganisation: Int, status : String) : InvitationOrganisation? = dao.changerStatus(idInvitationOrganisation, status)

    //Cas d'utilisation: 5.Entrer un jeton d'invitation (Participant)
    fun saisirJeton(jeton : String, utilisateur: Utilisateur) : InvitationOrganisation? = dao.saisirJeton(jeton, utilisateur)

    //Cas d'utilisation: 6.Générer son jeton d'invitation (Organisation)
    fun crééJeton(idOrganisation : Int) : InvitationOrganisation? = dao.crééJeton(idOrganisation)

    //Cas d'utilisation: 7.Éffacer une invitation (Participant + Organisation)
    fun effacerInvitation(id : Int) : InvitationOrganisation? = dao.supprimerParID(id)
}