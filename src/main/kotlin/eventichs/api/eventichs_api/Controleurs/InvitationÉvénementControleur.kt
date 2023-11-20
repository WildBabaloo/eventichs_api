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

    //Consulter ses invitations(Participant+Organisation)
    @GetMapping("/utilisateur/invitations/destinataire/{id}")
    fun obtenirInvitationsÉvénementsParIdDestinataire(@PathVariable id: Int) =
        service.chercherInvitationsÉvénementsParIdDestinataire(id)

    @GetMapping("/utilisateur/invitations/expediteur/{id}")
    fun obtenirInvitationsÉvénementsParIdExpediteur(@PathVariable id: Int) =
        service.chercherInvitationsÉvénementsParIdExpediteur(id)

    @GetMapping("/utilisateur/invitations/{id}")
    fun obtenirInvitationÉvénementParId(@PathVariable id: Int) =
        service.chercherInvitationÉvénementParId(id)


    //Inviter un autre participant à un événement publique (Participant)
    @PostMapping("/invitation")
    fun créerInvitationÉvénement(@RequestBody invitation: InvitationÉvénement) =
        service.créerInvitationÉvénement(invitation)

    @PutMapping("/invitation/{id}")
    fun majInvitation(@PathVariable id: Int, @RequestBody invitation: InvitationÉvénement, reponse: String) =
        service.modifierInvitationÉvénement(invitation)

    //Éffacer une invitation (Participant + Organisation)
    @DeleteMapping("/invitation/{id}")
    fun supprimerInvitation(@PathVariable id: Int) =
        service.supprimerInvitationsÉvénementsParId(id)


    //Entrer un jeton d'invitation (Participant)
    @PostMapping("/jetons/{utilisateur_id}")
    fun saisirJeton(@PathVariable utilisateur_id : Int, @RequestBody jeton : String) =
        service.entrerJetonEvenement(utilisateur_id, jeton)

    //Générer son jeton d'invitation (Organisation)
    @GetMapping("/jetons/{id}/{quantité}")
    fun générerJeton(@PathVariable id : Int,  @PathVariable quantité : Int) =
        service.genererJetonsEvenement(id)
}
