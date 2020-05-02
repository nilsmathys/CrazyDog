package ch.zhaw.psit3.crazydog.Model.GameField;

import ch.zhaw.psit3.crazydog.Model.Piece.Piece;

public class GameField implements Comparable<GameField>{
    private String gameFieldName;
    private String imageName;
    private String cssId;
    private String color;
    private int idForCalculation;
    private Piece pieceOnField;

    public GameField(String imageName,String cssId, String gameFieldName, String color, int idForCalculation) {
        setImageName(imageName);
        setCssId(cssId);
        setGameFieldName(gameFieldName);
        setColor(color);
        setIdForCalculation(idForCalculation);
        this.pieceOnField = null;
    }

    public GameField(String cssId) {
        setCssId(cssId);
    }

    public String getGameFieldName() { return gameFieldName; }

    public String getImageName() {
        return imageName;
    }

    public String getColor() {
        return color;
    }

    public String getCssId() {
        return cssId;
    }

    public void setGameFieldName(String gameFieldName) {
        if(!(gameFieldName.equals("wormhole") || gameFieldName.equals("standard") || gameFieldName.equals("startfield") ||
                gameFieldName.equals("destinationfield") || gameFieldName.equals("homefield")))
        {
            throw new IllegalArgumentException("Please use a right Game Field Name.");
        }
        this.gameFieldName = gameFieldName;
    }

    public void setImageName(String startImageName) {
        this.imageName = startImageName;
    }

    public void setColor(String color) {
        if(!(color.equals("white") || color.equals("black") || color.equals("green") ||
                color.equals("yellow") || color.equals("red") || color.equals("blue")))
        {
            throw new IllegalArgumentException("Please use a right color.");
        }
        this.color = color;
    }
    public void setCssId(String cssId) {
        this.cssId = cssId;
    }

    public int getIdForCalculation() {
        return idForCalculation;
    }

    public void setIdForCalculation(int idForCalculation) {
        this.idForCalculation = idForCalculation;
    }

    public Piece getPieceOnField() {
        return pieceOnField;
    }
    public void setPieceOnField(Piece pieceOnField) {
        this.pieceOnField = pieceOnField;
    }

    @Override
    public int compareTo(GameField d) {
        return this.idForCalculation - d.getIdForCalculation();
    }

}
