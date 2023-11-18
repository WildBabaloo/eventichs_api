package eventichs.api.eventichs_api.DAO

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class UtilisateurDAOImplMémoire(val db: JdbcTemplate): UtilisateurDAO {
    // TODO
}