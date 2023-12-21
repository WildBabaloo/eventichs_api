package eventichs.api.eventichs_api.Controleurs

import eventichs.api.eventichs_api.Modèle.Organisation
import eventichs.api.eventichs_api.Services.OrganisationService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*
@RestController
@RequestMapping("\${api.base-path:}")
@Tag(
    name = "Organisation",
    description = "Points d'accès aux ressources liées aux organisations inscrits au service"
)
class OrganisationControleur(val service: OrganisationService) {

    @Operation(
        summary = "Obtenir la liste des organisations",
        description = "Retourne la liste de toutes les organisations inscrites au service",
        operationId = "obtenirOrganisations"
    )
    @GetMapping("/organisations")
    fun obtenirOrganisations() = service.chercherTous()

    @Operation(
        summary = "Obtenir la liste des organisations par ID",
        description = "Retourne la liste de l'organisation inscrit au service selon l'ID donné",
        operationId = "obtenirOrgasationsParID",
        responses = [
            ApiResponse(responseCode = "200", description = "L'organisation à été trouvé"),
            ApiResponse(responseCode = "404", description = "L'organisation avec l'id recherché n'existe pas dans le service")
        ]
    )
    @GetMapping("/organisations/{id}")
    fun obtenirOrganisationParID(@PathVariable id: Int) = service.chercherParID(id)

    @Operation(
        summary = "Créer une organisation",
        description = "Retourne l'organisation créée",
        operationId = "ajouterOrganisation",
        responses = [
            ApiResponse(responseCode = "201", description = "L'organisation à été ajoutée"),
            ApiResponse(responseCode = "500", description = "L'organisation n'a pas été ajoutée au service")
        ]
    )
    @PostMapping("/organisations")
    fun ajouterOrganisation(@RequestBody organisation: Organisation) = service.ajouter(organisation)
    @Operation(
        summary = "Modifier une organisation ",
        description = "Modifie les attributs d'une organisation qui existe dans la liste des organisations",
        operationId = "modifierOrganisation",
        responses = [
            ApiResponse(responseCode = "200", description = "l'organisation a été modifié"),
            ApiResponse(responseCode = "404", description = "l'organisation n'eas pas été modifié")]
    )
    @PutMapping("/organisations/{id}")
    fun modifierOrganisation(@RequestBody organisation: Organisation) = service.modifier(organisation)
    @Operation(
        summary = "supprimer une organisation ",
        description = "Supprime une organisation qui existe dans la liste des organisations",
        operationId = "supprimerOrganisation",
        responses = [
            ApiResponse(responseCode = "200", description = "l'organisation a été supprimé"),
            ApiResponse(responseCode = "404", description = "l'organisation n'as pas été supprimé")]
    )
    @DeleteMapping("/organisations/{id}")
    fun supprimerOrganisation(@PathVariable id: Int) = service.supprimerParID(id)
    @Operation(
        summary = "obtenir la listes des organisations publiques ",
        description = "Retourne la liste des organisations qui existe dont le status est publique",
        operationId = "ChercherOrganisationPublique",
        responses = [
            ApiResponse(responseCode = "200", description = "les organisations publiques ont été trouvé"),
            ApiResponse(responseCode = "404", description = "Aucune organisation publique n'as pas été trouvé")]
    )
    @GetMapping("/organisations/publiques")
    fun obtenirOrganisationsPubliques() = service.consulterOrganisationPubliques()
    @Operation(
        summary = "obtenir la listes des organisations par gouts ",
        description = "Retourne la liste des organisations filtés par gouts",
        operationId = "obtenirOrganisationsParGout",
        responses = [
            ApiResponse(responseCode = "200", description = "les organisations filtrés ont été trouvées"),
            ApiResponse(responseCode = "404", description = "les organisations filtrés n'ont pas été trouvé")]
    )
    @GetMapping("/gouts/{idCategorie}/organisations")
    fun obtenirOrganisationsParGout(@PathVariable idCategorie: Int) = service.filtrerOrganisationParGouts(idCategorie)

}