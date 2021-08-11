package br.com.itau.bff.controller.translator

import br.com.itau.bff.domain.UserDomain
import br.com.itau.bff.gateway.database.postgres.model.UserDB
import br.com.itau.bff.model.UserResponse

class UserDomainToUserResponseTranslator {

    fun translate(userDomain: UserDomain) = UserResponse(
        publicId = userDomain.publicId!!,
        name = userDomain.name,
        email = userDomain.email,
        createdDate = userDomain.createdDate,
        updatedDate = userDomain.updatedDate
    )

    fun translate(domainList: List<UserDomain>) = domainList.map { userDomain ->
        translate(userDomain)
    }

}