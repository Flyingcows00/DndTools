package com.dnd.resources;

import com.dnd.dao.SpellDao;
import com.dnd.model.ErrorResponse;
import com.dnd.model.Spell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import static com.dnd.model.ErrorResponse.*;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("spells")
@CrossOrigin
public class SpellResource {

    @Autowired
    private SpellDao spellDao;
    private static final Logger logger = LoggerFactory.getLogger(SpellResource.class);

    @GetMapping
    public ResponseEntity<?> getSpells() {
        return ResponseEntity.ok(spellDao.getSpells());
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> getSpellById(@PathVariable String name) {
        return ResponseEntity.ok(spellDao.getSpellByName(name));
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<?> constraintViolationException(SQLIntegrityConstraintViolationException exception) {
        ErrorResponse response = SPELL_ALREADY_EXISTS;
        logger.warn(response.getError(), exception);
        return ResponseEntity.status(BAD_REQUEST).body(response);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<?> dataAccessException(EmptyResultDataAccessException exception) {
        ErrorResponse response = SPELL_NOT_FOUND;
        logger.warn(response.getError(), exception);
        return ResponseEntity.status(NOT_FOUND).body(response);
    }

}
