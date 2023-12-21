package eventichs.api.eventichs_api.Controleurs

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
class APIControleur {

        @GetMapping("/")
        fun index(principal: Principal?) : String {
            if (principal != null) {
                // Access user information from the Principal
                val username = principal .name;
                print(principal.name)
                return "Bonjour, $username! \n Service web du service de gestion d'événements";
            } else {
                return "Service web du service de gestion d'événements";
            }

    }
}