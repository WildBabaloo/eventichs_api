package eventichs.api.eventichs_api.DAO

import eventichs.api.eventichs_api.Modèle.Catégorie
import eventichs.api.eventichs_api.Modèle.UtilisateurÉvénement
import java.sql.ResultSet
import org.springframework.jdbc.core.RowMapper

class UtilisateurEvenementMapper : RowMapper<UtilisateurÉvénement> {
    override fun mapRow(résultat: ResultSet, RowNum: Int): UtilisateurÉvénement {
        val utilisateurÉvénement = UtilisateurÉvénement(
                résultat.getInt("idUtilisateur"),
                résultat.getInt("idEvenement"))
        return utilisateurÉvénement
    }
}