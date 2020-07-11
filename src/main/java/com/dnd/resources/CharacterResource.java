package com.dnd.resources;

import com.dnd.dao.CharacterDao;
import com.dnd.model.Character;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("character")
@CrossOrigin
@Validated
public class CharacterResource {

    @Autowired
    private CharacterDao characterDao;

    @GetMapping("/{playerId}")
    public ResponseEntity<?> getCharactersByPlayer(@PathVariable int playerId) {
        return ResponseEntity.ok(characterDao.getCharacters(playerId));
    }

    @PostMapping("/{playerId}")
    public ResponseEntity<?> createCharacter(@PathVariable int playerId, @RequestBody @Valid Character character) {
        characterDao.createCharacter(playerId, character.getCharacterName());
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{characterId}/campaign/{campaignId}")
    public ResponseEntity<?> addCharacterToCampaign(@PathVariable int characterId, @PathVariable int campaignId) {
        characterDao.addCharacterToCampaign(characterId, campaignId);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<?> emptyResultSetExceptionHandler(EmptyResultDataAccessException exception) {
        return ResponseEntity.status(400).body("Spell not found for provided name.");
    }

}
