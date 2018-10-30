package com.dnd.model.adapter;

import com.dnd.model.enums.SpellComponent;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SpellComponentAdapter extends TypeAdapter<List<SpellComponent>> {
    @Override
    public void write(JsonWriter out, List<SpellComponent> value) throws IOException {

    }

    @Override
    public List<SpellComponent> read(JsonReader in) throws IOException {
        String componentList = in.nextString();
        String[] array = componentList.split(",");
        return Arrays.stream(array).map(SpellComponent::getSpellComponent).collect(Collectors.toList());
    }
}
