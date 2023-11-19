package eventichs.api.eventichs_api.Modèle

import org.springframework.data.relational.core.sql.In

data class Organisation(val id: Int, val idUtilisateur: Int, val catégorie_id: Int, val estPublic: Boolean ) {
}