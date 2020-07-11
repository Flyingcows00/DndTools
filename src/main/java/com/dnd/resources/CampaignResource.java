package com.dnd.resources;

import com.dnd.dao.CampaignDao;
import com.dnd.model.Campaign;
import com.dnd.model.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLIntegrityConstraintViolationException;

import static com.dnd.model.ErrorResponse.CAMPAIGN_ALREADY_EXISTS;
import static com.dnd.model.ErrorResponse.CAMPAIGN_NOT_FOUND;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("campaigns")
@CrossOrigin
@Validated
public class CampaignResource {

    @Autowired
    private CampaignDao campaignDao;
    private static final Logger logger = LoggerFactory.getLogger(CampaignResource.class);

    @GetMapping
    public ResponseEntity<?> getCampaigns() {
        return ResponseEntity.ok(campaignDao.getCampaigns());
    }

    @GetMapping(path = "/{name}")
    public ResponseEntity<?> getCampaign(@PathVariable String name) {
        return ResponseEntity.ok(campaignDao.getCampaign(name));
    }

    @PostMapping
    public ResponseEntity<?> createCampaign(@RequestBody @Valid Campaign campaign) {
        campaignDao.createCampaign(campaign.getName());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{campaign}")
    public ResponseEntity<?> deleteCampaign(@PathVariable String campaign) {
        campaignDao.deleteCampaign(campaign);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<?> constraintViolationException(SQLIntegrityConstraintViolationException exception) {
        ErrorResponse response = CAMPAIGN_ALREADY_EXISTS;
        logger.warn(response.getError(), exception);
        return ResponseEntity.status(BAD_REQUEST).body(response);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<?> dataAccessException(EmptyResultDataAccessException exception) {
        ErrorResponse response = CAMPAIGN_NOT_FOUND;
        logger.warn(response.getError(), exception);
        return ResponseEntity.status(NOT_FOUND).body(response);
    }

}
