package commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;

import javax.annotation.Nonnull;

public abstract class Command {
    protected static String[] aliases;
    @Nonnull public static String minRole = RoleName.MEMBER.getName();

    public abstract void execute(@Nonnull Message msg, String alias, String[] args);

    public abstract EmbedBuilder getInfo();

    public enum RoleName {
        MEMBER("member");

        private String name;

        RoleName(String name) { this.name = name; }

        public String getName() { return name; }
    }

    @Nonnull
    public static String getMinRole() {
        return minRole;
    }
}
