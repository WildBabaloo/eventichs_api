package eventichs.api.eventichs_api.DAO

import eventichs.api.eventichs_api.Modèle.InvitationOrganisation
import eventichs.api.eventichs_api.Modèle.InvitationÉvénement
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.queryForObject
import org.springframework.stereotype.Repository


@Repository
class InvitationÉvénementDAOImplMémoire(val db: JdbcTemplate): InvitationÉvénementDAO {


    override fun supprimerParID(id: Int): InvitationÉvénement? {
        val invitationÀSupprimer = chercherParID(id)
        if (invitationÀSupprimer != null) {
            db.update("DELETE FROM Invitation_événement WHERE id = $id")
            }

        return invitationÀSupprimer
    }

    override fun modifier(element: InvitationÉvénement): InvitationÉvénement? {
        db.update("UPDATE Invitation_événement SET " +
                "idExpediteur = ?," +
                "idDestinataire = ?," +
                "idÉvénement = ?," +
                "jeton = ?," +
                "status = ?" +
                "WHERE id = ?",
            element.idExpéditeur,
            element.idDestinataire,
            element.idÉvénement,
            element.jeton,
            element.status,
            element.id)

        return element
    }

    override fun ajouter(element: InvitationÉvénement): InvitationÉvénement? {
        db.update("INSERT INTO Invitation_événement values (?,?,?,?,?,?)",
            element.id,
            element.idExpéditeur,
            element.idDestinataire,
            element.idÉvénement,
            element.jeton,
            element.status)

        return element
    }

    override fun chercherTous(): List<InvitationÉvénement> {
        return db.query("SELECT * FROM Invitation_événement", InvitationÉvénementMapper())
    }

    override fun chercherParID(id: Int): InvitationÉvénement? {
        return db.queryForObject("SELECT * FROM Invitation_événement WHERE id = $id", InvitationÉvénementMapper())
    }

    override fun chercherParIdDestinataire(id: Int): List<InvitationÉvénement> {
        return db.query("SELECT * FROM Invitation_événement WHERE idDestinataire = $id", InvitationÉvénementMapper())
    }

    override fun chercherParIdExpediteur(id: Int): List<InvitationÉvénement> {
        return db.query("SELECT * FROM Invitation_événement WHERE idExpediteur = $id", InvitationÉvénementMapper())
    }

    override fun entrerJetonEvenement(idInvité: Int, jeton: String): InvitationÉvénement? {
        val invitationAvecJetonExiste = db.queryForObject("SELECT * FROM Invitation_événement WHERE jeton = $jeton", InvitationÉvénementMapper())
        val idInvitation : Int? = invitationAvecJetonExiste?.id
        db.update("UPDATE Invitation_événement SET idDestinataire = $idInvité, status = 'accepté' WHERE idInvitation = $idInvitation", InvitationÉvénementMapper())

        return chercherParID(invitationAvecJetonExiste!!.id)
    }

    //gentiment copié du creerJeton de Julien
    override fun creerJeton(idEvenement: Int): InvitationÉvénement? {
        db.update(
            "INSERT INTO Invitation_événement (idDestinataire, idÉvénement, status) VALUES (null, $idEvenement,'généré'); ")
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
}