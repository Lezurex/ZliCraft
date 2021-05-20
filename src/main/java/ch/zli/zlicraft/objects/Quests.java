package ch.zli.zlicraft.objects;

public class Quests
{
    public String title;
    public String desc;
    public int id;
    private String type;

    public Quests(String title, String desc, int id)
    {
        this.title = title;
        this.desc = desc;
        this.id = id;
    }

    public String getType() {
        return this.type;
    }
}
