package eventichs.api.eventichs_api.DAO

import eventichs.api.eventichs_api.Modèle.InvitationÉvénement

interface InvitationÉvénementDAO: DAO<InvitationÉvénement> {


    override fun chercherTous(): List<InvitationÉvénement>

    override fun ajouter(element: InvitationÉvénement): InvitationÉvénement?

    override fun chercherParID(id: Int): InvitationÉvénement?

    override fun modifier(element: InvitationÉvénement): InvitationÉvénement?

    override fun supprimerParID(id: Int): InvitationÉvénement?

    fun chercherParIdDestinataire(id: Int): List<InvitationÉvénement>

    fun chercherParIdExpediteur(id: Int): List<InvitationÉvénement>

    fun entrerJetonEvenement(id: Int, jeton: String): InvitationÉvénement?

    fun creerJeton(idEvenement: Int): InvitationÉvénement?
    fun validerUtilisateur(id : Int, code_util: String): Boolean

}