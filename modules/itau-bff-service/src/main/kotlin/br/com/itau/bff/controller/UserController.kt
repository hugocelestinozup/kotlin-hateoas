package br.com.itau.bff.controller

import br.com.itau.bff.api.UserApi
import br.com.itau.bff.controller.translator.UserDomainToUserResponseTranslator
import br.com.itau.bff.controller.translator.UserRequestToUserDomainTranslator
import br.com.itau.bff.model.UserRequest
import br.com.itau.bff.model.UserResponse
import br.com.itau.bff.usecase.CreateUserUseCase
import br.com.itau.bff.usecase.GetUserByEmailUseCase
import br.com.itau.bff.usecase.GetUserByIdUseCase
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.hateoas.server.mvc.linkTo
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

    override fun createUser(userRequest: UserRequest): UserResponse {
        logger.info("User API receive a POST to create a new User")

        var userDomain = UserRequestToUserDomainTranslator().translate(userRequest)
        userDomain = createUserUseCase.execute(userDomain)

        val userResponse = UserDomainToUserResponseTranslator().translate(userDomain)

        val linkById = linkTo<UserController> { getUserById(userDomain.publicId!!) }
        val linkByEmail = linkTo<UserController> { getUserByEmail(userDomain.email) }

        userResponse.add(listOf(linkById.withSelfRel(), linkByEmail.withSelfRel()))

        logger.info("User API finished to save the user")

        return userResponse
    }

    override fun getUserById(userId: UUID): UserResponse {
        logger.info("User API receive a GET to find an User by Id")

        val userDomain = getUserByIdUseCase.execute(userId)
        val userResponse = UserDomainToUserResponseTranslator().translate(userDomain)

        val linkById = linkTo<UserController> { getUserById(userDomain.publicId!!) }
        userResponse.add(linkById.withSelfRel())

        logger.info("User API finished to find the user")
        return userResponse
    }

    override fun getUserByEmail(email: String): UserResponse {
        logger.info("User API receive a GET to find an User by Email")

        val userDomain = getUserByEmailUseCase.execute(email)
        val userResponse = UserDomainToUserResponseTranslator().translate(userDomain)

        val linkByEmail = linkTo<UserController> { getUserByEmail(userDomain.email) }
        userResponse.add(linkByEmail.withSelfRel())

        logger.info("User API finished to find the user")
        return userResponse
    }

}