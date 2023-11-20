package eventichs.api.eventichs_api.DAO

import eventichs.api.eventichs_api.Modèle.InvitationÉvénement

interface InvitationÉvénementDAO: DAO<InvitationÉvénement> {


    override fun chercherTous(): List<InvitationÉvénement> {
        TODO("Not yet implemented")
    }

    override fun ajouter(element: InvitationÉvénement): InvitationÉvénement? {
        TODO("Not yet implemented")
    }

    override fun chercherParID(id: Int): InvitationÉvénement? {
        TODO("Not yet implemented")
    }

    override fun modifier(element: InvitationÉvénement): InvitationÉvénement? {
        TODO("Not yet implemented")
    }

    override fun supprimerParID(id: Int): InvitationÉvénement? {
        TODO("Not yet implemented")
    }

    fun chercherParIdDestinataire(id: Int): List<InvitationÉvénement> {
        TODO("Not yet implemented")
    }

    fun chercherParIdExpediteur(id: Int): List<InvitationÉvénement> {
        TODO("Not yet implemented")
    }

}