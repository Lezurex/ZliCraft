package ch.zli.zlicraft.objects;

import ch.zli.zlicraft.ZliCraft;
import ch.zli.zlicraft.objects.tasks.Task;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bukkit.entity.Player;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Quest {

    private static final List<Quest> quests = new ArrayList<>();

    private String title;
    private String desc;
    private String type;
    private Task task;
    private int id;

    public Quest(String title, String desc, String type, int id, Task task) {
        this.title = title;
        this.desc = desc;
        this.type = type;
        this.id = id;
        this.task = task;
    }

    public void announceQuest(Player player) {
        player.sendMessage(ZliCraft.DIEGO_PREFIX + "§a§lQUEST");
        player.sendMessage(ZliCraft.DIEGO_PREFIX + "§7§l" + this.title);
        player.sendMessage(ZliCraft.DIEGO_PREFIX + "§7" + this.desc);
        player.sendMessage(ZliCraft.DIEGO_PREFIX + "§7Progress: " + task.getProgress() + " / " + task.getAmount());
    }

    public boolean isComplete() {
        return task.isComplete();
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

    public Task getTask() {
        return task;
    }

    public int getId() {
        return id;
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
                    Task task = Task.fromJSON(quest.getAsJsonObject("task"));
                    Quest newQuest = new Quest(quest.get("title").getAsString(), quest.get("desc").getAsString(), quest.get("type").getAsString(), quest.get("id").getAsInt(), task);
                    quests.add(newQuest);
                });
                System.out.println(quests.toString());
            } catch (IOException exception) {
                exception.printStackTrace();
            }

        } else {
            // does not exist
        }
    }

    public static Quest getById(int id) {
        return quests.stream().filter(quest -> quest.id == id).findFirst().orElse(null);
    }

    @Override
    public String toString() {
        return "Quest{" +
                "title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", type='" + type + '\'' +
                ", task=" + task +
                ", id=" + id +
                '}';
    }
}
