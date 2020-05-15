package ch.zhaw.psit3.crazydog.Model.GameField;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <h1>GameFieldDAO</h1>
 * The GameFieldDAO reads a JSON File to get GameFields Objects. The JSON File is stored in the same
 * package and the name is gamefields.json<br>
 *
 * @author R. Somma
 * @version 1.0
 * @since April 2020
 */
public class GameFieldDAO {
    private static final Logger LOGGER = Logger.getLogger(GameFieldDAO.class.getName());

    /**
     * Generates a GameField Object from a given JSON Object
     *
     * @param field one JSON Object from a single field
     * @return return an Object GameField from the JSON
     */
    private static GameField parseFieldObject(JSONObject field) {
        return new GameField((String) field.get("imageName"), (String) field.get("cssId"),
                (String) field.get("gameFieldName"), (String) field.get("color"), Integer.parseInt((String) field.get("idForCalculation")));
    }

    /**
     * read all GameField from the JSON File and return them in a list
     *
     * @return a List of GameFields
     */
    public static List<GameField> getFieldsFromJSON() {
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
        ArrayList<GameField> fieldList = new ArrayList<>();
        try (FileReader reader = new FileReader("./src/main/java/ch/zhaw/psit3/crazydog/Model/GameField/gamefields.json")) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
            JSONArray gameFieldList = (JSONArray) obj;
            //System.out.println(gameFieldList);

            //Iterate over employee array
            gameFieldList.forEach(
                    field -> fieldList.add(parseFieldObject((JSONObject) field))
            );

        } catch (IOException | ParseException e) {
            LOGGER.log(Level.SEVERE, "Couldn't load fields from JSON.", e);
        }

        return fieldList;
    }

}
