package eventichs.api.eventichs_api.DAO


import eventichs.api.eventichs_api.Mapper.CatégorieMapper
import eventichs.api.eventichs_api.Mapper.EvenementMapper
import eventichs.api.eventichs_api.Mapper.OrganisationMapper
import eventichs.api.eventichs_api.Modèle.Événement
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class EvenementDAOImplMemoire(val db: JdbcTemplate) : EvenementDAO {
    override fun chercherTous(): List<Événement> =
            db.query("select *, Catégorie.nom as categorie, Organisation.nomOrganisation as organisation " +
                    "from Événement " +
                    "join Catégorie on Événement.categorie_id = Catégorie.id" +
                    "join Organisation on Événement.organisation_id = Organisation.id", EvenementMapper())

    override fun chercherParID(id: Int): Événement? =
            db.queryForObject("select * from Événement where id = $id", EvenementMapper())
    override fun chercherParType(type: String): List<Événement> =
        db.query("select * from Événement where type = $type", EvenementMapper())

    override fun chercherParOrganisation(organisation: String): List<Événement> {

        val org = db.queryForObject("select * from Organisation where nomOrganisation = $organisation", OrganisationMapper())
        val id = org?.nomOrganisation
        return db.query("select * from Événement where organisation_id = $id", EvenementMapper())
    }

    override fun chercherParCategorie(categorie: String): List<Événement> {
        val org =
            db.queryForObject("select * from Catégorie where nomOrganisation = $categorie", OrganisationMapper())
        val id = org?.nomOrganisation
        return db.query("select * from Événement where organisation_id = $id", EvenementMapper())
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

