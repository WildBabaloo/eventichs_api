package eventichs.api.eventichs_api.Services


import eventichs.api.eventichs_api.DAO.CatégorieOrganisationDAO
import eventichs.api.eventichs_api.Modèle.Catégorie
import org.springframework.stereotype.Service
import java.security.Principal

@Service
class CatégorieOrganisationService(val dao : CatégorieOrganisationDAO) {

    fun chercherTous(name: String) : List<Catégorie> = dao.chercherTous()

    fun chercherParID(id: Int, name: String): Catégorie? = dao.chercherParID(id)

    fun supprimerParID(id: Int) : Catégorie? = dao.supprimerParID(id)

    fun modifierCatégorie(catégorie: Catégorie, principal: Principal?) : Catégorie? = dao.modifier(catégorie)

    fun ajouterCatégorie(catégorie: Catégorie, principal: Any?): Catégorie? = dao.ajouter(catégorie)


}
