package eventichs.api.eventichs_api.Controleurs

import com.fasterxml.jackson.databind.ObjectMapper
import eventichs.api.eventichs_api.Exceptions.RessourceInexistanteException
import eventichs.api.eventichs_api.Modèle.Organisation
import eventichs.api.eventichs_api.Modèle.OrganisationMembres
import eventichs.api.eventichs_api.Services.OrganisationMembresService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*


@SpringBootTest
@AutoConfigureMockMvc
class OrganisationMembresControleurTest {

    @Autowired
    private lateinit var mapper: ObjectMapper

    @MockBean
    lateinit var service: OrganisationMembresService

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun `Étant donné une organisation qui veut chercher ses participants mais il y en a aucun on obtient un JSON vide avec un code de retour 200`(){
        val listParticipant = listOf(OrganisationMembres(1, 1))

        Mockito.`when`(service.chercherParParticipantID(1)).thenReturn(listParticipant)

        mockMvc.perform(get("/organisations/1/participants"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id_organisation").value(1))
    }

    @Test
    fun `Étant donné une organisation qui effectue une recherche pour un participant non-exisatant et de l ajouter on obtient un code de retour 404`(){

    }

}