package ch.zhaw.psit3.crazydog.Model.Piece;

/*
This class should build simple Objects that will be returned to the ajax calls to manipulate the DOM.
 */
public class FieldAndPiece {

    private String field;
    private String piece;

    public FieldAndPiece(String field, String piece) {
        this.field = field;
        this.piece = piece;
    }

    public void setPiece(String piece) {
        this.piece = piece;
    }

    public String getField() {
        return field;
    }

    public String getPiece() {
        return piece;
    }
}
