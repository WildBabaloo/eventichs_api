package eventichs.api.eventichs_api.Modèle

data class InvitationÉvénement(val id: Int, val idExpéditeur: String, var idDestinataire: String, val idÉvénement: Int, val jeton: String?, var status: String) {
}
