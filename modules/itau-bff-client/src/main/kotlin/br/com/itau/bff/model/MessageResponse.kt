package br.com.itau.bff.model

import com.fasterxml.jackson.annotation.JsonInclude

data class MessageResponse(
    val code: Int,
    val message: String,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    var errors: MutableList<String>? = null
) {
    fun addError(message: String) {
        if (errors.isNullOrEmpty()) errors = mutableListOf() else errors!!.add(message)
    }
}