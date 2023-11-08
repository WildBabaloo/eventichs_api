package eventichs.api.eventichs_api.DAO

interface DAO<T> {
    fun chercherTous(): List<T>
    fun chercherParCode(code: Int): T?
    fun ajouter(element: T): T?
}