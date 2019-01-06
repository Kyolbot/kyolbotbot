package com.github.kyolbot.kyolbotbot.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;

public class Main {
    public static final String dataDirectory = "Data";
    public static final String tokenFile = dataDirectory + "/token.txt";
    static final Logger logger = LoggerFactory.getLogger(Main.class);
    public static IDiscordClient client;

    public static void main(String[] args) {
        try {
            // Set a shutdown hook to run any saving if we need it.
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                //client.logout();
                logger.info("Goodbye!");
            }));

            // Build client
            String token = Utilities.getToken();
            if (token == null || token.isEmpty()) throw new IllegalStateException("No bot token configured!");
            client = Utilities.getBotClient(token);
            if (client == null) throw new IllegalStateException("Client couldn't be created.");

            // Load the event listener and register it
            EventDispatcher dispatcher = client.getDispatcher();
            dispatcher.registerListener(new AnnotationListener());

            // Log in.
            client.login();
        } catch (Exception e) {
            logger.error("Exception occurred during initialization: ", e);
        }
    }


}
