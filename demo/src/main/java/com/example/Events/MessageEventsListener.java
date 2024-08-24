package com.example.Events;

import java.awt.Color;

import org.jetbrains.annotations.NotNull;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MessageEventsListener extends ListenerAdapter{

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        super.onMessageReceived(event);
        System.out.println(event.getAuthor().getName()+": " + event.getMessage().getContentRaw());

        switch (event.getMessage().getContentRaw()) {
            case "Waltah" -> event.getChannel().sendMessage("Who?").queue();
            default -> {
            }
        }
        if (event.getMessage().getContentRaw().equals("!embed")) {
            EmbedBuilder eb = new EmbedBuilder();

            eb.setTitle("Example Embed");
            eb.setDescription("This is an example of an embed.");
            eb.setColor(Color.RED);
            eb.addField("Field 1", "Some value here", false);
            eb.setFooter("Footer text", event.getJDA().getSelfUser().getAvatarUrl());

            event.getChannel().sendMessageEmbeds(eb.build()).queue();
        }

    }

}
