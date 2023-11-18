package eventichs.api.eventichs_api.DAO

import eventichs.api.eventichs_api.Modèle.Categorie
import eventichs.api.eventichs_api.Modèle.Organisation
import eventichs.api.eventichs_api.Modèle.Utilisateur
import org.springframework.jdbc.core.JdbcTemplate

class OrganisationDAOImplMémoire(val db: JdbcTemplate): OrganisationDAO {
    // TODO
    override fun chercherOrganisations(): List<Organisation> {
        TODO("Not yet implemented")
    }

    override fun chercherOrganisationParCode(codeOrganisation: Organisation): Organisation? {
        TODO("Not yet implemented")
    }

    override fun ajouterOrganisation(uneOrganisation: Organisation): Organisation? {
        TODO("Not yet implemented")
    }

    override fun modifierOrganisation(codeOrganisation: Organisation, uneOrganisation: Organisation): Organisation? {
        TODO("Not yet implemented")
    }

    override fun deleteOrganisation(codeOrganisation: Organisation) {
        TODO("Not yet implemented")
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