package eventichs.api.eventichs_api.Modele

import java.util.Date

class Evenement (val id: Int, val nom : String, val dateDebut : Date, val dateFin : Date, val type : String, val organisationId : String, val categorieId : String, val  description: String ){
}