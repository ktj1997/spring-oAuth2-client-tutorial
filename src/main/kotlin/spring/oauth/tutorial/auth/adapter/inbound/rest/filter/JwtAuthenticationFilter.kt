package spring.oauth.tutorial.auth.adapter.inbound.rest.filter

import io.jsonwebtoken.io.IOException
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import spring.oauth.tutorial.auth.applicaiton.inbound.rest.filter.AuthorizeUserUseCase
import spring.oauth.tutorial.auth.domain.TokenType

class JwtAuthenticationFilter(
    private val authorizeUserUseCase: AuthorizeUserUseCase
) : OncePerRequestFilter() {

    @Throws(IOException::class, ServletException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token: String? = request.getHeader("Authorization")

        token?.let {
            val userIdentifier = authorizeUserUseCase.parseUserIdentifierFromToken(token, TokenType.ACCESS)
            SecurityContextHolder.getContext().authentication = UsernamePasswordAuthenticationToken(userIdentifier, "", null)
        }
        filterChain.doFilter(request, response)
    }
}
