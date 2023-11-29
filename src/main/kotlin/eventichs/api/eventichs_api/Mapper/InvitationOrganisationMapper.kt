package eventichs.api.eventichs_api.Mapper

import eventichs.api.eventichs_api.Modèle.InvitationOrganisation
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

class InvitationOrganisationMapper : RowMapper<InvitationOrganisation>{
    override fun mapRow(résultat: ResultSet, rowNum: Int): InvitationOrganisation? {
        val invitation = InvitationOrganisation(
            résultat.getInt("id"),
            résultat.getInt("idDestinataire"),
            résultat.getInt("idOrganisation"),
            résultat.getString("jeton"),
            résultat.getString("status"))
        return invitation
    }
}