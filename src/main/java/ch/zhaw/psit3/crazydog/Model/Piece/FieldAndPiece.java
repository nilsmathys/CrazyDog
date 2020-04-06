package ch.zhaw.psit3.crazydog.Model.Piece;

public class FieldAndPiece {

    private String field;
    private String piece;

    public FieldAndPiece(String field, String piece) {
        this.field = field;
        this.piece = piece;
    }

    public void setField(String field) {
        this.field = field;
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
