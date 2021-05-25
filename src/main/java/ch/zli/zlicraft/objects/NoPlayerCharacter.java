package ch.zli.zlicraft.objects;

import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NoPlayerCharacter {

    public final List<Quest> quests;
    public String name;
    public String dialogue;

    public NoPlayerCharacter(List<Quest> quests) {
        this.quests = quests;
    }

    public void giveReward(Character character) throws IOException {
//        if (quest.getType().equals("armor")) {
//            character.incrementArmor();
//        } else if(quest.getType().equals("weapon")) {
//            character.incrementWeapon();
//        } else {
//
//        }
    }

    public void makeDialogue(Player player) {
        player.sendMessage(quests.toString());
    }

    public void assignQuest(Player player) {
        // player.sendMessage("§I + §2" + quest.getTitle() + " " + "§a" + quest.getDesc());
    }

}


