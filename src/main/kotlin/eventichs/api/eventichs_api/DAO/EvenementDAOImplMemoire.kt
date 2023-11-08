package eventichs.api.eventichs_api.DAO

import eventichs.api.eventichs_api.DAO.SourceDeDonnees
import eventichs.api.eventichs_api.Modele.Evenement
import org.springframework.stereotype.Repository

@Repository
class EvenementDAOImplMemoire : EvenementDAO {
    override fun chercherTous(): List<Evenement> = SourceDeDonnees.evenements
    override fun chercherParCode(code: Int): Evenement? = SourceDeDonnees.evenements.find{it.id == code}
    override fun chercherParOrganisation(code: Int): List<Evenement> = emptyList()//= SourceDeDonnees.evenements.find{it.code == code}?.evenements ?: emptyList()

    override fun ajouter(element: Evenement): Evenement? {
        TODO("Not yet implemented")
    }
}