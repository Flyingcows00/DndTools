package com.dnd.model;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.util.CollectionUtils;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.springframework.util.CollectionUtils.isEmpty;

public class Character {

    private int characterId;
    private int playerId;
    @NotNull(message = "characterName is a required field")
    private String characterName;
    private Boolean alive;
    private String notes;
    private List<Integer> campaignIds;

    public Character() {
    }

    public Character(Character another) {
        this.characterId = another.characterId;
        this.playerId = another.playerId;
        this.characterName = another.characterName;
        this.alive = another.alive;
        this.notes = another.notes;
        this.campaignIds = another.campaignIds;
    }

    public Character merge(Character another) {
        campaignIds.addAll(ofNullable(another.getCampaignIds()).orElseGet(ArrayList::new));
        return this;
    }

    public int getCharacterId() {
        return characterId;
    }

    public void setCharacterId(int characterId) {
        this.characterId = characterId;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public Boolean getAlive() {
        return alive;
    }

    public void setAlive(Boolean alive) {
        this.alive = alive;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<Integer> getCampaignIds() {
        return campaignIds;
    }

    public void setCampaignIds(List<Integer> campaignIds) {
        this.campaignIds = campaignIds;
    }

    public void setCampaignId(Integer campaignId) {
        this.campaignIds = new ArrayList<>();
        this.campaignIds.add(campaignId);
    }

    public boolean areAllUpdateFieldsBlank() {
        return (this.playerId <= 0 && this.alive == null && isBlank(this.notes) && isBlank(this.characterName) && isEmpty(this.campaignIds));
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
