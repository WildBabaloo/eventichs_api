package eventichs.api.eventichs_api.DAO

import eventichs.api.eventichs_api.Modèle.UtilisateurÉvénement
import eventichs.api.eventichs_api.Modèle.Événement
import org.springframework.jdbc.core.JdbcTemplate

class UtilisateurEvenementDAOImplMemoire(val db: JdbcTemplate) : UtilisateurEvenementDAO {
    override fun chercherTous(): List<UtilisateurÉvénement> {
        TODO("Not yet implemented")
    }

    override fun chercherParID(id: Int): UtilisateurÉvénement? {
        TODO("Not yet implemented")
    }

    override fun chercherParOrganisation(organisation_id: Int): List<UtilisateurÉvénement> {
        TODO("Not yet implemented")
    }

    override fun supprimerParID(id: Int): UtilisateurÉvénement? {
        TODO("Not yet implemented")
    }

    override fun modifier(element: UtilisateurÉvénement): UtilisateurÉvénement? {
        TODO("Not yet implemented")
    }

    override fun ajouter(element: UtilisateurÉvénement): UtilisateurÉvénement? {
        TODO("Not yet implemented")
    }

}