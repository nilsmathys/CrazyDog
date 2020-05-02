package ch.zhaw.psit3.crazydog.Model.Game;

import ch.zhaw.psit3.crazydog.CrazyDog;
import ch.zhaw.psit3.crazydog.Model.GameField.GameBoard;
import ch.zhaw.psit3.crazydog.Model.GameField.GameField;
import ch.zhaw.psit3.crazydog.Model.Message.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameLogic {
    static Message successmessage;
    static ArrayList<GameField> gameFieldList = new ArrayList<>();

    // Is responsible for returning a list
    public static void calculateDestinations(int cardValue, int sessionId) {
        gameFieldList.clear();
        Random rand = new Random();
        GameField gameField1 = new GameField("field" + rand.nextInt(96));
        GameField gameField2 = new GameField("field" + rand.nextInt(96));
        GameField gameField3 = new GameField("field" + rand.nextInt(96));
        GameField gameField4 = new GameField("field" + rand.nextInt(96));
        gameFieldList.add(gameField1);
        gameFieldList.add(gameField2);
        gameFieldList.add(gameField3);
        gameFieldList.add(gameField4);

        // How to access the List with fields, which will be needed to calculate the destinations
        List<GameField> fields = CrazyDog.getGameBoard().getFields();
        for(GameField field : fields) {
            //System.out.println(field.getImageName());
        }
    }

    public static ArrayList<GameField> getDestinations() {
        return gameFieldList;
    }

    public static void makeMove(int cardValue, int sessionId, String destinationfield) {
        successmessage = new Message("Erfolgreicher Zug");
    }

    public static Message getSuccessMessage() {
        return successmessage;
    }
}
