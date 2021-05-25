package ch.zli.zlicraft.objects.tasks;

import org.bukkit.Material;

public class MineTask extends Task {

    private Material material;

    public MineTask(int amount, TaskType taskType, Material material) {
        super(amount, taskType);
        this.material = material;
    }

    public Material getMaterial() {
        return material;
    }
}
