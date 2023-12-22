package eventichs.api.eventichs_api.DAO

import eventichs.api.eventichs_api.Mapper.OrganisationMapper
import eventichs.api.eventichs_api.Modèle.Organisation
import org.springframework.dao.DataAccessException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class OrganisationDAOImplMémoire(val db: JdbcTemplate): OrganisationDAO {
    override fun chercherTous(): List<Organisation> =
        db.query("select * from Organisation", OrganisationMapper())

    override fun chercherParID(id: Int): Organisation? =
        db.queryForObject("select * from Organisation where id = $id", OrganisationMapper())

    override fun ajouter(organisation: Organisation): Organisation? {
        val sql = "INSERT INTO Organisation (id, codeUtilisateur, nomOrganisation, catégorie_id, estPublic) VALUES (?, ?, ?, ?, ?)"
        db.update(
                sql,
                organisation.id,
                organisation.codeUtilisateur,
                organisation.nomOrganisation,
                organisation.catégorie_id,
                organisation.estPublic
        )
        return organisation
    }

    override fun modifier(element: Organisation): Organisation? {
        val id = element.id
        db.update(
            "update Organisation set nomOrganisation = ?, catégorie_id = ?, estPublic = ? where id = $id",
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
        db.query("select * from Organisation where catégorie_id = $idCategorie", OrganisationMapper())

    override fun validerUtilisateur(id: Int, codeUtilisateur: String): Boolean {
        try {
            val organisation: Organisation? = chercherParID(id)
            if (organisation?.codeUtilisateur == codeUtilisateur) {
                return true
            }

            return false
        } catch (e: Exception) {
            return false
        }

        //val organisation: Organisation? = chercherParID(id)

       // if (organisation?.codeUtilisateur == codeUtilisateur) {
           // return true
        //}

       // return false
    }
    override fun validerOrganisateur(code_util: String): Boolean {
        return db.query(
            "select * from Organisation where codeUtilisateur = '$code_util'", OrganisationMapper()
        ).isNotEmpty()
    }
}