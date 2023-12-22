package eventichs.api.eventichs_api.DAO


import eventichs.api.eventichs_api.Exceptions.ConflitAvecUneRessourceExistanteException
import eventichs.api.eventichs_api.Exceptions.RessourceInexistanteException
import eventichs.api.eventichs_api.Mapper.OrganisationMapper
import eventichs.api.eventichs_api.Mapper.OrganisationMembresMapper
import eventichs.api.eventichs_api.Modèle.Organisation
import eventichs.api.eventichs_api.Modèle.OrganisationMembres
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.queryForObject
import org.springframework.stereotype.Repository
import java.lang.Exception

@Repository
class OrganisationMembersDAOImplMémoire(val db: JdbcTemplate): OrganisationMembersDAO {
    override fun chercherTous(): List<OrganisationMembres> =
        db.query("select * from Organisations_membres", OrganisationMembresMapper())
    override fun chercherParUtilisateurID(codeUtilisateur: String): List<OrganisationMembres> =
        db.query("select * from Organisations_membres where code_utilisateur = '$codeUtilisateur'", OrganisationMembresMapper())

    override fun chercherParOrganisationID(id: Int): List<OrganisationMembres> =
         db.query("select * from Organisations_membres where id_organisation = $id", OrganisationMembresMapper())

    override fun validerUtilisateur(id: Int, codeUtilisateur: String): Boolean {
        val organisation: Organisation?
        println("Principal Name $codeUtilisateur")
        try {
            organisation = db.queryForObject("select * from Organisation where codeUtilisateur = '$codeUtilisateur' LIMIT 1", OrganisationMapper())
        } catch (e: EmptyResultDataAccessException) {
            throw RessourceInexistanteException("L'organisation associé à le code utilisateur $codeUtilisateur n'existe pas!")
        }

        if (organisation?.codeUtilisateur == codeUtilisateur) {
            return true
        }

        return false

    }

override fun ajouterParticipant(codeOrganisation: Int, codeUtilisateur: String): OrganisationMembres? {

    try {
        val sql =
            "INSERT INTO organisations_membres (id_organisation, code_utilisateur) VALUES (${codeOrganisation}, '${codeUtilisateur}')"
        db.update(sql)
    }
    catch(e : Exception) {
        throw ConflitAvecUneRessourceExistanteException("L'utilisateur est déjà membre de cette organisation.")
    }
    val selectSql = "SELECT * FROM Organisations_membres WHERE id_organisation = ${codeOrganisation} AND code_utilisateur = '${codeUtilisateur}'"

    return db.queryForObject(selectSql, OrganisationMembresMapper())
}

    override fun enleverParticipant(codeOrganisation: Int, codeUtilisateur: String) {
        db.update(
            "delete from organisations_membres where id_organisation = $codeOrganisation and code_utilisateur = $codeUtilisateur"
        )
    }
    fun existe(id: Int, codeUtil: String): Boolean {
        val sql = "SELECT COUNT(*) FROM Organisations_membres WHERE id_organisation = ? AND code_utilisateur = ?"
        val count = db.queryForObject(sql, Long::class.java, id, codeUtil)
        return count > 0
    }

    // Fonction Inutiles

    override fun chercherParID(id: Int): OrganisationMembres? {
        TODO("Not yet implemented")
    }
    override fun ajouter(element: OrganisationMembres): OrganisationMembres? {
        TODO("Not yet implemented")
    }
    override fun supprimerParID(id: Int): OrganisationMembres? {
        TODO("Not yet implemented")
    }
    override fun modifier(element: OrganisationMembres): OrganisationMembres? {
        TODO("Not yet implemented")
    }
}