package eventichs.api.eventichs_api.DAO

import eventichs.api.eventichs_api.Mapper.OrganisationMapper
import eventichs.api.eventichs_api.Mapper.UtilisateurMapper
import eventichs.api.eventichs_api.Modèle.Utilisateur
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class UtilisateurDAOImplMémoire(val db: JdbcTemplate): UtilisateurDAO {
    override fun chercherTous(): List<Utilisateur> {
        TODO("Not yet implemented")
    }

    override fun chercherParID(id: String): Utilisateur? = db.queryForObject("select * from Utilisateur where code = '$id'", UtilisateurMapper())
    override fun chercherParID(id: Int): Utilisateur? {
        TODO("Not yet implemented")
    }

    override fun ajouter(element: Utilisateur): Utilisateur? {
        TODO("Not yet implemented")
    }

    override fun supprimerParID(id: Int): Utilisateur? {
        TODO("Not yet implemented")
    }

    override fun modifier(element: Utilisateur): Utilisateur? {
        TODO("Not yet implemented")
    }
}