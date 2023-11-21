package eventichs.api.eventichs_api.Controleurs

import eventichs.api.eventichs_api.Modèle.Categorie
import eventichs.api.eventichs_api.Modèle.Invitation
import eventichs.api.eventichs_api.Modèle.Organisation
import eventichs.api.eventichs_api.Modèle.Utilisateur
import eventichs.api.eventichs_api.Services.OrganisationService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
@RestController
@RequestMapping("\${api.base-path:}")
class OrganisationControleur(val service: OrganisationService) {
    @GetMapping("/organisations")
    fun obtenirOrganisations() = service.chercherTous()

    @GetMapping("/organisations/{id}")
    fun obtenirOrganisationParID(@PathVariable id: Int) = service.chercherParID(id)

    @PostMapping("/organisations")
    fun ajouterOrganisation(@RequestBody organisation: Organisation) = service.ajouter(organisation)

    @PutMapping("/organisations/{id}")
    fun modifierOrganisation(@RequestBody organisation: Organisation) = service.modifier(organisation)

    @DeleteMapping("/organisations/{id}")
    fun supprimerOrganisation(@PathVariable id: Int) = service.supprimerParID(id)

    @GetMapping("/organisations/publiques")
    fun obtenirOrganisationsPubliques() = service.consulterOrganisationPubliques()

    @GetMapping("/gouts/{idCategorie}/organisations")
    fun obtenirOrganisationsParGout(@PathVariable idCategorie: Int) = service.filtrerOrganisationParGouts(idCategorie)

}