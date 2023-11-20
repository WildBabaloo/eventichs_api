package eventichs.api.eventichs_api.Controleurs

import eventichs.api.eventichs_api.Modèle.InvitationOrganisation
import eventichs.api.eventichs_api.Modèle.InvitationÉvénement
import eventichs.api.eventichs_api.Services.InvitationÉvénementService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("\${api.base-path:}")
class InvitationÉvénementControleur(val service: InvitationÉvénementService) {

    //Cas d'utilisation: 2.Inviter un autre participant à un événement publique (Participant)
    //Cas d'utilisation: 3.Consulter ses invitations(Participant+Organisation)
    //Cas d'utilisation: 5.Entrer un jeton d'invitation (Participant)
    //Cas d'utilisation: 6.Générer son jeton d'invitation (Organisation)
    //Cas d'utilisation: 7.Éffacer une invitation (Participant + Organisation)

    @GetMapping("/utilisateur/invitations/destinataire/{id}")
    fun obtenirInvitationsÉvénementsParIdDestinataire(@PathVariable id: Int) =
            service.chercherInvitationsÉvénementsParIdDestinataire(id)

    @GetMapping("/utilisateur/invitations/{id}")
    fun obtenirInvitationÉvénementParId(@PathVariable id: Int) =
        service.chercherInvitationÉvénementParId(id)

    @PostMapping("/invitation")
    fun créerInvitationÉvénement(@RequestBody Invitation: InvitationÉvénement) =
        service.créerInvitationÉvénement(Invitation)

    //@PutMapping("/invitation/{id}")
    fun majInvitation(@PathVariable id: String, @RequestBody reponse: String):
            ResponseEntity<InvitationOrganisation> = ResponseEntity(HttpStatus.NOT_IMPLEMENTED)

    //@DeleteMapping("/invitation/{id}")
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
