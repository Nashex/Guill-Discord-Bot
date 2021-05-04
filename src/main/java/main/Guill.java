package main;

import commands.CommandHub;
import commands.misc.CommandPing;
import listeners.CommandListener;
import listeners.MiscListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.requests.GatewayIntent;
import utils.requests.ProxyHelper;

import javax.security.auth.login.LoginException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class Guill {

    public static Config config;
    public static JDA jda;
    public static CommandHub commands = new CommandHub();
    public static long timeStarted = System.currentTimeMillis();

    public static ScheduledExecutorService TIMER = new ScheduledThreadPoolExecutor(2);
    public static ProxyHelper proxyHelper = new ProxyHelper();

    public static void main(String[] args) {

        try {
            config = new Config();
        } catch (Exception e) {
            System.out.println("Failed to Start Bot, Config Invalid");
            e.printStackTrace();
            System.exit(-1);
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

        addCommands();

        builder.setStatus(OnlineStatus.ONLINE);


        try {
            jda = builder.build();
        } catch (LoginException e) {
            e.printStackTrace();
            System.exit(-1);
        }

    }

    public static void addEventListeners(JDABuilder builder) {
        builder.addEventListeners(new CommandListener());
        builder.addEventListeners(new MiscListener());
    }

    public static void addCommands() {
        commands.add(new CommandPing());
    }

}
