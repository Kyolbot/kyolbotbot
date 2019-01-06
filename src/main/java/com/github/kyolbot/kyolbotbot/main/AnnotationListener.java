package com.github.kyolbot.kyolbotbot.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.ReadyEvent;

public class AnnotationListener {

    static final Logger logger = LoggerFactory.getLogger(AnnotationListener.class);

    @EventSubscriber
    public void onReadyEvent(ReadyEvent event) {
        logger.info("Bot ready.");
    }
}
