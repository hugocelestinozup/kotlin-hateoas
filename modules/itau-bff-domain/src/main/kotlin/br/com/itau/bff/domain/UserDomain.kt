package br.com.itau.bff.domain

import java.time.LocalDateTime
import java.util.*

data class UserDomain(
    val id: Long? = null,

    val publicId: UUID? = null,

    val name: String,

    val email: String,

    val createdDate: LocalDateTime = LocalDateTime.now(),

    val updatedDate: LocalDateTime = LocalDateTime.now()
)