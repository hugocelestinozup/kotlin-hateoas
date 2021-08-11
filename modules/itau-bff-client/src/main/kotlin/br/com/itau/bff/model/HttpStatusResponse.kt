package br.com.itau.bff.model

enum class HttpStatusResponse(val httpStatus: Int, val httpMessage: String) {
    CREATED(1201, "created"),
    UPDATED(1202,"updated"),
    ALREADY_REPORTED(1208, "already reported"),
    BAD_REQUEST(1400, "bad request"),
    NOT_FOUND(1404, "not found"),
    INTERNAL_ERROR(1500, "internal error")

}