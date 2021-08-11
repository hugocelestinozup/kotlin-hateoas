package br.com.itau.bff.usecase

import br.com.itau.bff.domain.UserDomain
import br.com.itau.bff.exception.NotFoundException
import br.com.itau.bff.gateway.GetUserByEmailGateway
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.inject.Named

@Named
class GetUserByEmailUseCase(private val getUserByEmailGateway: GetUserByEmailGateway) {

    private val logger: Logger = LoggerFactory.getLogger(GetUserByEmailUseCase::class.java)

    fun execute(email: String): UserDomain {
        val userDomain = getUserByEmailGateway.execute(email)

        if (userDomain.isPresent) {
            return userDomain.get()
        }

        logger.info("User not found: $email")
        throw NotFoundException("Usuário não encontrado: $email")
    }

}