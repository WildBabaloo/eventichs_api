package eventichs.api.eventichs_api.DAO

import eventichs.api.eventichs_api.Modèle.Categorie
import eventichs.api.eventichs_api.Modèle.Organisation
import eventichs.api.eventichs_api.Modèle.Utilisateur

interface OrganisationDAO: DAO<Organisation> {
    override fun chercherOrganisations(): List<Organisation>
    override fun chercherOrganisationParCode(codeOrganisation: Organisation): Organisation?
    override fun ajouterOrganisation(uneOrganisation: Organisation): Organisation?
    override fun modifierOrganisation(codeOrganisation: Organisation, uneOrganisation: Organisation): Organisation?
    override fun deleteOrganisation(codeOrganisation: Organisation)
    override fun consulterOrganisationPubliques(): List<Organisation>
    override fun filtrerOrganisationParGouts(uneCategorie: Categorie): List<Organisation>
    // AJOUTER PARTICIPANT COULD BE IN INVITATIONS IM NOT SURE
    override fun ajouterParticipant(codeOrganisation: Organisation, unParticipant: Utilisateur): Organisation?

    override fun enleverParticipant(codeOrganisation: Organisation, unParticipant: Utilisateur): Organisation?
    override fun changerVisiblitéOrganisation(codeOrganisation: Organisation, estPublic: Boolean): Organisation?


}