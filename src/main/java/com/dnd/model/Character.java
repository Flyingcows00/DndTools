package com.dnd.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;

public class Character {

    @Null
    private int characterId;
    @NotNull
    private String characterName;
    @Null
    private Boolean alive;
    @Null
    private String notes;
    @Null
    private List<Integer> campaignIds;

    public Character() {
    }

    public Character(Character another) {
        this.characterId = another.characterId;
        this.characterName = another.characterName;
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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
