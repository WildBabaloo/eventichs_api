package eventichs.api.eventichs_api.Controleurs

import eventichs.api.eventichs_api.Modèle.InvitationOrganisation
import eventichs.api.eventichs_api.Modèle.Organisation
import eventichs.api.eventichs_api.Modèle.Utilisateur
import eventichs.api.eventichs_api.Services.InvitationOrganisationService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("\${api.base-path:}")
class InvitationOrganisationControleur(val service: InvitationOrganisationService) {

    @GetMapping("/organisations/invitations")
    fun obtenirInvitationOrganisation() = service.chercherTous()

    //si l'utilisateur est un participant, cela affiche des invitations. si il est une organisation, cela affiche des demandes d'invitations.
    @GetMapping("/organisations/invitations/{id}")
    fun obtenirInvitationsParIdUtilisateur(@PathVariable id: Int) = service.chercherParID(id)

    @PostMapping("/organisations/{idOrganisation}/invitations/{idParticipant}")
    //Cas d'utilisation: 1.Demander à joindre une organisation (Participant)
    fun demandeJoindreOrganisation(@PathVariable idOrganisation: Int, @PathVariable idParticipant: Int) = service.demandeJoindreOrganisation( idOrganisation, idParticipant)

    @GetMapping("/organisations/{idOrganisation}/invitations")
    //Cas d'utilisation: 3.Consulter ses invitations(Organisation)
    fun obtenirInvitationOrganisation(@PathVariable idOrganisation: Int) = service.chercherParOrganisation(idOrganisation)

    @GetMapping("/utilisateurs/{idParticipant}/invitations")
    //Cas d'utilisation: 3.Consulter ses invitations(Participant)
    fun obtenirInvitationParticipant(@PathVariable idParticipant: Int) = service.chercherParParticipant(idParticipant)

    @PutMapping("/organisations/invitations/{id}/status/{status}")
    //Cas d'utilisation: 4.Accepter la demande de joindre l'organisation par le participant (Organisation)
    fun changerStatus(@PathVariable id: Int, @PathVariable status : String) = service.changerStatus(id, status)

    @PutMapping("/organisations/jetons/{jeton}/{idUtilisateur}")
    //Cas d'utilisation: 5.Entrer un jeton d'invitation (Participant)
    fun saisirJeton(@PathVariable jeton : String, @PathVariable idUtilisateur : Int) = service.saisirJeton(jeton, idUtilisateur)

    @PostMapping("/organisations/{idOrganisation}/jetons")
    //Cas d'utilisation: 6.Générer son jeton d'invitation (Organisation)
    fun crééJeton(@PathVariable idOrganisation : Int) = service.crééJeton(idOrganisation)

    @DeleteMapping("/organisations/invitations/{idInvitationOrganisation}")
    //Cas d'utilisation: 7.Éffacer une invitation (Participant + Organisation)
    fun effacerInvitation(@PathVariable idInvitationOrganisation: Int) = service.effacerInvitation(idInvitationOrganisation)

}

