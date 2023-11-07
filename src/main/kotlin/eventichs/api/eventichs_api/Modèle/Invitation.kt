package eventichs.api.eventichs_api.Modèle;

data class Invitation(val id: Int, val codeExpediteur: Int, val codeDestinataire: Int, val codeQuoiRejoindre: Int, var status: String) {
}
