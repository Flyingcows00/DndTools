package com.dnd.resources;

import com.dnd.dao.SpellDao;
import com.dnd.model.Spell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("spells")
@CrossOrigin
public class SpellResource {

    @Autowired
    private SpellDao dao;

    @GetMapping
    public ResponseEntity<?> getSpells() {
        List<Spell> spells = dao.getSpells();
        return ResponseEntity.ok(spells);
    }

    @GetMapping("/{spellId}")
    public ResponseEntity<?> getSpellById(@PathVariable String spellId) {
        Spell spell = dao.getSpellById(spellId);
        return ResponseEntity.ok(spell);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<?> emptyResultSetExceptionHandler(EmptyResultDataAccessException exception) {
        return ResponseEntity.status(404).body("Spell not found for provided ID.");
    }

}
