package eventichs.api.eventichs_api.DAO

import eventichs.api.eventichs_api.Modèle.Catégorie
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

class CatégorieMapper: RowMapper<Catégorie> {
    override fun mapRow(résultat: ResultSet, RowNum: Int): Catégorie? {
        val catégorie = Catégorie(
            résultat.getInt("id"),
            résultat.getString("nom"),
            résultat.getString("description"))
        return catégorie
    }
}