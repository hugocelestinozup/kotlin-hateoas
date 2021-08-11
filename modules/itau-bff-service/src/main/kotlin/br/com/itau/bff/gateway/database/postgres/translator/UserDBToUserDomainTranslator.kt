package br.com.itau.bff.gateway.database.postgres.translator

import br.com.itau.bff.domain.UserDomain
import br.com.itau.bff.gateway.database.postgres.model.UserDB

class UserDBToUserDomainTranslator {

    fun translate(userDB: UserDB) = UserDomain(
        publicId = userDB.publicId,
        name = userDB.name,
        email = userDB.email,
        createdDate = userDB.createdDate,
        updatedDate = userDB.updatedDate
    )

    fun translate(dbList: List<UserDB>) = dbList.map { userDB ->
        translate(userDB)
    }

}