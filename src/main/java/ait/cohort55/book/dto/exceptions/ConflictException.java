package ait.cohort55.book.dto.exceptions;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
@NoArgsConstructor
public class ConflictException extends RuntimeException {
    public ConflictException(String message) {
        super(message);
    }
}
