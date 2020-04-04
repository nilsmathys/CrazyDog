package ch.zhaw.psit3.crazydog.Model.Piece;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Piece {
    @NotNull
    private int id;

    @Size(min = 1, max = 4)
    private int number;

    private int colourId;

    private String pictureId;

    private int test = 0;

    public Piece() {

    }

    public Piece(int id, int number, int colourId, String pictureId) {
        this.id = id;
        this.number = number;
        this.colourId = colourId;
        this.pictureId = pictureId;
    }

    public int getInt() {
        test = test + 1;
        return test;
    }

    public String getPictureId() {
        return pictureId;
    }

    public void setPictureId(String pictureId) {
        this.pictureId = pictureId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getColourId() {
        return colourId;
    }

    public void setColourId(int colourId) {
        this.colourId = colourId;
    }


}
