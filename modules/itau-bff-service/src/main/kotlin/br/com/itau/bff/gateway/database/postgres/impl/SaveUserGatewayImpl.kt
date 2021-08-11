package br.com.itau.bff.gateway.database.postgres.impl

import br.com.itau.bff.domain.UserDomain
import br.com.itau.bff.gateway.SaveUserGateway
import br.com.itau.bff.gateway.database.postgres.repository.UserRepository
import br.com.itau.bff.gateway.database.postgres.translator.UserDBToUserDomainTranslator
import br.com.itau.bff.gateway.database.postgres.translator.UserDomainToUserDBTranslator
import javax.inject.Named

@Named
class SaveUserGatewayImpl(private val userRepository: UserRepository) : SaveUserGateway {

    override fun execute(userToBeSaved: UserDomain): UserDomain {
        var userDB = UserDomainToUserDBTranslator().translate(userToBeSaved)

        userDB = userRepository.save(userDB)

        return UserDBToUserDomainTranslator().translate(userDB)
    }

}