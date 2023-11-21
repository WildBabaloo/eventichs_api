package eventichs.api.eventichs_api.Modèle

data class InvitationÉvénement(val id: Int, val idExpéditeur : Int, var idDestinataire: Int?, val idÉvénement: Int, val jeton: String?, var status: String) {
}
