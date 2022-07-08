package br.com.covid.brazil.api.config;

import br.com.covid.brazil.api.dto.ErrorApiDTO;
import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class BasicExceptionHandlerTest {

    private static final String CONSTRAINT_VIOLATION_MSG = "javax.validation.ConstraintViolationException: textToChatBot.message: Message can't be null or empty";
    @Spy
    BasicExceptionHandler basicExceptionHandler;

    @Test
    void mustReturnErrorWhenSimplePropertyPath() {
        ConstraintViolationException ex = getConstraintViolationException("message");

        ErrorApiDTO errorDto = basicExceptionHandler.exceptionHandler(ex).getBody();

        assertNotNull(errorDto);
        assertEquals(CONSTRAINT_VIOLATION_MSG, errorDto.getError());
        assertEquals(LocalDateTime.now().toString().substring(0, 20), errorDto.getTimestamp().substring(0, 20));
    }

    @Test
    void mustReturnErrorWhenComposePropertyPath() {
        ConstraintViolationException ex = getConstraintViolationException("object.message");

        ErrorApiDTO errorDto = basicExceptionHandler.exceptionHandler(ex).getBody();

        assertNotNull(errorDto);
        assertEquals(CONSTRAINT_VIOLATION_MSG, errorDto.getError());
        assertEquals(LocalDateTime.now().toString().substring(0, 20), errorDto.getTimestamp().substring(0, 20));
    }

    private ConstraintViolationException getConstraintViolationException(String value) {
        ConstraintViolation<Object> violation = ConstraintViolationImpl.forParameterValidation(
                null, null, null,
                null,
                null, null, null, null,
                PathImpl.createPathFromString(value),
                null, null, null);

        Set<ConstraintViolation<Object>> violations = new HashSet<>();
        violations.add(violation);

        return new ConstraintViolationException("textToChatBot.message: Message can't be null or empty", violations);
    }
}
