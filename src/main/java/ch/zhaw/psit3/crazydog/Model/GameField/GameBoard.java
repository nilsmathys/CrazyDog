package ch.zhaw.psit3.crazydog.Model.GameField;

import ch.zhaw.psit3.crazydog.Model.GameField.GameField;
import ch.zhaw.psit3.crazydog.Model.GameField.GameFieldDAO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class GameBoard {

    private List<GameField> fields;

    public GameBoard() {
        this.fields = GameFieldDAO.getFieldsFromJSON();
    }

    public void changePictureOnField(int index, String pictureName) {
        if(index < 0 || index > fields.size())
        {
            throw new IllegalArgumentException("Please specify a correct index");
        }
        fields.get(index).setImageName(pictureName);
    }

    public List<GameField> getFields()
    {
        return fields;
    }

    public GameField getSpecificField(int index) {
        if(index < 0 || index > fields.size())
        {
            throw new IllegalArgumentException("Please specify a correct index");
        }
        return fields.get(index);
    }

}
