package br.com.itau.bff.gateway.feign

import br.com.itau.bff.gateway.feign.config.EmailApiConfig
import br.com.itau.bff.gateway.feign.model.EmailApiResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(
    name = "emailApi",
    url = "https://api.eva.pingutil.com",
    configuration = [EmailApiConfig::class]
)
interface EmailApi {

    @GetMapping(
        value = ["/email"],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun getEmailData(@RequestParam("email") email: String): EmailApiResponse

}