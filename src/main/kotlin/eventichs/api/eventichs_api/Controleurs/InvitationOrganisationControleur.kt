package eventichs.api.eventichs_api.Controleurs

import eventichs.api.eventichs_api.Modèle.InvitationOrganisation
import eventichs.api.eventichs_api.Services.InvitationOrganisationService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("\${api.base-path:}")
class InvitationOrganisationControleur(val service: InvitationOrganisationService) {


    // Cas d'utilisation: Demander à joindre une organisation (Participant)
    //@PostMapping("/organisations/{id}/invitations")
    //fun demanderJoindreOrganisation(@PathVariable id: Int, @RequestBody idUtilisateur: Int) {}


    // Cas d'utilisation: Inviter un autre participant à un événement publique (Participant)
    // Cas d'utilisation: Consulter ses invitations et ses demandes (Participant+Organisation)
    // Cas d'utilisation: Accepter la demande de joindre l'org.. par le participant (Organisation)
    // Cas d'utilisation: Entrer un jeton d'invitation (Participant)
    // Cas d'utilisation: Générer son jeton d'invitation (Organisation)
    // Cas d'utilisation: Éffacer une invitation (Participant + Organisation)






    @GetMapping("/organisations/invitations")
    fun obtenirInvitationOrganisation() = service.chercherTous()

    //si l'utilisateur est un participant, cela affiche des invitations. si il est une organisation, cela affiche des demandes d'invitations.
    @GetMapping("/organisations/invitations/{id}")
    fun obtenirInvitationsParIdUtilisateur(@PathVariable id: Int) = service.chercherParID(id)

    @PostMapping("/invitations")
    fun inviterOuDemanderInvitation(@RequestBody invitationOuDemande: InvitationOrganisation):
            ResponseEntity<InvitationOrganisation> = ResponseEntity(HttpStatus.NOT_IMPLEMENTED)

    @PutMapping("/invitation/{id}")
    fun majInvitation(@PathVariable id: String, @RequestBody reponse: String):
            ResponseEntity<InvitationOrganisation> = ResponseEntity(HttpStatus.NOT_IMPLEMENTED)

    @DeleteMapping("/invitation/{id}")
    fun supprimerInvitation(@PathVariable id: String):
            ResponseEntity<InvitationOrganisation> = ResponseEntity(HttpStatus.NOT_IMPLEMENTED)


    /*
    @PostMapping("/jetons/{utilisateur_id}")
    fun saisirJeton(@PathVariable utilisateur_id : Int, @RequestBody jeton : InvitationÉvénement):
            ResponseEntity<InvitationOrganisation> = ResponseEntity(HttpStatus.NOT_IMPLEMENTED)

    @GetMapping("/jetons/{id}/{quantité}")
    fun générerJeton(@PathVariable id : Int,  @PathVariable quantité : Int):
            ResponseEntity<InvitationOrganisation> = ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
     */

}

