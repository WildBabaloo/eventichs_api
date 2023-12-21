package eventichs.api.eventichs_api.GestionAccès

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator
import org.springframework.security.oauth2.core.OAuth2TokenValidator
import org.springframework.security.oauth2.jwt.JwtValidators
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.oauth2.jwt.*


@Configuration
@EnableWebSecurity
class ServeurRessourcesOAuth2ConfigurationAccès {
    @Value("\${auth0.audience}")
    private lateinit var audience: String

    @Value("\${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private lateinit var issuer: String

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http {
            authorizeHttpRequests {
                authorize("/", permitAll)
                authorize(HttpMethod.GET, "/**", permitAll)
                authorize(HttpMethod.POST, "/**", permitAll)
                authorize(HttpMethod.GET,"organisations/**",authenticated)
                authorize(HttpMethod.PUT,"organisations/**",authenticated)
                authorize(HttpMethod.POST, "/organisations/invitations/", permitAll)
                authorize(HttpMethod.PUT, "/**", permitAll)
                authorize(HttpMethod.DELETE, "/**", permitAll)
                authorize(anyRequest, authenticated)
            }
            oauth2ResourceServer {
                jwt { }
            }
            csrf { disable() } // désactivation temporaire de la protection par défaut de spring sur les opérations POST, PUT et DELETE
        }
        return http.build()
    }

    @Bean
    fun jwtDecoder(): JwtDecoder {
        val withAudience: OAuth2TokenValidator<Jwt> = AudienceValidateur(audience)
        val withIssuer: OAuth2TokenValidator<Jwt> = JwtValidators.createDefaultWithIssuer(issuer)
        val validator: OAuth2TokenValidator<Jwt> = DelegatingOAuth2TokenValidator(withAudience, withIssuer);

        val jwtDecoder: NimbusJwtDecoder = JwtDecoders.fromOidcIssuerLocation(issuer) as NimbusJwtDecoder
        jwtDecoder.setJwtValidator(validator)
        return jwtDecoder
    }

}
