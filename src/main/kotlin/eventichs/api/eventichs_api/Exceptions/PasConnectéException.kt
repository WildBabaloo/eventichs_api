package eventichs.api.eventichs_api.Exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.UNAUTHORIZED)
class PasConnectéException(message: String? = null, cause: Throwable? = null) : RuntimeException(message, cause) {
}
