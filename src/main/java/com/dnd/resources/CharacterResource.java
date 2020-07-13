package com.dnd.resources;

import com.dnd.dao.CharacterDao;
import com.dnd.model.Character;
import com.dnd.model.ErrorResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.dnd.model.ErrorResponse.*;
import static org.apache.commons.lang3.StringUtils.isBlank;

@RestController
@RequestMapping("characters")
@CrossOrigin
@Validated
public class CharacterResource {

    @Autowired
    private CharacterDao characterDao;

    @GetMapping("/{characterId}")
    public ResponseEntity<?> getCharacter(@PathVariable int characterId) {
        return ResponseEntity.ok(characterDao.getCharacter(characterId));
    }

    @GetMapping
    public ResponseEntity<?> getCharacters(@RequestParam(required = false, defaultValue = "0") int campaignId, @RequestParam(required = false, defaultValue = "0") int playerId) {
        return ResponseEntity.ok(characterDao.getCharacters(campaignId, playerId, 0));
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

    @DeleteMapping("/{characterId}/campaign/{campaignId}")
    public ResponseEntity<?> removeCharacterFromCampaign(@PathVariable int characterId, @PathVariable int campaignId) {
        characterDao.removeCharacterFromCampaign(characterId, campaignId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{characterId}")
    public ResponseEntity<?> updateCharacter(@PathVariable int characterId, @RequestBody Character character) {
        if (character.getAlive() == null && isBlank(character.getCharacterName()) && isBlank(character.getNotes())) {
            return ResponseEntity.status(CAMPAIGN_REQUIRED_FIELDS.getStatusCode()).body(CAMPAIGN_REQUIRED_FIELDS);
        }
        characterDao.updateCharacter(characterId, character);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{characterId}")
    public ResponseEntity<?> deleteCharacter(@PathVariable int characterId) {
        characterDao.deleteCharacter(characterId);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<?> emptyResultSetExceptionHandler(EmptyResultDataAccessException exception) {
        return ResponseEntity.status(CHARACTER_NOT_FOUND.getStatusCode()).body(CHARACTER_NOT_FOUND);
    }

}
