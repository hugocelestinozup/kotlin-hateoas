package br.com.itau.bff.gateway.database.postgres.impl

import br.com.itau.bff.domain.UserDomain
import br.com.itau.bff.gateway.GetUserByEmailGateway
import br.com.itau.bff.gateway.database.postgres.repository.UserRepository
import br.com.itau.bff.gateway.database.postgres.translator.UserDBToUserDomainTranslator
import java.util.*
import javax.inject.Named

@Named
class GetUserByEmailGatewayImpl(private val userRepository: UserRepository) : GetUserByEmailGateway {

    override fun execute(email: String): Optional<UserDomain> {
        val userDB = userRepository.findByEmail(email)
        if (userDB.isPresent) {
            val userDomain = UserDBToUserDomainTranslator().translate(userDB.get())
            return Optional.of(userDomain)
        }

        return Optional.empty()
    }

}