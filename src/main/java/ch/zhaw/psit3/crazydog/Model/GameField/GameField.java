package ch.zhaw.psit3.crazydog.Model.GameField;

import ch.zhaw.psit3.crazydog.Model.Piece.Piece;

/**
 * <h1>GameFieldDAO</h1>
 * The GameField is a single Field which also stores the current piece that is on the field <br>
 *
 * @author R. Somma
 * @version 1.0
 * @since April 2020
 */
public class GameField implements Comparable<GameField> {
    private String gameFieldName;
    private String imageName;
    private String cssId;
    private String color;
    private int idForCalculation;
    private Piece pieceOnField;

    public GameField(String imageName, String cssId, String gameFieldName, String color, int idForCalculation) {
        setImageName(imageName);
        setCssId(cssId);
        setGameFieldName(gameFieldName);
        setColor(color);
        setIdForCalculation(idForCalculation);
        this.pieceOnField = null;
    }

    /**
     * Get the gameFieldName
     *
     * @return gameFieldName
     */
    public String getGameFieldName() {
        return gameFieldName;
    }

    /**
     * Get the imageName
     *
     * @return imageName
     */
    public String getImageName() {
        return imageName;
    }

    /**
     * Get the color
     *
     * @return color
     */
    public String getColor() {
        return color;
    }

    /**
     * Get the cssId
     *
     * @return cssId
     */
    public String getCssId() {
        return cssId;
    }

    /**
     * sets the Name of the GameField
     *
     * @param gameFieldName new Name of the GameField (must be wormhole, standard, startfield, destinationfield or homefield)
     */
    public void setGameFieldName(String gameFieldName) {
        if (!(gameFieldName.equals("wormhole") || gameFieldName.equals("standard") || gameFieldName.equals("startfield") ||
                gameFieldName.equals("destinationfield") || gameFieldName.equals("homefield"))) {
            throw new IllegalArgumentException("Please use a right Game Field Name.");
        }
        this.gameFieldName = gameFieldName;
    }

    /**
     * sets the ImageName of the GameField
     *
     * @param startImageName new Image Name of the Gamefield
     */
    public void setImageName(String startImageName) {
        this.imageName = startImageName;
    }

    /**
     * sets the Color of the GameField
     *
     * @param color new color of the GameField (must be white, black, green, yellow, blue or red)
     */
    public void setColor(String color) {
        if (!(color.equals("white") || color.equals("black") || color.equals("green") ||
                color.equals("yellow") || color.equals("red") || color.equals("blue"))) {
            throw new IllegalArgumentException("Please use a right color.");
        }
        this.color = color;
    }

    /**
     * sets the cssId of the GameField
     *
     * @param cssId new cssId of the GameField
     */
    public void setCssId(String cssId) {
        this.cssId = cssId;
    }

    /**
     * Get the idForCalculation
     *
     * @return idForCalculation
     */
    public int getIdForCalculation() {
        return idForCalculation;
    }

    public void setIdForCalculation(int idForCalculation) {
        this.idForCalculation = idForCalculation;
    }

    /**
     * Get the current Piece on the Field
     *
     * @return pieceOnField
     */
    public Piece getPieceOnField() {
        return pieceOnField;
    }

    /**
     * Set the current Piece that is on the field (null if no Field is on it)
     *
     * @param pieceOnField new Piece that should be on the field
     */
    public void setPieceOnField(Piece pieceOnField) {
        this.pieceOnField = pieceOnField;
    }

    @Override
    public int compareTo(GameField d) {
        return this.idForCalculation - d.getIdForCalculation();
    }

    /**
     * Return true when the given object is equals to the piece
     *
     * @param field the object who you want to check
     * @return true when equals, else false
     */
    @Override
    public boolean equals(Object field) {
        GameField f = (GameField) field;
        if (this.getColor().equals(f.getColor()) &&
                ((this.getPieceOnField() == null && f.getPieceOnField() == null) || this.getPieceOnField().equals(f.getPieceOnField()))
                && this.getGameFieldName().equals(f.getGameFieldName()) &&
                this.getCssId().equals(f.getCssId()) && this.getImageName().equals(f.getImageName()) &&
                this.getIdForCalculation() == f.getIdForCalculation()) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 1;
        hash = hash * 13 + (getColor().hashCode());
        hash = hash * 17 + (getIdForCalculation());
        hash = hash * 31 + (getImageName().hashCode());
        hash = hash * 31 + (getCssId().hashCode());
        hash = hash * 31 + (getGameFieldName().hashCode());
        if(getPieceOnField() != null) {
            hash = hash * 31 + (getPieceOnField().hashCode());
        }
        return hash;
    }

}
