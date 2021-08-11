package br.com.itau.bff.gateway.database.postgres.impl

import br.com.itau.bff.domain.UserDomain
import br.com.itau.bff.gateway.GetUserByIdGateway
import br.com.itau.bff.gateway.database.postgres.repository.UserRepository
import br.com.itau.bff.gateway.database.postgres.translator.UserDBToUserDomainTranslator
import java.util.*
import javax.inject.Named

@Named
class GetUserByIdGatewayImpl(private val userRepository: UserRepository): GetUserByIdGateway {

    override fun execute(userId: UUID): Optional<UserDomain> {
        val userDB = userRepository.findByPublicId(userId)
        if (userDB.isPresent) {
            val userDomain = UserDBToUserDomainTranslator().translate(userDB.get())
            return Optional.of(userDomain)
        }

        return Optional.empty()
    }

}