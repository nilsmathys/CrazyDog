package ch.zhaw.psit3.crazydog;

import ch.zhaw.psit3.crazydog.Model.GameField.GameField;
import ch.zhaw.psit3.crazydog.Model.GameField.GameFieldDAO;

import java.util.List;

public class GameBoard {

    private GameField[] fields;

    public GameBoard() {
        initialize();
    }

    private void initialize() {
        //fields = GameFieldDAO.findAll();
        List<GameField> listGameFields = GameFieldDAO.findAll();
        int size = listGameFields.size();
        fields = new GameField[size];
        System.out.println(size);
        int index = 0;
        for (GameField field : listGameFields) {
            fields[index] = field;
            index++;
        }
    }

    public void changePictureOnField(int index, String pictureName) {
        if(index < 0 || index > fields.length)
        {
            throw new IllegalArgumentException("Please specify a correct index");
        }
        fields[index].setImageName(pictureName);
    }

    public GameField[] getFields()
    {
        return fields;
    }

    public GameField getSpecificField(int index) {
        if(index < 0 || index > fields.length)
        {
            throw new IllegalArgumentException("Please specify a correct index");
        }
        return fields[index];
    }
}
