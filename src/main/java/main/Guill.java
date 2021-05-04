package main;

import listeners.CommandListener;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

public class Guill {

    public static void main(String[] args) {
        JDABuilder builder = JDABuilder.create("ODM4OTQ1MjM3Mzc1NjQ3Nzc1.YJCeow.65Y3VuVdlehYMC9-y29WXOJrdx0",
                GatewayIntent.GUILD_BANS,
                GatewayIntent.DIRECT_MESSAGE_REACTIONS,
                GatewayIntent.GUILD_EMOJIS,
                GatewayIntent.GUILD_MESSAGE_REACTIONS,
                GatewayIntent.GUILD_MEMBERS,
                GatewayIntent.DIRECT_MESSAGE_TYPING,
                GatewayIntent.GUILD_VOICE_STATES,
                GatewayIntent.DIRECT_MESSAGES,
                GatewayIntent.GUILD_MESSAGES,
                GatewayIntent.GUILD_VOICE_STATES,
                GatewayIntent.GUILD_MESSAGE_TYPING,
                GatewayIntent.GUILD_PRESENCES);

        builder.setAutoReconnect(true);

        builder.setStatus(OnlineStatus.ONLINE);
        builder.addEventListeners(new CommandListener());

        try {
            builder.build();
        } catch (LoginException e) {
            e.printStackTrace();
        }

    }

}
