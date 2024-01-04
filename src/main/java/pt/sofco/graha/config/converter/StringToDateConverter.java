package pt.sofco.graha.config.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

@Component
public class StringToDateConverter implements Converter<String, LocalDate> {

    private final DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ROOT); // use package java.time
    private static final DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ROOT);

    static LocalDate localDate;
    static String text;

    @Override
    public LocalDate convert(String source) {
        localDate = LocalDate.parse(source, formatter1);
        if (localDate == null) {
            throw new DateTimeParseException("format is dd-MM-yyyy", null, formatter1.hashCode());
        }
        return localDate;

    }

    public static String convert(LocalDateTime source) {

        try {

            localDate = source.toLocalDate();
            text = localDate.format(formatter2);
            return text;

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "format is dd/MM/yyyy");
        }

    }
}
