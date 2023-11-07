package eventichs.api.eventichs_api.Controleurs

import eventichs.api.eventichs_api.Modèle.Invitation
import eventichs.api.eventichs_api.Services.InvitationService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("\${api.base-path:}")
class InvitationControleur(val service: InvitationService) {

    @GetMapping("/invitation/{id}")
    fun obtenirInvitationsParIdUtilisateur(@PathVariable id: String):
            ResponseEntity<Invitation> = ResponseEntity(HttpStatus.NOT_IMPLEMENTED)

    @PostMapping("/invitations")
    fun inviterOuDemanderInvitation(@RequestBody invitationOuDemande: Invitation):
            ResponseEntity<Invitation> = ResponseEntity(HttpStatus.NOT_IMPLEMENTED)

}