package ch.zhaw.psit3.crazydog.Model.Piece;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Piece {
    @NotNull
    private int id;

    @Size(min = 1, max = 4)
    private int number;

    private int colourId;

    private String pictureName;

    public Piece() {

    }

    public Piece(int id, int number, int colourId, String pictureName) {
        this.id = id;
        this.number = number;
        this.colourId = colourId;
        this.pictureName = pictureName;
    }

    public String getPictureName() {
        return pictureName;
    }

    public void setPictureName(String pictureName) {
        this.pictureName = pictureName;
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
