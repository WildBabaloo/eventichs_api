package eventichs.api.eventichs_api.Mapper

import eventichs.api.eventichs_api.Modèle.InvitationÉvénement
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

class InvitationÉvénementMapper: RowMapper<InvitationÉvénement> {

    override fun mapRow(résultat: ResultSet, rowNum: Int): InvitationÉvénement? {
        val invitationÉvénement = InvitationÉvénement(
            résultat.getInt("id"),
            résultat.getString("codeExpediteur"),
            résultat.getString("codeDestinataire"),
            résultat.getInt("idÉvénement"),
            résultat.getString("jeton"),
            résultat.getString("status")
        )
        return invitationÉvénement
    }
}