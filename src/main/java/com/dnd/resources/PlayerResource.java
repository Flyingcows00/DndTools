package com.dnd.resources;

import com.dnd.dao.PlayerDao;
import com.dnd.dao.SpellDao;
import com.dnd.model.Character;
import com.dnd.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("players")
@CrossOrigin
@Validated
public class PlayerResource {

    @Autowired
    private PlayerDao playerDao;

    @GetMapping
    public ResponseEntity<?> getPlayers() {
        return ResponseEntity.ok(playerDao.getPlayers());
    }

    @PostMapping
    public ResponseEntity<?> createPlayer(@RequestBody @Valid Player player) {
        playerDao.createPlayer(player.getPlayerName());
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<?> emptyResultSetExceptionHandler(EmptyResultDataAccessException exception) {
        return ResponseEntity.status(400).body("Spell not found for provided name.");
    }

}
