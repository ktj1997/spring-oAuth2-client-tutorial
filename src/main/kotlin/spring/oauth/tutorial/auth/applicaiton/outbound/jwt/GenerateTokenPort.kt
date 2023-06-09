package spring.oauth.tutorial.auth.applicaiton.outbound.jwt

import spring.oauth.tutorial.auth.domain.TokenType

interface GenerateTokenPort {
    fun generateToken(userIdentifier: String, tokenType: TokenType): String
}
