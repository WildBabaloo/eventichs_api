package eventichs.api.eventichs_api.Controleurs

import eventichs.api.eventichs_api.Modèle.Catégorie
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("\${api.base-path:}")
@Tag(
    name = "Catégorie",
    description = "Points d'accès aux ressources liées aux catégories éxistantes sur le service."
)
class CatégorieControleur {
    @PostMapping("/categories")
    fun inscrire(@RequestBody catégorie: Catégorie):
            ResponseEntity<Catégorie> = ResponseEntity(HttpStatus.NOT_IMPLEMENTED)

    @GetMapping("/categories/{id}")
    fun connexion(@PathVariable id: Int):
            ResponseEntity<Catégorie> = ResponseEntity(HttpStatus.NOT_IMPLEMENTED)

    @PutMapping("/categories/{id}")
    fun modifier(@PathVariable id: Int, @RequestBody catégorie: Catégorie):
            ResponseEntity<Catégorie> = ResponseEntity(HttpStatus.NOT_IMPLEMENTED)

    @DeleteMapping("/categories/{id}")
    fun effacer(@PathVariable id: Int):
            ResponseEntity<Catégorie> = ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
}