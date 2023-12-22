package eventichs.api.eventichs_api.DAO

import eventichs.api.eventichs_api.Mapper.EvenementMapper
import eventichs.api.eventichs_api.Mapper.InvitationÉvénementMapper
import eventichs.api.eventichs_api.Modèle.InvitationÉvénement
import eventichs.api.eventichs_api.Modèle.Événement
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

    override fun chercherParIdDestinataire(id: String): List<InvitationÉvénement> {
        return db.query("SELECT * FROM Invitation_événement WHERE codeDestinataire = $id", InvitationÉvénementMapper())
    }

    override fun chercherParIdExpediteur(id: String): List<InvitationÉvénement> {
        return db.query("SELECT * FROM Invitation_événement WHERE codeExpediteur = $id", InvitationÉvénementMapper())
    }

    override fun entrerJetonEvenement(idInvité: String, jeton: String): InvitationÉvénement? {
        val invitationAvecJetonExiste = db.queryForObject("SELECT * FROM Invitation_événement WHERE jeton = $jeton", InvitationÉvénementMapper())
        val idInvitation : Int? = invitationAvecJetonExiste?.id
        db.update("UPDATE Invitation_événement SET codeDestinataire = $idInvité, status = 'accepté' WHERE codeInvitation = $idInvitation", InvitationÉvénementMapper())

        return chercherParID(invitationAvecJetonExiste!!.id)
    }

    //gentiment copié du creerJeton de Julien
    override fun creerJeton(idEvenement: Int): InvitationÉvénement? {
        db.update(
            //TODO: FOREIGN KEYS PREVENT THIS FROM FUNCTIONNING PROPERLY. AUTH0 CODES ARE HARD CODED, FIX THIS *QUICKLY*
            "INSERT INTO Invitation_événement (codeDestinataire, codeExpediteur, idÉvénement, status) VALUES ('auth0|656d3f1aa19599c9209a6371', 'auth0|656d3f1aa19599c9209a6371', $idEvenement,'généré'); ")
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

    override fun validerUtilisateur(id: String, code_util: String): Boolean {
        if (id == code_util ) {
            return true
        }
        return false
    }

//    override fun validerÉvènement(idEvenement: Int, code_util: String): Boolean {
//        var evenement: Événement?
//        try {
//            evenement = db.queryForObject("SELECT * FROM Événement WHERE organisation_id IN (SELECT id FROM Organisation WHERE codeUtilisateur = $code_util);", )
//        } catch ()
//    }
}