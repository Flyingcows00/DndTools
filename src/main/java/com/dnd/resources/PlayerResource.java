package com.dnd.resources;

import com.dnd.dao.PlayerDao;
import com.dnd.dao.SpellDao;
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

    @GetMapping("/{campaignId}")
    public ResponseEntity<?> getPlayersByCampaign(@PathVariable String campaignId) {
        return ResponseEntity.ok(playerDao.getPlayersByCampaign(campaignId));
    }

    @PostMapping("/{campaignId}")
    public ResponseEntity<?> createPlayer(@PathVariable String campaignId, @RequestBody @Valid Player player) {
        playerDao.createPlayer(campaignId, player);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<?> emptyResultSetExceptionHandler(EmptyResultDataAccessException exception) {
        return ResponseEntity.status(400).body("Spell not found for provided name.");
    }

}
