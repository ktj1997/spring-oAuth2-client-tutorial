package spring.oauth.tutorial.auth.applicaiton.inbound.rest.controller.model

import spring.oauth.tutorial.auth.domain.OAuthType

data class SignUpUseCaseInput(
    val userName: String?,
    val password: String?,
    val oAuthType: OAuthType,
    val email: String,
)
