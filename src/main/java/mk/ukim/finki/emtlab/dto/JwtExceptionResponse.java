package mk.ukim.finki.emtlab.dto;

import java.util.Date;

public record JwtExceptionResponse(
        Date timestamp,
        int status,
        String error,
        String message,
        String path
) {

    public JwtExceptionResponse(int status, String error, String message, String path) {
        this(new Date(), status, error, message, path);
    }

}

