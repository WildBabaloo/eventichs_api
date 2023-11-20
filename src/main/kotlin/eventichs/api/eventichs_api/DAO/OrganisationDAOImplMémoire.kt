package eventichs.api.eventichs_api.DAO

import eventichs.api.eventichs_api.Modèle.Categorie
import eventichs.api.eventichs_api.Modèle.Organisation
import eventichs.api.eventichs_api.Modèle.Utilisateur
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.queryForObject
import org.springframework.stereotype.Repository

@Repository
class OrganisationDAOImplMémoire(val db: JdbcTemplate): OrganisationDAO {
    override fun chercherTous(): List<Organisation> =
        db.query("select * from organisation", OrganisationMapper())

    override fun chercherParID(codeOrganisation: Int): Organisation? =
        db.queryForObject("select * from organisation where id = $codeOrganisation", OrganisationMapper())

    override fun ajouter(uneOrganisation: Organisation): Organisation? {
        db.update(
            "insert into organisation values (?, ?, ?, ?)",
            uneOrganisation.id,
            uneOrganisation.idUtilisateur,
            uneOrganisation.nomOrganisation,
            uneOrganisation.catégorie_id,
            uneOrganisation.estPublic
        )
        return uneOrganisation
    }

    override fun modifier(uneOrganisation: Organisation): Organisation? {
        db.update(
            "update organisation set nomOrganisation = ?, catégorie_id = ? where id = $uneOrganisation.id",
            uneOrganisation.nomOrganisation,
            uneOrganisation.catégorie_id
        )
        return uneOrganisation
    }

    override fun supprimerParID(codeOrganisation: Int): Organisation? {
        val uneOrganisation = db.queryForObject("select * from organisation where id = $codeOrganisation", OrganisationMapper())
         db.update(
            "delete from organisation where id = $codeOrganisation"
        )
        return uneOrganisation
    }

    override fun consulterOrganisationPubliques(): List<Organisation> {
        TODO("Not yet implemented")
    }

    override fun filtrerOrganisationParGouts(uneCategorie: Categorie): List<Organisation> {
        TODO("Not yet implemented")
    }

    override fun changerVisiblitéOrganisation(codeOrganisation: Organisation, estPublic: Boolean): Organisation? {
        TODO("Not yet implemented")
    }
}