package com.example;

//import java.io.Console;

import javax.security.auth.login.LoginException;

import com.example.Events.InteractionEventListener;
import com.example.Events.MessageEventsListener;
import com.example.Events.ReadyEventListener;
import com.example.Events.VoiceTimerTracker;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class DiscordBott_Todd {
    public static void main(String[] args) throws LoginException {

        Dotenv dotenv = Dotenv.load();
     
        final String TOKEN = dotenv.get("ToddToken");

        JDABuilder jdaBuilder = JDABuilder.createDefault(TOKEN);

        JDA jda = jdaBuilder
        .enableIntents(GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_VOICE_STATES)
        .addEventListeners(new ReadyEventListener(), new MessageEventsListener(),
         new InteractionEventListener(), new VoiceTimerTracker())
        .build();

        jda.upsertCommand("slash-cmd", "This is a command").setGuildOnly(true).queue();
        jda.upsertCommand("stats", "Stats of current server").setGuildOnly(true).queue();

    }

}
