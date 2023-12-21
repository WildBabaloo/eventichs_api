package eventichs.api.eventichs_api.Services


import eventichs.api.eventichs_api.DAO.CatégorieOrganisationDAO
import eventichs.api.eventichs_api.Modèle.Catégorie
import org.springframework.stereotype.Service

@Service
class CatégorieOrganisationService(val dao : CatégorieOrganisationDAO) {

    fun chercherTous() : List<Catégorie> = dao.chercherTous()

    fun chercherParID(id: Int): Catégorie? = dao.chercherParID(id)

    fun supprimerParID(id: Int) : Catégorie? = dao.supprimerParID(id)

    fun modifierCatégorie(catégorie: Catégorie) : Catégorie? = dao.modifier(catégorie)

    fun ajouterCatégorie(catégorie: Catégorie): Catégorie? = dao.ajouter(catégorie)


}
