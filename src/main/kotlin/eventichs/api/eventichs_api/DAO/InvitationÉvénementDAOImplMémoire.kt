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
        TODO("Not yet implemented")
    }

    override fun ajouter(element: InvitationÉvénement): InvitationÉvénement? {
        return super.ajouter(element)
    }

    override fun chercherTous(): List<InvitationÉvénement> {
        return super.chercherTous()
    }

    override fun chercherParID(id: Int): InvitationÉvénement? {
        return super.chercherParID(id)
    }

    override fun chercherParIdDestinataire(id: Int): List<InvitationÉvénement> {
        return db.query("SELECT * FROM Invitation_événement WHERE idDestinataire = $id", InvitationÉvénementMapper())
    }
}