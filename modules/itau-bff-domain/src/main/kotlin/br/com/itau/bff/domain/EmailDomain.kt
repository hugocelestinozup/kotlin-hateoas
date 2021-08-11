package br.com.itau.bff.domain

data class EmailDomain(
    val emailAddress: String,
    val domain: String,
    val validSyntax: Boolean,
    val webmail: Boolean,
    val spam: Boolean
)