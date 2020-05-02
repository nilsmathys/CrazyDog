package ch.zhaw.psit3.crazydog.Model.Game;

import ch.zhaw.psit3.crazydog.CrazyDog;
import ch.zhaw.psit3.crazydog.Model.GameField.GameBoard;
import ch.zhaw.psit3.crazydog.Model.GameField.GameField;
import ch.zhaw.psit3.crazydog.Model.Message.Message;
import ch.zhaw.psit3.crazydog.Model.Piece.Piece;
import ch.zhaw.psit3.crazydog.Model.Player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import static ch.zhaw.psit3.crazydog.Model.Game.Direction.CLOCKWISE;

public class GameLogic {
    private static Message successmessage;
    private static List<GameField> gameFieldList;                                        // This is a copy of List<GameField> from Gameboard

    private static List<GameField> calculatedDestinations = new ArrayList<GameField>();  // This List contains the calculated destinations

    // Is responsible for returning a list
    public static void calculateDestinations(int cardValue, int sessionId) {
        calculatedDestinations = null;      // Reset the calculated destinations
        getGameFieldsFromGameBoard();
        String playerColor = getPlayersColorFromId(sessionId);
        List<GameField> GameFieldsWithThisColor = getGameFieldsWithPiecesOfPlayersColor(playerColor);
        // Now we have all the fields with Pieces of the Player
        List<GameField> cleanedList = removeGameFieldsWithPiecesOnHomeFields(GameFieldsWithThisColor);
        // Removed the GameFields with Pieces on HomeFields

        // Jetzt haben wir eigentlich alle GameFields in der Liste, die Spielfiguren ausserhalb des HomeFields beinhalten
        // Jetzt müssen wir die neuen Werte berechnen

        // Abfrage der direction
        // Berücksichtigen, das es einen Sprung bei 64 gibt
        if(cleanedList.isEmpty()) {
            // do nothing
        }
        else {
            // Calculate each destination field
            for(GameField gamefield : cleanedList) {
                int sourceId = gamefield.getIdForCalculation();
                if(CrazyDog.getDirection() == CLOCKWISE) {
                    int destinationId = getDestinationId(sourceId, cardValue);
                    int calculationIdOfPassedStartField = getStartFieldIfStartFieldIsPassed(sourceId, destinationId);
                    // No StartField was passed, everything is ok, we can simply add the card value
                    if(calculationIdOfPassedStartField == 0) {
                        GameField calculatedGameField = getGameFieldWithCalculationId(destinationId, "standard");
                        calculatedDestinations.add(calculatedGameField);
                    }
                    // Startfield was passed (piece would land on startfield), we have to check some things
                    else {
                        GameField startField = getGameFieldWithCalculationId(calculationIdOfPassedStartField, "startfield");
                        // First check if there is a piece on the startfield (if there is, we can't continue)
                        if(!isFieldOccupied(startField)) {
                            if(destinationId == calculationIdOfPassedStartField) {
                                GameField calculatedGameField = getGameFieldWithCalculationId(destinationId, "startfield");
                                calculatedDestinations.add(calculatedGameField);
                            }
                            // Now we know that we pass the startfield and don't land on it
                            else {
                                // Now we check if the Field after the StartField is a Destinationfield of the player
                                // We also need to check if the destinationfield is out of range (bigger than the calculationId's of the destination fields)
                                GameField destinationFieldNextToStartField = getGameFieldWithCalculationId(calculationIdOfPassedStartField + 1, "destinationfield");
                                if(destinationFieldNextToStartField.getColor().equals(playerColor) && (destinationId <= calculationIdOfPassedStartField + 4)) {
                                    // Now we know that we could move a piece into player's destination fields.
                                    // But only if there are no Pieces blocking (we can't move past pieces in destination fields)
                                    if(!arePiecesBlockingOnDestinationFields(destinationId, destinationFieldNextToStartField)) {
                                        GameField calculatedGameField = getGameFieldWithCalculationId(destinationId, "destinationfield");
                                        calculatedDestinations.add(calculatedGameField);
                                        calculatedGameField = getGameFieldWithCalculationId(destinationId, "standard");
                                        calculatedDestinations.add(calculatedGameField);
                                    }
                                    // Some pieces are blocking, so we can only move to a standard field
                                    else {
                                        GameField calculatedGameField = getGameFieldWithCalculationId(destinationId, "standard");
                                        calculatedDestinations.add(calculatedGameField);
                                    }
                                }
                                // We can't move the Piece into the Destination Fields. But we can move past the Destination Fields,
                                // and just place it on a standard field.
                                else {
                                    GameField calculatedGameField = getGameFieldWithCalculationId(destinationId, "standard");
                                    calculatedDestinations.add(calculatedGameField);
                                }
                            }
                        }
                    }
                }
                else {
                    // Do the whole logic counterclockwise
                }
            }
        }

        //calculatedDestinations.clear();
        //Random rand = new Random();
        //GameField gameField1 = new GameField("field" + rand.nextInt(96));
        //GameField gameField2 = new GameField("field" + rand.nextInt(96));
        //GameField gameField3 = new GameField("field" + rand.nextInt(96));
        //GameField gameField4 = new GameField("field" + rand.nextInt(96));
        //calculatedDestinations.add(gameField1);
        //calculatedDestinations.add(gameField2);
        //calculatedDestinations.add(gameField3);
        //calculatedDestinations.add(gameField4);
    }

    public static List<GameField> getDestinations() {
        return calculatedDestinations;
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

    // Get all the GameFields where there is a piece with a certain color on it
    // This method does not change the gameFieldList
    private static List<GameField> getGameFieldsWithPiecesOfPlayersColor(String color) {
        List<GameField> GameFieldsWithAPieceOfPlayersColor = new ArrayList<GameField>();
        for(GameField gamefield : gameFieldList) {
            Piece piece = gamefield.getPieceOnField();
            if(piece != null) {
                if(piece.getColor().equals(color)) {
                    GameFieldsWithAPieceOfPlayersColor.add(gamefield);
                }
            }
        }
        return GameFieldsWithAPieceOfPlayersColor;
    }

    private static List<GameField> removeGameFieldsWithPiecesOnHomeFields(List<GameField> gamefields) {
        ListIterator<GameField> iter = gamefields.listIterator();
        while(iter.hasNext()){
            if(iter.next().getGameFieldName().equals("homefield")){
                iter.remove();
            }
        }
        return gamefields;
    }

    // Returns a gamefield if we know its CalculationId
    private static GameField getGameFieldWithCalculationId(int calculationId, String fieldName) {
        ListIterator<GameField> iter = gameFieldList.listIterator();
        boolean notFound = true;
        while(iter.hasNext() && notFound == true){
            int nextId = iter.next().getIdForCalculation();
            String nextFieldName = iter.next().getGameFieldName();
            if(nextId == calculationId && nextFieldName == fieldName){
                notFound = false;
            }
        }
        return iter.next();
    }

    // Calculates the destinationID
    private static int getDestinationId(int sourceId, int cardValue) {
        int destinationId = sourceId + cardValue;
        if(destinationId > 64) {
            destinationId = destinationId - 64;
        }
        return destinationId;
    }

    // This method returns true if a Startfield is passed, based on the sourceId and the destinationId
    // If no Startfield is passed, then return 0
    private static int getStartFieldIfStartFieldIsPassed(int sourceId, int destinationId) {
        int passedStartField = 0;
        // Check if we passed the Startfield with idForCalculation 21
        if((sourceId >= 8 && sourceId <= 20) && (destinationId >= 21 && destinationId <= 33)) {
            passedStartField = 21;
        }
        // Check if we passed the Startfield with idForCalculation 37
        if((sourceId >= 24 && sourceId <= 36) && (destinationId >= 37 && destinationId <= 49)) {
            passedStartField = 37;
        }
        // Check if we passed the Startfield with idForCalculation 53
        if((sourceId >= 40 && sourceId <= 52) && (destinationId >= 53 && destinationId <= 64)) {
            passedStartField = 53;
        }
        // Check if we passed the Startfield with idForCalculation 53
        // This is a special case, which happens if the Piece is on idForCalculation 52 and the Player plays Card 13
        if((sourceId == 52) && (destinationId == 1)) {
            passedStartField = 53;
        }
        // Check if we passed the Startfield with idForCalculation 5
        if((sourceId >= 56 && sourceId <= 64) && (destinationId >= 5 && destinationId <= 13)) {
            passedStartField = 5;
        }
        // Check if we passed the Startfield with idForCalculation 5
        if((sourceId >= 1 && sourceId <= 4) && (destinationId >= 5 && destinationId <= 17)) {
            passedStartField = 5;
        }

        return passedStartField;
    }

    private static boolean isFieldOccupied(GameField gamefield) {
        boolean isOccupied = false;
        if(gamefield.getPieceOnField() != null) {
            isOccupied = true;
        }
        return isOccupied;
    }

    private static boolean arePiecesBlockingOnDestinationFields( int destinationId, GameField firstDestinationField) {
        boolean piecesAreBlockingDestinationFields = false;

        int diff = destinationId - firstDestinationField.getIdForCalculation();
        for(int i = 0; i <= diff; i++) {
            GameField calculatedGameField = getGameFieldWithCalculationId(destinationId + i, "destinationfield");
            if(calculatedGameField.getPieceOnField() != null) {
                piecesAreBlockingDestinationFields = true;
            }
        }
        return piecesAreBlockingDestinationFields;
    }
}
