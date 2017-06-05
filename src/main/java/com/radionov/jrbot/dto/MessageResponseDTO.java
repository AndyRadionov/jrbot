package com.radionov.jrbot.dto;

/**
 * @author Andrey Radionov
 */
public class MessageResponseDTO {
    private String type;
    private UserData from;
    private ConversationData conversation;
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

    public UserData getFrom() {
        return from;
    }

    public void setFrom(UserData from) {
        this.from = from;
    }

    public ConversationData getConversation() {
        return conversation;
    }

    public void setConversation(ConversationData conversation) {
        this.conversation = conversation;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "MessageResponseDTO{" +
                "type='" + type + '\'' +
                ", from=" + from +
                ", conversation=" + conversation +
                ", text='" + text + '\'' +
                '}';
    }
}
