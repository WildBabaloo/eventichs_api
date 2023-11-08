package eventichs.api.eventichs_api.Controleurs

import eventichs.api.eventichs_api.Modèle.Invitation
import eventichs.api.eventichs_api.Modèle.Organisation
import eventichs.api.eventichs_api.Modèle.Utilisateur
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
@RestController
@RequestMapping("\${api.base-path:}")
class OrganisationControleur {
    @PostMapping("/organisations")
    fun inscrire(@RequestBody organisation: Organisation):
            ResponseEntity<Organisation> = ResponseEntity(HttpStatus.NOT_IMPLEMENTED)

    @GetMapping("/organisations/{id}")
    fun connexion(@PathVariable id : Int):
            ResponseEntity<Organisation> = ResponseEntity(HttpStatus.NOT_IMPLEMENTED)

    @PutMapping("/organisations/{id}")
    fun modifier(@PathVariable id : Int, @RequestBody utilisateur: Utilisateur):
            ResponseEntity<Organisation> = ResponseEntity(HttpStatus.NOT_IMPLEMENTED)

    @DeleteMapping("/organisations/{id}")
    fun effacer(@PathVariable id : Int):
            ResponseEntity<Organisation> = ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
}