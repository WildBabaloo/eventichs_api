package eventichs.api.eventichs_api.DAO

import eventichs.api.eventichs_api.Modèle.Categorie
import eventichs.api.eventichs_api.Modèle.Organisation
import eventichs.api.eventichs_api.Modèle.Utilisateur

interface DAO<T> {
    fun chercherTous(): List<T>
    fun chercherParID(id: Int): T?
    fun ajouter(element: T): T?
    fun supprimerParID(id : Int) : T?
    fun modifier(element : T) : T?

}