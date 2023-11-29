package eventichs.api.eventichs_api.DAO

import eventichs.api.eventichs_api.Mapper.CatégorieOrganisationMapper
import eventichs.api.eventichs_api.Modèle.Catégorie
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class CatégorieOrganisationDAOImplMémoire(val db: JdbcTemplate): CatégorieOrganisationDAO {

    override fun chercherTous(): List<Catégorie> =
        db.query("select * from Catégorie_Organisation", CatégorieOrganisationMapper())

    override fun chercherParID(id: Int): Catégorie? =
        db.queryForObject("select * from Catégorie_Organisation where id = $id", CatégorieOrganisationMapper())
    
    override fun supprimerParID(id: Int): Catégorie? {
        val element = db.queryForObject("select * from Catégorie_Organisation where id = $id", CatégorieOrganisationMapper())
        db.update("DELETE from Catégorie_Organisation where id = $id")
        return element
    }
    override fun ajouter(element: Catégorie): Catégorie {
        db.update(
            "insert into Catégorie_Organisation values (?, ?, ?)",
            element.id,
            element.nom,
            element.description)
        return element
    }

    override fun modifier(element: Catégorie): Catégorie {
        db.update(
            "UPDATE Catégorie_Organisation SET nom = ?, description = ? WHERE id = $element.id",
            element.nom,
            element.description)
        return element
    }
}