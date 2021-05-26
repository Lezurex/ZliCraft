package ch.zli.zlicraft.objects.tasks;

import org.bukkit.entity.EntityType;

public class KillTask extends Task {

    private EntityType entityType;

    public KillTask(int amount, TaskType taskType, EntityType entityType) {
        super(amount, taskType);
        this.entityType = entityType;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    @Override
    public String toString() {
        return "KillTask{" +
                "entityType=" + entityType +
                "} " + super.toString();
    }
}
