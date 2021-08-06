package br.com.zupacademy.kleysson.mercadolivre.utils.validation;

import br.com.zupacademy.kleysson.mercadolivre.dto.ErrorFormatDTO;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ValidationHandler {

    private MessageSource messageSource;

    public ValidationHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErrorFormatDTO> handle(MethodArgumentNotValidException exception) {
        List<ErrorFormatDTO> dto = new ArrayList<>();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        fieldErrors.forEach(e -> {
            String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            ErrorFormatDTO erro = new ErrorFormatDTO(e.getField(), mensagem);
            dto.add(erro);
        });

        return dto;
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorFormatDTO handleIllegal(IllegalArgumentException exception) {
        return new ErrorFormatDTO("request", exception.getLocalizedMessage());
    }
}
