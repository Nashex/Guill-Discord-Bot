package listeners;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import java.util.List;

public class MiscListener extends ListenerAdapter {
    @Override
    public void onReady(@Nonnull ReadyEvent event) {
        List<Guild> guilds = event.getJDA().getGuilds();
        System.out.println("This Instance is Currently Running on " + event.getGuildTotalCount() + " Server(s) with a total of " + guilds.stream()
                .map(Guild::getMembers).mapToInt(List::size).sum() + " members!");
        guilds.forEach(guild -> System.out.println("\t⇒ " + guild.getName() + " | Member Count: " + guild.getMembers().size()));
        System.out.println();
    }
}
