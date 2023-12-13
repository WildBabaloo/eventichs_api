package eventichs.api.eventichs_api.DAO

import eventichs.api.eventichs_api.Modèle.Organisation
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class OrganisationDAOImplMémoire(val db: JdbcTemplate): OrganisationDAO {
    override fun chercherTous(): List<Organisation> =
        db.query("select * from Organisation", OrganisationMapper())

    override fun chercherParID(id: Int): Organisation? =
        db.queryForObject("select * from Organisation where id = $id", OrganisationMapper())

    override fun ajouter(element: Organisation): Organisation? {
        db.update(
        "INSERT INTO Organisation (id, idUtilisateur, nomOrganisation, catégorie_id, estPublic) VALUES (?, ?, ?, ?, ?)",
        element.id, element.idUtilisateur, element.nomOrganisation, element.catégorie_id, element.estPublic
        )
        return element
    }

    override fun modifier(element: Organisation): Organisation? {
        db.update(
            "update Organisation set nomOrganisation = ?, catégorie_id = ?, estPublic = ? where id = $element.id",
            element.nomOrganisation,
            element.catégorie_id,
            element.estPublic
        )
        return element
    }

    override fun supprimerParID(id: Int): Organisation? {
        val uneOrganisation = db.queryForObject("select * from Organisation where id = $id", OrganisationMapper())
         db.update(
            "delete from Organisation where id = $id"
        )
        return uneOrganisation
    }

    override fun consulterOrganisationPubliques(): List<Organisation> =
        db.query("select * from Organisation where estPublic=true", OrganisationMapper())

    override fun filtrerOrganisationParGouts(idCategorie: Int): List<Organisation> =
        db.query("select * from Organisation where catégorie_id = $idCategorie",OrganisationMapper())

}