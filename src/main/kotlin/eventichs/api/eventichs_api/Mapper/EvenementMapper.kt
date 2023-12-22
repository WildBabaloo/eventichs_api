package eventichs.api.eventichs_api.Mapper

import eventichs.api.eventichs_api.Modèle.Événement
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

class EvenementMapper : RowMapper<Événement>{
    override fun mapRow(résultat: ResultSet, rowNum: Int): Événement? {
        val event = Événement(
                résultat.getInt("id"),
                résultat.getString("nom"),
                résultat.getString("adresse"),
                résultat.getDate("dateDebut"),
                résultat.getDate("dateFin"),
                résultat.getString("type"),
                résultat.getString("categorie_id"),
                résultat.getString("description"),
                résultat.getString("image"),
                résultat.getInt("organisation_id"))

        return event
    }
}