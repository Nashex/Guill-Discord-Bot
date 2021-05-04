package main;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Objects;


public class Database {

    final static String dbUrl = "jdbc:sqlite:database.db";

    public static void addGuild(Guild guild) {
        try (Connection conn = DriverManager.getConnection(dbUrl);
             Statement stmt = conn.createStatement()){
            stmt.executeUpdate("INSERT OR IGNORE INTO guildConfig(guildId) VALUES (" + guild.getId() + ")");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Database connection error.");
        }
    }

    public static String query(String column, String table, String conditions) {
        try (Connection conn = DriverManager.getConnection(dbUrl);
             Statement stmt = conn.createStatement()){
            return stmt.executeQuery("SELECT " + column + " FROM " + table + " WHERE " + conditions).getString(column);
        } catch (Exception e) {
            System.out.println("Database connection error.");
        }
        return "";
    }

    public static String getPrefix(Guild guild) {
        addGuild(guild);
        return query("prefix", "guildConfig", "guildId = " + guild.getId());
    }

    public static Role getRole(Guild guild, String roleName) {
        return guild.getRoleById(Objects.requireNonNull(query(roleName + "RoleId", "guildConfig", "guildId = " + guild.getId())));
    }

    public static Role getTextChannel(Guild guild, String channelName) {
        return guild.getRoleById(Objects.requireNonNull(query(channelName + "ChannelId", "guildConfig", "guildId = " + guild.getId())));
    }

}
