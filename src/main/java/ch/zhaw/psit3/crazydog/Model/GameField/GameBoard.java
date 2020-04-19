package ch.zhaw.psit3.crazydog.Model.GameField;

import ch.zhaw.psit3.crazydog.Model.Piece.FieldAndPiece;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GameBoard {

    private List<GameField> fields;
    private static Map<String, String> fieldsAndPieces = new HashMap<>();

    public GameBoard() {
        this.fields = GameFieldDAO.getFieldsFromJSON();

        for(GameField field : fields) {
            fieldsAndPieces.put(field.getCssId(),field.getImageName());
        }
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

    public Map<String, String> getFieldsAndPieces() {
        return fieldsAndPieces;
    }

    // Put one relationship between a field and a piece
    public static void put(FieldAndPiece fap) {
        fieldsAndPieces.put(fap.getField(), fap.getPiece());
    }

}
