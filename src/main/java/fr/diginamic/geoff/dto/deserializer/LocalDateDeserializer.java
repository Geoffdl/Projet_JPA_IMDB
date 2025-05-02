package fr.diginamic.geoff.dto.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

/**
 * Utility class that handles String to LocalDate conversion using the jackson annotation @JsonDeserialize
 */
public class LocalDateDeserializer extends StdDeserializer<LocalDate>
{
    protected LocalDateDeserializer()
    {
        super(LocalDate.class);
    }

    @Override
    public LocalDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException
    {
        String dateString = jsonParser.readValueAs(String.class);
        if (dateString == null || dateString.trim().isEmpty())
        {
            return null;
        }
        try
        {
            return LocalDate.parse(
                    dateString.trim(),
                    DateTimeFormatter.ofPattern("MMMM d yyyy")
                            .withLocale(Locale.ENGLISH)
            );
        } catch (DateTimeParseException e)
        {
            return null;
        }
    }
}