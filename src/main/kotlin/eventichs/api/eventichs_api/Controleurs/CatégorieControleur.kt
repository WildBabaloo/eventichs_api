package eventichs.api.eventichs_api.Controleurs

import eventichs.api.eventichs_api.Modèle.Catégorie
import eventichs.api.eventichs_api.Services.CatégorieService
import eventichs.api.eventichs_api.Services.EvenementService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("\${api.base-path:}")
class CatégorieControleur(val service : CatégorieService) {
    @PostMapping("/catégories")
    fun inscrire(@RequestBody catégorie: Catégorie):
            ResponseEntity<Catégorie> = ResponseEntity(HttpStatus.NOT_IMPLEMENTED)

    @GetMapping("/catégories/{id}")
    fun connexion(@PathVariable id: Int):
            ResponseEntity<Catégorie> = ResponseEntity(HttpStatus.NOT_IMPLEMENTED)

    @PutMapping("/catégories/{id}")
    fun modifier(@PathVariable id: Int, @RequestBody catégorie: Catégorie):
            ResponseEntity<Catégorie> = ResponseEntity(HttpStatus.NOT_IMPLEMENTED)

    @DeleteMapping("/catégories/{id}")
    fun effacer(@PathVariable id: Int):
            ResponseEntity<Catégorie> = ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
}