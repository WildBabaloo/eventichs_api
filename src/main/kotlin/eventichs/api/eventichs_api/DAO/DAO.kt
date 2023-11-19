package eventichs.api.eventichs_api.DAO

interface DAO<T> {
    fun chercherTous(): List<T>
    fun chercherParID(id: Int): T?
    fun ajouter(element: T): T?

}