package eventichs.api.eventichs_api.DAO

import eventichs.api.eventichs_api.Modèle.Organisation
import eventichs.api.eventichs_api.Modèle.OrganisationMembres
import eventichs.api.eventichs_api.Modèle.Utilisateur
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class OrganisationMembersDAOImplMémoire(val db: JdbcTemplate): OrganisationMembersDAO {
    override fun chercherTous(): List<OrganisationMembres> =
        db.query("select * from organisations_membres", OrganisationMembresMapper())
    override fun chercherParUtilisateurID(id: Int): List<OrganisationMembres> =
        db.query("select * from organisations_membres where id_utilisateur = $id", OrganisationMembresMapper())

    override fun chercherParOrganisationID(id: Int): List<OrganisationMembres> =
         db.query("select * from organisations_membres where id_organisation = $id", OrganisationMembresMapper())

    override fun ajouterParticipant(codeOrganisation: Organisation, unParticipant: Utilisateur): Organisation? {
        TODO("Not yet implemented")
    }

    override fun enleverParticipant(codeOrganisation: Organisation, unParticipant: Utilisateur): Organisation? {
        TODO("Not yet implemented")
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