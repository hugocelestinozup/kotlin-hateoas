package br.com.itau.bff.model

import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

class UserRequest(

    @ApiModelProperty(required = true)
    @field:NotBlank(message = "The name must be informed")
    @field:Size(max = 255, message = "The max name size is 255 characters")
    val name: String,

    @ApiModelProperty(required = true)
    @field:NotBlank(message = "The email must be informed")
    @field:Size(max = 100, message = "The max email size is 100 characters")
    val email: String,
)