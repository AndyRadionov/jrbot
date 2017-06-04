package com.radionov.jrbot.app;

import com.radionov.jrbot.service.MessageService;
import com.radionov.jrbot.service.MessageServiceImpl;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

import javax.inject.Singleton;

/**
 * @author Andrey Radionov
 */
public class JRBotBinder extends AbstractBinder {
    @Override
    protected void configure() {
        bind(MessageServiceImpl.class).to(MessageService.class).in(Singleton.class);
    }
}
