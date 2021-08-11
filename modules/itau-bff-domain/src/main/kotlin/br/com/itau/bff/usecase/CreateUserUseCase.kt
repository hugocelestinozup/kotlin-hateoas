package br.com.itau.bff.usecase

import br.com.itau.bff.domain.UserDomain
import br.com.itau.bff.exception.AlreadyReportedException
import br.com.itau.bff.gateway.GetUserByEmailGateway
import br.com.itau.bff.gateway.SaveUserGateway
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.time.LocalDateTime
import java.util.*
import javax.inject.Named

@Named
class CreateUserUseCase(
    private val saveUserGateway: SaveUserGateway,
    private val getUserByEmailGateway: GetUserByEmailGateway
) {

    private val logger: Logger = LoggerFactory.getLogger(CreateUserUseCase::class.java)

    fun execute(userDomain: UserDomain) : UserDomain {
        val registeredUser = getUserByEmailGateway.execute(userDomain.email)

        if (registeredUser.isPresent) {
            logger.error("Email already registered: ${userDomain.email}")
            throw AlreadyReportedException("Email já cadastrado: ${userDomain.email}")
        }

        val userToBeSaved = updateProperties(userDomain)
        return saveUserGateway.execute(userToBeSaved)
    }

    private fun updateProperties(userDomain: UserDomain): UserDomain {
        val currentDate = LocalDateTime.now()
        return userDomain.copy(
            publicId = UUID.randomUUID(),
            createdDate = currentDate,
            updatedDate = currentDate
        )
    }

}