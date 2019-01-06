package com.github.kyolbot.kyolbotbot.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.RequestBuffer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.NoSuchFileException;

public class Utilities {


    static final Logger logger = LoggerFactory.getLogger(Utilities.class);

    static IDiscordClient getBotClient(String token) {
        return new ClientBuilder()
                .withToken(token)
                .withRecommendedShardCount()
                .build();
    }

    static void sendMessage(IChannel channel, String message) {
        RequestBuffer.request(() -> {
            try {
                channel.sendMessage(message);
            } catch (DiscordException e) {
                logger.error("Message send error:", e);
            }
        });

    }

    static String getToken() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(Main.tokenFile));
            return br.readLine();
        } catch (NoSuchFileException e) {
            // the file doesn't exist, so we should probably make it
            try {
                File file = new File(Main.tokenFile);
                if (file.getParentFile() != null) {
                    file.getParentFile().mkdirs();
                    file.createNewFile();
                }
            } catch (IOException ex) {
                logger.error("Couldn't create necessary files for bot to run!", ex);
            }
            return null;
        } catch (IOException e) {
            logger.error("Token file couldn't be read.", e);
            return null;
        }
    }
}
