package com.dnd.resources;

import com.dnd.dao.SpellDao;
import com.dnd.model.Spell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("spells")
@CrossOrigin
public class SpellResource {

    @Autowired
    private SpellDao dao;

    @GetMapping
    public ResponseEntity<?> getSpellDetails() {
        List<Spell> spells = dao.getSpellDetails();
        return ResponseEntity.ok(spells);
    }

}
