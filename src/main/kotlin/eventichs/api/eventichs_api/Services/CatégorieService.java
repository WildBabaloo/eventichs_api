package eventichs.api.eventichs_api.Services;

import eventichs.api.eventichs_api.DAO.CatégorieDAO;
import eventichs.api.eventichs_api.Modèle.Catégorie;
import eventichs.api.eventichs_api.Modèle.Événement;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class CatégorieService(val dao : CatégorieDAO) {

    fun chercherTous() : List<Catégorie> = dao.chercherTous()

    fun chercherParID(id: Int): Catégorie? = dao.chercherParID(id)

    fun supprimerParID(id: Int) : Catégorie? = dao.modifier(catégorie)

    fun modifierCatégorie(catégorie: Catégorie) : Catégorie? = dao.modifier(catégorie)

    fun ajouterCatégorie(catégorie: Catégorie): Catégorie? = dao.ajouter(catégorie)


}
