package br.com.itau.bff.api

import br.com.itau.bff.model.MessageResponse
import br.com.itau.bff.model.UserRequest
import br.com.itau.bff.model.UserResponse
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@RequestMapping("/v1/user")
@Api(tags = ["User"])
interface UserApi {

    @ApiOperation(value = "Create a new User", notes = "Create a new User")
    @ApiResponses(value = [ApiResponse(code = 201, message = "Created", response = MessageResponse::class),
        ApiResponse(code = 400, message = "Bad request", response = MessageResponse::class),
        ApiResponse(code = 500, message = "Internal error to create user", response = MessageResponse::class)])
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun createUser(@Valid @RequestBody userRequest: UserRequest): MessageResponse

    @ApiOperation(value = "Find User by Id", notes = "Find User by Id")
    @ApiResponses(value = [ApiResponse(code = 200, message = "Ok", response = UserResponse::class),
        ApiResponse(code = 400, message = "Bad request", response = MessageResponse::class),
        ApiResponse(code = 404, message = "User not found", response = MessageResponse::class),
        ApiResponse(code = 500, message = "Internal error to get the User", response = MessageResponse::class)])
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    fun getUserById(@Valid @RequestParam( "userId") userId: UUID): UserResponse

    @ApiOperation(value = "Find User by Email", notes = "Find User by Email")
    @ApiResponses(value = [ApiResponse(code = 200, message = "Ok", response = UserResponse::class),
        ApiResponse(code = 400, message = "Bad request", response = MessageResponse::class),
        ApiResponse(code = 404, message = "User not found", response = MessageResponse::class),
        ApiResponse(code = 500, message = "Internal error to get the User", response = MessageResponse::class)])
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/email")
    fun getUserByEmail(@Valid @RequestParam("email") email: String): UserResponse

}