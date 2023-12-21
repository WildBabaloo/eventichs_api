package eventichs.api.eventichs_api.Controleurs
import eventichs.api.eventichs_api.Exceptions.PasConnectéException
import eventichs.api.eventichs_api.Modèle.Événement
import eventichs.api.eventichs_api.Services.EvenementService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.security.Principal

@RequestMapping("\${api.base-path:}")
@RestController
@Tag(
    name = "Événement",
    description = "Points d'accès aux ressources liées aux événements éxistants sur le service."
)
class EvenementControleur(val service : EvenementService) {
    @Operation(
        summary = "Obtenir la liste des évenements.",
        description = "Retourne la liste de tous les évènements dans le service.",
        operationId = "obtenirEvenements",
        responses = [
            ApiResponse(responseCode = "201", description = "Événements trouvés"),
            ApiResponse(responseCode = "401", description = "L'utilisateur n'est pas connecté"),
            ApiResponse(responseCode = "403", description = "L'utilisateur n'a pas le droit de consulter cet événement")]
    )
    @GetMapping("/evenements")
    fun obtenirEvenements(principal: Principal?) : List<Événement>{
        if (principal == null) {
            throw PasConnectéException("L'utilisateur n'est pas connecté.")
        }
        return service.chercherTous(principal.name)
    }

    @Operation(
        summary = "Obtenir un évenement spécifique par son ID.",
        description = "Retourne l'évènement qui porte l'ID spécifié dans tous les évènements du service",
        operationId = "obtenirEvenementParID",
        responses = [
            ApiResponse(responseCode = "201", description = "Événement trouvé"),
            ApiResponse(responseCode = "401", description = "L'utilisateur n'est pas connecté"),
            ApiResponse(responseCode = "403", description = "L'utilisateur n'a pas le droit de consulter cet événement"),
            ApiResponse(responseCode = "404", description = "L'événement n'existe pas dans le service")]
    )
    @GetMapping("/evenements/{id}")
    fun obtenirEvenementParId(@PathVariable id: Int, principal: Principal?) : Événement?{
        if (principal == null) {
            throw PasConnectéException("L'utilisateur n'est pas connecté.")
        }
        return  service.chercherParID(id, principal.name)
    }

    @Operation(
            summary = "Obtenir une liste d'évenements par l'id de leur organisation.",
            description = "Retourne l'évènement qui porte l'ID spécifié dans tous les évènements du service",
            operationId = "obtenirEvenementParID",
            responses = [
                ApiResponse(responseCode = "201", description = "Événement trouvé"),
                ApiResponse(responseCode = "401", description = "L'utilisateur n'est pas connecté"),
                ApiResponse(responseCode = "403", description = "L'utilisateur n'a pas le droit de consulter cet événement"),
                ApiResponse(responseCode = "404", description = "L'organisation n'existe pas dans le service")]
    )
    @GetMapping("/organisations/{id}/evenements")
    fun obtenirEvenementParOrganisation(@PathVariable id: Int, principal: Principal?) : List<Événement> {
        if (principal == null) {
            throw PasConnectéException("L'utilisateur n'est pas connecté.")
        }
        return service.chercherParOrganisation(id, principal.name)
    }

    @Operation(
            summary = "Supprimer un évenement spécifique par son ID.",
            description = "Supprime et retourne l'évènement qui porte l'ID spécifié dans tous les évènements du service",
            operationId = "supprimerEvenement",
            responses = [
                ApiResponse(responseCode = "200", description = "L'événement a été effacé"),
                ApiResponse(responseCode = "401", description = "L'utilisateur n'est pas connecté"),
                ApiResponse(responseCode = "403", description = "L'utilisateur n'a pas le droit d'effacer cet événement"),
                ApiResponse(responseCode = "404", description = "L'événement n'existe pas")]
    )
    @DeleteMapping("/evenements/{id}")
    fun supprimerEvenement(@PathVariable id: Int, principal: Principal?) : Événement? {
        if (principal == null) {
            throw PasConnectéException("L'utilisateur n'est pas connecté.")
        }
        return service.supprimerParID(id)
    }

    @Operation(
        summary = "Modifier un évènement existant.",
        description = "Modifie et retourne l'évènement spécifié dans la base de données",
        operationId = "modifierEvenement",
        responses = [
            ApiResponse(responseCode = "200", description = "L'événement a été modifié"),
            ApiResponse(responseCode = "401", description = "L'utilisateur n'est pas connecté"),
            ApiResponse(responseCode = "403", description = "L'utilisateur n'a pas le droit de modifier cet événement"),
            ApiResponse(responseCode = "404", description = "L'événement ou l'organisation n'existent pas")]
    )
    @PutMapping("/evenements/{id}")
    fun modifierEvenement(@PathVariable id: Int, @RequestBody evenement : Événement, principal: Principal?) : ResponseEntity<Événement> {
        if (principal == null) {
            throw PasConnectéException("L'utilisateur n'est pas connecté.")
        }
        val événementModifié : Événement? = service.modifierEvenement(id, evenement, principal)
            val uri = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/evenements/{id}")
                    .buildAndExpand(evenement.id)
                    .toUri()
        return ResponseEntity.created(uri).body(événementModifié)
    }

    @Operation(
    summary = "Ajouter un nouvel évènement.",
    description = "Crée et ajoute un nouvel évènement dans la base de données",
    operationId = "ajouterEvenement",
    responses = [
        ApiResponse(responseCode = "200", description = "L'événement a été modifié"),
        ApiResponse(responseCode = "401", description = "L'utilisateur n'est pas connecté"),
        ApiResponse(responseCode = "403", description = "L'utilisateur n'a pas le droit de modifier cet événement"),
        ApiResponse(responseCode = "404", description = "L'événement ou l'organisation n'existent pas")]
    )
    @PostMapping("/evenements")
    fun ajouterEvenement(@RequestBody evenement : Événement, principal: Principal?) : ResponseEntity<Événement>{
        if (principal == null) {
            throw PasConnectéException("L'utilisateur n'est pas connecté.")
        }
        val nouvelEvenement : Événement? = service.ajouterEvenement(evenement, principal)
        val uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/evenements/{id}")
                .buildAndExpand(evenement.id)
                .toUri()
        return ResponseEntity.created(uri).body(nouvelEvenement)
    }
}