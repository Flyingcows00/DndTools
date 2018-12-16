package com.dnd.resources;

import com.dnd.dao.MonsterDao;
import com.dnd.model.ErrorResponse;
import com.dnd.model.Monster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("monsters")
@CrossOrigin
public class MonsterResource {

    @Autowired
    private MonsterDao monsterDao;

    @GetMapping
    public ResponseEntity<?> getMonsterNames() {
        List<String> names = null;
        try {
            names = monsterDao.getAllMonsterNames();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(500, e).toJson());
        }
        return ResponseEntity.ok(names);
    }

    @GetMapping(path = "/{name}")
    public ResponseEntity<?> getMonsterByName(@PathVariable String name) {
        Monster monster = null;
        try {
            monster = monsterDao.getMonsterByName(name);
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.MONSTER_NOT_FOUND.toJson());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(500, e).toJson());
        }
        return ResponseEntity.ok(monster);
    }

}
