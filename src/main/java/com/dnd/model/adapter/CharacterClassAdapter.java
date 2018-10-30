package com.dnd.model.adapter;

import com.dnd.model.enums.CharacterClass;
import com.dnd.model.enums.SpellComponent;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CharacterClassAdapter extends TypeAdapter<List<CharacterClass>> {

    @Override
    public void write(JsonWriter out, List<CharacterClass> value) throws IOException {

    }

    @Override
    public List<CharacterClass> read(JsonReader in) throws IOException {
        String componentList = in.nextString();
        String[] array = componentList.split(",");
        return Arrays.stream(array).map(CharacterClass::getCharacterClass).collect(Collectors.toList());
    }
}
