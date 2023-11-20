package eventichs.api.eventichs_api.DAO

import eventichs.api.eventichs_api.Modèle.InvitationÉvénement
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository


@Repository
class InvitationÉvénementDAOImplMémoire(val db: JdbcTemplate): InvitationÉvénementDAO {


    override fun supprimerParID(id: Int): InvitationÉvénement? {
        TODO("Not yet implemented")
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
}