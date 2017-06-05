package com.radionov.jrbot.dto;

/**
 * @author Andrey Radionov
 */
public class ConversationData {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Conversation{" +
                "id='" + id + '\'' +
                '}';
    }
}
