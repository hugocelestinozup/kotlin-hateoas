package br.com.itau.bff.gateway.feign.model

data class EmailApiResponse(
    val status: String,
    val data: EmailData
)