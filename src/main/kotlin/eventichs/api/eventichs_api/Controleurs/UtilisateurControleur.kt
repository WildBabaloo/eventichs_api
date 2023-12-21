package eventichs.api.eventichs_api.Controleurs

import eventichs.api.eventichs_api.Exceptions.RessourceInexistanteException
import eventichs.api.eventichs_api.Modèle.InvitationOrganisation
import eventichs.api.eventichs_api.Modèle.Utilisateur
import eventichs.api.eventichs_api.Services.UtilisateurService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("\${api.base-path:}")
class UtilisateurControleur(val service: UtilisateurService) {

    @PostMapping("/utilisateurs")
    fun inscrire(@RequestBody utilisateur: Utilisateur):
            ResponseEntity<InvitationOrganisation> = ResponseEntity(HttpStatus.NOT_IMPLEMENTED)

    @GetMapping("/utilisateurs/{id}")
    fun connexion(@PathVariable id : String) = service.chercherParID(id) ?: throw RessourceInexistanteException("L'utilisateur $id n'est pas inscrit au service")
    @PutMapping("/utilisateurs/{id}")
    fun modifier(@PathVariable id : Int, @RequestBody utilisateur: Utilisateur):
            ResponseEntity<InvitationOrganisation> = ResponseEntity(HttpStatus.NOT_IMPLEMENTED)

    @DeleteMapping("/utilisateurs/{id}")
    fun effacer(@PathVariable id : Int):
            ResponseEntity<InvitationOrganisation> = ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
}