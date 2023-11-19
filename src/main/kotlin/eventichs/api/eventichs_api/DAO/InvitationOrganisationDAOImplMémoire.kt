package eventichs.api.eventichs_api.DAO

import eventichs.api.eventichs_api.Modèle.InvitationOrganisation
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class InvitationOrganisationDAOImplMémoire(val db: JdbcTemplate): InvitationOrganisationDAO {
    override fun chercherTous(): List<InvitationOrganisation> =
        db.query("select * from invitation_organisation", InvitationOrganisationMapper())

    override fun chercherParID(id: Int): InvitationOrganisation? =
        db.queryForObject("select * from invitation_organisation where id = $id", InvitationOrganisationMapper())

    override fun ajouter(element: InvitationOrganisation): InvitationOrganisation? {
        return super.ajouter(element)
    }
}