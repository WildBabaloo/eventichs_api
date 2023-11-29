package eventichs.api.eventichs_api.DAO


import eventichs.api.eventichs_api.Mapper.CatégorieMapper
import eventichs.api.eventichs_api.Mapper.EvenementMapper
import eventichs.api.eventichs_api.Mapper.OrganisationMapper
import eventichs.api.eventichs_api.Modèle.Événement
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class EvenementDAOImplMemoire(val db: JdbcTemplate) : EvenementDAO {
    val selectQuery = "select Événement.id, Événement.nom, Événement.adresse, Événement.dateDebut, Événement.dateFin, Événement.type, Catégorie.nom as categorie, Événement.description, Événement.image, Organisation.nomOrganisation as organisation from Événement join Catégorie on Événement.categorie_id = Catégorie.id join Organisation on Événement.organisation_id = Organisation.id"
    override fun chercherTous(): List<Événement> =
            db.query("$selectQuery", EvenementMapper())

    override fun chercherParID(id: Int): Événement? =
            db.queryForObject("$selectQuery where Événement.id = $id", EvenementMapper())
    override fun chercherEvenementPublic(): List<Événement> =
        db.query("$selectQuery where Événement.type = 'public'", EvenementMapper())

    override fun chercherParOrganisation(organisation: String): List<Événement> {

        val org = db.queryForObject("select * from Organisation where nomOrganisation = $organisation", OrganisationMapper())
        val id = org?.nomOrganisation
        return db.query("$selectQuery where Événement.organisation_id = $id", EvenementMapper())
    }

    override fun chercherParCategorie(categorie: String): List<Événement> {
        val org =
            db.queryForObject("select * from Catégorie where nomOrganisation = $categorie", OrganisationMapper())
        val id = org?.nomOrganisation
        return db.query("$selectQuery where Événement.organisation_id = $id", EvenementMapper())
    }

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
                element.categorie,
                element.description,
                element.image,
                element.organisation)
        return element
    }


    override fun modifier(element: Événement): Événement{
        db.update(
            "UPDATE Événement SET nom = ?, dateDebut = ?, dateFin = ?, type = ?, categorie_id = ?, description = ?, image = ?, organisation_id = ? WHERE id =$element.id",

                element.nom,
                element.dateDebut,
                element.dateFin,
                element.type,
                element.categorie,
                element.description,
                element.organisation,
                element.image)
            return element
    }
}

