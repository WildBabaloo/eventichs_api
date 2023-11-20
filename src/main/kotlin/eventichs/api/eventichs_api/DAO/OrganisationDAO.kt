package eventichs.api.eventichs_api.DAO

import eventichs.api.eventichs_api.Modèle.Categorie
import eventichs.api.eventichs_api.Modèle.Organisation
import eventichs.api.eventichs_api.Modèle.Utilisateur

interface OrganisationDAO: DAO<Organisation> {
    override fun chercherTous(): List<Organisation>
    override fun chercherParID(codeOrganisation: Int): Organisation?
    override fun ajouter(uneOrganisation: Organisation): Organisation?
    override fun supprimerParID(codeOrganisation: Int): Organisation?
    override fun modifier(uneOrganisation: Organisation): Organisation?
    fun consulterOrganisationPubliques(): List<Organisation>
    fun filtrerOrganisationParGouts(uneCategorie: Categorie): List<Organisation>
    fun changerVisiblitéOrganisation(codeOrganisation: Organisation, estPublic: Boolean): Organisation?


}