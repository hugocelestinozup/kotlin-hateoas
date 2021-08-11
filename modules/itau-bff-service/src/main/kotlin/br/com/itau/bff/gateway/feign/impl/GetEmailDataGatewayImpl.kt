package br.com.itau.bff.gateway.feign.impl

import br.com.itau.bff.domain.EmailDomain
import br.com.itau.bff.exception.InternalErrorException
import br.com.itau.bff.gateway.GetEmailDataGateway
import br.com.itau.bff.gateway.feign.EmailApi
import br.com.itau.bff.gateway.feign.translator.EmailApiResponseToEmailDomainTranslator
import feign.FeignException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.retry.annotation.Retryable
import javax.inject.Named

@Named
open class GetEmailDataGatewayImpl(private val emailApi: EmailApi) : GetEmailDataGateway {

    private val logger: Logger = LoggerFactory.getLogger(GetEmailDataGatewayImpl::class.java)

    @Retryable(FeignException::class)
    override fun execute(email: String): EmailDomain {
        try {
            val emailApiResponse = emailApi.getEmailData(email)
            logger.info("Recovered Email: $emailApiResponse")
            return EmailApiResponseToEmailDomainTranslator().translate(emailApiResponse)
        } catch (ex: FeignException) {
            logger.error("Email API received an error when try to retrieve the Email ", ex)
            throw InternalErrorException("Erro interno ao recuperar o Email: $email")
        }
    }

}