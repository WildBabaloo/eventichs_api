package eventichs.api.eventichs_api.Mapper

import eventichs.api.eventichs_api.Modèle.Participant
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

class ParticipantMapper : RowMapper<Participant> {
    override fun mapRow(résultat: ResultSet, RowNum: Int): Participant {
        val participant = Participant(
                résultat.getInt("id"),
                résultat.getString("nom"),
                résultat.getString("prénom"))
        return participant
    }
}