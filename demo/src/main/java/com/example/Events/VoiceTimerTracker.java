package com.example.Events;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceUpdateEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class VoiceTimerTracker extends ListenerAdapter {
    // Store the start time of the session
    private final Map<UUID, LocalDateTime> joinTimes = new HashMap<>();
    // Store the total time spent in VC for the current month
    private final Map<UUID, Duration> monthlyVoiceTime = new HashMap<>();

    @Override
    public void onGuildVoiceUpdate(GuildVoiceUpdateEvent event) {
        super.onGuildVoiceUpdate(event);
        GuildVoiceState voiceState = event.getVoiceState();
        if (voiceState.getChannel() != null) {
            System.out.println(event.getMember().getEffectiveName() + " joined voice channel: " + voiceState.getChannel().getName());
        //Member member = event.getMember();
        //UUID memberId = UUID.fromString(member.getId());

        //joinTimes.put(memberId, LocalDateTime.now());
        }
    }

 
 /*       
    public void onGuildVoiceLeave (onGuildVoiceLeave event) {
        Member member = event.getMember();
        UUID memberId = UUID.fromString(member.getId());

        // Get the join time
        LocalDateTime joinTime = joinTimes.get(memberId);
        if (joinTime != null) {
            // Calculate the time spent in the VC
            Duration duration = Duration.between(joinTime, LocalDateTime.now());
            // Update the total time for the month
            monthlyVoiceTime.merge(memberId, duration, Duration::plus);
            // Remove the join time as the session has ended
            joinTimes.remove(memberId);
    }*/
    // Method to get the total time spent in VC for the current month
    public Duration getMonthlyVoiceTime(Member member) {
        UUID memberId = UUID.fromString(member.getId());
        return monthlyVoiceTime.getOrDefault(memberId, Duration.ZERO);
    }

    // Reset the tracker for a new month
    public void resetMonthlyVoiceTime() {
        monthlyVoiceTime.clear();
    }

}