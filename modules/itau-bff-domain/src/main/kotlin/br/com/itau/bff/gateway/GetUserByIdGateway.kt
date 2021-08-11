package br.com.itau.bff.gateway

import br.com.itau.bff.domain.UserDomain
import java.util.*

interface GetUserByIdGateway {

    fun execute(userId: UUID): Optional<UserDomain>

}