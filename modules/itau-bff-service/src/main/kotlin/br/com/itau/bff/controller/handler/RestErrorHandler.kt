package br.com.itau.bff.controller.handler

import br.com.itau.bff.exception.AlreadyReportedException
import br.com.itau.bff.exception.InternalErrorException
import br.com.itau.bff.exception.NotFoundException
import br.com.itau.bff.model.HttpStatusResponse
import br.com.itau.bff.model.MessageResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import javax.validation.ConstraintViolationException

@RestControllerAdvice
class RestErrorHandler {

    private val log: Logger = LoggerFactory.getLogger(RestErrorHandler::class.java)

    @ExceptionHandler(IllegalArgumentException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleIllegalArgumentException(exception: IllegalArgumentException): MessageResponse {
        return MessageResponse(
            HttpStatusResponse.BAD_REQUEST.httpStatus,
            HttpStatusResponse.BAD_REQUEST.httpMessage,
            mutableListOf(exception.message!!)
        )
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleHttpMessageNotReadableException(exception: HttpMessageNotReadableException): MessageResponse {
        return MessageResponse(
            HttpStatusResponse.BAD_REQUEST.httpStatus,
            HttpStatusResponse.BAD_REQUEST.httpMessage,
            mutableListOf(HttpStatusResponse.BAD_REQUEST.httpMessage)
        )
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleMethodArgumentNotValidException(exception: MethodArgumentNotValidException): MessageResponse {
        val errors: MutableList<String> = mutableListOf()

        exception.bindingResult.fieldErrors.forEach { error -> errors.add(error.defaultMessage!!) }

        return MessageResponse(
            HttpStatusResponse.BAD_REQUEST.httpStatus,
            HttpStatusResponse.BAD_REQUEST.httpMessage, errors
        )
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleMethodArgumentTypeMismatchException(exception: MethodArgumentTypeMismatchException): MessageResponse? {
        return MessageResponse(
            HttpStatusResponse.BAD_REQUEST.httpStatus,
            HttpStatusResponse.BAD_REQUEST.httpMessage,
            mutableListOf(HttpStatusResponse.BAD_REQUEST.httpMessage)
        )
    }

    @ExceptionHandler(MissingServletRequestParameterException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleMissingServletRequestParameterException(exception: MissingServletRequestParameterException): MessageResponse {
        return MessageResponse(
            HttpStatusResponse.BAD_REQUEST.httpStatus,
            HttpStatusResponse.BAD_REQUEST.httpMessage, mutableListOf(exception.message)
        )
    }

    @ExceptionHandler(ConstraintViolationException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleConstraintViolationException(exception: ConstraintViolationException): MessageResponse {
        val errors: MutableList<String> = mutableListOf()

        exception.constraintViolations.forEach { violation -> errors.add(violation.message) }

        return MessageResponse(
            HttpStatusResponse.BAD_REQUEST.httpStatus,
            HttpStatusResponse.BAD_REQUEST.httpMessage, errors
        )
    }

    @ExceptionHandler(InternalErrorException::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleDataBaseInternalErrorException(exception: InternalErrorException): MessageResponse {
        log.error("Internal error: ${exception.message}", exception)

        return MessageResponse(
            HttpStatusResponse.INTERNAL_ERROR.httpStatus,
            HttpStatusResponse.INTERNAL_ERROR.httpMessage, mutableListOf(exception.message!!)
        )
    }

    @ExceptionHandler(AlreadyReportedException::class)
    @ResponseStatus(HttpStatus.ALREADY_REPORTED)
    fun handleAlreadyReportedException(exception: AlreadyReportedException): MessageResponse {
        return MessageResponse(
            HttpStatusResponse.ALREADY_REPORTED.httpStatus,
            HttpStatusResponse.ALREADY_REPORTED.httpMessage, mutableListOf(exception.message!!)
        )
    }

    @ExceptionHandler(NotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleNotFoundException(exception: NotFoundException): MessageResponse {
        log.error("Not found error: ${exception.message}", exception)

        return MessageResponse(
            HttpStatusResponse.NOT_FOUND.httpStatus,
            HttpStatusResponse.NOT_FOUND.httpMessage, mutableListOf(exception.message!!)
        )
    }

    @ExceptionHandler(Throwable::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleThrowableException(exception: Throwable): MessageResponse? {
        log.error("Internal error: ${exception.message}", exception)

        return MessageResponse(
            HttpStatusResponse.INTERNAL_ERROR.httpStatus,
            HttpStatusResponse.INTERNAL_ERROR.httpMessage,
            mutableListOf(HttpStatusResponse.INTERNAL_ERROR.httpMessage)
        )
    }

}