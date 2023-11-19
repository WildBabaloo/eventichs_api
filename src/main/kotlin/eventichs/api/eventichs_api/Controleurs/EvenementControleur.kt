package eventichs.api.eventichs_api.Controleurs
import eventichs.api.eventichs_api.Modèle.Événement
import eventichs.api.eventichs_api.Services.EvenementService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
@RestController
class EvenementControleur(val service : EvenementService) {
    @GetMapping("/evenements")
    fun obtenirEvenements() = service.chercherTous()
    @GetMapping("/evenements/{id}")
    fun obtenirEvenementParId(@PathVariable id: Int) = service.chercherParID(id)
    @DeleteMapping("/evenements/{id}")
    fun supprimerEvenement(@PathVariable id: Int) = service.supprimerParID(id)

    @PostMapping("/evenements/{id}")
    fun modifierEvenement(@RequestBody evenement : Événement) = service.modifierEvenement(evenement)
}