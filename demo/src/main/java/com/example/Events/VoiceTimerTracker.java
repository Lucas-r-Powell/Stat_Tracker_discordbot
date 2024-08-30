package com.example.Events;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceUpdateEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class VoiceTimerTracker extends ListenerAdapter {
    // Store the start time of the session
    private final Map<Long, LocalDateTime> joinTimes = new HashMap<>();
    // Store the total time spent in VC for the current month
    private final Map<Long, Duration> monthlyVoiceTime = new HashMap<>();

    @Override
    public void onGuildVoiceUpdate(GuildVoiceUpdateEvent event) {
        Member member = event.getMember();
       Long memberId = member.getIdLong();
        super.onGuildVoiceUpdate(event);
        if (event.getChannelJoined() != null) {
            System.out.println(event.getMember().getEffectiveName() + " joined voice channel: " + event.getChannelJoined().getName());

        joinTimes.put(memberId,LocalDateTime.now());
        System.out.println(joinTimes.get(memberId));
        } else if(event.getChannelLeft() != null) {
            System.out.println(event.getMember().getEffectiveName() + " Left voice channel: " + event.getChannelLeft().getName());
            LocalDateTime joinTime = joinTimes.get(memberId);
            Duration duration = Duration.between(joinTime, LocalDateTime.now());

            // Update the total time for the month
            monthlyVoiceTime.merge(memberId, duration, Duration::plus);

            // Remove the join time as the session has ended
            joinTimes.remove(memberId);

            //convert the stored values into a readable time
            Duration normalizedMvoiceTime = monthlyVoiceTime.get(memberId);
            long hoursInVc = normalizedMvoiceTime.toHours();
            long minsInVc = normalizedMvoiceTime.toMinutesPart();
            long secsInVc = normalizedMvoiceTime.toSecondsPart();

            System.out.println("Hours: " + hoursInVc + "Mins: " + minsInVc + "Seconds: " + secsInVc);
        }
    }

    // Method to get the total time spent in VC for the current month
    public Duration getMonthlyVoiceTime(Member member) {
        long memberId = member.getIdLong();
        return monthlyVoiceTime.getOrDefault(memberId, Duration.ZERO);
    }

    // Reset the tracker for a new month
    public void resetMonthlyVoiceTime() {
        monthlyVoiceTime.clear();
    }

}