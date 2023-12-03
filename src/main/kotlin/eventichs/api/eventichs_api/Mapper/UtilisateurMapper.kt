package eventichs.api.eventichs_api.Mapper

import eventichs.api.eventichs_api.Modèle.Utilisateur
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

class UtilisateurMapper : RowMapper<Utilisateur> {
    override fun mapRow(résultat: ResultSet, rowNum: Int): Utilisateur? {
        val utilisateur = Utilisateur(
            résultat.getInt("id"),
            résultat.getString("nom"),
            résultat.getString("prénom"),
            résultat.getString("motDePasse"),
            résultat.getString("courriel")
        )
        return utilisateur
    }
}