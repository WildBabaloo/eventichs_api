package eventichs.api.eventichs_api.DAO

import eventichs.api.eventichs_api.Modèle.InvitationOrganisation
import eventichs.api.eventichs_api.Modèle.InvitationÉvénement
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

class InvitationÉvénementMapper: RowMapper<InvitationÉvénement> {

    override fun mapRow(résultat: ResultSet, rowNum: Int): InvitationÉvénement? {
        val invitationÉvénement = InvitationÉvénement(
            résultat.getInt("id"),
            résultat.getInt("idÉxpéditeur"),
            résultat.getInt("idDestinataire"),
            résultat.getInt("idOrganisation"),
            résultat.getString("jeton"),
            résultat.getString("status")
        )
        return invitationÉvénement
    }
}