package com.dnd.resources;

import com.dnd.dao.MonsterDao;
import com.dnd.model.ErrorResponse;
import com.dnd.model.Monster;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.dnd.model.ErrorResponse.MONSTER_NOT_FOUND;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("monsters")
@CrossOrigin
public class MonsterResource {

    @Autowired
    private MonsterDao monsterDao;
    private static final Logger logger = LoggerFactory.getLogger(MonsterResource.class);

    @GetMapping
    public ResponseEntity<?> getMonsterNames() {
        List<String> names = monsterDao.getAllMonsterNames();
        return ResponseEntity.ok(names);
    }

    @GetMapping(path = "/{name}")
    public ResponseEntity<?> getMonsterByName(@PathVariable String name) {
        Monster monster = null;
        monster = monsterDao.getMonsterByName(name);
        return ResponseEntity.ok(monster);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<?> dataAccessException(EmptyResultDataAccessException exception) {
        ErrorResponse response = MONSTER_NOT_FOUND;
        logger.warn(response.getError(), exception);
        return ResponseEntity.status(NOT_FOUND).body(response);
    }

}
