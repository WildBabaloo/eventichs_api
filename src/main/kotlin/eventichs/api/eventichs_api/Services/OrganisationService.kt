package eventichs.api.eventichs_api.Services

import eventichs.api.eventichs_api.DAO.OrganisationDAO
import eventichs.api.eventichs_api.Exceptions.DroitAccèsInsuffisantException
import eventichs.api.eventichs_api.Modèle.Organisation
import org.springframework.stereotype.Service

@Service
class OrganisationService(val dao: OrganisationDAO) {
    fun chercherTous(): List<Organisation> = dao.chercherTous()
    fun chercherParID(id: Int): Organisation? = dao.chercherParID(id)
    fun ajouter(element: Organisation, codeUtilisateur: String): Organisation? {
        if (!dao.validerOrganisationCreation(codeUtilisateur)){
            return null
        }
        return dao.ajouter(element, codeUtilisateur)
    }
    fun supprimerParID(id: Int, codeUtilisateur: String): Organisation? {
        if (!dao.validerUtilisateur(id, codeUtilisateur)) { throw DroitAccèsInsuffisantException("L'utilisateur n'as pas le droit de consulter cette organisation") }

        return dao.supprimerParID(id)
    }
    fun modifier(element: Organisation, codeUtilisateur: String): Organisation? {
        if (!dao.validerUtilisateur(element.id, codeUtilisateur)) { throw DroitAccèsInsuffisantException("L'utilisateur n'as pas le droit de consulter cette organisation") }

        return dao.modifier(element)
    }
    fun consulterOrganisationPubliques(): List<Organisation> = dao.consulterOrganisationPubliques()
    fun filtrerOrganisationParGouts(idCategorie: Int): List<Organisation> = dao.filtrerOrganisationParGouts(idCategorie)
}