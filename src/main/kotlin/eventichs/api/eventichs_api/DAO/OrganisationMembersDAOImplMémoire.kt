package eventichs.api.eventichs_api.DAO


import eventichs.api.eventichs_api.Mapper.OrganisationMembresMapper
import eventichs.api.eventichs_api.Modèle.OrganisationMembres
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class OrganisationMembersDAOImplMémoire(val db: JdbcTemplate): OrganisationMembersDAO {
    override fun chercherTous(): List<OrganisationMembres> =
        db.query("select * from Organisations_membres", OrganisationMembresMapper())
    override fun chercherParUtilisateurID(id: String): List<OrganisationMembres> =
        db.query("select * from Organisations_membres where id_utilisateur = $id", OrganisationMembresMapper())

    override fun chercherParOrganisationID(id: Int): List<OrganisationMembres> =
         db.query("select * from Organisations_membres where id_organisation = $id", OrganisationMembresMapper())

    override fun ajouterParticipant(codeOrganisation: Int, IdParticipant: String){
        db.update(
            "Update Organisations_membres set id_utilisateur=$IdParticipant where id_organisation=$codeOrganisation"
            , OrganisationMembresMapper()
        )

    }

    override fun enleverParticipant(codeOrganisation: Int, idParticipant: String) {
        db.update(
            "Update Organisations_membres set id_utilisateur= null where id_organisation= $codeOrganisation and id_utilisateur = $idParticipant"
            , OrganisationMembresMapper()
        )
    }
    fun existe(id: Int, codeUtil: String): Boolean {
        val sql = "SELECT COUNT(*) FROM Organisations_membres WHERE id_organisation = ? AND id_utilisateur = ?"
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