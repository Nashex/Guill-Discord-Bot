package main;

import listeners.CommandListener;
import listeners.MiscListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

public class Guill {

    public static Config config;
    public static JDA jda;

    public static void main(String[] args) {

        try {
            config = new Config();
        } catch (Exception e) {
            System.out.println("Failed to Start Bot, Config Invalid");
            e.printStackTrace();
        }

        JDABuilder builder = JDABuilder.create(config.token,
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

        addEventListeners(builder);

        builder.setStatus(OnlineStatus.ONLINE);


        try {
            jda = builder.build();
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }

    public static void addEventListeners(JDABuilder builder) {
        builder.addEventListeners(new CommandListener());
        builder.addEventListeners(new MiscListener());
    }

}
