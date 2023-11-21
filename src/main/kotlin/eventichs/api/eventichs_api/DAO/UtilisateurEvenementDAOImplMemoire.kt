package eventichs.api.eventichs_api.DAO

import eventichs.api.eventichs_api.Modèle.UtilisateurÉvénement
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class UtilisateurEvenementDAOImplMemoire(val db: JdbcTemplate) : UtilisateurEvenementDAO {
    override fun chercherTous(): List<UtilisateurÉvénement> =
            db.query("select * from Utilisateur_événement", UtilisateurEvenementMapper())
    override fun chercherParUtilisateurID(id: Int):  List<UtilisateurÉvénement> =
            db.query("select * from Utilisateur_événement where idUtilisateur = $id", UtilisateurEvenementMapper())
    override fun chercherParEvenementID(id: Int): List<UtilisateurÉvénement> =
            db.query("select * from Utilisateur_événement where idEvenement = $id", UtilisateurEvenementMapper())
    override fun supprimerParUtilisateurID(id: Int): UtilisateurÉvénement? {
        val element = db.queryForObject("select * from Utilisateur_événement where idUtilisateur = $id", UtilisateurEvenementMapper())
        db.update("DELETE from Utilisateur_événement where idUtilisateur = $id")
        return element
    }
    override fun supprimerParEvenementID(id: Int): UtilisateurÉvénement? {
        val element = db.queryForObject("select * from Utilisateur_événement where idEvenement = $id", UtilisateurEvenementMapper())
        db.update("DELETE from Utilisateur_événement where idEvenement = $id")
        return element
    }
    override fun ajouter(element: UtilisateurÉvénement): UtilisateurÉvénement? {
        db.update(
                "insert into Utilisateur_événement values (?, ?)",
                element.idUtilisateur,
                element.idEvenement)
        return element
    }

//Fonctions inutiles
override fun supprimerParID(id: Int): UtilisateurÉvénement? {
    TODO("Not yet implemented")
}
    override fun chercherParID(id: Int): UtilisateurÉvénement? {
        TODO("Not yet implemented")
    }
    override fun modifier(element: UtilisateurÉvénement): UtilisateurÉvénement? {
        TODO("Not yet implemented")
    }
}