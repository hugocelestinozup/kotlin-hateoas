package br.com.itau.bff.model

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import io.swagger.annotations.ApiModelProperty
import org.springframework.hateoas.RepresentationModel
import java.time.LocalDateTime
import java.util.*

data class UserResponse(
    val publicId: UUID,

    val name: String,

    @ApiModelProperty(required = true)
    val email: String,

    @ApiModelProperty(required = true)
    @JsonDeserialize(using= LocalDateTimeDeserializer::class)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    val createdDate: LocalDateTime,

    @ApiModelProperty(required = true)
    @JsonDeserialize(using= LocalDateTimeDeserializer::class)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    val updatedDate: LocalDateTime
) : RepresentationModel<UserResponse>()