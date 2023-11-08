package eventichs.api.eventichs_api.Controleurs

import eventichs.api.eventichs_api.Services.EvenementService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class EvenementControleur(val service : EvenementService) {

    @GetMapping("/evenements")
    fun obtenirEvenements() = service.chercherTous()

    @GetMapping("/evenements/{id}")
    fun obtenirEvenementParId(@PathVariable id: Int) = service.chercherParCode(id)

}
