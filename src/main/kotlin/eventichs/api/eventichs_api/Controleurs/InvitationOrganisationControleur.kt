package eventichs.api.eventichs_api.Controleurs

import eventichs.api.eventichs_api.Exceptions.RessourceInexistanteException
import eventichs.api.eventichs_api.Modèle.InvitationOrganisation
import eventichs.api.eventichs_api.Modèle.Organisation
import eventichs.api.eventichs_api.Modèle.Utilisateur
import eventichs.api.eventichs_api.Services.InvitationOrganisationService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
@RequestMapping("\${api.base-path:}")
@Tag(
    name = "InvitationOrganisation",
    description = "Points d'accès aux ressources liées aux invitations des organisations inscrites au service."
)
class InvitationOrganisationControleur(val service: InvitationOrganisationService) {

    @Operation(
        summary = "Obtenir la liste des invitations aux organisation.",
        description = "Retourne la liste de toutes les invitations à des organisation inscrites dans le service.",
        operationId = "obtenirInvitationOrganisation"
    )
    @GetMapping("/organisations/invitations")
    fun obtenirInvitationOrganisation() = service.chercherTous()

    @Operation(
        summary = "Obtenir une invitation à une organisation selon son id.",
        description = "Retourne une invitation à une organisation selon son id inscrites dans le service.",
        operationId = "obtenirInvitationsParId",
        responses = [
            ApiResponse(responseCode = "200", description = "L'invitation a été trouvé"),
            ApiResponse(responseCode = "404", description = "L'invitation recherché n'existe pas dans le service")]
    )
    @GetMapping(
        value = ["/organisations/invitations/{id}"],
        produces = ["application/json"])
    fun obtenirInvitationsParId(@PathVariable id: Int) = service.chercherParID(id) ?: throw RessourceInexistanteException(" L'invitation à une organisation $id n'est pas inscrit au service ")

    @Operation(
        summary = "Créer une invitation pour joindre une organisation pour un participant.",
        description = "Retourne l'invitation créée pour joindre une organisation pour ce participant avec un jeton null et un status 'envoyé'.",
        operationId = "demandeJoindreOrganisation",
        responses = [
            ApiResponse(responseCode = "200", description = "L'invitation a été créé"),
            ApiResponse(responseCode = "409", description = "Une invitation pour cette organisation et ce participant existe déjà dans le service")]
    )
    @PostMapping(
        value = ["/organisations/{idOrganisation}/invitations/{idParticipant}"])
    //Cas d'utilisation: 1.Demander à joindre une organisation (Participant)
    fun demandeJoindreOrganisation(@PathVariable idOrganisation: Int, @PathVariable idParticipant: Int) : ResponseEntity<InvitationOrganisation>{
        val nouvelleInvitation : InvitationOrganisation? = service.demandeJoindreOrganisation( idOrganisation, idParticipant)
        if (nouvelleInvitation != null) {
            val uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/organisations/invitations/{id}")
                .buildAndExpand(nouvelleInvitation.id)
                .toUri()

            return ResponseEntity.created(uri).body(nouvelleInvitation)
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build()
    }

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
    //Cas d'utilisation: 3.Consulter ses invitations(Organisation)
    fun obtenirInvitationOrganisation(@PathVariable idOrganisation: Int) : List<InvitationOrganisation> = service.chercherParOrganisation(idOrganisation)

    @Operation(
        summary = "Consulter ses invitations en tant que participant.",
        description = "Retourne la liste de toutes les invitations inscrites dans le service pour un participant selon son id.",
        operationId = "obtenirInvitationParticipant",
        responses = [
            ApiResponse(responseCode = "200", description = "Les invitations de ce participant ont été trouvé"),
            ApiResponse(responseCode = "404", description = "Ce participant n'existe pas")]
    )
    @GetMapping(
        value = ["/utilisateurs/{idParticipant}/invitations"],
        produces = ["application/json"])
    //Cas d'utilisation: 3.Consulter ses invitations(Participant)
    fun obtenirInvitationParticipant(@PathVariable idParticipant: Int) = service.chercherParParticipant(idParticipant)

    @Operation(
        summary = "Changer le status d'une invitation.",
        description = "Retourne l'invitation inscrite dans le service avec le status modifié à 'accepté' ou 'refusé'.",
        operationId = "changerStatus",
        responses = [
            ApiResponse(responseCode = "200", description = "Le status de l'invitation a été modifié"),
            ApiResponse(responseCode = "404", description = "L'invitation à modifier n'existe pas dans le service")]
    )
    @PutMapping(
        value = ["/organisations/invitations/{id}/status/{status}"])
    //Cas d'utilisation: 4.Accepter la demande de joindre l'organisation par le participant (Organisation)
    fun changerStatus(@PathVariable id: Int, @PathVariable status : String) = service.changerStatus(id, status)


    @Operation(
        summary = "Saisir un jeton en tant que participant.",
        description = "Retourne l'invitation qui contient ce jeton modifié dans le service ayant maintenant un destinataire et un status 'accepté'.",
        operationId = "saisirJeton",
        responses = [
            ApiResponse(responseCode = "200", description = "L'invitation qui contient ce jeton à été attribué au participant et enregistré comme 'accepté'"),
            ApiResponse(responseCode = "404", description = "Aucune invitation existe contenant ce jeton")]
    )
    @PutMapping(
        value = ["/organisations/jetons/{jeton}/{idUtilisateur}"])
    //Cas d'utilisation: 5.Entrer un jeton d'invitation (Participant)
    fun saisirJeton(@PathVariable jeton : String, @PathVariable idUtilisateur : Int) = service.saisirJeton(jeton, idUtilisateur)

    @Operation(
        summary = "Créer une invitation pour une organisation.",
        description = "Retourne l'invitation créée pour joindre une organisation avec un idParticipant null, un jeton alléatoire et un status 'généré'.",
        operationId = "crééJeton",
        responses = [
            ApiResponse(responseCode = "200", description = "L'invitation qui contient un jeton a été créé"),
            ApiResponse(responseCode = "500", description = "L'invitation qui contient un jeton n'a pas été créé, erreur du serveur")]
    )
    @PostMapping(
        value = ["/organisations/{idOrganisation}/jetons"])
    //Cas d'utilisation: 6.Générer son jeton d'invitation (Organisation)
    fun crééJeton(@PathVariable idOrganisation : Int) = service.crééJeton(idOrganisation)

    @Operation(
        summary = "Effacer une invitation.",
        description = "Retourne l'invitation effacé du service.",
        operationId = "effacerInvitation",
        responses = [
            ApiResponse(responseCode = "200", description = "L'invitation a été effacé"),
            ApiResponse(responseCode = "404", description = "L'invitation n'existe pas")]
    )
    @DeleteMapping(
        value = ["/organisations/invitations/{idInvitationOrganisation}"])
    //Cas d'utilisation: 7.Éffacer une invitation (Participant + Organisation)
    fun effacerInvitation(@PathVariable idInvitationOrganisation: Int) = service.effacerInvitation(idInvitationOrganisation)

}