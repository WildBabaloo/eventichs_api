package eventichs.api.eventichs_api.Controleurs

import com.fasterxml.jackson.databind.ObjectMapper
import eventichs.api.eventichs_api.Exceptions.RessourceInexistanteException
import eventichs.api.eventichs_api.Modèle.Organisation
import eventichs.api.eventichs_api.Modèle.OrganisationMembres
import eventichs.api.eventichs_api.Modèle.Utilisateur
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
    fun `Étant donné une organisation qui veut chercher ses participants on obtient un JSON avec ses participants et un code de retour 200`(){
        val listParticipant = listOf(OrganisationMembres(Organisation(1,1,"Illuminati",1,false).id, Utilisateur(1, "test", "testo", "password", "test@test.com").id))

        Mockito.`when`(service.chercherParParticipantID(1)).thenReturn(listParticipant)

        val response = mockMvc.perform(get("/utilisateurs/1/organisations"))
            .andExpect(status().isOk)
            //.andReturn().response.contentAsString
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$[0].id_utilisateur").value(1))
        println("JSON response: $response")
    }

    @Test
    fun `Étant donné une organisation qui effectue une recherche pour un participant non-exisatant et de l ajouter on obtient un code de retour 404`(){

    }

}