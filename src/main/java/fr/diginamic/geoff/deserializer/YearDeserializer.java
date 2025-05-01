package fr.diginamic.geoff.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.Year;
import java.time.format.DateTimeParseException;

/**
 * Utility class that handles String to Year conversion using the jackson annotation @JsonDeserialize
 */
public class YearDeserializer extends StdDeserializer<Year>
{

    public YearDeserializer() {
        super(Year.class);
    }

    @Override
    public Year deserialize(JsonParser jsonParser, DeserializationContext context)
            throws IOException
    {
        String yearString = jsonParser.readValueAs(String.class);

        if (yearString == null || yearString.trim().isEmpty()) {
            return null;
        }

        try {
            return Year.parse(yearString.trim());
        } catch (DateTimeParseException e) {
            return null;
        }
    }
}