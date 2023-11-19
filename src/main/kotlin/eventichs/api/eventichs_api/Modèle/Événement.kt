package eventichs.api.eventichs_api.Modèle;

import java.util.*

data class Événement(
        val id: Int,
        val nom: String,
        val dateDebut: Date,
        val dateFin: Date,
        val type: String,
        val categorie_id: Int,
        val description: String,
        val photo: String,
        val organisation_Id: Int)
