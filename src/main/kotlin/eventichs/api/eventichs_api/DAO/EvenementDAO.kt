package eventichs.api.eventichs_api.DAO

import eventichs.api.eventichs_api.Modèle.Événement

interface EvenementDAO : DAO<Événement> {
    override fun chercherTous(): List<Événement>
    override fun chercherParID(id: Int): Événement?
    fun chercherParCategorie(categorie: String): List<Événement>
    fun chercherParOrganisation(organisation: Int): List<Événement>
    fun chercherEvenementPublic() : List<Événement>
    override fun supprimerParID(id : Int) : Événement?

    fun modifier(id : Int, element: Événement): Événement?

    override fun ajouter(element: Événement): Événement?

    fun validerParticipant(idEvent : Int, codeUtil : String): Boolean
    fun validerMembreOrganisation(idEvent : Int, codeUtil : String): Boolean
    fun validerOrganisateur(idEvent : Int, codeUtil : String): Boolean

}
