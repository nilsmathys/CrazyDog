package ch.zhaw.psit3.crazydog;

import java.awt.*;

public class GameField {
    private String gameFieldName;
    private int positionX;
    private int positionY;
    private Color color;
    private GameField(String gameFieldName, int positionX, int positionY, Color color) {
        this.gameFieldName = gameFieldName;
        this.positionX = positionX;
        this.positionY = positionY;
        this.color = color;
    }

    public String getGameFieldName() {
        return gameFieldName;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public Color getColor() {
        return color;
    }

    public void setGameFieldName(String gameFieldName) {
        this.gameFieldName = gameFieldName;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
