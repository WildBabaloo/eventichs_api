package eventichs.api.eventichs_api.DAO


import eventichs.api.eventichs_api.Modèle.Événement
import org.springframework.jdbc.core.JdbcTemplate

import org.springframework.stereotype.Repository

@Repository
class EvenementDAOImplMemoire(val db: JdbcTemplate) : EvenementDAO {
    override fun chercherTous(): List<Événement> =
            db.query("select * from Événement", EvenementMapper())

    override fun chercherParID(id: Int): Événement? =
            db.queryForObject("select * from Événement where id = $id", EvenementMapper())

    override fun chercherParOrganisation(id: Int): List<Événement> =
            db.query("select * from Événement where organisation_id = $id", EvenementMapper())

    override fun supprimerParID(id: Int): Événement? {
        val element = db.queryForObject("select * from Événement where id = $id", EvenementMapper())
        db.update("DELETE from Événement where id = $id")
        return element
    }

    override fun ajouter(element: Événement): Événement {
        db.update(
                "insert into Événement values ( ?, ?, ?, ? , ?, ?, ?, ?, ?, ?)",
                element.id,
                element.nom,
                element.adresse,
                element.dateDebut,
                element.dateFin,
                element.type,
                element.categorie_Id,
                element.description,
                element.organisation_Id,
                element.photo)
        return element
    }


    override fun modifier(element: Événement): Événement{
        db.update(
            "UPDATE Événement SET nom = ?, dateDebut = ?, dateFin = ?, type = ?, categorie_id = ?, description = ?, photo = ?, organisation_id = ? WHERE id =$element.id",

                element.nom,
                element.dateDebut,
                element.dateFin,
                element.type,
                element.categorie_Id,
                element.description,
                element.organisation_Id,
                element.photo)
            return element
    }
}

