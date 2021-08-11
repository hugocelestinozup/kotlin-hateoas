package br.com.itau.bff.usecase

import br.com.itau.bff.domain.UserDomain
import br.com.itau.bff.exception.NotFoundException
import br.com.itau.bff.gateway.GetUserByIdGateway
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.*
import javax.inject.Named

@Named
class GetUserByIdUseCase(private val getUserByIdGateway: GetUserByIdGateway) {

    private val logger: Logger = LoggerFactory.getLogger(GetUserByIdUseCase::class.java)

    fun execute(userId: UUID): UserDomain {
        val userDomain = getUserByIdGateway.execute(userId)

        if (userDomain.isPresent) {
            return userDomain.get()
        }

        logger.info("User not found: $userId")
        throw NotFoundException("Usuário não encontrado: $userId")
    }

}