package eventichs.api.eventichs_api.Controleurs


import eventichs.api.eventichs_api.Modèle.Organisation
import eventichs.api.eventichs_api.Services.OrganisationService
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
@SpringBootTest
@AutoConfigureMockMvc
class TestOrganisation {

    @MockBean
    lateinit var service: OrganisationService

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun `Étant donné un administrateur qui effectue une requete GET pour afficher toute les organisations lorsqu on exécute la requête on obtient un fichier JSON qui reqgroupe les organisations et un code de retour 200 `(){
        var listorganisation: MutableList<Organisation> = mutableListOf(Organisation(1,1,"Illuminati",1,false))
        //listorganisation?.add(Organisation(1,1,"Illuminati",1,false))

        Mockito.`when`(service.chercherTous()).thenReturn(listorganisation)

        mockMvc.perform(get("/organisations"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$[0].id").value(1))
            .andExpect(jsonPath("$[0].idUtilisateur").value(1))
            .andExpect(jsonPath("$[0].catégorie_id").value(1))
            .andExpect(jsonPath("$[0].nomOrganisation").value("Illuminati"))
            .andExpect(jsonPath("$[0].estPublic").value(false))

    }

}