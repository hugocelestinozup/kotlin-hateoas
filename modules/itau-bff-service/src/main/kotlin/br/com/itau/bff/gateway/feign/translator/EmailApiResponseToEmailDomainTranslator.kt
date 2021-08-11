package br.com.itau.bff.gateway.feign.translator

import br.com.itau.bff.domain.EmailDomain
import br.com.itau.bff.gateway.feign.model.EmailApiResponse

class EmailApiResponseToEmailDomainTranslator {

    fun translate(emailApiResponse: EmailApiResponse) = EmailDomain(
        emailAddress = emailApiResponse.data.emailAddress,
        domain = emailApiResponse.data.domain,
        validSyntax = emailApiResponse.data.validSyntax,
        webmail = emailApiResponse.data.webmail,
        spam = emailApiResponse.data.spam
    )

}