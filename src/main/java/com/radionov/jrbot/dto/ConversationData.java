package com.radionov.jrbot.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Andrey Radionov
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConversationData {
    private String id;
    private boolean isGroup;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isGroup() {
        return isGroup;
    }

    public void setGroup(boolean group) {
        isGroup = group;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
