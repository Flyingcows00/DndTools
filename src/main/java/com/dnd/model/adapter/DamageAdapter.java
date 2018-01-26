package com.dnd.model.adapter;

import com.dnd.model.enums.DamageType;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.List;

public class DamageAdapter extends TypeAdapter<List<DamageType>> {
    @Override
    public void write(JsonWriter writer, List<DamageType> value) throws IOException {
        if (value == null) {
            writer.nullValue();
            return;
        }
        writer.value(value.toString());
    }

    @Override
    public List<DamageType> read(JsonReader reader) throws IOException {
        if (reader.peek() == JsonToken.NULL) {
            reader.nextNull();
            return null;
        }
        return DamageType.getDamageTypeList(reader.nextString());
    }
}
