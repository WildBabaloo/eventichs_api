package eventichs.api.eventichs_api.Modèle;

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.boot.jackson.JsonComponent

data class InvitationOrganisation(
    @JsonProperty("id") val id: Int,
    @JsonProperty("utilisateur") var Utilisateur: Utilisateur?,
    @JsonProperty("organisation") val Organisation: Organisation,
    @JsonProperty("jeton") var jeton: String?,
    @JsonProperty("status") var status: String) {}