package com.example.project.exception.handler

import com.example.project.error.ApplicationError
import com.example.project.error.FieldError
import com.example.project.exception.TitleExistException
import com.example.project.exception.UserNotExistsException
import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.ConstraintViolationException
import org.apache.commons.logging.LogFactory
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.client.HttpClientErrorException.Unauthorized

@RestControllerAdvice
class ExceptionHandler {
    private val log = LogFactory.getLog(ExceptionHandler::class.java)

    /**
     * 400 Error　独自例外
     * タイトルが既にDBに存在する場合に発生
     * */
    @ExceptionHandler(TitleExistException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    fun titleExistException(
        request: HttpServletRequest,
        ex: TitleExistException
    ): ResponseEntity<ApplicationError> {
        log.error(ex)

        val fieldError: FieldError = FieldError("title", "タイトルは既に存在します")

        val fieldErrors = listOf<FieldError>(fieldError)

        val error = ApplicationError("入力エラー発生", HttpStatus.BAD_REQUEST.value(), request.method, fieldErrors);

        return ResponseEntity<ApplicationError>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * 400 Error　独自例外
     * タイトルが既にDBに存在する場合に発生
     * */
    @ExceptionHandler(UserNotExistsException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    fun userNotExistException(
        request: HttpServletRequest,
        ex: TitleExistException
    ): ResponseEntity<ApplicationError> {
        log.error(ex)

        val error = ex.message?.let { ApplicationError(it, HttpStatus.BAD_REQUEST.value(), request.method) };

        return ResponseEntity<ApplicationError>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * 400 Error
     * Entityの制約違反の場合の例外
     * 主にBindingResultで使用
     * */
    @ExceptionHandler(ConstraintViolationException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    fun constraintViolationException(
        request: HttpServletRequest,
        ex: ConstraintViolationException
    ): ResponseEntity<ApplicationError> {
        log.error(ex)

        val fieldErrors =
            ex.constraintViolations.map { it -> FieldError(it.propertyPath.toString(), it.message) }

        val error = ApplicationError("入力エラー発生", HttpStatus.BAD_REQUEST.value(), request.method, fieldErrors);

        return ResponseEntity<ApplicationError>(error, HttpStatus.BAD_REQUEST);
    }


    /**
     * 400 Error
     * Entityの制約違反の場合の例外
     * @valid, @RequestBodyがある場合のバリデーションで発生
     * */
    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    fun methodArgumentNotValidException(
        request: HttpServletRequest,
        ex: MethodArgumentNotValidException
    ): ResponseEntity<ApplicationError> {
        log.error(ex)

        val fieldErrors =
            ex.fieldErrors.map { it -> it.defaultMessage?.let { it1 -> FieldError(it.field, it1) } }

        val error = ApplicationError("入力エラー発生", HttpStatus.BAD_REQUEST.value(), request.method, fieldErrors);

        return ResponseEntity<ApplicationError>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * 400 Error
     * Entityの制約違反の場合の例外
     * @valid, @RequestBodyがある場合のバリデーションで発生
     * */
    @ExceptionHandler(Unauthorized::class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    fun unauthorizedException(
        request: HttpServletRequest,
        ex: Unauthorized
    ): ResponseEntity<ApplicationError> {
        log.error(ex)

        val error = ex.message?.let { ApplicationError(it, HttpStatus.BAD_REQUEST.value(), request.method) };

        return ResponseEntity<ApplicationError>(error, HttpStatus.UNAUTHORIZED);
    }

    /**
     * 405 Error
     * HTTPメソッドとリクエスト内容が一致しない場合の例外
     * */
    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ResponseBody
    fun notSupportedException(
        request: HttpServletRequest,
        ex: HttpRequestMethodNotSupportedException
    ): ResponseEntity<ApplicationError> {
        log.error(ex.message)

        val status: HttpStatus = getStatus(request)

        val error = ApplicationError("リクエスト方法が間違っています", status.value(), request.method);

        return ResponseEntity<ApplicationError>(error, status);
    }

    /**
     * 500 Error
     * 要素が見つからなかった場合の例外処理
     * */
    @ExceptionHandler(NoSuchElementException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    fun noSuchElementException(
        request: HttpServletRequest,
        ex: NoSuchElementException
    ): ResponseEntity<ApplicationError> {
        log.error(ex.message)

        val error = ApplicationError("リクエストされた要素が見つかりません", HttpStatus.BAD_REQUEST.value(), request.method);

        return ResponseEntity<ApplicationError>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * 500 Error
     * 文字列を数値に変換できなかった場合の例外
     * */
    @ExceptionHandler(NumberFormatException::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    fun numberFormatException(
        request: HttpServletRequest,
        ex: NumberFormatException
    ): ResponseEntity<ApplicationError> {
        log.error(ex.message)

        val status: HttpStatus = getStatus(request)

        val error = ApplicationError("数値に変換できない文字列です", status.value(), request.method);

        return ResponseEntity<ApplicationError>(error, status);
    }

    /**
     * 500 Error
     * データベースの制約違反の場合の例外
     * */
    @ExceptionHandler(DataIntegrityViolationException::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    fun dataIntegrityViolationException(
        request: HttpServletRequest,
        ex: DataIntegrityViolationException
    ): ResponseEntity<ApplicationError> {
        log.error(ex.message)

        val status: HttpStatus = getStatus(request)

        val error = ApplicationError("入力エラー発生", status.value(), request.method);

        return ResponseEntity<ApplicationError>(error, status);
    }

    /**
     * 500 Error
     * 値がnullだった場合の例外
     * */
    @ExceptionHandler(NullPointerException::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    fun nullPointerException(
        request: HttpServletRequest,
        ex: NullPointerException
    ): ResponseEntity<ApplicationError> {
        log.error(ex.message)

        val status: HttpStatus = getStatus(request)

        val error = ApplicationError("値がnullです", status.value(), request.method);

        return ResponseEntity<ApplicationError>(error, status);
    }

    private fun getStatus(request: HttpServletRequest): HttpStatus {
        val statusCode: Int = request.getAttribute("javax.servlet.error.status_code") as Int?
            ?: return HttpStatus.INTERNAL_SERVER_ERROR
        return HttpStatus.valueOf(statusCode)
    }
}