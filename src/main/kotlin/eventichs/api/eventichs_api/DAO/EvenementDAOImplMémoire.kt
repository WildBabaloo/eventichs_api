package eventichs.api.eventichs_api.DAO


import eventichs.api.eventichs_api.Modèle.Événement
import org.springframework.jdbc.core.JdbcTemplate

import org.springframework.stereotype.Repository

@Repository
class EvenementDAOImplMemoire(val db: JdbcTemplate) : EvenementDAO {
    override fun chercherTous(): List<Événement> =
            db.query("select * from Événement", EvenementMapper())
    override fun chercherParID(id: Int): Événement? =
            db.queryForObject("select * from Événement where id = $id", EvenementMapper())
    override fun chercherParOrganisation(id: Int): List<Événement> =
            db.query("select * from Événement where organisation_id = $id", EvenementMapper())
    override fun ajouter(element: Événement): Événement? {
        TODO("Not yet implemented")
    }
}
