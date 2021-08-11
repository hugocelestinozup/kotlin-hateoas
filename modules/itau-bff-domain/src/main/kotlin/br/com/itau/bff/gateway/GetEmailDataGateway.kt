package br.com.itau.bff.gateway

import br.com.itau.bff.domain.EmailDomain

interface GetEmailDataGateway {

    fun execute(email: String): EmailDomain

}