package ch.zhaw.psit3.crazydog.Model.Game;

import ch.zhaw.psit3.crazydog.Model.GameField.GameField;

import java.util.ArrayList;

public class GameLogic {
    static ArrayList<GameField> gameFieldList = new ArrayList<>();

    // Is responsible for returning a list
    public static void calculateDestinations(int cardValue, int sessionId) {
        GameField gameField1 = new GameField("field1");
        GameField gameField2 = new GameField("field2");
        GameField gameField3 = new GameField("field3");
        GameField gameField4 = new GameField("field4");
        gameFieldList.add(gameField1);
        gameFieldList.add(gameField2);
        gameFieldList.add(gameField3);
        gameFieldList.add(gameField4);
    }

    public static ArrayList<GameField> getDestinations() {
        return gameFieldList;
    }
}
