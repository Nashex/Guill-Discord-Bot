package commands.misc;

import commands.Command;
import main.Guill;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import org.apache.commons.lang3.time.DurationFormatUtils;
import org.jetbrains.annotations.NotNull;

public class CommandPing extends Command {

    public CommandPing() {
        aliases = new String[]{"ping"};
        minRole = RoleName.MEMBER.getName();
    }

    @Override
    public void execute(@NotNull Message msg, String alias, String[] args) {
        msg.getTextChannel().sendMessage("Uptime: `" +
                DurationFormatUtils.formatDurationWords(System.currentTimeMillis() - Guill.timeStarted, true, false) + "`").queue();
    }

    @Override
    public EmbedBuilder getInfo() {
        return null;
    }
}
