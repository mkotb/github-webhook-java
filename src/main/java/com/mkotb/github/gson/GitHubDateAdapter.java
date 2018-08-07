package com.mkotb.github.gson;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.TimeZone;

// converts to and from ISO 8601
public class GitHubDateAdapter extends TypeAdapter<Date> {
    @Override
    public void write(JsonWriter jsonWriter, Date date) throws IOException {
        TimeZone timeZone = TimeZone.getTimeZone("UTC");
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
        format.setTimeZone(timeZone);

        jsonWriter.value(format.format(date));
    }

    @Override
    public Date read(JsonReader jsonReader) throws IOException {
        return Date.from(OffsetDateTime.parse(jsonReader.nextString()).toInstant());
    }
}
