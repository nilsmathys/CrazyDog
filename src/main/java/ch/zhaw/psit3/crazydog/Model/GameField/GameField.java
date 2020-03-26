package ch.zhaw.psit3.crazydog.Model.GameField;

public class GameField {
    private String gameFieldName;
    private String startImageName;
    private String cssId;
    private String color;

    public GameField(String startImageName,String cssId, String gameFieldName, String color) {
        this.gameFieldName = gameFieldName;
        this.startImageName = startImageName;
        this.color = color;
    }

    public String getGameFieldName() {
        return gameFieldName;
    }

    public String getStartImageName() {
        return startImageName;
    }

    public String getColor() {
        return color;
    }

    public String getCssId() {
        return cssId;
    }

    public void setGameFieldName(String gameFieldName) {
        this.gameFieldName = gameFieldName;
    }

    public void setStartImageName(String startImageName) {
        this.startImageName = startImageName;
    }

    public void setColor(String color) {
        this.color = color;
    }
    public void setCssId(String cssId) {
        this.cssId = cssId;
    }
}
