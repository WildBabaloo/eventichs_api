package eventichs.api.eventichs_api.Modèle;

data class InvitationOrganisation(
    val id: Int,
    var Utilisateur: Utilisateur?,
    val Organisation: Organisation,
    val jeton: String?,
    var status: String) {}
