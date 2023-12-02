package eventichs.api.eventichs_api.Controleurs

import eventichs.api.eventichs_api.Exceptions.RessourceInexistanteException
import eventichs.api.eventichs_api.Modèle.Organisation
import eventichs.api.eventichs_api.Services.OrganisationMembresService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("\${api.base-path:}")
class OrganisationMembresControleur(val service: OrganisationMembresService) {

    @Operation(
        summary = "Obtenir les membres des organisations.",
        description = "Retourne une liste des membres des organisations",
        operationId = "obtenirOrganisationsMembres"
    )
    @GetMapping("/organisationsMembres")
    fun obtenirOrganisationsMembres() = service.chercherTous()

    @Operation(
        summary = "Obtenir les organisations qu'un partipant est membre à",
        description = "Retourne une liste d'organisations selon l'id du participant",
        operationId = "obtenirOrganisationsParticipantParID",
        responses = [
            ApiResponse(responseCode = "200", description = "Les organisation selon id du participant sont trouvés"),
            ApiResponse(responseCode = "404", description = "Ce participant n'existe pas dans le système")]
    )
    @GetMapping(
        value = ["/utilisateurs/{codeParticipant}/organisations"],
        produces = ["application/json"])
    fun obtenirOrganisationsParticipantParID(@PathVariable codeParticipant: Int) = service.chercherParParticipantID(codeParticipant)

    @Operation(
        summary = "Obtenir des participants à une organisation selon son id.",
        description = "Retourne une liste de participants",
        operationId = "obtenirParticipantDansOrganisationParID",
        responses = [
            ApiResponse(responseCode = "200", description = "Les participants sont trouvés"),
            ApiResponse(responseCode = "404", description = "L'organisation n'existe pas dans le système")]
    )
    @GetMapping(
        value = ["/organisations/{codeOrganisation}/participants"],
        produces = ["application/json"])
    fun obtenirParticipantDansOrganisationParID(@PathVariable codeOrganisation: Int) = service.chercherParOrganisationID(codeOrganisation)


    @Operation(
        summary = "Ajouter un participant à une organisation",
        description = "Retourne un status que ce participant à été ajouté",
        operationId = "ajouterParticipant",
        responses = [
            ApiResponse(responseCode = "200", description = "Le participant est ajouté"),
            ApiResponse(responseCode = "404", description = "Le participant n'existe pas"),
            ApiResponse(responseCode = "409", description = "Le participant est déja dans l'organisation")]
    )
    @PostMapping(value = ["/organisations/{codeOrganisation}/participants"])
    fun ajouterParticipant(@PathVariable codeOrganisation: Int, @RequestBody idParticpant: Int) = service.ajouterParticipant(codeOrganisation, idParticpant)


    @Operation(
        summary = "Enlever un participant à une organisation",
        description = "Retourne un status que ce participant à été enlevé",
        operationId = "enleverParticipant",
        responses = [
            ApiResponse(responseCode = "200", description = "Le participant est ajouté"),
            ApiResponse(responseCode = "404", description = "Le participant n'existe pas"),
            ApiResponse(responseCode = "409", description = "Le participant n'est pas dans l'organisation")]
    )
    @DeleteMapping("organisations/{codeOrganisation}/participants/{codeParticipant}")
    fun enleverParticipant(@PathVariable codeOrganisation: Int, @PathVariable codeParticipant: Int) = service.enleverParticipant(codeOrganisation, codeParticipant)
}