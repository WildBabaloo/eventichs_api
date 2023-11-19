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
        db.update(
            "insert into invitation_organisation values ( ?, ?, ? , ?, ?)",
            element.id,
            element.idDestinataire,
            element.idOrganisation,
            element.jeton,
            element.status)
        //Comment retourner l'invitation créé dans la bd avec le bon id.
        //Aussi ajouter une vérification pour ne pas créer plus qu'une invitations pour la même organisation et le même participant(destinataire)
        return element
    }

    override fun chercherParOrganisation(idOrganisation: Int) : List<InvitationOrganisation> =
        db.query("select * from invitation_organisation where idOrganisation = $idOrganisation", InvitationOrganisationMapper())

    override fun chercherParParticipant(idParticipant: Int): List<InvitationOrganisation> =
        db.query("select * from invitation_organisation where idDestinataire = $idParticipant", InvitationOrganisationMapper())
}