package eventichs.api.eventichs_api.Controleurs

import eventichs.api.eventichs_api.Modèle.Invitation
import eventichs.api.eventichs_api.Modèle.Jeton
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("\${api.base-path:}")
class JetonControleur() {

    @PostMapping("/jetons/{utilisateur_id}")
    fun saisirJeton(@PathVariable utilisateur_id : Int, @RequestBody jeton : Jeton):
            ResponseEntity<Invitation> = ResponseEntity(HttpStatus.NOT_IMPLEMENTED)

    @GetMapping("/jetons/{id}/{quantité}")
    fun générerJeton(@PathVariable id : Int,  @PathVariable quantité : Int):
            ResponseEntity<Invitation> = ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
}