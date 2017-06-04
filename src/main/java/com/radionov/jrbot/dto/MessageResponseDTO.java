package com.radionov.jrbot.dto;

import com.radionov.jrbot.model.Conversation;
import com.radionov.jrbot.model.User;

/**
 * @author Andrey Radionov
 */
public class MessageResponseDTO {
    private String type;
    private User from;
    private Conversation conversation;
    private String text;

    public MessageResponseDTO() {
    }

    public MessageResponseDTO(MessageRequestDTO messageRequestDTO, String appId, String text) {
        this.type = "message";
        this.from = messageRequestDTO.getRecipient();
        this.from.setId(appId);
        this.conversation = messageRequestDTO.getConversation();
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
