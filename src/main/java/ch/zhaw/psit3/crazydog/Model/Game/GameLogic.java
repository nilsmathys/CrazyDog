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
    private static boolean isLegalMoveMade = false;          // If a legal move is made, i set this to true in the gameLogic
    private static Message successmessage;
    private static List<GameField> gameFieldList;           // This is a copy of List<GameField> from Gameboard
    private static List<GameField> calculatedDestinations;  // This List contains the calculated destinations
    private static int index = 1;

    // Is responsible for returning a list
    public static void calculateDestinations(int cardValue, int sessionId) {
        calculatedDestinations = new ArrayList<GameField>();
        getGameFieldsFromGameBoard();

        // ------------------------ nur zum Testen, Positionen der GamePieces ändern
        if(index == 1) {
            index++;
            CrazyDog.setDirection(CLOCKWISE);
            // Set Piece1red to GameField with calculationId 14. Set Null Piece to the homefield after swap.
            GameField gameFieldWithPiece1Red = gameFieldList.get(10);       // Get GameField containing piece1red
            GameField gameFieldWithCalcId35 = gameFieldList.get(54);        // Get GameField where i want to put piece1red

            gameFieldWithCalcId35.setPieceOnField(gameFieldWithPiece1Red.getPieceOnField());
            gameFieldList.set(54, gameFieldWithCalcId35);

            gameFieldWithPiece1Red.setPieceOnField(null);
            gameFieldList.set(10, gameFieldWithPiece1Red);

            // Set Piece2red to GameField with calculationId 14. Set Null Piece to the homefield after swap.
            GameField gameFieldWithPiece2Red = gameFieldList.get(7);       // Get GameField containing piece2red
            GameField gameFieldWithCalcId49 = gameFieldList.get(72);        // Get GameField where i want to put piece1red

            gameFieldWithCalcId49.setPieceOnField(gameFieldWithPiece2Red.getPieceOnField());
            gameFieldList.set(72, gameFieldWithCalcId49);

            gameFieldWithPiece2Red.setPieceOnField(null);
            gameFieldList.set(7, gameFieldWithPiece2Red);

            // Set Piece3red to GameField with calculationId 14. Set Null Piece to the homefield after swap.
            GameField gameFieldWithPiece3Red = gameFieldList.get(4);       // Get GameField containing piece2red
            GameField gameFieldWithCalcId53 = gameFieldList.get(84);        // Get GameField where i want to put piece1red

            gameFieldWithCalcId53.setPieceOnField(gameFieldWithPiece3Red.getPieceOnField());
            gameFieldList.set(84, gameFieldWithCalcId53);

            gameFieldWithPiece3Red.setPieceOnField(null);
            gameFieldList.set(4, gameFieldWithPiece3Red);

            // Move Piece1yellow to StartField with calculationId 21. Set Null Piece to the homefield after swap.
            GameField gameFieldWithPiece1Yellow = gameFieldList.get(34);       // Get GameField containing piece1yellow
            GameField gameFieldWithCalcId21 = gameFieldList.get(36);        // Get GameField where i want to put piece1red

            gameFieldWithPiece1Yellow.setPieceOnField(new Piece(5, 1, "yellow", "piece1yellow.png", 10));
            gameFieldWithCalcId21.setPieceOnField(gameFieldWithPiece1Yellow.getPieceOnField());
            gameFieldList.set(36, gameFieldWithCalcId21);

            gameFieldWithPiece1Yellow.setPieceOnField(null);
            gameFieldList.set(34, gameFieldWithPiece1Yellow);

            // Move Piece4red to DestinationField with calculationId 37. Set Null Piece to the homefield after swap.
            GameField gameFieldWithPiece4Red = gameFieldList.get(1);       // Get GameField containing piece4Red
            GameField gameFieldWithCalcId39 = gameFieldList.get(56);        // Get GameField where i want to put piece4Red

            gameFieldWithCalcId39.setPieceOnField(gameFieldWithPiece4Red.getPieceOnField());
            gameFieldList.set(56, gameFieldWithCalcId39);

            gameFieldWithPiece4Red.setPieceOnField(null);
            gameFieldList.set(1, gameFieldWithPiece4Red);
        }
        // ------------------------ nur zum Testen fertig

        String playerColor = getPlayersColorFromId(sessionId);
        // Get all the fields with Pieces of the player
        List<GameField> GameFieldsWithThisColor = getGameFieldsWithPiecesOfPlayersColor(playerColor);

        // Calculate "normal" Card Values
        if(cardValue == 2 || cardValue == 5 || cardValue == 6 || cardValue == 8 || cardValue == 9 || cardValue == 10 || cardValue == 12) {
            // Remove the GameFields with Pieces on HomeFields
            List<GameField> GameFieldsWithNoPiecesOnHomeFields = removeGameFieldsWithPiecesOnHomeFields(GameFieldsWithThisColor);
            if(GameFieldsWithNoPiecesOnHomeFields.isEmpty()) {
                // do nothing
            }
            else {
                calculateNormalFields(GameFieldsWithNoPiecesOnHomeFields, cardValue, playerColor);
            }
        }

        if(cardValue == 13) {
            // get a List of Pieces with Pieces on HomeField
            // calculate the Destination for that list (only one destination is possible) -> add it to HashMap
            // calculate List with PiecesOnNoHomeFields
            // calculateNormalFields(GameFieldsWithNoPiecesOnHomeFields, cardValue, playerColor);
        }
        if(cardValue == 11) {
            // get a List of Pieces with Pieces on HomeField
            // calculate the Destination for that list (only one destination is possible) -> add it to HashMap
            // calculate List with PiecesOnNoHomeFields
            // calculateNormalFields(GameFieldsWithNoPiecesOnHomeFields, 1, playerColor);       // Cardvalue 1 !!!!
            // calculateNormalFields(GameFieldsWithNoPiecesOnHomeFields, 11, playerColor);      // Cardvalue 11 !!!!
        }
    }

    public static void calculateNormalFields(List<GameField> GameFieldsWithNoPiecesOnHomeFields, int cardValue, String playerColor ) {
        // Calculate each destination field
        for(GameField gamefield : GameFieldsWithNoPiecesOnHomeFields) {
            int sourceId = gamefield.getIdForCalculation();
            System.out.println(gamefield.getPieceOnField().getPictureName() + " is on Field with calcID " + gamefield.getIdForCalculation());
            int destinationId = getDestinationId(sourceId, cardValue);
            int calculationIdOfPassedStartField = getStartFieldIfStartFieldIsPassed(sourceId, destinationId);
            // No StartField was passed, everything is ok, we can simply add the card value
            if(calculationIdOfPassedStartField == 0) {
                System.out.println("The piece doesn't pass a startfield");
                // Case for destinationfields
                if(gamefield.getGameFieldName().equals("destinationfield")) {
                    GameField calculatedGameField = getGameFieldWithCalculationId(destinationId, "destinationfield");
                    if(calculatedGameField != null && !calculatedGameField.getGameFieldName().equals("wormhole")) {
                        if(!isPieceOfPlayerOnField(calculatedGameField, playerColor)) {
                            System.out.println("No Piece of the Player is already on the calculated field");
                            calculatedDestinations.add(calculatedGameField);
                            System.out.println("The piece lands on the field" + destinationId);
                        }
                    }
                }
                // Case for regular fields
                else {
                    GameField calculatedGameField = getGameFieldWithCalculationId(destinationId, "standard");
                    if(!isPieceOfPlayerOnField(calculatedGameField, playerColor)) {
                        System.out.println("No Piece of the Player is already on the calculated field");
                        calculatedDestinations.add(calculatedGameField);
                        System.out.println("The piece lands on the field" + destinationId);
                    }
                }
            }
            // A Startfield was passed we have to check some things
            else {
                System.out.println("The piece passes or lands on the startfield with the calcID" + calculationIdOfPassedStartField);
                GameField startField = getGameFieldWithCalculationId(calculationIdOfPassedStartField, "startfield");
                // First check if there is a piece on the startfield of same color as startfield (if there is, we can't continue)
                // There is no ELSE -  we can NOT move with this piece.
                if(!isStartFieldOccupiedByPieceOfSameColor(startField)) {
                    System.out.println("The startfield with calcID " + calculationIdOfPassedStartField + " has no piece with same color on it. No Block.");
                    if(destinationId == calculationIdOfPassedStartField) {
                        GameField calculatedGameField = getGameFieldWithCalculationId(destinationId, "startfield");
                        if(!isPieceOfPlayerOnField(calculatedGameField, playerColor)) {
                            System.out.println("No Piece of the Player is already on the calculated field");
                            calculatedDestinations.add(calculatedGameField);
                            System.out.println("The piece lands exactly on the startfield with calcID" + calculationIdOfPassedStartField);
                        }
                    }
                    // Now we know that we pass the startfield and don't land on it
                    else {
                        System.out.println("The piece doesn't land on the startfield.");
                        // So we check if the Field after the StartField is a Destinationfield of the player
                        // We also need to check if the destinationfield is out of range (bigger than the calculationId's of the destination fields)
                        GameField destinationFieldNextToStartField = getGameFieldWithCalculationId(calculationIdOfPassedStartField + 1, "destinationfield");
                        if(destinationFieldNextToStartField.getColor().equals(playerColor) && (destinationId <= calculationIdOfPassedStartField + 4)) {
                            System.out.println("The destination fields belong to the players color. The piece could land on a destination field.");
                            // Now we know that we could move a piece into player's destination fields.
                            // But only if there are no Pieces blocking (we can't move past pieces in destination fields)
                            if(!arePiecesBlockingOnDestinationFields(destinationId, destinationFieldNextToStartField)) {
                                System.out.println("No own pieces are blocking the destination fields!");
                                GameField calculatedGameField = getGameFieldWithCalculationId(destinationId, "destinationfield");
                                if(!isPieceOfPlayerOnField(calculatedGameField, playerColor)) {
                                    System.out.println("No Piece of the Player is already on the calculated destination field");
                                    calculatedDestinations.add(calculatedGameField);
                                    System.out.println("The piece can land on the destinationfield  with calcId" + destinationId);
                                }
                                GameField calculatedGameField2 = getGameFieldWithCalculationId(destinationId, "standard");
                                if(!isPieceOfPlayerOnField(calculatedGameField2, playerColor)) {
                                    System.out.println("No Piece of the Player is already on the calculated standard field");
                                    calculatedDestinations.add(calculatedGameField2);
                                    System.out.println("The piece can land on the standard field with calcId" + destinationId);
                                }
                            }
                            // Some pieces are blocking, so we can only move to a standard field
                            else {
                                System.out.println("Some pieces in the destination field is blocking - piece can not move past them.");
                                GameField calculatedGameField = getGameFieldWithCalculationId(destinationId, "standard");
                                if(!isPieceOfPlayerOnField(calculatedGameField, playerColor)) {
                                    System.out.println("No Piece of the Player is already on the calculated field");
                                    calculatedDestinations.add(calculatedGameField);
                                    System.out.println("The piece can land on the standard field with calcId " + destinationId);
                                }
                            }
                        }
                        // We can't move the Piece into the Destination Fields. But we can move past the Destination Fields,
                        // and just place it on a standard field.
                        else {
                            System.out.println("The destination fields do not belong to the players color. The piece moves past them.");
                            GameField calculatedGameField = getGameFieldWithCalculationId(destinationId, "standard");
                            if(!isPieceOfPlayerOnField(calculatedGameField, playerColor)) {
                                System.out.println("No Piece of the Player is already on the calculated field");
                                calculatedDestinations.add(calculatedGameField);
                                System.out.println("The piece can land on the standard field with calcId " + destinationId);
                            }
                        }
                    }
                }
            }
            System.out.println("------------------");
        }
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
        GameField gamefield = null;
        boolean stop = false;
        boolean notFound = true;
        int i = 0;
        while(notFound && !stop) {
                gamefield = gameFieldList.get(i);
                if (gamefield.getIdForCalculation() == calculationId && (gamefield.getGameFieldName().equals(fieldName) || gamefield.getGameFieldName().equals("wormhole"))) {
                    notFound = false;   // The Gamefield is found!
                }
                if(i == 95) { // gameFieldList has a maximum of 96 Pieces. (0-95)
                    stop = true;
                    if(notFound) {
                        gamefield = null;
                    }
                }
                i++;
        }
        return gamefield;
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

    private static boolean isStartFieldOccupiedByPieceOfSameColor(GameField gamefield) {
        boolean isOccupied = false;
        if(gamefield.getPieceOnField() != null) {
            String gameFieldColor = gamefield.getColor();
            String pieceColor = gamefield.getPieceOnField().getColor();
            if(gameFieldColor.equals(pieceColor)) {
                isOccupied = true;
                System.out.println("A piece of the same color as the startfield is on the startfield.");
            }
        }
        return isOccupied;
    }

    private static boolean arePiecesBlockingOnDestinationFields( int destinationId, GameField firstDestinationField) {
        boolean piecesAreBlockingDestinationFields = false;
        int diff = destinationId - firstDestinationField.getIdForCalculation();
        for(int i = 0; i <= diff; i++) {
            GameField calculatedGameField = getGameFieldWithCalculationId(firstDestinationField.getIdForCalculation() + i, "destinationfield");
            if(calculatedGameField.getPieceOnField() != null) {
                piecesAreBlockingDestinationFields = true;
            }
        }
        return piecesAreBlockingDestinationFields;
    }

    public static boolean isLegalMoveMade() {
        return isLegalMoveMade;
    }

    public static void resetLegalMoveStatus() {
        isLegalMoveMade = false;
    }

    // Checks if a GameField contains a Piece with the color of the Player.
    private static boolean isPieceOfPlayerOnField(GameField calculatedGameField, String playerColor) {
        boolean isPieceOfPlayerOnField = false;
        if(calculatedGameField.getPieceOnField() != null) {
            if(calculatedGameField.getPieceOnField().getColor().equals(playerColor)) {
                System.out.println("Piece can not be moved to destination. Player already has a piece there.");
                isPieceOfPlayerOnField = true;
            }
        }
        return isPieceOfPlayerOnField;
    }
}
