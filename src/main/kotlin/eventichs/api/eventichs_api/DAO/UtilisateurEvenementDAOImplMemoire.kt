package eventichs.api.eventichs_api.DAO

import eventichs.api.eventichs_api.Mapper.EvenementMapper
import eventichs.api.eventichs_api.Mapper.ParticipantMapper
import eventichs.api.eventichs_api.Mapper.UtilisateurEvenementMapper
import eventichs.api.eventichs_api.Modèle.Participant
import eventichs.api.eventichs_api.Modèle.UtilisateurÉvénement
import eventichs.api.eventichs_api.Modèle.Événement
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class UtilisateurEvenementDAOImplMemoire(val db: JdbcTemplate) : UtilisateurEvenementDAO {
    val selectQuery = "select Événement.id, Événement.nom, Événement.adresse, Événement.dateDebut, Événement.dateFin, Événement.type, Catégorie.nom as categorie, Événement.description, Événement.image, Organisation.nomOrganisation as organisation from Événement join Catégorie on Événement.categorie_id = Catégorie.id join Organisation on Événement.organisation_id = Organisation.id"
    override fun chercherTous(): List<UtilisateurÉvénement> =
            db.query("select * from Utilisateur_événement", UtilisateurEvenementMapper())
    override fun chercherParUtilisateurID(id: Int):  List<Événement> =
            db.query("$selectQuery inner join Utilisateur_événement on Utilisateur_événement.idEvenement = Événement.id WHERE Utilisateur_événement.idUtilisateur = $id", EvenementMapper())
    override fun chercherParEvenementID(id: Int): List<Participant> =
            db.query("select Utilisateur.id, Utilisateur.nom, Utilisateur.prénom From Utilisateur inner join Utilisateur_événement on Utilisateur_événement.idUtilisateur = Utilisateur.id WHERE Utilisateur_événement.idEvenement = $id", ParticipantMapper())
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