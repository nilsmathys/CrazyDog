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
    private int homeFieldId;

    public Piece() {

    }

    public Piece(int id, int number, String color, String pictureName, int homeFieldId) {
        this.id = id;
        this.number = number;
        this.color = color;
        this.pictureName = pictureName;
        this.homeFieldId = homeFieldId;
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

    /**
     * Set a new color, must be green, yellow, red or blue
     * @param color
     */
    public void setColor(String color) {
        if(!(color.equals("green") || color.equals("yellow") || color.equals("red") || color.equals("blue")))
        {
            throw new IllegalArgumentException("Please use a right color.");
        }
        this.color = color;
    }

    public int getHomeFieldId() {
        return homeFieldId;
    }

    public void setHomeFieldId(int homeFieldId) {
        this.homeFieldId = homeFieldId;
    }

    @Override
    public boolean equals(Object p) {
        Piece o = (Piece) p;
        if(this.getColor().equals(o.getColor()) && this.getNumber() == o.getNumber() && this.getHomeFieldId() == o.getHomeFieldId() &&
            this.getId() == o.getId() && this.getPictureName().equals(o.getPictureName()) )
        {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 1;
        hash = hash * 13 + (getColor().hashCode());
        hash = hash * 17 + (getNumber());
        hash = hash * 31 + (getHomeFieldId());
        hash = hash * 31 + (getId());
        hash = hash * 31 + (getPictureName().hashCode());
        return hash;
    }

}
