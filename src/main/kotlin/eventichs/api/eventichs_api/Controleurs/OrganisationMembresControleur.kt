package eventichs.api.eventichs_api.Controleurs

import eventichs.api.eventichs_api.Exceptions.ConflitAvecUneRessourceExistanteException
import eventichs.api.eventichs_api.Exceptions.PasConnectéException
import eventichs.api.eventichs_api.Exceptions.RessourceInexistanteException
import eventichs.api.eventichs_api.Modèle.Organisation
import eventichs.api.eventichs_api.Modèle.OrganisationMembres
import eventichs.api.eventichs_api.Services.OrganisationMembresService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.security.Principal

@RestController
@RequestMapping("\${api.base-path:}")
class OrganisationMembresControleur(val service: OrganisationMembresService) {

    // PAS D'ADMIN :(
    //@Operation(
        //summary = "Obtenir les membres des organisations.",
        //description = "Retourne une liste des membres des organisations",
        //operationId = "obtenirOrganisationsMembres"
    //)
    //@GetMapping("/organisationsMembres")
    //fun obtenirOrganisationsMembres() = service.chercherTous()

    @Operation(
        summary = "Obtenir les organisations qu'un partipant est membre à",
        description = "Retourne une liste d'organisations selon l'id du participant",
        operationId = "obtenirOrganisationsParticipantParID",
        responses = [
            ApiResponse(responseCode = "200", description = "Les organisation selon id du participant sont trouvés"),
            ApiResponse(responseCode = "404", description = "Ce participant n'existe pas dans le système")]
    )
    @GetMapping(
            value = ["/utilisateurs/organisations"],
        produces = ["application/json"])
    fun obtenirOrganisationsParticipantParID(principal: Principal?): List<OrganisationMembres> {
        if (principal == null) { throw PasConnectéException("L'utilisateur n'est pas connecté.") }
        return service.chercherParParticipantID(principal.name)
    }

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
    fun obtenirParticipantDansOrganisationParID(@PathVariable codeOrganisation: Int, principal: Principal?): List<OrganisationMembres> {
        if (principal == null) { throw PasConnectéException("L'utilisateur n'est pas connecté.") }
        return service.chercherParOrganisationID(codeOrganisation, principal.name)
    }


    @Operation(
        summary = "Ajouter un participant à une organisation",
        description = "Retourne un status que ce participant à été ajouté",
        operationId = "ajouterParticipant",
        responses = [
            ApiResponse(responseCode = "201", description = "Le participant est ajouté"),
            ApiResponse(responseCode = "404", description = "Le participant n'existe pas"),
            ApiResponse(responseCode = "409", description = "Le participant est déja dans l'organisation")]
    )
    @PostMapping(value = ["/organisations/participants"])
    fun ajouterParticipant(@RequestBody uneOrganisationMembres: OrganisationMembres, principal: Principal?): ResponseEntity<OrganisationMembres> {
        if (principal == null) { throw PasConnectéException("L'utilisateur n'est pas connecté.") }

        val organisationMembres = service.ajouterParticipant(uneOrganisationMembres, principal.name)
        if (organisationMembres != null) {
            println(organisationMembres)
            val uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/organisations/{codeOrganisation}/participants/{codeUtilisateur}")
                .buildAndExpand(organisationMembres.id_organisation, organisationMembres.code_utilisateur)
                .toUri()

            return ResponseEntity.created(uri).body(organisationMembres)
        }
        return throw ConflitAvecUneRessourceExistanteException("Cet utilisateur dont l'id est ${uneOrganisationMembres.code_utilisateur} est déjà membre de cette organisation dont l'id est ${uneOrganisationMembres.id_organisation}")
    }


    @Operation(
        summary = "Enlever un participant à une organisation",
        description = "Retourne un status que ce participant à été enlevé",
        operationId = "enleverParticipant",
        responses = [
            ApiResponse(responseCode = "200", description = "Le participant est ajouté"),
            ApiResponse(responseCode = "404", description = "Le participant n'existe pas"),
            ApiResponse(responseCode = "409", description = "Le participant n'est pas dans l'organisation")]
    )
    @DeleteMapping("/organisations/participants")
    fun enleverParticipant(@RequestBody organisationMembres: OrganisationMembres, principal: Principal?) {
        if (principal == null) { throw PasConnectéException("L'utilisateur n'est pas connecté.") }
        service.enleverParticipant(organisationMembres.id_organisation, organisationMembres.code_utilisateur, principal.name)
    }
}