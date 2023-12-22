package eventichs.api.eventichs_api.Controleurs

import eventichs.api.eventichs_api.Exceptions.PasConnectéException
import eventichs.api.eventichs_api.Modèle.Participant
import eventichs.api.eventichs_api.Modèle.UtilisateurÉvénement
import eventichs.api.eventichs_api.Modèle.nombre
import eventichs.api.eventichs_api.Modèle.Événement
import eventichs.api.eventichs_api.Services.UtilisateurEvenementService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController
class UtilisateurEvenementControleur(val service : UtilisateurEvenementService) {

    @Operation(
        summary = "Obtenir la liste d'événements/utilisateur.",
        description = "Retourne la table jointe Utilisateur et Événement, contenant le code de chaque utilisateur et l'événement associé",
        operationId = "getUtilisateursEvenements",
        responses = [
            ApiResponse(responseCode = "200", description = "Les objets UtilisateurEvenement ont été trouvés"),
            ApiResponse(responseCode = "401", description = "L'utilisateur n'est pas connecté"),
            ApiResponse(responseCode = "403", description = "L'utilisateur n'a pas le droit de consulter cette page"),
            ApiResponse(responseCode = "404", description = "La table n'existe pas")]
    )
    @GetMapping("/utilisateursevenements")
    fun obtenirUtilisateursEvenements(principal: Principal?) : List<UtilisateurÉvénement>{
        if (principal == null) {
            throw PasConnectéException("L'utilisateur n'est pas connecté.")
        }
        return service.chercherTous(principal.name)
    }

    @Operation(
            summary = "Obtenir la liste d'événements par l'utilisateur.",
            description = "Retourne tous les événements auquel l'utilisateur s'est inscrit",
            operationId = "getEvenementsParUtilisateur",
            responses = [
                ApiResponse(responseCode = "200", description = "Les événements ont été trouvés"),
                ApiResponse(responseCode = "401", description = "L'utilisateur n'est pas connecté"),
                ApiResponse(responseCode = "403", description = "L'utilisateur n'a pas le droit de consulter cette page"),
                ApiResponse(responseCode = "404", description = "L'utilisateur n'existe pas dans le service")]
    )
    @GetMapping("/utilisateurs/evenements")
    fun obtenirListeEvenementsParUtilisateurId(principal: Principal?) : List<Événement>{
        if (principal == null) {
            throw PasConnectéException("L'utilisateur n'est pas connecté.")
        }
        return service.chercherEvenementsParUtilisateur(principal.name)
}
    @Operation(
            summary = "Obtenir la liste d'utilisateurs par événement.",
            description = "Retourne tous les utilisateurs participant à l'événement fournis",
            operationId = "getUtilisateursParEvenements",
            responses = [
                ApiResponse(responseCode = "200", description = "Les utilisateurs ont été trouvés"),
                ApiResponse(responseCode = "401", description = "L'utilisateur n'est pas connecté"),
                ApiResponse(responseCode = "403", description = "L'utilisateur n'a pas le droit de consulter cette page"),
                ApiResponse(responseCode = "404", description = "L'événement n'existe pas dans le service")]
    )
    @GetMapping("/evenements/{id}/utilisateurs")
    fun obtenirUtilisateurEvenementParEvenementId(@PathVariable id: Int, principal: Principal?) : List<Participant>{
        if (principal == null) {
            throw PasConnectéException("L'utilisateur n'est pas connecté.")
        }
        return service.chercherUtilisateursParEvenement(id,principal.name)
}
    @Operation(
            summary = "Obtenir le nombre de participants d'un événements.",
            description = "Retourne le nombre de participants d'un événement fournis",
            operationId = "getNbParticipants",
            responses = [
                ApiResponse(responseCode = "200", description = "Les participants ont été trouvés"),
                ApiResponse(responseCode = "401", description = "L'utilisateur n'est pas connecté"),
                ApiResponse(responseCode = "403", description = "L'utilisateur n'a pas le droit de consulter cette page"),
                ApiResponse(responseCode = "404", description = "L'événement n'existe pas dans le service")]
    )
    @GetMapping("/evenements/{id}/participants")
    fun obtenirNombreParticipantsParEvenementId(@PathVariable id: Int, principal: Principal?): nombre {
        if (principal == null)  {
            throw PasConnectéException("L'utilisateur n'est pas connecté.")
        }
        return nombre(service.chercherUtilisateursParEvenement(id,principal.name).size)}

    @Operation(
            summary = "Quitter un événement selon le code d'utilisateur.",
            description = "Permet l'utilisateur de faire une requete DELETE afin de quitter un événement",
            operationId = "quitterEvenement",
            responses = [
                ApiResponse(responseCode = "200", description = "L'événement a été quitté"),
                ApiResponse(responseCode = "401", description = "L'utilisateur n'est pas connecté"),
                ApiResponse(responseCode = "403", description = "L'utilisateur n'a pas d'effectuer cette action"),
                ApiResponse(responseCode = "404", description = "L'utilisateur ne fait pas partie de cet événement")]
    )
    @DeleteMapping("/evenement/{eventId}/quitter")
    fun quitterÉvénement(@PathVariable eventId : Int , principal: Principal?) {
        if (principal == null) {
            throw PasConnectéException("L'utilisateur n'est pas connecté.")
        }
        service.supprimerParUtilisateur(eventId,principal.name)
}
    @Operation(
            summary = "Joint un utilisateur à un événement.",
            description = "Permet l'utilisateur de faire une requete POST afin de rejoindre un événement",
            operationId = "rejoindreEvenement",
            responses = [
                ApiResponse(responseCode = "200", description = "L'utilisateur a rejoint l'événement"),
                ApiResponse(responseCode = "401", description = "L'utilisateur n'est pas connecté"),
                ApiResponse(responseCode = "403", description = "L'utilisateur n'a pas d'effectuer cette action"),
                ApiResponse(responseCode = "404", description = "L'événement n'existe pas")]
    )
    @PostMapping("/evenements/{eventId}/rejoindre")
    fun rejoindreEvenement(@PathVariable eventId : Int , principal: Principal?) : UtilisateurÉvénement?{
        if (principal == null) {
            throw PasConnectéException("L'utilisateur n'est pas connecté.")
        }
        return service.ajouter(eventId, principal.name)
    }
}