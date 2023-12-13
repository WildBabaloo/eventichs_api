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

    @PutMapping("/organisations/{id}")
    fun modifierOrganisation(@RequestBody organisation: Organisation) = service.modifier(organisation)

    @DeleteMapping("/organisations/{id}")
    fun supprimerOrganisation(@PathVariable id: Int) = service.supprimerParID(id)

    @GetMapping("/organisations/publiques")
    fun obtenirOrganisationsPubliques() = service.consulterOrganisationPubliques()

    @GetMapping("/gouts/{idCategorie}/organisations")
    fun obtenirOrganisationsParGout(@PathVariable idCategorie: Int) = service.filtrerOrganisationParGouts(idCategorie)

}