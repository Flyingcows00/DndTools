package com.dnd.model.adapter;

import com.dnd.model.enums.Condition;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.List;

public class ConditionAdapter extends TypeAdapter<List<Condition>> {
    @Override
    public void write(JsonWriter writer, List<Condition> value) throws IOException {
        if (value == null) {
            writer.nullValue();
            return;
        }
        writer.value(value.toString());
    }

    @Override
    public List<Condition> read(JsonReader reader) throws IOException {
        if (reader.peek() == JsonToken.NULL) {
            reader.nextNull();
            return null;
        }
        return Condition.getConditionList(reader.nextString());
    }
}
