package ch.zli.zlicraft.objects.tasks;

import com.google.gson.JsonObject;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;

public abstract class Task implements ITask {

    private int amount;
    private int progress = 0;
    private final TaskType taskType;

    public Task(int amount, TaskType taskType) {
        this.amount = amount;
        this.taskType = taskType;
    }

    public boolean isComplete() {
        return this.progress >= this.amount;
    };

    public TaskType getTaskType() {
        return taskType;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getProgress() {
        return progress;
    }

    public void incrementProgress(int i) {
        this.progress += i;
    }

    public static Task fromJSON(JsonObject jsonObject) {
        if (jsonObject.get("type").getAsString().equalsIgnoreCase("mine")) {
            return new MineTask(jsonObject.get("amount").getAsInt(), TaskType.MINE, Material.valueOf(jsonObject.get("target").getAsString()));
        } else {
            return new KillTask(jsonObject.get("amount").getAsInt(), TaskType.KILL, EntityType.valueOf(jsonObject.get("target").getAsString()));
        }
    }

    @Override
    public String toString() {
        return "Task{" +
                "amount=" + amount +
                ", progress=" + progress +
                ", taskType=" + taskType +
                '}';
    }
}
