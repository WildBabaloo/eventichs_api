package eventichs.api.eventichs_api.Controleurs
import eventichs.api.eventichs_api.Modèle.Événement
import eventichs.api.eventichs_api.Services.EvenementService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class EvenementControleur(val service : EvenementService) {
    @GetMapping("/evenements")
    fun obtenirEvenements() = service.chercherTous()
    @GetMapping("/evenements/{id}")
    fun obtenirEvenementParId(@PathVariable id: Int) = service.chercherParID(id)
    @DeleteMapping("/evenements/{id}")
    fun supprimerEvenement(@PathVariable id: Int) = service.supprimerParID(id)

    @PutMapping("/evenements/{id}")
    fun modifierEvenement(@RequestBody evenement : Événement) = service.modifierEvenement(evenement)

    @PostMapping("/evenements")
    fun ajouterEvenement(@RequestBody evenement : Événement) = service.ajouterEvenement(evenement)
}