package utils;

import java.io.File;
import java.net.URLDecoder;
import java.security.CodeSource;

public class Utils {

    /**
     * Get the Folder that contains the .jar file
     * @param aClass
     * @return string
     * @throws Exception
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

}
