package br.com.itau.bff.gateway.feign.model

import com.fasterxml.jackson.annotation.JsonProperty

data class EmailData(

    @JsonProperty("email_address")
    val emailAddress: String,

    val domain: String,

    @JsonProperty("valid_syntax")
    val validSyntax: Boolean,

    val webmail: Boolean,

    val spam: Boolean
)