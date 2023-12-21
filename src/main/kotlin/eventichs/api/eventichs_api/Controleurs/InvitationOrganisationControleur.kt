package eventichs.api.eventichs_api.Controleurs

import eventichs.api.eventichs_api.Exceptions.ConflitAvecUneRessourceExistanteException
import eventichs.api.eventichs_api.Exceptions.PasConnectéException
import eventichs.api.eventichs_api.Exceptions.RessourceInexistanteException
import eventichs.api.eventichs_api.Modèle.InvitationOrganisation
import eventichs.api.eventichs_api.Modèle.Utilisateur
import eventichs.api.eventichs_api.Services.InvitationOrganisationService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.security.Principal

@RestController
@RequestMapping("\${api.base-path:}")
@Tag(
    name = "InvitationOrganisation",
    description = "Points d'accès aux ressources liées aux invitations des organisations inscrites au service."
)
class InvitationOrganisationControleur(val service: InvitationOrganisationService) {

    /* Utilisé pour tester ou pour un future compte admin.
    @Operation(
        summary = "Obtenir la liste des invitations aux organisation.",
        description = "Retourne la liste de toutes les invitations à des organisation inscrites dans le service.",
        operationId = "obtenirInvitationOrganisation"
    )
    @GetMapping("/organisations/invitations")
    fun obtenirInvitationOrganisation() = service.chercherTous()
    */

    @Operation(
        summary = "Obtenir une invitation à une organisation selon son id.",
        description = "Retourne une invitation à une organisation selon son id inscrites dans le service.",
        operationId = "obtenirInvitationsParId",
        responses = [
            ApiResponse(responseCode = "200", description = "L'invitation a été trouvé"),
            ApiResponse(responseCode = "401", description = "L'utilisateur n'est pas connecté"),
            ApiResponse(responseCode = "403", description = "L'utilisateur n'as pas le droit de consulter cet invitation"),
            ApiResponse(responseCode = "404", description = "L'invitation recherché n'existe pas dans le service")]
    )
    @GetMapping(
        value = ["/organisations/invitations/{id}"],
        produces = ["application/json"])
    fun obtenirInvitationsParId(@PathVariable id: Int, principal: Principal?) : InvitationOrganisation {
        if (principal == null) {
            throw PasConnectéException("L'utilisateur n'est pas connecté.")
        }
        return service.chercherParID(id, principal.name) ?: throw RessourceInexistanteException("L'invitation $id à une organisation n'est pas inscrit au service")
    }

    // ---------------------------------------------------------------------
    //
    // Cas d'utilisation: 1.Demander à joindre une organisation (Participant)
    //
    // ---------------------------------------------------------------------
    @Operation(
        summary = "Créer une invitation pour joindre une organisation pour un participant.",
        description = "Retourne l'invitation créée pour joindre une organisation pour ce participant avec un jeton null et un status 'envoyé'.",
        operationId = "demandeJoindreOrganisation",
        responses = [
            ApiResponse(responseCode = "201", description = "L'invitation a été créé"),
            ApiResponse(responseCode = "404", description = "Impossible de créer une invitation à cette organisation pour ce participant"),
            ApiResponse(responseCode = "409", description = "Une invitation pour cette organisation et ce participant existe déjà dans le service")]
    )
    @PostMapping(
        value = ["/organisations/invitations"])
    fun demandeJoindreOrganisation(@RequestBody invitation: InvitationOrganisation, principal: Principal) : ResponseEntity<InvitationOrganisation>{
        if (principal == null) {
            throw PasConnectéException("L'utilisateur n'est pas connecté.")
        }

        val nouvelleInvitation : InvitationOrganisation? =
            principal?.name?.let { service.demandeJoindreOrganisation(invitation, it) }
        if (nouvelleInvitation != null) {
            val uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/organisations/invitations/{id}")
                .buildAndExpand(nouvelleInvitation.id)
                .toUri()

            return ResponseEntity.created(uri).body(nouvelleInvitation)
        }
        return throw ConflitAvecUneRessourceExistanteException("Il y existe déjà une invitation à l'organisation ${invitation.Organisation.nomOrganisation} " +
                "assigné au participant ${invitation.Utilisateur?.prénom} ${invitation.Utilisateur?.nom} inscrit au service")
    }

    // ---------------------------------------------------------------------
    //
    // Cas d'utilisation: 3.Consulter ses invitations(Organisation)
    //
    // ---------------------------------------------------------------------
    @Operation(
        summary = "Consulter ses invitations en tant que organisation.",
        description = "Retourne la liste de toutes les invitations inscrites dans le service pour une organisation selon son id.",
        operationId = "obtenirInvitationOrganisation",
        responses = [
            ApiResponse(responseCode = "200", description = "Les invitations de cet organisation ont été trouvé"),
            ApiResponse(responseCode = "404", description = "Cette organisation n'existe pas")]
    )
    @GetMapping(
        value = ["/organisations/{idOrganisation}/invitations"],
        produces = ["application/json"])
    fun obtenirInvitationOrganisation(@PathVariable idOrganisation: Int, principal: Principal?) : List<InvitationOrganisation> = service.chercherParOrganisation(idOrganisation)

    // ---------------------------------------------------------------------
    //
    // Cas d'utilisation: 3.Consulter ses invitations(Participant)
    //
    // ---------------------------------------------------------------------
    @Operation(
        summary = "Consulter ses invitations en tant que participant.",
        description = "Retourne la liste de toutes les invitations inscrites dans le service pour un participant selon son id.",
        operationId = "obtenirInvitationParticipant",
        responses = [
            ApiResponse(responseCode = "200", description = "Les invitations de ce participant ont été trouvé"),
            ApiResponse(responseCode = "404", description = "Ce participant n'existe pas")]
    )
    @GetMapping(
        value = ["/utilisateurs/{idParticipant}/invitations/organisations"],
        produces = ["application/json"])
    fun obtenirInvitationParticipant(@PathVariable idParticipant: Int, principal: Principal?) : List<InvitationOrganisation> = service.chercherParParticipant(idParticipant)

    // ---------------------------------------------------------------------
    //
    // Cas d'utilisation: 4.Accepter la demande de joindre l'organisation par le participant (Organisation)
    //
    // ---------------------------------------------------------------------
    @Operation(
        summary = "Changer le status d'une invitation.",
        description = "Retourne l'invitation inscrite dans le service avec le status modifié à 'accepté' ou 'refusé'.",
        operationId = "changerStatus",
        responses = [
            ApiResponse(responseCode = "200", description = "Le status de l'invitation a été modifié"),
            ApiResponse(responseCode = "404", description = "L'invitation à modifier n'existe pas dans le service"),
            ApiResponse(responseCode = "409", description = "Le participant est déjà membre de l'organisation qu'il essaye de joindre")]
    )
    @PutMapping(
        value = ["/organisations/invitations/{id}/status/{status}"])
    fun changerStatus(@PathVariable id: Int, @PathVariable status : String, principal: Principal?) = service.changerStatus(id, status)

    // ---------------------------------------------------------------------
    //
    // Cas d'utilisation: 5.Entrer un jeton d'invitation (Participant)
    //
    // ---------------------------------------------------------------------
    @Operation(
        summary = "Saisir un jeton en tant que participant.",
        description = "Retourne l'invitation qui contient ce jeton modifié dans le service ayant maintenant un destinataire et un status 'accepté'.",
        operationId = "saisirJeton",
        responses = [
            ApiResponse(responseCode = "200", description = "L'invitation qui contient ce jeton à été attribué au participant et enregistré comme 'accepté'"),
            ApiResponse(responseCode = "404", description = "Impossible d'attribuer une invitation qui a ce jeton à ce participant"),
            ApiResponse(responseCode = "409", description = "Ce participant est déjà membre de cette organisation")]
    )
    @PutMapping(
        value = ["/organisations/jetons/{jeton}"])
    fun saisirJeton(@PathVariable jeton : String, @RequestBody utilisateur : Utilisateur, principal: Principal?) = service.saisirJeton(jeton, utilisateur)

    // ---------------------------------------------------------------------
    //
    // Cas d'utilisation: 6.Générer son jeton d'invitation (Organisation)
    //
    // ---------------------------------------------------------------------
    @Operation(
        summary = "Créer une invitation pour une organisation.",
        description = "Retourne l'invitation créée pour joindre une organisation avec un idParticipant null, un jeton alléatoire et un status 'généré'.",
        operationId = "crééJeton",
        responses = [
            ApiResponse(responseCode = "201", description = "L'invitation qui contient un jeton a été créé"),
            ApiResponse(responseCode = "404", description = "Cette organisation n'existe pas")]
    )
    @PostMapping(
        value = ["/organisations/{idOrganisation}/jetons"])
    fun crééJeton(@PathVariable idOrganisation : Int, principal: Principal?) : ResponseEntity<InvitationOrganisation>{
        val nouvelleInvitation : InvitationOrganisation? = service.crééJeton(idOrganisation)

        if (nouvelleInvitation != null) {
            val uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/organisations/invitations/{id}")
                .buildAndExpand(nouvelleInvitation.id)
                .toUri()

            return ResponseEntity.created(uri).body(nouvelleInvitation)
        }
        return ResponseEntity.internalServerError().build()
    }

    // ---------------------------------------------------------------------
    //
    // Cas d'utilisation: 7.Éffacer une invitation (Participant + Organisation)
    //
    // ---------------------------------------------------------------------
    @Operation(
        summary = "Effacer une invitation.",
        description = "Retourne l'invitation effacé du service.",
        operationId = "effacerInvitation",
        responses = [
            ApiResponse(responseCode = "200", description = "L'invitation a été effacé"),
            ApiResponse(responseCode = "404", description = "L'invitation n'existe pas")]
    )
    @DeleteMapping(
        value = ["/organisations/invitations/{id}"])
    fun effacerInvitation(@PathVariable id: Int, principal: Principal?) = service.effacerInvitation(id)
}