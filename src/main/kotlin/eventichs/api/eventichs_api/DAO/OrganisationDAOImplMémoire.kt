package eventichs.api.eventichs_api.DAO

import eventichs.api.eventichs_api.Modèle.Categorie
import eventichs.api.eventichs_api.Modèle.Organisation
import eventichs.api.eventichs_api.Modèle.Utilisateur
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class OrganisationDAOImplMémoire(val db: JdbcTemplate): OrganisationDAO {
    override fun chercherOrganisations(): List<Organisation> =
        db.query("select * from organisation", OrganisationMapper())

    override fun chercherOrganisationParCode(codeOrganisation: Int): Organisation? =
        db.queryForObject("select * from organisation where id = $codeOrganisation", OrganisationMapper())

    override fun ajouterOrganisation(uneOrganisation: Organisation): Organisation? {
        db.update(
            "insert into organisation values (?, ?, ?, ?)",
            uneOrganisation.id,
            uneOrganisation.idUtilisateur,
            uneOrganisation.nomOrganisation,
            uneOrganisation.catégorie_id,
            uneOrganisation.estPublic
        )
        return uneOrganisation
    }

    override fun modifierOrganisation(codeOrganisation: Int, uneOrganisation: Organisation): Organisation? {
        db.update(
            "update organisation set nomOrganisation = ?, catégorie_id = ? where id = $codeOrganisation",
            uneOrganisation.nomOrganisation,
            uneOrganisation.catégorie_id
        )
        return uneOrganisation
    }

    override fun deleteOrganisation(codeOrganisation: Int) {
        db.update(
            "delete from organisation where id = $codeOrganisation"
        )
    }

    override fun consulterOrganisationPubliques(): List<Organisation> {
        TODO("Not yet implemented")
    }

    override fun filtrerOrganisationParGouts(uneCategorie: Categorie): List<Organisation> {
        TODO("Not yet implemented")
    }

    override fun ajouterParticipant(codeOrganisation: Organisation, unParticipant: Utilisateur): Organisation? {
        TODO("Not yet implemented")
    }

    override fun enleverParticipant(codeOrganisation: Organisation, unParticipant: Utilisateur): Organisation? {
        TODO("Not yet implemented")
    }

    override fun changerVisiblitéOrganisation(codeOrganisation: Organisation, estPublic: Boolean): Organisation? {
        TODO("Not yet implemented")
    }
}