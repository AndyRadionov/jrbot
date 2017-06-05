package com.radionov.jrbot.dto;

/**
 * @author Andrey Radionov
 */
public class MessageResponseDTO {
    private String type;
    private UserData from;
    private UserData recipient;
    private ConversationData conversation;
    private String text;
    private String replyToId;

    public MessageResponseDTO() {
    }

    public MessageResponseDTO(MessageRequestDTO messageRequestDTO, String text) {
        this.type = "message";
        this.from = messageRequestDTO.getRecipient();
        this.recipient = messageRequestDTO.getFrom();
        this.conversation = messageRequestDTO.getConversation();
        this.replyToId = messageRequestDTO.getId();
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

    public UserData getRecipient() {
        return recipient;
    }

    public void setRecipient(UserData recipient) {
        this.recipient = recipient;
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

    public String getReplyToId() {
        return replyToId;
    }

    public void setReplyToId(String replyToId) {
        this.replyToId = replyToId;
    }

    @Override
    public String toString() {
        return "MessageResponse{" +
                "type='" + type + '\'' +
                ", from=" + from +
                ", conversation=" + conversation +
                ", text='" + text + '\'' +
                '}';
    }
}
