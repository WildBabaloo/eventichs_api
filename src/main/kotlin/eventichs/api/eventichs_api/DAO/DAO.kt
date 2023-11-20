package eventichs.api.eventichs_api.DAO

import eventichs.api.eventichs_api.Modèle.Categorie
import eventichs.api.eventichs_api.Modèle.Organisation
import eventichs.api.eventichs_api.Modèle.Utilisateur

interface DAO<T> {
    fun chercherOrganisations(): List<Organisation>
    fun chercherOrganisationParCode(codeOrganisation: Int): Organisation?
    fun ajouterOrganisation(uneOrganisation: Organisation): Organisation?
    fun modifierOrganisation(codeOrganisation: Int, uneOrganisation: Organisation): Organisation?
    fun deleteOrganisation(codeOrganisation: Int)
    fun consulterOrganisationPubliques(): List<Organisation>
    fun filtrerOrganisationParGouts(uneCategorie: Categorie): List<Organisation>
    fun ajouterParticipant(codeOrganisation: Organisation, unParticipant: Utilisateur): Organisation?
    fun enleverParticipant(codeOrganisation: Organisation, unParticipant: Utilisateur): Organisation?
    fun changerVisiblitéOrganisation(codeOrganisation: Organisation, estPublic: Boolean): Organisation?
}