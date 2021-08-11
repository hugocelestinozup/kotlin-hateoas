package br.com.itau.bff.usecase

import br.com.itau.bff.domain.UserDomain
import br.com.itau.bff.exception.AlreadyReportedException
import br.com.itau.bff.exception.BadRequestException
import br.com.itau.bff.gateway.GetEmailDataGateway
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
    private val getUserByEmailGateway: GetUserByEmailGateway,
    private val getEmailDataGateway: GetEmailDataGateway
) {

    private val logger: Logger = LoggerFactory.getLogger(CreateUserUseCase::class.java)

    fun execute(userDomain: UserDomain): UserDomain {
        validateEmail(userDomain.email)

        val userToBeSaved = updateProperties(userDomain)
        return saveUserGateway.execute(userToBeSaved)
    }

    private fun validateEmail(email: String) {
        validateEmailSyntax(email)
        validateEmailAlreadyRegistered(email)
    }

    private fun validateEmailSyntax(email: String) {
        val emailDomain = getEmailDataGateway.execute(email)
        if (!emailDomain.validSyntax) {
            logger.error("Invalid Email syntax: $email")
            throw BadRequestException("Email inválido: $email")
        }
    }

    private fun validateEmailAlreadyRegistered(email: String) {
        val registeredUser = getUserByEmailGateway.execute(email)
        if (registeredUser.isPresent) {
            logger.error("Email already registered: $email")
            throw AlreadyReportedException("Email já cadastrado: $email")
        }
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