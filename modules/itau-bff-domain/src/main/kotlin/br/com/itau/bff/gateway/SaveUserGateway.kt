package br.com.itau.bff.gateway

import br.com.itau.bff.domain.UserDomain

interface SaveUserGateway {

    fun execute(userDomain: UserDomain)

}