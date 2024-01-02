package eventichs.api.eventichs_api.DAO

import eventichs.api.eventichs_api.Mapper.CatégorieMapper
import eventichs.api.eventichs_api.Modèle.Catégorie
import eventichs.api.eventichs_api.Modèle.Organisation
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class CatégorieDAOImplMémoire(val db: JdbcTemplate): CatégorieDAO {

    override fun chercherTous(): List<Catégorie> =
        db.query("select * from Catégorie", CatégorieMapper())

    override fun chercherParID(id: Int): Catégorie? =
        db.queryForObject("select * from Catégorie where id = $id", CatégorieMapper())

    override fun supprimerParID(id: Int): Catégorie? {
        val element = db.queryForObject("select * from Catégorie where id = $id", CatégorieMapper())
        db.update("DELETE from Catégorie where id = $id")
        return element
    }

    override fun ajouter(element: Catégorie): Catégorie {
        db.update(
            "insert into Catégorie(nom, description) values ( ?, ?)",
            element.nom,
            element.description)
        return element
    }

    override fun modifier(element: Catégorie): Catégorie {
        db.update(
            "UPDATE Catégorie SET nom = '$element.nom', description = '$element.description' WHERE id = $element.id")
        return element
    }

    override fun validerUtilisateur(code_util: String): Boolean {
        var organisation: OrganisationDAO = OrganisationDAOImplMémoire(db)
        return organisation.validerOrganisateur(code_util)
    }

}