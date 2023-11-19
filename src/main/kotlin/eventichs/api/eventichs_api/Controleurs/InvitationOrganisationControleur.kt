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

    @PostMapping("/organisations/{idOrganisation}/invitations/{idParticipant}")
    //Cas d'utilisation: 1.Demander à joindre une organisation (Participant)
    fun demandeJoindreOrganisation(@PathVariable idOrganisation: Int, @PathVariable idParticipant: Int) = service.demandeJoindreOrganisation( idOrganisation, idParticipant)

    @GetMapping("/organisations/{idOrganisation}/invitations")
    //Cas d'utilisation: 3.Consulter ses invitations(Organisation)
    fun obtenirInvitationOrganisation(@PathVariable idOrganisation: Int) = service.chercherParOrganisation(idOrganisation)

    @GetMapping("/organisations/invitations/{idParticipant}")
    //Cas d'utilisation: 3.Consulter ses invitations(Participant)
    fun obtenirInvitationParticipant(@PathVariable idParticipant: Int) = service.chercherParParticipant(idParticipant)

    //Cas d'utilisation: 4.Accepter la demande de joindre l'organisation par le participant (Organisation)
    //Cas d'utilisation: 5.Entrer un jeton d'invitation (Participant)
    //Cas d'utilisation: 6.Générer son jeton d'invitation (Organisation)
    //Cas d'utilisation: 7.Éffacer une invitation (Participant + Organisation)




    @GetMapping("/organisations/invitations")
    fun obtenirInvitationOrganisation() = service.chercherTous()

    //si l'utilisateur est un participant, cela affiche des invitations. si il est une organisation, cela affiche des demandes d'invitations.
    @GetMapping("/organisations/invitations/{id}")
    fun obtenirInvitationsParIdUtilisateur(@PathVariable id: Int) = service.chercherParID(id)


    /*

    @PostMapping("/invitations")
    fun inviterOuDemanderInvitation(@RequestBody invitationOuDemande: InvitationOrganisation):
            ResponseEntity<InvitationOrganisation> = ResponseEntity(HttpStatus.NOT_IMPLEMENTED)

    @PutMapping("/invitation/{id}")
    fun majInvitation(@PathVariable id: String, @RequestBody reponse: String):
            ResponseEntity<InvitationOrganisation> = ResponseEntity(HttpStatus.NOT_IMPLEMENTED)

    @DeleteMapping("/invitation/{id}")
    fun supprimerInvitation(@PathVariable id: String):
            ResponseEntity<InvitationOrganisation> = ResponseEntity(HttpStatus.NOT_IMPLEMENTED)



    @PostMapping("/jetons/{utilisateur_id}")
    fun saisirJeton(@PathVariable utilisateur_id : Int, @RequestBody jeton : InvitationÉvénement):
            ResponseEntity<InvitationOrganisation> = ResponseEntity(HttpStatus.NOT_IMPLEMENTED)

    @GetMapping("/jetons/{id}/{quantité}")
    fun générerJeton(@PathVariable id : Int,  @PathVariable quantité : Int):
            ResponseEntity<InvitationOrganisation> = ResponseEntity(HttpStatus.NOT_IMPLEMENTED)

     */


}

