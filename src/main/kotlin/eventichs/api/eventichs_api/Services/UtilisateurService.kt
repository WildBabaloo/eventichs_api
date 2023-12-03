package eventichs.api.eventichs_api.Services


import eventichs.api.eventichs_api.DAO.UtilisateurDAO
import eventichs.api.eventichs_api.Modèle.Utilisateur
import org.springframework.stereotype.Service

@Service
class UtilisateurService(val dao : UtilisateurDAO) {
    fun chercherParID(id: Int): Utilisateur? = dao.chercherParID(id)
}