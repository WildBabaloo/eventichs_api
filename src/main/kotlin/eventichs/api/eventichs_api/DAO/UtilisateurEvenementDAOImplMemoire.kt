package eventichs.api.eventichs_api.DAO

import eventichs.api.eventichs_api.Mapper.EvenementMapper
import eventichs.api.eventichs_api.Mapper.OrganisationMapper
import eventichs.api.eventichs_api.Mapper.ParticipantMapper
import eventichs.api.eventichs_api.Mapper.UtilisateurEvenementMapper
import eventichs.api.eventichs_api.Modèle.Participant
import eventichs.api.eventichs_api.Modèle.UtilisateurÉvénement
import eventichs.api.eventichs_api.Modèle.Événement
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class UtilisateurEvenementDAOImplMemoire(val db: JdbcTemplate) : UtilisateurEvenementDAO {
    val selectQuery = "select Événement.id, Événement.nom, Événement.adresse, Événement.dateDebut, Événement.dateFin, Événement.type, Catégorie.nom as categorie, Événement.description, Événement.image, Événement.organisation_id  from Événement join Catégorie on Événement.categorie_id = Catégorie.id"
    override fun chercherTous(): List<UtilisateurÉvénement> =
            db.query("select * from Utilisateur_événement", UtilisateurEvenementMapper())
    override fun chercherParUtilisateurID(codeUtilisateur: String):  List<Événement> =
            db.query("$selectQuery inner join Utilisateur_événement on Utilisateur_événement.idEvenement = Événement.id WHERE Utilisateur_événement.codeUtilisateur = $codeUtilisateur", EvenementMapper())
    override fun chercherParEvenementID(id: Int): List<Participant> =
            db.query("select Utilisateur.code, Utilisateur.nom, Utilisateur.prénom From Utilisateur inner join Utilisateur_événement on Utilisateur_événement.codeUtilisateur = Utilisateur.code WHERE Utilisateur_événement.idEvenement = $id", ParticipantMapper())
    override fun supprimerParID(id: Int, codeUtilisateur: String): UtilisateurÉvénement? {
        val element = db.queryForObject("select * from Utilisateur_événement where codeUtilisateur = $codeUtilisateur", UtilisateurEvenementMapper())
        db.update("DELETE from Utilisateur_événement where codeUtilisateur = $codeUtilisateur")
        return element
    }

    override fun ajouter(element: UtilisateurÉvénement): UtilisateurÉvénement? {
        db.update(
                "insert into Utilisateur_événement values (?, ?)",
                element.codeUtilisateur,
                element.idEvenement)
        return element
    }

    fun chercherParID(eventId: Int, codeUtil : String): UtilisateurÉvénement? {
        val element = db.queryForObject("select * from Utilisateur_événement where codeUtilisateur = '$codeUtil' AND idEvenement = $eventId" , UtilisateurEvenementMapper())
        return element
    }

    override fun validerUtilisateur(eventId: Int, codeUtilisateur: String): Boolean {
        try {
            val sql = "SELECT organisation_id FROM Événement WHERE id = ?"
            val idOrg =  db.queryForObject(sql, Long::class.java, eventId)
            val organisation = db.queryForObject("SELECT * FROM Organisation WHERE id = $idOrg", OrganisationMapper())
            print(codeUtilisateur)
            print(organisation?.codeUtilisateur)
            return organisation?.codeUtilisateur == codeUtilisateur
        } catch (e: Exception) {
            return true
        }
    }

    //Fonctions inutiles
    override fun chercherParID(id: Int): UtilisateurÉvénement? {
        TODO("Not yet implemented")
    }
    override fun modifier(element: UtilisateurÉvénement): UtilisateurÉvénement? {
        TODO("Not yet implemented")
    }
    override fun supprimerParID(id: Int): UtilisateurÉvénement? {
        TODO("Not yet implemented")
    }
}