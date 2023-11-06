package eventichs.api.eventichs_api.Controleurs

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc

@SpringBootTest
@AutoConfigureMockMvc
class UtilisateurControleurTest {

    //    @MockBean
    //    lateinit var service: InvitationService

    @Autowired
    private lateinit var mockMvc: MockMvc
    
}