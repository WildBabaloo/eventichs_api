package eventichs.api.eventichs_api.DAO

import eventichs.api.eventichs_api.Modèle.Utilisateur

interface UtilisateurDAO : DAO<Utilisateur> {
    override fun chercherTous(): List<Utilisateur>

    fun chercherParID(id: String): Utilisateur?

    override fun ajouter(element: Utilisateur): Utilisateur?

    override fun supprimerParID(id: Int): Utilisateur?

    override fun modifier(element: Utilisateur): Utilisateur?
}