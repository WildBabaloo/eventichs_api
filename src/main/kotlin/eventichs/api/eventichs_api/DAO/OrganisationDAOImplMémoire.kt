package eventichs.api.eventichs_api.DAO

import eventichs.api.eventichs_api.Modèle.Categorie
import eventichs.api.eventichs_api.Modèle.Organisation
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class OrganisationDAOImplMémoire(val db: JdbcTemplate): OrganisationDAO {
    override fun chercherTous(): List<Organisation> =
        db.query("select * from organisation", OrganisationMapper())

    override fun chercherParID(id: Int): Organisation? =
        db.queryForObject("select * from organisation where id = $id", OrganisationMapper())

    override fun ajouter(element: Organisation): Organisation? {
        db.update(
            "insert into organisation values (?, ?, ?, ?)",
            element.id,
            element.idUtilisateur,
            element.nomOrganisation,
            element.catégorie_id,
            element.estPublic
        )
        return element
    }

    override fun modifier(element: Organisation): Organisation? {
        db.update(
            "update organisation set nomOrganisation = ?, catégorie_id = ? where id = $element.id",
            element.nomOrganisation,
            element.catégorie_id
        )
        return element
    }

    override fun supprimerParID(id: Int): Organisation? {
        val uneOrganisation = db.queryForObject("select * from organisation where id = $id", OrganisationMapper())
         db.update(
            "delete from organisation where id = $id"
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