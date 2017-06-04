package com.radionov.jrbot.service;

import com.radionov.jrbot.dto.MessageRequestDTO;

/**
 * @author Andrey Radionov
 */
public interface MessageService {
    void processMessage(MessageRequestDTO messageRequestDTO);
}
