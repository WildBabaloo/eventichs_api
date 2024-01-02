package eventichs.api.eventichs_api.Services

import org.springframework.stereotype.Service
import eventichs.api.eventichs_api.DAO.EvenementDAO

import eventichs.api.eventichs_api.Exceptions.DroitAccèsInsuffisantException
import eventichs.api.eventichs_api.Modèle.Événement


@Service
class EvenementService(val dao : EvenementDAO) {
    fun chercherTous(name: String): List<Événement> {
        return dao.chercherTous()
    }
    fun chercherParID(id: Int, name: String): Événement? {
        if (!dao.validerParticipant(id,name)){throw DroitAccèsInsuffisantException("Droits d'accès insuffisants")}
        return dao.chercherParID(id)
    }

    fun chercherParOrganisation(id: Int, name: String) : List<Événement>{
        if (!dao.validerMembreOrganisation(id,name)){throw DroitAccèsInsuffisantException("Droits d'accès insuffisants")}
        return dao.chercherParOrganisation(id)
    }
    fun supprimerParID(id:Int, name : String) : Événement?  {
        if (!dao.validerOrganisateur(id,name)){throw DroitAccèsInsuffisantException("Droits d'accès insuffisants")}
        return dao.supprimerParID(id)
    }
    fun modifierEvenement(id: Int, evenement: Événement, name : String) : Événement? {
        if (!dao.validerOrganisateur(id,name)){throw DroitAccèsInsuffisantException("Droits d'accès insuffisants")}
        return dao.modifier(id,evenement)
    }
    fun ajouterEvenement(evenement: Événement, name: String) : Événement? {
        if (!dao.validerOrganisateur(evenement.organisation,name)){throw DroitAccèsInsuffisantException("Droits d'accès insuffisants")}

        return dao.ajouter(evenement)
    }
}
