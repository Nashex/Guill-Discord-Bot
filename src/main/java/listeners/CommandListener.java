package listeners;

import commands.Command;
import main.Database;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import utils.Permissions;
import utils.Utils;

import javax.annotation.Nonnull;
import java.util.Arrays;

import static main.Guill.commands;

public class CommandListener extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {

        String prefix = Database.getPrefix(event.getGuild());
        Member member = event.getMember();

        if (member == null) return;

        if (!event.getMessage().getContentRaw().startsWith(prefix))
            return;

        String[] split = event.getMessage().getContentRaw().split(" ");
        String alias = split[0].toLowerCase().replace(prefix, "");
        String[] args = Arrays.copyOfRange(split, 1, split.length);

        Command command;
        if ((command = commands.getCommand(alias)) != null) {
            try {
                if (Permissions.hasPermission(command, member)) {
                    command.execute(event.getMessage(), alias, args);
                }
            } catch (Exception e) { Utils.handleError(event.getChannel(), e); }
        }
    }
}
