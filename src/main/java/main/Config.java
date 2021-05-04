package main;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import utils.Utils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Config {

    public String token;
    public String ownerId;

    public Config() throws Exception {
        File file = new File (Utils.getJarContainingFolder(Guill.class) + "/config.json");

        if (file.exists()) {
            JSONParser parser = new JSONParser();
            JSONObject config = (JSONObject) parser.parse(new FileReader(file));
            token = (String) config.get("Token");
            ownerId = (String) config.get("OwnerId");
        } else {
            FileWriter fileWriter = new FileWriter(file);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Token", null);
            jsonObject.put("Owner_Id", null);

            fileWriter.write(jsonObject.toJSONString());
            fileWriter.close();
        }
    }
}
