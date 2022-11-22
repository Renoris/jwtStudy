package study.jdnc7.homeworkproject.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // 400
    @Override
    protected ResponseEntity<Object> handleBindException(final BindException ex, final HttpHeaders headers,
        final HttpStatus status, final WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse("올바른 요청이 아닙니다.", ex.getBindingResult());
        return handleExceptionInternal(ex, exceptionResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
        final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse("올바른 요청이 아닙니다.", ex.getBindingResult());
        return handleExceptionInternal(ex, exceptionResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
        HttpStatus status, WebRequest request) {
        log.debug("400 Status Code: {}", ex.getLocalizedMessage());
        ExceptionResponse exceptionResponse = new ExceptionResponse("올바른 요청이 아닙니다.", ex.getRequiredType());
        return handleExceptionInternal(ex, exceptionResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    // 401
    @ExceptionHandler(AuthenticationException.class)
    protected ResponseEntity<Object> handleAuthenticationException(final AuthenticationException ex,
        WebRequest request) {
        log.debug("401 Status Code: {}", ex.getLocalizedMessage());
        ExceptionResponse exceptionResponse = new ExceptionResponse("인증 되지 않았습니다.", ex.getLocalizedMessage());
        return handleExceptionInternal(ex, exceptionResponse, new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
    }

    // 403
    @ExceptionHandler(AccessDeniedException.class)
    protected ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
        log.debug("403 Status Code: {}", ex.getLocalizedMessage());
        ExceptionResponse exceptionResponse = new ExceptionResponse("인가 되지 않았습니다.", ex.getLocalizedMessage());
        return handleExceptionInternal(ex, exceptionResponse, new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }

    // 500
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleInternal(final Exception ex, final WebRequest request) {
        log.debug("500 Status Code: {}", ex.getLocalizedMessage());
        final ExceptionResponse exceptionResponse = new ExceptionResponse("서버에 에러가 일어났습니다.", ex.getLocalizedMessage());
        return handleExceptionInternal(ex, exceptionResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

}
