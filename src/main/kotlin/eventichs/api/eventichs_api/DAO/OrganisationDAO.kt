package eventichs.api.eventichs_api.DAO

import eventichs.api.eventichs_api.Modèle.Categorie
import eventichs.api.eventichs_api.Modèle.Organisation

interface OrganisationDAO: DAO<Organisation> {
    override fun chercherTous(): List<Organisation>
    override fun chercherParID(id: Int): Organisation?
    override fun ajouter(element: Organisation): Organisation?
    override fun supprimerParID(id: Int): Organisation?
    override fun modifier(element: Organisation): Organisation?
    fun consulterOrganisationPubliques(): List<Organisation>
    fun filtrerOrganisationParGouts(uneCategorie: Categorie): List<Organisation>
    fun changerVisiblitéOrganisation(uneOrganisation: Organisation, estPublic: Boolean)


}