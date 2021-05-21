package ch.zli.zlicraft.objects;

import ch.zli.zlicraft.ZliCraft;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bukkit.Bukkit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Quest {

    private static List<Quest> quests = new ArrayList<>();

    private String title;
    private String desc;
    private String type;
    private String dialogue;
    private int id;

    public Quest(String title, String desc, String type, String dialogue,int id) {
        this.title = title;
        this.desc = desc;
        this.type = type;
        this.dialogue = dialogue;
        this.id = id;
    }

    public String getType() {
        return this.type;
    }

    public String getDesc() {
        return this.desc;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDialogue() {
        return this.dialogue;
    }
    public static List<Quest> getQuests() {
        return quests;
    }

    public static void loadQuests() {
        File[] files = ZliCraft.getInstance().getDataFolder().listFiles();

        File questFile = Arrays.stream(files).filter(file -> file.getName().equalsIgnoreCase("quests.json")).findFirst().orElse(null);
        if (questFile != null) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(questFile));
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    content.append(line);
                }
                JsonArray jsonQuests = (JsonArray) new JsonParser().parse(content.toString());
                jsonQuests.forEach(questObj -> {
                    JsonObject quest = (JsonObject) questObj;
                    Quest newQuest = new Quest(quest.get("title").getAsString(), quest.get("desc").getAsString(), quest.get("type").getAsString(), quest.get("dialogue").getAsString() , quest.get("id").getAsInt());
                    quests.add(newQuest);
                });
            } catch (IOException exception) {
                exception.printStackTrace();
            }

        } else {
            // does not exist
        }
    }
}
