package eventichs.api.eventichs_api.Controleurs

import eventichs.api.eventichs_api.Modèle.UtilisateurÉvénement
import eventichs.api.eventichs_api.Modèle.nombre
import eventichs.api.eventichs_api.Modèle.Événement
import eventichs.api.eventichs_api.Services.UtilisateurEvenementService
import org.springframework.web.bind.annotation.*

@RestController
class
UtilisateurEvenementControleur(val service : UtilisateurEvenementService) {

    @GetMapping("/utilisateursevenements")
    fun obtenirUtilisateursEvenements() = service.chercherTous()
    @GetMapping("/utilisateurs/{id}/evenements")
    fun obtenirListeEvenementsParUtilisateurId(@PathVariable id: Int) = service.chercherEvenementsParUtilisateur(id)
    @GetMapping("/evenements/{id}/utilisateurs")
    fun obtenirUtilisateurEvenementParEvenementId(@PathVariable id: Int) = service.chercherUtilisateursParEvenement(id)
    @GetMapping("/evenements/{id}/participants")
    fun obtenirNombreParticipantsParEvenementId(@PathVariable id: Int) = nombre(service.chercherUtilisateursParEvenement(id).size)

    @DeleteMapping("/utilisateurevenements/utilisateur/{id}")
    fun supprimerUtilisateurEvenementParUtilisateurId(@PathVariable id: Int) =  service.supprimerParUtilisateur(id)

    @DeleteMapping("/utilisateurevenements/evenements/{id}")
    fun supprimerUtilisateurEvenementParEvenementId(@PathVariable id: Int) = service.supprimerParEvenement(id)

    @PostMapping("/utilisateursevenements")
    fun rejoindreEvenement(@RequestBody utilisateurÉvénement : UtilisateurÉvénement) = service.ajouter(utilisateurÉvénement)
}