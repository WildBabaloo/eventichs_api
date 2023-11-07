package eventichs.api.eventichs_api.Controleurs

import eventichs.api.eventichs_api.Modèle.Invitation
import eventichs.api.eventichs_api.Services.InvitationService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("\${api.base-path:}")
class InvitationControleur(val service: InvitationService) {

    //si l'utilisateur est un participant, cela affiche des invitations. si il est une organisation, cela affiche des demandes d'invitations.
    @GetMapping("/invitations/{id}")
    fun obtenirInvitationsParIdUtilisateur(@PathVariable id: String):
            ResponseEntity<Invitation> = ResponseEntity(HttpStatus.NOT_IMPLEMENTED)

    @PostMapping("/invitations")
    fun inviterOuDemanderInvitation(@RequestBody invitationOuDemande: Invitation):
            ResponseEntity<Invitation> = ResponseEntity(HttpStatus.NOT_IMPLEMENTED)

    @PutMapping("/invitation/{id}")
    fun majInvitationGroupe(@PathVariable id: String, @RequestBody reponse: String):
            ResponseEntity<Invitation> = ResponseEntity(HttpStatus.NOT_IMPLEMENTED)

    //ne devrait pas avoir besoin d'être utilisé?
    @DeleteMapping("/invitation/{id}")
    fun supprimerInvitation(@PathVariable id: String):
            ResponseEntity<Invitation> = ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
}

