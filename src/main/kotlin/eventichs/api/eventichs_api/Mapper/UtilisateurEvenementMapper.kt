package eventichs.api.eventichs_api.Mapper

import eventichs.api.eventichs_api.Modèle.UtilisateurÉvénement
import java.sql.ResultSet
import org.springframework.jdbc.core.RowMapper

class UtilisateurEvenementMapper : RowMapper<UtilisateurÉvénement> {
    override fun mapRow(résultat: ResultSet, RowNum: Int): UtilisateurÉvénement {
        val utilisateurÉvénement = UtilisateurÉvénement(
                résultat.getString("codeUtilisateur"),
                résultat.getInt("idEvenement"))
        return utilisateurÉvénement
    }
}