package ch.zli.zlicraft.objects;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Quest {
    private String title;
    private String desc;
    private String type;
    private int id;

    public Quest(String title, String desc, String type,int id) {
        this.title = title;
        this.desc = desc;
        this.type = type;
        this.id = id;
    }

    public static ArrayList<Quest> recieve(List file) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONArray results = (JSONArray) parser.parse(new FileReader(String.valueOf(file)));

        ArrayList<Quest> returnQuest = new ArrayList<>();
        for (Object result : results) {
            JSONObject jsonResult = (JSONObject) result;
            Quest newResult = new Quest((String) jsonResult.get("title"), (String) jsonResult.get("desc"), (String) jsonResult.get("type"), (int) jsonResult.get("id"));
            returnQuest.add( newResult);
        }
        return returnQuest;
    }
}
