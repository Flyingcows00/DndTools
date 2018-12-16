package com.dnd;

import com.dnd.dao.SpellDao;
import com.dnd.model.Spell;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ReadJsonFile {

    @Autowired
    private SpellDao dao;

    @Test
    public void insertSpells() throws Exception {
        URL url = Resources.getResource("spells.json");
        String json = Resources.toString(url, Charsets.UTF_8);
        List<Spell> spells = new Gson().fromJson(json, new TypeToken<List<Spell>>(){}.getType());

        spells.forEach(spell -> {
            try {
                System.out.println(spell.getName());
                dao.insertSpell(spell);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

    }
}
