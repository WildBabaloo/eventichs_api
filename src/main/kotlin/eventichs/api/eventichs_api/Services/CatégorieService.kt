package eventichs.api.eventichs_api.Services

import eventichs.api.eventichs_api.DAO.CatégorieDAO
import eventichs.api.eventichs_api.Exceptions.DroitAccèsInsuffisantException
import eventichs.api.eventichs_api.Modèle.Catégorie
import org.springframework.stereotype.Service
import java.security.Principal

@Service
class CatégorieService(val dao : CatégorieDAO) {

    fun chercherTous(name: String): List<Catégorie> = dao.chercherTous()

    fun chercherParID(id: Int, code_util: String): Catégorie? {
        if (dao.validerUtilisateur(code_util) == false) {
            throw DroitAccèsInsuffisantException("L'utilisateur n'a pas le droit de consulter cette catégorie.")
        }
        return dao.chercherParID(id)
    }
    fun modifierCatégorie(catégorie: Catégorie, principal: Principal?) : Catégorie? = dao.modifier(catégorie)

    fun ajouterCatégorie(catégorie: Catégorie, principal: Any?): Catégorie? = dao.ajouter(catégorie)
    fun supprimerParID(id: Int,code_util: String) : Catégorie? {
        if (dao.validerUtilisateur(code_util) == false) {
            throw DroitAccèsInsuffisantException("L'utilisateur n'a pas le droit de consulter cette catégorie.")
        }
        return dao.supprimerParID(id)
    }

}

