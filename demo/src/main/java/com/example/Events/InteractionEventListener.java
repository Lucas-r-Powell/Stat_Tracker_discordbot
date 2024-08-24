package com.example.Events;

import org.jetbrains.annotations.NotNull;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class InteractionEventListener extends ListenerAdapter{

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        super.onSlashCommandInteraction(event);
        //System.out.println("Interaction!");
        //event.reply("This command is working").setEphemeral(true).queue();
        System.out.println(event.getName());
        String guildName = event.getGuild().getName();
        switch (event.getName()) {
            case "slash-cmd" -> event.reply("This is a debug command").queue();
        
            case "stats" -> event.reply("Here are the stats for " + guildName).queue();
        }
    }
}
