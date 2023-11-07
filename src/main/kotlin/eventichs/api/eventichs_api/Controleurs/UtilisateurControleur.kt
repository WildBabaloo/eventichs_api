package eventichs.api.eventichs_api.Controleurs

import eventichs.api.eventichs_api.Modèle.Invitation
import eventichs.api.eventichs_api.Modèle.Jeton
import eventichs.api.eventichs_api.Modèle.Utilisateur
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("\${api.base-path:}")
class UtilisateurControleur() {

    @PostMapping("/utilisateurs")
    fun inscrire(@RequestBody utilisateur: Utilisateur):
            ResponseEntity<Invitation> = ResponseEntity(HttpStatus.NOT_IMPLEMENTED)

    @GetMapping("/utilisateurs/{id}")
    fun connexion(@PathVariable id : Int):
            ResponseEntity<Invitation> = ResponseEntity(HttpStatus.NOT_IMPLEMENTED)

    @PutMapping("/utilisateurs/{id}")
    fun modifier(@PathVariable id : Int, @RequestBody utilisateur: Utilisateur):
            ResponseEntity<Invitation> = ResponseEntity(HttpStatus.NOT_IMPLEMENTED)

    @DeleteMapping("/utilisateurs/{id}")
    fun effacer(@PathVariable id : Int):
            ResponseEntity<Invitation> = ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
}