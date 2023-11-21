package eventichs.api.eventichs_api.Services

import eventichs.api.eventichs_api.DAO.OrganisationDAO
import eventichs.api.eventichs_api.Modèle.Categorie
import eventichs.api.eventichs_api.Modèle.Organisation
import org.springframework.stereotype.Service

@Service
class OrganisationService(val dao: OrganisationDAO) {
    fun chercherTous(): List<Organisation> = dao.chercherTous()
    fun chercherParID(id: Int): Organisation? = dao.chercherParID(id)
    fun ajouter(element: Organisation): Organisation? = dao.ajouter(element)
    fun supprimerParID(id: Int): Organisation? = dao.supprimerParID(id)
    fun modifier(element: Organisation): Organisation? = dao.modifier(element)
    fun consulterOrganisationPubliques(): List<Organisation> = dao.consulterOrganisationPubliques()
    fun filtrerOrganisationParGouts(idCategorie: Int): List<Organisation> = dao.filtrerOrganisationParGouts(idCategorie)
}