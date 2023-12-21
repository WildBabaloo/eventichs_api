package eventichs.api.eventichs_api.Controleurs

import eventichs.api.eventichs_api.Exceptions.PasConnectéException
import eventichs.api.eventichs_api.Exceptions.RessourceInexistanteException
import eventichs.api.eventichs_api.Modèle.InvitationÉvénement
import eventichs.api.eventichs_api.Services.InvitationÉvénementService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController
@RequestMapping("\${api.base-path:}")
@Tag(
    name = "InvitationÉvénement",
    description = "Points d'accès aux ressources liées aux invitations des événements éxistants sur le service."
)
class InvitationÉvénementControleur(val service: InvitationÉvénementService) {

    //Consulter ses invitations(Participant+Organisation)
    @Operation(
        summary = "Obtenir la liste des invitations destinés à l'id de l'utilisateur indiqué",
        description = "Retourne la liste de toutes les invitations dont l'id du destinataire est égal à celui entré dans la requête.",
        operationId = "obtenirInvitationsÉvénementsParIdDestinataire",
        responses = [
            ApiResponse(responseCode = "200", description = "Une ou plusieures invitations ont été trouvées"),
            ApiResponse(responseCode = "404", description = "Aucune invitation trouvée")]
    )
    @GetMapping("/utilisateur/invitations/destinataire/{codeDestinataire}")
    fun obtenirInvitationsÉvénementsParIdDestinataire(@PathVariable codeDestinataire: String,  principal: Principal?): List<InvitationÉvénement> {
        if (principal == null) {
            throw PasConnectéException("l'utilisateur n'est pas connecté.")
        }
        val invitations = service.chercherInvitationsÉvénementsParIdDestinataire(codeDestinataire, principal.name)

        if (invitations.isNotEmpty()) {
            return invitations
        } else {
            throw RessourceInexistanteException("Aucune invitation trouvée")
        }
    }
    @Operation(
        summary = "Obtenir la liste des invitations créés par l'id de l'éxpéditeur indiqué",
        description = "Retourne la liste de toutes les invitations dont l'id de l'éxpéditeur est égal à celui entré dans la reqête.",
        operationId = "obtenirInvitationsÉvénementsParIdExpediteur",
        responses = [
            ApiResponse(responseCode = "200", description = "Une ou plusieures invitations ont été trouvées"),
            ApiResponse(responseCode = "404", description = "Aucune invitation trouvée")]
    )
    @GetMapping("/utilisateur/invitations/expediteur/{idExpediteur}")
    fun obtenirInvitationsÉvénementsParIdExpediteur(@PathVariable idExpediteur: String, principal: Principal?): List<InvitationÉvénement> {
        if (principal == null) {
            throw PasConnectéException("L'utilisateur n'est pas connecté.")
        }

        val invitations = service.chercherInvitationsÉvénementsParIdExpediteur(idExpediteur, principal.name)

        if (invitations.isNotEmpty()){
            return invitations
        } else {
            throw RessourceInexistanteException("Aucune invitation trouvée")
        }
    }


    @Operation(
        summary = "chercher une invitation par l'id",
        description = "Retourne l'invitation dont l'id est égal à celui entré dans la reqête.",
        operationId = "obtenirInvitationsÉvénementsParIdExpediteur",
        responses = [
            ApiResponse(responseCode = "200", description = "L'invitation a été trouvé"),
            ApiResponse(responseCode = "404", description = "Aucune invitation trouvé")]
    )
    @GetMapping("/utilisateur/invitations/{id}")
    fun obtenirInvitationÉvénementParId(@PathVariable id: Int) =
        service.chercherInvitationÉvénementParId(id)

    //Inviter un autre participant à un événement publique (Participant)

    @Operation(
        summary = "Creer une invitation à un événement",
        description = "Retourne l'invitation créée, sans jeton.",
        operationId = "créerInvitationÉvénement",
        responses = [
            ApiResponse(responseCode = "200", description = "L'invitation a été créé"),
            ApiResponse(responseCode = "409", description = "Une invitation identique existe déja."),
            ApiResponse(responseCode = "500", description = "Impossible de creer l'invitation, erreur du serveur.")]

    )
    @PostMapping("/invitation")
    fun créerInvitationÉvénement(@RequestBody invitation: InvitationÉvénement) =
        service.créerInvitationÉvénement(invitation)


    @Operation(
        summary = "Modifier les informations d'une invitation.",
        description = "Retourne l'invitation modifiée. Ne devrait être utilisée que pour la modification du statut, mais on sait jamais.",
        operationId = "majInvitation",
        responses = [
            ApiResponse(responseCode = "200", description = "L'invitation existe et a ete modifiee."),
            ApiResponse(responseCode = "409", description = "L'événement à modifier n'existe pas."),
            ApiResponse(responseCode = "500", description = "Impossible de modifier l'invitation, erreur du serveur.")]

    )
    @PutMapping("/invitation/{id}")
    fun majInvitation(@PathVariable id: Int, @RequestBody invitation: InvitationÉvénement, reponse: String) =
        service.modifierInvitationÉvénement(invitation)

    //Éffacer une invitation (Participant + Organisation)

    @Operation(
        summary = "Supprime une invitation.",
        description = "Retourne l'invitation supprimée si elle existe.",
        operationId = "supprimerInvitation",
        responses = [
            ApiResponse(responseCode = "200", description = "L'invitation existe et a ete supprimée."),
            ApiResponse(responseCode = "409", description = "L'événement à supprimer n'existe pas."),
            ApiResponse(responseCode = "500", description = "Impossible de supprimer l'invitation, erreur du serveur.")]

    )
    @DeleteMapping("/invitation/{id}")
    fun supprimerInvitation(@PathVariable id: Int) =
        service.supprimerInvitationsÉvénementsParId(id)


    @Operation(
        summary = "Accepter une invitation via jeton.",
        description = "Retourne l'invitation référée par le jeton, et la modifie pour l'accepter.",
        operationId = "saisirJeton",
        responses = [
            ApiResponse(responseCode = "200", description = "Le jeton est valide et l'invitation est acceptée."),
            ApiResponse(responseCode = "409", description = "Le jeton est invalide."),
            ApiResponse(responseCode = "500", description = "Impossible de traiter le jeton, erreur du serveur.")]

    )
    @PostMapping("/jetons/{utilisateur_id}")
    fun saisirJeton(@PathVariable utilisateur_id : Int, @RequestBody jeton : String) =
        service.entrerJetonEvenement(utilisateur_id, jeton)

    @Operation(
        summary = "Générer des jetons pour un événement.",
        description = "Retourne les invitations créées pour joindre l'événement via jeton.",
        operationId = "générerJeton",
        responses = [
            ApiResponse(responseCode = "200", description = "Le jeton est valide et l'invitation est acceptée."),
            ApiResponse(responseCode = "409", description = "Le jeton est invalide."),
            ApiResponse(responseCode = "500", description = "Impossible de traiter le jeton, erreur du serveur.")]
    )
    @GetMapping("/invitation/jetons/{id}/{quantité}")
    fun générerJeton(@PathVariable id : Int,  @PathVariable quantité : Int) =
        service.genererJetonsEvenement(id)
}
