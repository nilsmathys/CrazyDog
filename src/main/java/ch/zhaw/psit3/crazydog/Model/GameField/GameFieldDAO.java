package ch.zhaw.psit3.crazydog.Model.GameField;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class GameFieldDAO {

    private static GameField parseFieldObject(JSONObject field)
    {
        return new GameField((String)field.get("imageName"), (String)field.get("cssId"),
                (String)field.get("gameFieldName"), (String)field.get("color"), Integer.parseInt((String)field.get("idForCalculation")));
    }

    public static List<GameField> getFieldsFromJSON()
    {
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
        ArrayList<GameField> fieldList = new ArrayList<>();
        try (FileReader reader = new FileReader("./src/main/java/ch/zhaw/psit3/crazydog/Model/GameField/gamefields.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
            JSONArray gameFieldList = (JSONArray) obj;
            //System.out.println(gameFieldList);

            //Iterate over employee array
            gameFieldList.forEach(
                    field -> fieldList.add(parseFieldObject( (JSONObject) field ))
            );

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return fieldList;
    }

    public static void main(String[] args) {
        List<GameField> fieldListAll = getFieldsFromJSON();
        int i = 0;
        for(GameField field : fieldListAll) {
            System.out.println(i + " "+ field.getImageName() + " " + field.getIdForCalculation());
            i++;
        }


    }
}
