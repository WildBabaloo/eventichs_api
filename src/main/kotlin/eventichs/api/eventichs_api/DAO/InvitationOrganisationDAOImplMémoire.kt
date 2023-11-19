package eventichs.api.eventichs_api.DAO

import eventichs.api.eventichs_api.Modèle.InvitationOrganisation
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.ResultSetExtractor
import org.springframework.jdbc.core.queryForObject
import org.springframework.stereotype.Repository
import java.sql.ResultSet

@Repository
class InvitationOrganisationDAOImplMémoire(val db: JdbcTemplate): InvitationOrganisationDAO {
    override fun chercherTous(): List<InvitationOrganisation> =
        db.query("select * from invitation_organisation", InvitationOrganisationMapper())

    override fun chercherParID(id: Int): InvitationOrganisation? =
        db.queryForObject("select * from invitation_organisation where id = $id", InvitationOrganisationMapper())

    override fun ajouter(element: InvitationOrganisation): InvitationOrganisation? {

        //Comment vérifié qu'une invitation n'existe pas qui a le même idDestinataire et le même idOrganisation.
        // if idDestinataire /= null && /= element.idDestinataire && idOrganisation /= element.idOrganisation
        // ...

        db.update(
            "insert into invitation_organisation values ( ?, ?, ? , ?, ?)",
            element.id,
            element.idDestinataire,
            element.idOrganisation,
            element.jeton,
            element.status)

        //Query pour obtenir l'id de la nouvelle invitation dernièrement créé.
        val id = db.queryForObject<Int>("SELECT @lid:=LAST_INSERT_ID(); ")
        return chercherParID(id)
    }

    override fun modifier(element: InvitationOrganisation): InvitationOrganisation? {
        return super.modifier(element)
    }

    override fun supprimerParID(id: Int): InvitationOrganisation? {
        val invitation = chercherParID(id)
        db.update("delete from invitation_organisation where id = $id")
        return invitation
    }

    override fun chercherParOrganisation(idOrganisation: Int) : List<InvitationOrganisation> =
        db.query("select * from invitation_organisation where idOrganisation = $idOrganisation", InvitationOrganisationMapper())

    override fun chercherParParticipant(idParticipant: Int): List<InvitationOrganisation> =
        db.query("select * from invitation_organisation where idDestinataire = $idParticipant", InvitationOrganisationMapper())

    override fun changerStatus(idInvitationOrganisation: Int, status: String): InvitationOrganisation? {
        db.update("update invitation_organisation set `status` = ? where id = ?",status, idInvitationOrganisation)
        return chercherParID(idInvitationOrganisation)
    }


    //Insertion d'une invitation ayant aucun destinataire, le bon id Organisation et le status 'envoyé'
    //Un select pour obtenir l'id de l'invitation dernièrement créé.
    //Un update sur l'invitation dernièrement créé grace à l'id pour y ajouter un jeton de 8 charactères alléatoire.
    override fun crééJeton(idOrganisation: Int): InvitationOrganisation? {
        db.update(
            "INSERT INTO invitation_organisation (idDestinataire, idOrganisation, status) VALUES (null, $idOrganisation,'généré'); ")
        val id = db.queryForObject<Int>("SELECT @lid:=LAST_INSERT_ID(); ")
        db.update("update invitation_organisation set jeton=concat( " +
                    "substring('ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', rand(@seed:=round(rand($id)*4294967296))*36+1, 1)," +
                    "  substring('ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', rand(@seed:=round(rand(@seed)*4294967296))*36+1, 1)," +
                    "  substring('ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', rand(@seed:=round(rand(@seed)*4294967296))*36+1, 1)," +
                    "  substring('ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', rand(@seed:=round(rand(@seed)*4294967296))*36+1, 1)," +
                    "  substring('ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', rand(@seed:=round(rand(@seed)*4294967296))*36+1, 1)," +
                    "  substring('ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', rand(@seed:=round(rand(@seed)*4294967296))*36+1, 1)," +
                    "  substring('ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', rand(@seed:=round(rand(@seed)*4294967296))*36+1, 1)," +
                    "  substring('ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', rand(@seed)*36+1, 1)" +
                    ")" +
                    "where id=$id;")
        return chercherParID(id)
    }

    override fun saisirJeton(jeton: String, idUtilisateur: Int): InvitationOrganisation? {
        val invitation : InvitationOrganisation? = db.queryForObject("select * from invitation_organisation where jeton = ?", InvitationOrganisationMapper(),jeton)
        val id : Int? = invitation?.id
        db.update("update invitation_organisation set idDestinataire = $idUtilisateur, status = 'accepté' where id = $id")

        //appeller fonction d'ajouter organisation membre ici.

        return chercherParID(invitation!!.id)
    }
}