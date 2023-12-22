package eventichs.api.eventichs_api.DAO


import eventichs.api.eventichs_api.Exceptions.RessourceInexistanteException
import eventichs.api.eventichs_api.Mapper.CatégorieMapper
import eventichs.api.eventichs_api.Mapper.EvenementMapper
import eventichs.api.eventichs_api.Mapper.OrganisationMapper
import eventichs.api.eventichs_api.Modèle.Événement
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class EvenementDAOImplMémoire(val db: JdbcTemplate) : EvenementDAO {
    val selectQuery = "SELECT * FROM Événement "
    override fun chercherTous(): List<Événement> =
            db.query(selectQuery, EvenementMapper())

    override fun chercherParID(id: Int): Événement? =
            db.queryForObject("$selectQuery where id = $id", EvenementMapper())
    override fun chercherEvenementPublic(): List<Événement> =
        db.query("$selectQuery where type = 'public'", EvenementMapper())

    override fun chercherParOrganisation(organisation: Int): List<Événement> {

        return db.query("$selectQuery where organisation_id = $organisation", EvenementMapper())
    }



    override fun supprimerParID(id: Int): Événement? {
        val element = db.queryForObject("$selectQuery where id = $id", EvenementMapper())
        db.update("DELETE from Événement where id = $id")
        return element
    }

    override fun ajouter(element: Événement): Événement? {
        db.update(
                "insert into Événement(nom, adresse, dateDebut, dateFin, type, categorie_id, description,image,organisation_id) values ( ?, ?, ? , ?, ?, ?, ?, ?, ?)",
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

    override fun modifier(id : Int, element: Événement): Événement{
        db.update("UPDATE Événement SET nom = ? WHERE id =$id", element.nom)
        db.update("UPDATE Événement SET adresse = ? WHERE id =$id", element.adresse)
        db.update("UPDATE Événement SET dateDebut = ? WHERE id =$id", element.dateDebut)
        db.update("UPDATE Événement SET dateFin = ? WHERE id =$id",element.dateFin)
        db.update("UPDATE Événement SET type = ? WHERE id =$id",element.type)
        db.update("UPDATE Événement SET categorie_id = ? WHERE id =$id", element.categorie)
        db.update("UPDATE Événement SET description = ? WHERE id =$id",element.description)
        db.update("UPDATE Événement SET image = ? WHERE id =$id", element.image)
        db.update("UPDATE Événement SET organisation_id = ? WHERE id =$id", element.organisation)

        return element
    }
    override fun validerParticipant(idEvent : Int, codeUtil : String): Boolean{
        val dao = UtilisateurEvenementDAOImplMemoire(db)
        return  dao.chercherParID(idEvent, codeUtil) != null
    }
    override fun validerOrganisateur(idOrganisation: Int, codeUtil: String): Boolean {
        try {
            val orgdao = OrganisationDAOImplMémoire(db)
            val organisationMembres = orgdao.chercherParID(idOrganisation)

            // Check if the organization exists and the organizer's code matches
            return organisationMembres?.codeUtilisateur == codeUtil
        } catch (e: EmptyResultDataAccessException) {

            return false
        } catch (e: Exception) {
            throw RessourceInexistanteException("An error occurred while validating the organizer", e)
        }
    }

    override fun validerMembreOrganisation(idEvent : Int, codeUtil : String) : Boolean{
        val membreOrgDao = OrganisationMembersDAOImplMémoire(db)
        return membreOrgDao.existe(idEvent, codeUtil)
    }
    override fun modifier(element: Événement): Événement? {
        TODO("Not yet implemented")
    }
    override fun chercherParCategorie(categorie: String): List<Événement> {
        TODO("Not Used")
    }
}

