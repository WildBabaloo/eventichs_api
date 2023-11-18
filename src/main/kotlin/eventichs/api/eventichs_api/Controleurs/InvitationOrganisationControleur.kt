package eventichs.api.eventichs_api.Controleurs

import eventichs.api.eventichs_api.Modèle.InvitationOrganisation
import eventichs.api.eventichs_api.Services.InvitationOrganisationService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("\${api.base-path:}")
class InvitationOrganisationControleur(val service: InvitationOrganisationService) {

    //si l'utilisateur est un participant, cela affiche des invitations. si il est une organisation, cela affiche des demandes d'invitations.
    @GetMapping("/invitations/{id}")
    fun obtenirInvitationsParIdUtilisateur(@PathVariable id: String):
            ResponseEntity<InvitationOrganisation> = ResponseEntity(HttpStatus.NOT_IMPLEMENTED)

    @PostMapping("/invitations")
    fun inviterOuDemanderInvitation(@RequestBody invitationOuDemande: InvitationOrganisation):
            ResponseEntity<InvitationOrganisation> = ResponseEntity(HttpStatus.NOT_IMPLEMENTED)

    @PutMapping("/invitation/{id}")
    fun majInvitation(@PathVariable id: String, @RequestBody reponse: String):
            ResponseEntity<InvitationOrganisation> = ResponseEntity(HttpStatus.NOT_IMPLEMENTED)

    @DeleteMapping("/invitation/{id}")
    fun supprimerInvitation(@PathVariable id: String):
            ResponseEntity<InvitationOrganisation> = ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
}

