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
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final String badRequestErrorMessage = "올바른 요청이 아닙니다.";
    private final String unAuthorizedErrorMessage = "인증 되지 않았습니다.";
    private final String forbiddenErrorMessage = "인가 되지 않았습니다";
    private final String internalServerErrorMessage = "서버에 에러가 일어났습니다";

    // 400
    @Override
    protected ResponseEntity<Object> handleBindException(final BindException ex, final HttpHeaders headers,
        final HttpStatus status, final WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(badRequestErrorMessage, ex.getBindingResult());
        return handleExceptionInternal(ex, exceptionResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
        final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(badRequestErrorMessage, ex.getBindingResult());
        return handleExceptionInternal(ex, exceptionResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
        HttpStatus status, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(badRequestErrorMessage, ex.getRequiredType());
        return handleExceptionInternal(ex, exceptionResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    // 401
    @ExceptionHandler(AuthenticationException.class)
    protected ResponseEntity<Object> handleAuthenticationException(final AuthenticationException ex,
        WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(unAuthorizedErrorMessage, ex.getLocalizedMessage());
        return handleExceptionInternal(ex, exceptionResponse, new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
    }

    // 403
    @ExceptionHandler(AccessDeniedException.class)
    protected ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(forbiddenErrorMessage, ex.getLocalizedMessage());
        return handleExceptionInternal(ex, exceptionResponse, new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }

    // 500
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleInternal(final Exception ex, final WebRequest request) {
        final ExceptionResponse exceptionResponse = new ExceptionResponse(internalServerErrorMessage, ex.getLocalizedMessage());
        return handleExceptionInternal(ex, exceptionResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

}
