package ch.zhaw.psit3.crazydog.Model.Game;

import ch.zhaw.psit3.crazydog.CrazyDog;
import ch.zhaw.psit3.crazydog.Model.GameField.GameBoard;
import ch.zhaw.psit3.crazydog.Model.GameField.GameField;
import ch.zhaw.psit3.crazydog.Model.Message.Message;
import ch.zhaw.psit3.crazydog.Model.Player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameLogic {
    private static Message successmessage;
    private static List<GameField> gameFieldList;       // This is a copy of List<GameField> from Gameboard

    // Is responsible for returning a list
    public static void calculateDestinations(int cardValue, int sessionId) {
        getGameFieldsFromGameBoard();
        System.out.println(getPlayersColorFromId(1));
        System.out.println(getPlayersColorFromId(2));
        System.out.println(getPlayersColorFromId(3));
        System.out.println(getPlayersColorFromId(4));

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
        //List<GameField> fields = CrazyDog.getGameBoard().getFields();
        //for(GameField field : fields) {
        //    System.out.println(field.getGameFieldName());
        //    System.out.println(field.getImageName());
        //    System.out.println(field.getCssId());
        //    System.out.println(field.getColor());
        //    System.out.println("----------------------------");
        //}
    }

    public static List<GameField> getDestinations() {
        return gameFieldList;
    }

    public static void makeMove(int cardValue, int sessionId, String destinationfield) {
        successmessage = new Message("Erfolgreicher Zug");
    }

    public static Message getSuccessMessage() {
        return successmessage;
    }

    // INTERNAL CLASSES ONLY USED BY GAMELOGIC (private)

    // Responsible for getting the most recent version of the GameFieldList from GameBoard
    private static void getGameFieldsFromGameBoard() {
        gameFieldList = CrazyDog.getGameBoard().getFields();
    }

    // Responsible for getting a Players Color, when we know his id
    private static String getPlayersColorFromId(int id) {
        List<Player> playerList = CrazyDog.getPlayerList();
        String color = "";
        for(Player player : playerList) {
            if(player.getId() == id) {
                color = player.getColor();
            }
        }
        return color;
    }
}
