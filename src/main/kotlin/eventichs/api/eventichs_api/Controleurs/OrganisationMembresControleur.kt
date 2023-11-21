package eventichs.api.eventichs_api.Controleurs

import eventichs.api.eventichs_api.Modèle.Organisation
import eventichs.api.eventichs_api.Services.OrganisationMembresService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("\${api.base-path:}")
class OrganisationMembresControleur(val service: OrganisationMembresService) {

    @GetMapping("/organisationsMembres")
    fun obtenirOrganisationsMembres() = service.chercherTous()

    @GetMapping("/utilisateurs/{codeParticipant}/organisations")
    fun obtenirOrganisationsParticipantParID(@PathVariable codeParticipant: Int) = service.chercherParParticipantID(codeParticipant)

    @GetMapping("/organisations/{codeOrganisation}/participants")
    fun obtenirParticipantDansOrganisationParID(@PathVariable codeOrganisation: Int) = service.chercherParOrganisationID(codeOrganisation)

    @PostMapping("/organisations/{codeOrganisation}/participants")
    fun ajouterParticipant(@PathVariable codeOrganisation: Int, @RequestBody idParticpant: Int) = service.ajouterParticipant(codeOrganisation, idParticpant)

    @DeleteMapping("organisations/{codeOrganisation}/participants/{codeParticipant}")
    fun enleverParticipant(@PathVariable codeOrganisation: Int, @PathVariable codeParticipant: Int) = service.enleverParticipant(codeOrganisation, codeParticipant)
}