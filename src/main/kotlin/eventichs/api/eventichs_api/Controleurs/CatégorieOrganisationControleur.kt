package eventichs.api.eventichs_api.Controleurs

import eventichs.api.eventichs_api.Modèle.Catégorie
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("\${api.base-path:}")
@Tag(
    name = "CatégorieOrganisation",
    description = "Points d'accès aux ressources liées aux catégories d'organisations existantes sur le service."
)
class CatégorieOrganisationControleur {
    @PostMapping("/organisations/categories_")
    fun inscrire(@RequestBody catégorie: Catégorie):
            ResponseEntity<Catégorie> = ResponseEntity(HttpStatus.NOT_IMPLEMENTED)

    @GetMapping("/organisations/categories/{id}")
    fun connexion(@PathVariable id: Int):
            ResponseEntity<Catégorie> = ResponseEntity(HttpStatus.NOT_IMPLEMENTED)

    @PutMapping("/organisations/categories/{id}")
    fun modifier(@PathVariable id: Int, @RequestBody catégorie: Catégorie):
            ResponseEntity<Catégorie> = ResponseEntity(HttpStatus.NOT_IMPLEMENTED)

    @DeleteMapping("/organisations/categories/{id}")
    fun effacer(@PathVariable id: Int):
            ResponseEntity<Catégorie> = ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
}