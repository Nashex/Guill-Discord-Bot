package utils;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;
import java.io.File;
import java.net.URLDecoder;
import java.security.CodeSource;

public class Utils {

    /**
     * Get the Folder that contains the .jar file
     * @param aClass Class we would like the jar containing folder of
     * @return string
     * @throws Exception Exception we throw if there is an error getting the class location
     */
    public static String getJarContainingFolder(Class aClass) throws Exception {
        CodeSource codeSource = aClass.getProtectionDomain().getCodeSource();

        File jarFile;

        if (codeSource.getLocation() != null) {
            jarFile = new File(codeSource.getLocation().toURI());
        }
        else {
            String path = aClass.getResource(aClass.getSimpleName() + ".class").getPath();
            String jarFilePath = path.substring(path.indexOf(":") + 1, path.indexOf("!"));
            jarFilePath = URLDecoder.decode(jarFilePath, "UTF-8");
            jarFile = new File(jarFilePath);
        }
        return jarFile.getParentFile().getAbsolutePath();
    }

    public static void handleError(TextChannel textChannel, Exception e) {
        EmbedBuilder embedBuilder = new EmbedBuilder().setColor(Color.RED);

        if (e instanceof Exceptions.InvalidSetupException) {
            embedBuilder.setTitle("Error: Invalid Setup")
                    .setDescription(e.getLocalizedMessage());
            textChannel.sendMessage(embedBuilder.build()).queue();
        }
    }

}
