package eventichs.api.eventichs_api.DAO

import eventichs.api.eventichs_api.Modèle.Organisation
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

class OrganisationMapper: RowMapper<Organisation> {
    override fun mapRow(resultat: ResultSet, rowNum: Int): Organisation? {
        val uneOrganisation = Organisation(
            resultat.getInt("id"),
            resultat.getInt("idUtilisateur"),
            resultat.getString("nomOrganisation"),
            resultat.getInt("catégorie_id"),
            resultat.getBoolean("estPublic")
        )
        return uneOrganisation
    }
}