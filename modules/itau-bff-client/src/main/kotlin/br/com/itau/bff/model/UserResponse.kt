package br.com.itau.bff.model

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import org.springframework.hateoas.RepresentationModel
import java.time.LocalDateTime
import java.util.*

open class UserResponse(
    val publicId: UUID,

    val name: String,

    val email: String,

    @JsonDeserialize(using = LocalDateTimeDeserializer::class)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    val createdDate: LocalDateTime,

    @JsonDeserialize(using = LocalDateTimeDeserializer::class)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    val updatedDate: LocalDateTime
) : RepresentationModel<UserResponse>()