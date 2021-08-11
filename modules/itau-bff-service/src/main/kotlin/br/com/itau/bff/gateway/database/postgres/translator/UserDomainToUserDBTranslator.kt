package br.com.itau.bff.gateway.database.postgres.translator

import br.com.itau.bff.domain.UserDomain
import br.com.itau.bff.gateway.database.postgres.model.UserDB

class UserDomainToUserDBTranslator {

    fun translate(userDomain: UserDomain) = UserDB(
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