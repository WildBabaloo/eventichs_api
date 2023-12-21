package eventichs.api.eventichs_api.Mapper

import eventichs.api.eventichs_api.Modèle.OrganisationMembres
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

class OrganisationMembresMapper: RowMapper<OrganisationMembres> {
    override fun mapRow(resultat: ResultSet, rowNum: Int): OrganisationMembres? {
        val uneOrganisationMembres = OrganisationMembres(
            resultat.getInt("id_organisation"),
            resultat.getString("id_utilisateur")

        )
        return uneOrganisationMembres
    }
}