package eventichs.api.eventichs_api.Modèle;

data class Utilisateur(
    val code: String,
    var email: String,
    var nom: String,
    var prénom: String
) {
}
