package fr.diginamic.geoff.dto.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

/**
 * Utility class that handles String to float conversion using the jackson annotation @JsonDeserialize
 */
public class FloatDeserializer extends StdDeserializer<Float>
{

    public FloatDeserializer()
    {
        super(Float.class);
    }

    @Override
    public Float deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException
    {
        String value = jsonParser.getValueAsString();

        if (value == null || value.trim().isEmpty())
        {
            return null;
        }

        try
        {

            return Float.parseFloat(value.replace(",", ".").trim());
        } catch (NumberFormatException e)
        {
            return null;
        }
    }
}