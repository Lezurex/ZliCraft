package ch.zli.zlicraft.objects;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class noPlayerCharacter {

    Quests quest;
    String name;
    ArrayList<String> dialogue;
    Player player;
    Character character;

    public PlayerInventory checkInventory() {
        PlayerInventory inv = player.getInventory();

        return inv;
    }

    public void giveReward() {
        if (quest.getType().equals("armor")) {
            character.setArmorlvl();
        } else if(quest.getType().equals("weapon")) {
            character.setWeaponlvl();
        } else {

        }
    }

    public void makeDialogue() {
        for (String sentence : dialogue) {
            player.sendMessage(sentence);
        }
    }

    public void assignQuest() {
        player.sendMessage("§I + §2" + quest.title + " " + "§a" + quest.desc);
    }

}


