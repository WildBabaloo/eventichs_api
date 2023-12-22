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
            db.query(selectQuery, EvenementMapper())

    override fun chercherParID(id: Int): Événement? =
            db.queryForObject("$selectQuery where Événement.id = $id", EvenementMapper())
    override fun chercherEvenementPublic(): List<Événement> =
        db.query("$selectQuery where Événement.type = 'public'", EvenementMapper())

    override fun chercherParOrganisation(organisation: Int): List<Événement> {

        return db.query("$selectQuery where Événement.organisation_id = $organisation", EvenementMapper())
    }

    override fun chercherParCategorie(categorie: String): List<Événement> {
        val org =
            db.queryForObject("select * from Catégorie where nomOrganisation = $categorie", OrganisationMapper())
        val id = org?.nomOrganisation
        return db.query("$selectQuery where Événement.organisation_id = $id", EvenementMapper())
    }

    override fun supprimerParID(id: Int): Événement? {
        val element = db.queryForObject("$selectQuery where Événement.id = $id", EvenementMapper())
        db.update("DELETE from Événement where id = $id")
        return element
    }

    override fun ajouter(element: Événement): Événement? {
        val categorie = element.categorie
        val categorieID = db.queryForObject("select * from Catégorie where Catégorie.nom = '$categorie'", CatégorieMapper())
        val organisation = element.organisation
        val org = db.queryForObject("select * from Organisation where Organisation.nomOrganisation = '$organisation'", OrganisationMapper())
            db.update(
                    "insert into Événement values ( ?, ?, ? , ?, ?, ?, ?, ?, ?)",
                    element.nom,
                    element.adresse,
                    element.dateDebut,
                    element.dateFin,
                    element.type,
                    categorieID?.id,
                    element.description,
                    element.image,
                    org?.id)
            return element
    }

    override fun modifier(id : Int, element: Événement): Événement{
        val categorie = element.categorie
        val categorieID = db.queryForObject("select * from Catégorie where Catégorie.nom = '$categorie'", CatégorieMapper())
        val organisation = element.organisation
        val org = db.queryForObject("select * from Organisation where Organisation.nomOrganisation = '$organisation'", OrganisationMapper())
        db.update("UPDATE Événement SET nom = ? WHERE id =$id", element.nom)
        db.update("UPDATE Événement SET adresse = ? WHERE id =$id", element.adresse)
        db.update("UPDATE Événement SET dateDebut = ? WHERE id =$id", element.dateDebut)
        db.update("UPDATE Événement SET dateFin = ? WHERE id =$id",element.dateFin)
        db.update("UPDATE Événement SET type = ? WHERE id =$id",element.type)
        db.update("UPDATE Événement SET categorie_id = ? WHERE id =$id",categorieID?.id)
        db.update("UPDATE Événement SET description = ? WHERE id =$id",element.description)
        db.update("UPDATE Événement SET image = ? WHERE id =$id", element.image)
        db.update("UPDATE Événement SET organisation_id = ? WHERE id =$id", org?.id)

        return element
    }
    fun validerParticipant(idEvent : Int, codeUtil : String){

    }
    fun validerMembreOrganisation(){}
    override fun modifier(element: Événement): Événement? {
        TODO("Not yet implemented")
    }
}

