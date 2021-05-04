package commands;

import java.util.ArrayList;
import java.util.Arrays;

public class CommandHub extends ArrayList<Command> {

    public Command getCommand(String alias) {
        return stream().filter(cmd -> Arrays.stream(cmd.aliases).anyMatch(s -> s.equalsIgnoreCase(alias))).findAny().orElse(null);
    }

}
