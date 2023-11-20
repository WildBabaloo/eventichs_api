package eventichs.api.eventichs_api.Controleurs

import eventichs.api.eventichs_api.Modèle.UtilisateurÉvénement
import eventichs.api.eventichs_api.Modèle.Événement
import eventichs.api.eventichs_api.Services.UtilisateurEvenementService
import org.springframework.web.bind.annotation.*

@RestController
class UtilisateurEvenementControleur(val service : UtilisateurEvenementService) {
    @GetMapping("/utilisateursevenements")
    fun obtenirUtilisateursEvenements() = service.chercherTous()
    @GetMapping("/utilisateurs/evenements/{id}")
    fun obtenirUtilisateurEvenementParUtilisateurId(@PathVariable id: Int) = service.chercherParUtilisateur(id)
    @GetMapping("/evenements/participants/{id}")
    fun obtenirUtilisateurEvenementParEvenementId(@PathVariable id: Int) = service.chercherParEvenement(id)
    @DeleteMapping("/evenements/participants{id}")
    fun supprimerUtilisateurEvenementParUtilisateurId(@PathVariable id: Int) = service.supprimerParUtilisateur(id)

    @DeleteMapping("/utilisateurs/evenements/{id}")
    fun supprimerUtilisateurEvenementParEvenementId(@PathVariable id: Int) = service.supprimerParEvenement(id)

    @PostMapping("/utilisateursevenements")
    fun rejoindreEvenement(@RequestBody utilisateurÉvénement : UtilisateurÉvénement) = service.ajouter(utilisateurÉvénement)
}