package ua.alextwit.twitserver.utils.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.time.LocalDate;
import java.time.LocalDateTime;

public final class JsonUtil {
    private Gson gson;

    public JsonUtil() {
        GsonBuilder builder = new GsonBuilder();
        builder.excludeFieldsWithoutExposeAnnotation();
        builder.registerTypeAdapter(new TypeToken<LocalDateTime>() {
                }.getType(),
                new LocalDateTimeConverter());
        builder.registerTypeAdapter(new TypeToken<LocalDate>() {
                }.getType(),
                new LocalDateConverter());
        builder.serializeNulls();
        gson = builder.create();
    }

    public Gson getGson() {
        return gson;
    }
}
