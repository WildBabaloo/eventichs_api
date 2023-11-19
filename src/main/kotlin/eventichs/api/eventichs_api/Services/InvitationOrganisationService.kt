package eventichs.api.eventichs_api.Services

import eventichs.api.eventichs_api.DAO.InvitationOrganisationDAO
import eventichs.api.eventichs_api.Modèle.InvitationOrganisation
import org.springframework.stereotype.Service

@Service
class InvitationOrganisationService(val dao : InvitationOrganisationDAO){
    fun chercherTous(): List<InvitationOrganisation> = dao.chercherTous()
    fun chercherParID(id: Int): InvitationOrganisation? = dao.chercherParID(id)

    //Cas d'utilisation: 1.Demander à joindre une organisation (Participant)
    fun demandeJoindreOrganisation( idOrganisation: Int, idUtilisateur : Int) : InvitationOrganisation? =
        dao.ajouter(InvitationOrganisation(
            0,
            idUtilisateur,
            idOrganisation,
            null,
            "envoyé")
        )

    //Cas d'utilisation: 3.Consulter ses invitations(Participant+Organisation)
    fun chercherParOrganisation(idOrganisation: Int) : List<InvitationOrganisation> = dao.chercherParOrganisation(idOrganisation)
    fun chercherParParticipant(idParticipant: Int) : List<InvitationOrganisation> = dao.chercherParParticipant(idParticipant)
}