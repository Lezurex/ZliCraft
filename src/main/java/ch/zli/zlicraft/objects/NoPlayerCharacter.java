package ch.zli.zlicraft.objects;

import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

import java.io.IOException;
import java.util.ArrayList;

public class NoPlayerCharacter {

    Quest quest;
    String name;
    ArrayList<String> dialogue;
    Player player;
    Character character;

    public PlayerInventory checkInventory() {
        PlayerInventory inv = player.getInventory();

        return inv;
    }

    public void giveReward() throws IOException {
        if (quest.getType().equals("armor")) {
            character.incrementArmor();
        } else if(quest.getType().equals("weapon")) {
            character.incrementWeapon();
        } else {

        }
    }

    public void makeDialogue() {
        for (String sentence : dialogue) {
            player.sendMessage(sentence);
        }
    }

    public void assignQuest() {
        player.sendMessage("§I + §2" + quest.getTitle() + " " + "§a" + quest.getDesc());
    }

}


