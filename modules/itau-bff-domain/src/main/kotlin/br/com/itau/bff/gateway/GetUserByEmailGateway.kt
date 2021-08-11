package br.com.itau.bff.gateway

import br.com.itau.bff.domain.UserDomain
import java.util.*

interface GetUserByEmailGateway {

    fun execute(email: String): Optional<UserDomain>

}