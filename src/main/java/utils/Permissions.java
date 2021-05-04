package utils;

import commands.Command;
import main.Database;
import main.Guill;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;


public class Permissions {

    public static boolean hasPermission(Command command, Member member) throws Exceptions.InvalidSetupException {
        Guild guild = member.getGuild();
        Role minRole = Database.getRole(member.getGuild(), command.getMinRole());
        if (minRole == null) throw new Exceptions.InvalidSetupException("Role (" + command.getMinRole() + ") is not setup or has an invalid ID.");

        return (member.hasPermission(Permission.ADMINISTRATOR) || member.getId().equals(Guill.config.ownerId))
                || member.getRoles().stream().anyMatch(role -> role.getPosition() >= minRole.getPosition());
    }

}
