package ch.zhaw.psit3.crazydog.Model.Piece;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Piece {
    @NotNull
    private int id;

    @Size(min = 1, max = 4)
    private int number;

    private String color;

    private String pictureName;

    public Piece() {

    }

    public Piece(int id, int number, String color, String pictureName) {
        this.id = id;
        this.number = number;
        this.color = color;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        if(!(color.equals("green") || color.equals("yellow") || color.equals("red") || color.equals("blue")))
        {
            throw new IllegalArgumentException("Please use a right color.");
        }
        this.color = color;
    }


}
