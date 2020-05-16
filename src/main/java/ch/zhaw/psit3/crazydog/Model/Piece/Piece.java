package ch.zhaw.psit3.crazydog.Model.Piece;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * <h1>Piece</h1>
 * Piece holds necessary information about one Piece<br>
 *
 * @author N. Mathys
 * @version 1.0
 * @since April 2020
 */
public class Piece {
    @NotNull
    private int id;
    @Size(min = 1, max = 4)
    private int number;
    private String color;
    private String pictureName;
    private int homeFieldId;

    /**
     * Constructor to create a new Piece, is used in PieceDAO to create a Player before the database connects
     */
    public Piece() {

    }

    /**
     * Constructor to create a new Piece
     *
     * @param id          id from Piece
     * @param number      Number of Piece
     * @param color       color of piece
     * @param pictureName pictureName of piece
     * @param homeFieldId homeFieldId of piece
     */
    public Piece(int id, int number, String color, String pictureName, int homeFieldId) {
        this.id = id;
        this.number = number;
        this.color = color;
        this.pictureName = pictureName;
        this.homeFieldId = homeFieldId;
    }

    /**
     * Get the picture name from the piece
     *
     * @return pictureName
     */
    public String getPictureName() {
        return pictureName;
    }

    /**
     * Sets the pictureName
     *
     * @param pictureName pictureName who you want to set
     */
    public void setPictureName(String pictureName) {
        this.pictureName = pictureName;
    }

    /**
     * Get the id from the piece
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets a id
     *
     * @param id id who you want to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get the number from the piece
     *
     * @return number
     */
    public int getNumber() {
        return number;
    }

    /**
     * Sets a number
     *
     * @param number number who you want to set
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Get the color from the piece
     *
     * @return color
     */
    public String getColor() {
        return color;
    }

    /**
     * Set a new color, must be green, yellow, red or blue
     *
     * @param color color who you want to set
     */
    public void setColor(String color) {
        if (!(color.equals("green") || color.equals("yellow") || color.equals("red") || color.equals("blue"))) {
            throw new IllegalArgumentException("Please use a right color.");
        }
        this.color = color;
    }

    /**
     * Get the homefield id from the piece
     *
     * @return homeFieldId
     */
    public int getHomeFieldId() {
        return homeFieldId;
    }

    /**
     * Set a homeFieldId
     *
     * @param homeFieldId homeFieldId who you want to set
     */
    public void setHomeFieldId(int homeFieldId) {
        this.homeFieldId = homeFieldId;
    }

    /**
     * Return true when the given object is equals to the piece
     *
     * @param p the object who you want to check
     * @return true when equals, else false
     */
    @Override
    public boolean equals(Object p) {
        Piece o = (Piece) p;
        if (this.getColor().equals(o.getColor()) && this.getNumber() == o.getNumber() && this.getHomeFieldId() == o.getHomeFieldId() &&
                this.getId() == o.getId() && this.getPictureName().equals(o.getPictureName())) {
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
