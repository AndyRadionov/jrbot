package com.radionov.jrbot.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

/**
 * @author Andrey Radionov
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageRequestDTO {
    private String id;
    private String text;
    private String type;
    private Date timestamp;
    private ConversationData conversation;
    private UserData from;
    private UserData recipient;
    private String serviceUrl;
    private String channelId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        if (text == null) return "";
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public ConversationData getConversation() {
        return conversation;
    }

    public void setConversation(ConversationData conversation) {
        this.conversation = conversation;
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

    public String getServiceUrl() {
        return serviceUrl;
    }

    public void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    @Override
    public String toString() {
        return "MessageRequest{" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                ", type='" + type + '\'' +
                ", timestamp=" + timestamp +
                ", conversation=" + conversation +
                ", from=" + from +
                ", recipient=" + recipient +
                ", serviceUrl='" + serviceUrl + '\'' +
                '}';
    }
}
