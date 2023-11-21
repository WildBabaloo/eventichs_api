package eventichs.api.eventichs_api.Modèle;

data class InvitationOrganisation(val id: Int, var idDestinataire: Int?, val idOrganisation: Int, val jeton: String?, var status: String) {
}
