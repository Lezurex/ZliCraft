package ch.zli.zlicraft.listener;

import ch.zli.zlicraft.ZliCraft;
import ch.zli.zlicraft.objects.Character;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class JoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) throws IOException, ParseException {
        event.joinMessage(Component.text("§a+ §1" + event.getPlayer().getName() + " §7joined the game."));
        Character character = new Character(Bukkit.getPlayer(event.getPlayer().getName()), 20, 20, 6, 6);

        eqArmor(character);
        eqWeapon(character);
        something();

        ZliCraft.getInstance().getNpcs().forEach(npc -> npc.show(event.getPlayer()));
    }

    public void eqArmor(Character character) {
        switch (character.getArmor()) {
            case 0:
                character.setArmor(Material.AIR, Material.AIR, Material.AIR, Material.AIR);
                break;
            case 1:
                character.setArmor(Material.LEATHER_BOOTS, Material.LEATHER_LEGGINGS, Material.LEATHER_CHESTPLATE, Material.LEATHER_HELMET);
                break;
            case 2:
                character.setArmor(Material.GOLDEN_BOOTS, Material.GOLDEN_LEGGINGS, Material.GOLDEN_CHESTPLATE, Material.GOLDEN_HELMET);
                break;
            case 3:
                character.setArmor(Material.CHAINMAIL_BOOTS, Material.CHAINMAIL_LEGGINGS, Material.CHAINMAIL_CHESTPLATE, Material.CHAINMAIL_HELMET);
                break;
            case 4:
                character.setArmor(Material.IRON_BOOTS, Material.IRON_LEGGINGS, Material.IRON_CHESTPLATE, Material.IRON_HELMET);
                break;
            case 5:
                character.setArmor(Material.DIAMOND_BOOTS, Material.DIAMOND_LEGGINGS, Material.DIAMOND_CHESTPLATE, Material.DIAMOND_HELMET);
                break;
            case 6:
                character.setArmor(Material.NETHERITE_BOOTS, Material.NETHERITE_LEGGINGS, Material.NETHERITE_CHESTPLATE, Material.NETHERITE_HELMET);
                System.out.println("yeseseseajhsbfjksbdfvb");
                break;
        }
    }

    public void eqWeapon(Character character) {
        switch (character.getWeapon()) {
            case 0:
                character.setWeapon(Material.AIR, Material.AIR);
                break;
            case 1:
                character.setWeapon(Material.WOODEN_SWORD, Material.AIR);
                break;
            case 2:
                character.setWeapon(Material.GOLDEN_SWORD, Material.SHIELD);
                break;
            case 3:
                character.setWeapon(Material.STONE_SWORD, Material.AIR);
                break;
            case 4:
                character.setWeapon(Material.IRON_SWORD, Material.SHIELD);
                break;
            case 5:
                character.setWeapon(Material.DIAMOND_SWORD, Material.AIR);
                break;
            case 6:
                character.setWeapon(Material.NETHERITE_SWORD, Material.SHIELD);
                break;
        }
    }

    public void something() {

        File[] files = ZliCraft.getInstance().getDataFolder().listFiles();

        List<File> quests = new ArrayList<>();
        for (File file : files) {
            if (file.getName().toLowerCase().endsWith(".json")) {
                quests.add(file);
            }
        }
    }
}