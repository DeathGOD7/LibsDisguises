package me.libraryaddict.disguise.utilities.gson;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.json.JSONComponentSerializer;

import java.lang.reflect.Type;

/**
 * Created by libraryaddict on 27/11/2018.
 */
public class SerializerChatComponent implements JsonDeserializer<Component>, JsonSerializer<Component> {

    @Override
    public Component deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json.isJsonPrimitive()) {
            return JSONComponentSerializer.json().deserialize(json.getAsString());
        }

        return null;
    }

    @Override
    public JsonElement serialize(Component src, Type typeOfSrc, JsonSerializationContext context) {
        return context.serialize(JSONComponentSerializer.json().serialize(src));
    }
}
