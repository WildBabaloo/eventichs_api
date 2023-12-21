package eventichs.api.eventichs_api.Controleurs

import eventichs.api.eventichs_api.Modèle.Catégorie
import io.swagger.v3.oas.annotations.tags.Tag
import eventichs.api.eventichs_api.Services.CatégorieService
import eventichs.api.eventichs_api.Services.EvenementService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("\${api.base-path:}")
@Tag(
    name = "Catégorie",
    description = "Points d'accès aux ressources liées aux catégories d'evenements existantes sur le service."
)
class CatégorieControleur(val service : CatégorieService) {

    @GetMapping("/categories")
    fun obtenirCategories() = service.chercherTous()

    @GetMapping("/categories/{id}")
    fun obtenirCategorieParID(@PathVariable id: Int) = service.chercherParID(id)

    @PutMapping("/categories/{id}")
    fun modifierCategorie(@PathVariable id: Int, @RequestBody catégorie: Catégorie) = service.modifierCatégorie(catégorie)

    @PostMapping("/categories")
    fun ajouterCategorie(@RequestBody catégorie: Catégorie) = service.ajouterCatégorie(catégorie)
    @DeleteMapping("/categories/{id}")
    fun effacerCategorie(@PathVariable id: Int) = service.supprimerParID(id)
}