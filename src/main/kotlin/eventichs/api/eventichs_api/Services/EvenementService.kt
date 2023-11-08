package eventichs.api.eventichs_api.Services

import eventichs.api.eventichs_api.Modele.Evenement
import org.springframework.stereotype.Service
import eventichs.api.eventichs_api.DAO.EvenementDAO

@Service
class EvenementService(val dao : EvenementDAO) {
        fun chercherTous(): List<Evenement> = dao.chercherTous()
        fun chercherParCode(code: Int): Evenement? = dao.chercherParCode(code)
}