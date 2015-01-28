package com.epam.model.json;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class LocalDateSerializer extends JsonSerializer<LocalDateTime> {

	@Override
	public void serialize(LocalDateTime date, JsonGenerator generator, SerializerProvider provider) throws IOException,
			JsonProcessingException {
		String dateString = date.format(DateTimeFormatter.ISO_DATE_TIME);
		generator.writeString(dateString);
	}

}
