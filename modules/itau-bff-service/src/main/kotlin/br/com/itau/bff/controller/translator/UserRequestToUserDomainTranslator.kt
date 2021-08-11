package br.com.itau.bff.controller.translator

import br.com.itau.bff.domain.UserDomain
import br.com.itau.bff.model.UserRequest

class UserRequestToUserDomainTranslator {

    fun translate(userRequest: UserRequest) = UserDomain(
        name = userRequest.name,
        email = userRequest.email
    )

}