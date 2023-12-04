package eventichs.api.eventichs_api.Mapper

import eventichs.api.eventichs_api.Modèle.InvitationOrganisation
import eventichs.api.eventichs_api.Modèle.Organisation
import eventichs.api.eventichs_api.Modèle.Utilisateur
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

class InvitationOrganisationMapper : RowMapper<InvitationOrganisation>{
    override fun mapRow(résultat: ResultSet, rowNum: Int): InvitationOrganisation? {
        var utilisateur : Utilisateur? = null
        if (résultat.getString("nom") != null) {
            utilisateur = Utilisateur(
                résultat.getString(6),
                résultat.getString("courriel"),
                résultat.getString("nom"),
                résultat.getString("prénom"))
        }
        val invitation = InvitationOrganisation(
            résultat.getInt("id"),
            utilisateur,
            Organisation(
                résultat.getInt(11),
                résultat.getInt("idUtilisateur"),
                résultat.getString("nomOrganisation"),
                résultat.getInt("catégorie_id"),
                résultat.getBoolean("estPublic")
            ),
            résultat.getString("jeton"),
            résultat.getString("status"))
        return invitation
    }
}