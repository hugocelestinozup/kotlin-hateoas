package br.com.itau.bff.controller

import br.com.itau.bff.api.UserApi
import br.com.itau.bff.controller.translator.UserDomainToUserResponseTranslator
import br.com.itau.bff.controller.translator.UserRequestToUserDomainTranslator
import br.com.itau.bff.model.HttpStatusResponse
import br.com.itau.bff.model.MessageResponse
import br.com.itau.bff.model.UserRequest
import br.com.itau.bff.model.UserResponse
import br.com.itau.bff.usecase.CreateUserUseCase
import br.com.itau.bff.usecase.GetUserByEmailUseCase
import br.com.itau.bff.usecase.GetUserByIdUseCase
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.RestController
import java.util.*

@Validated
@RestController
class UserController(
    private val createUserUseCase: CreateUserUseCase,
    private val getUserByIdUseCase: GetUserByIdUseCase,
    private val getUserByEmailUseCase: GetUserByEmailUseCase
) : UserApi {

    private val logger: Logger = LoggerFactory.getLogger(UserController::class.java)

    override fun createUser(userRequest: UserRequest): MessageResponse {
        logger.info("User API receive a POST to create a new User")

        val userDomain = UserRequestToUserDomainTranslator().translate(userRequest)
        createUserUseCase.execute(userDomain)

        logger.info("User API finished to save the user")

        return MessageResponse(
            HttpStatusResponse.CREATED.httpStatus,
            HttpStatusResponse.CREATED.httpMessage
        )
    }

    override fun getUserById(userId: UUID): UserResponse {
        logger.info("User API receive a GET to find an User")

        val userDomain = getUserByIdUseCase.execute(userId)

        logger.info("User API finished to find the user")
        return UserDomainToUserResponseTranslator().translate(userDomain)
    }

    override fun getUserByEmail(email: String): UserResponse {
        logger.info("User API receive a GET to find an User")

        val userDomain = getUserByEmailUseCase.execute(email)

        logger.info("User API finished to find the user")
        return UserDomainToUserResponseTranslator().translate(userDomain)
    }

}