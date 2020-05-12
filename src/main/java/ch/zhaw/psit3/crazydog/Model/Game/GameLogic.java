package ch.zhaw.psit3.crazydog.Model.Game;

import ch.zhaw.psit3.crazydog.CrazyDog;
import ch.zhaw.psit3.crazydog.Model.GameField.GameBoard;
import ch.zhaw.psit3.crazydog.Model.GameField.GameField;
import ch.zhaw.psit3.crazydog.Model.Message.Message;
import ch.zhaw.psit3.crazydog.Model.Piece.Piece;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameLogic {
    private static final Logger LOGGER = Logger.getLogger(GameLogic.class.getName());

    private static List<GameField> gameFieldList;         // This is a copy of List<GameField> from Gameboard
    private static List<Move> moves;                      // This List containss all the calculated Sources and Destinations in Move Objects

    private static boolean isLegalMoveMade = false;       // If a legal move is made, set this to true in the gameLogic
    private static Message successmessage;
    private static int chosenCardId;
    private static int index = 1;

    /**
     * Is responsible for returning a list
     *
     * @param cardValue
     * @param sessionId
     */
    public static void calculateMoves(int cardValue, int sessionId) {
        moves = new ArrayList<Move>();
        // Responsible for getting the most recent version of the GameFieldList from GameBoard
        gameFieldList = CrazyDog.getGameBoard().getFields();

        // Get players color
        String playerColor = CrazyDog.getPlayerColorById(sessionId);
        // Get all the fields with Pieces of the player
        List<GameField> GameFieldsWithThisColor = GameBoard.getGameFieldsWithPiecesOfPlayersColor(playerColor);
        // Remove the GameFields with Pieces on HomeFields
        List<GameField> GameFieldsWithNoPiecesOnHomeFields = removeGameFieldsWithPiecesOnHomeFields(GameFieldsWithThisColor);

        // Calculate "normal" Card Values
        if (cardValue == 2 || cardValue == 3 || cardValue == 5 || cardValue == 6 || cardValue == 8 || cardValue == 9 || cardValue == 10 || cardValue == 12) {
            calculateNormalFields(GameFieldsWithNoPiecesOnHomeFields, cardValue, playerColor);
        }
        // Calculate Card 4
        if (cardValue == 4) {
            calculateNormalFields(GameFieldsWithNoPiecesOnHomeFields, cardValue, playerColor);
            CrazyDog.changeDirection();
            calculateNormalFields(GameFieldsWithNoPiecesOnHomeFields, cardValue, playerColor);
            CrazyDog.changeDirection();
        }
        // Calculate Card 13
        if (cardValue == 13) {
            calculateIfAPieceCanMoveFromHomeToStartField(playerColor);
            calculateNormalFields(GameFieldsWithNoPiecesOnHomeFields, cardValue, playerColor);
        }
        // Calculate Card 11
        if (cardValue == 11) {
            calculateIfAPieceCanMoveFromHomeToStartField(playerColor);
            calculateNormalFields(GameFieldsWithNoPiecesOnHomeFields, 1, playerColor);  // Calculate for CardValue 1
            calculateNormalFields(GameFieldsWithNoPiecesOnHomeFields, cardValue, playerColor);  // Calculate for CardValue 11
        }
        //Calculate Exchange Card 15
        if (cardValue == 15) {
            calculateDestinationFieldForExchangeCard(GameFieldsWithNoPiecesOnHomeFields, playerColor);
        }
    }

    /**
     * @param GameFieldsWithNoPiecesOnHomeFields
     * @param cardValue
     * @param playerColor
     */
    public static void calculateNormalFields(List<GameField> GameFieldsWithNoPiecesOnHomeFields, int cardValue, String playerColor) {
        if (GameFieldsWithNoPiecesOnHomeFields.isEmpty()) {
            // do nothing
        } else {
            // Calculate each destination field
            for (GameField sourceField : GameFieldsWithNoPiecesOnHomeFields) {
                int sourceId = sourceField.getIdForCalculation();
                Object[] loggerArray = new Object[2];
                loggerArray[0] = sourceField.getPieceOnField().getPictureName();
                loggerArray[1] = sourceField.getIdForCalculation();
                //LOGGER.log(Level.INFO, "{0} is on Field with calcID {1}", sourceField.getPieceOnField().getPictureName(), sourceField.getIdForCalculation());
                //TODO: Überprüfen ob es anders geht zu Loggen
                LOGGER.log(Level.INFO, "{0} is on field with calcID {1}.", loggerArray);
                //System.out.println(sourceField.getPieceOnField().getPictureName() + " is on Field with calcID " + sourceField.getIdForCalculation());
                int destinationId = getDestinationId(sourceId, cardValue);
                int calculationIdOfPassedStartField = getStartFieldIfStartFieldIsPassed(sourceId, destinationId);
                // No StartField was passed, everything is ok, we can simply add the card value
                if (calculationIdOfPassedStartField == 0) {
                    LOGGER.info("The piece doesn't pass a startfield.");
                    // Case for destinationfields
                    if (sourceField.getGameFieldName().equals("destinationfield")) {
                        GameField calculatedGameField = GameBoard.getGameFieldByCalculationId(destinationId, "destinationfield");
                        if (calculatedGameField != null && !calculatedGameField.getGameFieldName().equals("wormhole")) {
                            addToSourcesAndDestinations(sourceField, calculatedGameField, playerColor);
                        }
                    }
                    // Case for regular fields
                    else {
                        GameField calculatedGameField = GameBoard.getGameFieldByCalculationId(destinationId, "standard");
                        addToSourcesAndDestinations(sourceField, calculatedGameField, playerColor);
                    }
                }
                // A Startfield was passed we have to check some things
                else {
                    // Important: If the sourcefield is a destinationField, it is not possible to make the move.
                    // We can't pass a startfield when the source piece is on a destinationfield
                    if (!sourceField.getGameFieldName().equals("destinationfield")) {
                        LOGGER.log(Level.INFO, "The piece passes or lands on the startfield with the calcID {0}.", calculationIdOfPassedStartField);
                        GameField startField = GameBoard.getGameFieldByCalculationId(calculationIdOfPassedStartField, "startfield");
                        // First check if there is a piece on the startfield of same color as startfield (if there is, we can't continue)
                        // There is no ELSE -  we can NOT move with this piece.
                        if (!isStartFieldOccupiedByPieceOfSameColor(startField)) {
                            LOGGER.log(Level.INFO, "The startfield with calcID {0} has no piece with the same color on it. No block.", calculationIdOfPassedStartField);
                            if (destinationId == calculationIdOfPassedStartField) {
                                GameField calculatedGameField = GameBoard.getGameFieldByCalculationId(destinationId, "startfield");
                                addToSourcesAndDestinations(sourceField, calculatedGameField, playerColor);
                            }
                            // Now we know that we pass the startfield and don't land on it
                            else {
                                LOGGER.info("the piece doesn't land on the startfield.");
                                // So we check if the Field after the StartField is a Destinationfield of the player
                                // We also need to check if the destinationfield is out of range (bigger than the calculationId's of the destination fields)
                                GameField destinationFieldNextToStartField;
                                if (CrazyDog.getDirection() == Direction.CLOCKWISE) {
                                    destinationFieldNextToStartField = GameBoard.getGameFieldByCalculationId((calculationIdOfPassedStartField + 1), "destinationfield");
                                } else {
                                    destinationFieldNextToStartField = GameBoard.getGameFieldByCalculationId((calculationIdOfPassedStartField - 1), "destinationfield");
                                }
                                if (destinationFieldNextToStartField.getColor().equals(playerColor) && canPlayerLandOnDestinationField(destinationId, calculationIdOfPassedStartField)) {
                                    LOGGER.info("The destination fields belong to the players color. The piece could land on a destination field.");
                                    // Now we know that we could move a piece into player's destination fields.
                                    // But only if there are no Pieces blocking (we can't move past pieces in destination fields)
                                    if (!arePiecesBlockingOnDestinationFields(destinationId, destinationFieldNextToStartField)) {
                                        LOGGER.info("No own pieces are blocking the destination fields!");
                                        GameField calculatedGameField = GameBoard.getGameFieldByCalculationId(destinationId, "destinationfield");
                                        addToSourcesAndDestinations(sourceField, calculatedGameField, playerColor);
                                        GameField calculatedGameField2 = GameBoard.getGameFieldByCalculationId(destinationId, "standard");
                                        addToSourcesAndDestinations(sourceField, calculatedGameField2, playerColor);
                                    }
                                    // Some pieces are blocking, so we can only move to a standard field
                                    else {
                                        LOGGER.info("Some pieces in the destination field is blocking - piece can not move past them.");
                                        GameField calculatedGameField = GameBoard.getGameFieldByCalculationId(destinationId, "standard");
                                        addToSourcesAndDestinations(sourceField, calculatedGameField, playerColor);
                                    }
                                }
                                // We can't move the Piece into the Destination Fields. But we can move past the Destination Fields,
                                // and just place it on a standard field.
                                else {
                                    LOGGER.info("The destination fields do not belong to the players color. The piece moves past them.");
                                    GameField calculatedGameField = GameBoard.getGameFieldByCalculationId(destinationId, "standard");
                                    addToSourcesAndDestinations(sourceField, calculatedGameField, playerColor);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static List<Move> getMoves() {
        return moves;
    }

    /**
     * This functions makes the real move and saves the changes in the backend game status
     *
     * @param cardValue             Value of the card which was played
     * @param sessionId             Session ID from the current player
     * @param sourceFieldCSSId      CSS Id from the Source Field where the piece is actually
     * @param destinationFieldCSSId CSS Id from the destination Field where the piece should go
     * @param cardId                card ID from the played card, to remove it from the list of cards.
     */
    public static void makeMove(int cardValue, int sessionId, String sourceFieldCSSId, String destinationFieldCSSId, int cardId) {
        //check if the move was made by the current user, if not - the user will be informed
        if (sessionId == CrazyDog.getNextPlayer()) {
            calculateMoves(cardValue, sessionId);       // Calculate all the possible moves
            // Get players color
            String playerColor = CrazyDog.getPlayerColorById(sessionId);
            if (isMoveIsLegal(sourceFieldCSSId, destinationFieldCSSId)) {
                successmessage = new Message("Erfolgreicher Zug");
                chosenCardId = cardId;
                GameField sourceField = GameBoard.getGameFieldByCSSId(sourceFieldCSSId);
                GameField destinationField = GameBoard.getGameFieldByCSSId(destinationFieldCSSId);
                //if exchange card was played, exchange cards, not make a normal move
                if(cardValue == 15) {
                    //exchange the Pieces
                    Piece sourcePiece = sourceField.getPieceOnField(); //temporary save source field
                    sourceField.setPieceOnField(destinationField.getPieceOnField()); //set destination Piece on Source Field
                    destinationField.setPieceOnField(sourcePiece); //set source Piece on Destination Field

                }
                else {
                    //if Destination is a wormhole, then the new destination should be a random GameField
                    //it is not allowed, that a piece of the same color is on the destination field
                    while (destinationField.getGameFieldName().equals("wormhole") || isPieceOfPlayerOnField(destinationField, playerColor)) {
                        destinationField = calcDestWhenPieceOnWormhole();
                    }

                    //check if there is another piece on the field:
                    if (checkIfOpponentPieceOnField(destinationField, playerColor)) {
                        //move field to its home field
                        UserInstructions.addNewInstruction("Spielfigur "+ destinationField.getPieceOnField().getNumber()+
                                " der Farbe "+destinationField.getPieceOnField().getColor()+" wurde nach Hause geschickt");
                        GameBoard.setPieceOnHomefield(destinationField.getPieceOnField().getHomeFieldId(),destinationField.getPieceOnField());
                        destinationField.setPieceOnField(null);
                    }

                    destinationField.setPieceOnField(sourceField.getPieceOnField());        // Set Piece of sourceField to destinationField
                    sourceField.setPieceOnField(null);
                }

                isLegalMoveMade = true;
                UserInstructions.addNewInstruction("Spieler " + sessionId + " hat die Karte " + cardValue + " gespielt");
            } else {
                successmessage = new Message("Ungültiger Zug");
                isLegalMoveMade = false;
            }
        } else {
            successmessage = new Message("Warten Sie mit dem Zug, bis sie an der Reihe sind");
            isLegalMoveMade = false;
        }

    }

    public static int getChosenCardId() {
        return chosenCardId;
    }

    public static void resetChosenCardId() {
        chosenCardId = 0;
    }

    public static Message getSuccessMessage() {
        return successmessage;
    }

    /**
     * change Direction of the Game and give a message back to GUI
     */
    public static void changeDirection(int cardId) {
        CrazyDog.changeDirection();
        chosenCardId = cardId;
        isLegalMoveMade = true;
        successmessage = new Message("Sie haben die Richtung geändert.");
    }

    // INTERNAL METHODS ONLY USED BY GAMELOGIC (private)

    /**
     * check if there is a Piece from an opponent on the destination Field and return true or false
     *
     * @param dstField      Destination Field where the Piece should land
     * @param color         Color of the current player
     */
    private static boolean checkIfOpponentPieceOnField(GameField dstField, String color) {
        boolean opponentOnField = false;
        if (dstField.getPieceOnField() != null) {
            if (dstField.getPieceOnField().getColor() != color) {
                opponentOnField = true;
            }
        }
        return opponentOnField;
    }

    /**
     * Responsible for adding the calculated Sources and Destinations to the HashMap
     *
     * @param sourceField
     * @param destinationField
     * @param playerColor
     */
    private static void addToSourcesAndDestinations(GameField sourceField, GameField destinationField, String playerColor) {
        if (!isPieceOfPlayerOnField(destinationField, playerColor)) {
            LOGGER.info("No piece of the player is already on the calculated field.");
            Move move = new Move(sourceField, destinationField);
            moves.add(move);
        }
    }

    /**
     * @param gamefields
     * @return
     */
    private static List<GameField> removeGameFieldsWithPiecesOnHomeFields(List<GameField> gamefields) {
        ListIterator<GameField> iter = gamefields.listIterator();
        while (iter.hasNext()) {
            if (iter.next().getGameFieldName().equals("homefield")) {
                iter.remove();
            }
        }
        return gamefields;
    }

    /**
     * Calculates the destinationID, where a piece would land
     *
     * @param sourceId
     * @param cardValue
     * @return
     */
    private static int getDestinationId(int sourceId, int cardValue) {
        int destinationId;
        if (CrazyDog.getDirection() == Direction.CLOCKWISE) {
            destinationId = sourceId + cardValue;
            if (destinationId > 64) {
                destinationId = destinationId - 64;
            }
        } else {
            destinationId = sourceId - cardValue;
            if (destinationId < 1) {
                destinationId = destinationId + 64;
            }
        }
        return destinationId;
    }

    /**
     * This method returns the number of the Startfield if it is passed, based on the sourceId and the destinationId
     * If no Startfield is passed, then return 0
     *
     * @param sourceId
     * @param destinationId
     * @return returns the number of the Startfield if it is passed
     */
    private static int getStartFieldIfStartFieldIsPassed(int sourceId, int destinationId) {
        int passedStartField = 0;
        if (CrazyDog.getDirection() == Direction.CLOCKWISE) {
            // TODO: Logic for CrazyDog.getDirection() == Direction.CLOCKWISE can be a bit simplified -> (like it is in the ELSE case)
            // Check if we passed the Startfield with idForCalculation 21
            if ((sourceId >= 8 && sourceId <= 20) && (destinationId >= 21 && destinationId <= 33)) {
                passedStartField = 21;
            }
            // Check if we passed the Startfield with idForCalculation 37
            if ((sourceId >= 24 && sourceId <= 36) && (destinationId >= 37 && destinationId <= 49)) {
                passedStartField = 37;
            }
            // Check if we passed the Startfield with idForCalculation 53
            if ((sourceId >= 40 && sourceId <= 52) && (destinationId >= 53 && destinationId <= 64)) {
                passedStartField = 53;
            }
            // Check if we passed the Startfield with idForCalculation 53
            // This is a special case, which happens if the Piece is on idForCalculation 52 and the Player plays Card 13
            if ((sourceId == 52) && (destinationId == 1)) {
                passedStartField = 53;
            }
            // Check if we passed the Startfield with idForCalculation 5
            if ((sourceId >= 56 && sourceId <= 64) && (destinationId >= 5 && destinationId <= 13)) {
                passedStartField = 5;
            }
            // Check if we passed the Startfield with idForCalculation 5
            if ((sourceId >= 1 && sourceId <= 4) && (destinationId >= 5 && destinationId <= 17)) {
                passedStartField = 5;
            }
        } else {
            // Check if we passed YELLOW startfield
            if (sourceId > 21 && destinationId <= 21) {
                passedStartField = 21;
            }
            // Check if we passed RED startfield
            if (sourceId > 5 && destinationId <= 5) {
                passedStartField = 5;
            }
            // Check if we passed RED startfield
            if ((sourceId >= 6 && sourceId <= 12) && (destinationId >= 57 && destinationId <= 64)) {
                passedStartField = 5;
            }
            // Check if we passed BLUE startfield
            if (sourceId > 53 && destinationId <= 53) {
                passedStartField = 53;
            }
            // Check if we passed BLUE startfield
            if ((sourceId >= 1 && sourceId <= 2) && (destinationId >= 52 && destinationId <= 53)) {
                passedStartField = 53;
            }
            // Check if we passed GREEN startfield
            if (sourceId > 37 && destinationId <= 37) {
                passedStartField = 37;
            }
        }
        return passedStartField;
    }

    /**
     * @param gamefield
     * @return
     */
    private static boolean isStartFieldOccupiedByPieceOfSameColor(GameField gamefield) {
        boolean isOccupied = false;
        if (gamefield.getPieceOnField() != null) {
            String gameFieldColor = gamefield.getColor();
            String pieceColor = gamefield.getPieceOnField().getColor();
            if (gameFieldColor.equals(pieceColor)) {
                isOccupied = true;
                LOGGER.info("A piece of the same color as the startfield is on the startfield.");
            }
        }
        return isOccupied;
    }

    /**
     * In this method with receive a gamefield which is the first destinationField of the player. We also receive the
     * destinationId. Starting from the destinationField, we check if a Piece of the Player is already on the Field,
     * and do this for each destinationfield up to the destinationid.
     *
     * @param destinationId
     * @param firstDestinationField
     * @return true if there is a piece found on a destinationField
     */
    private static boolean arePiecesBlockingOnDestinationFields(int destinationId, GameField firstDestinationField) {
        boolean piecesAreBlockingDestinationFields = false;
        int diff = Math.abs(destinationId - firstDestinationField.getIdForCalculation());
        for (int i = 0; i <= diff; i++) {
            GameField calculatedGameField;
            if (CrazyDog.getDirection() == Direction.CLOCKWISE) {
                calculatedGameField = GameBoard.getGameFieldByCalculationId(firstDestinationField.getIdForCalculation() + i, "destinationfield");
            } else {
                calculatedGameField = GameBoard.getGameFieldByCalculationId(firstDestinationField.getIdForCalculation() - i, "destinationfield");
            }
            if (calculatedGameField.getPieceOnField() != null) {
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

    /**
     * Checks if a GameField contains a Piece with the color of the Player.
     *
     * @param calculatedGameField
     * @param playerColor
     * @return
     */
    private static boolean isPieceOfPlayerOnField(GameField calculatedGameField, String playerColor) {
        boolean isPieceOfPlayerOnField = false;
        if (calculatedGameField.getPieceOnField() != null) {
            if (calculatedGameField.getPieceOnField().getColor().equals(playerColor)) {
                LOGGER.info("Piece can not be moved to destination. Player already has a piece there.");
                isPieceOfPlayerOnField = true;
            }
        }
        return isPieceOfPlayerOnField;
    }

    /**
     * Checks if a player has Pieces on his HomeFields. If he has at least one Piece on a HomeField, and the startfield
     * is not occupied by himself, then a new calculated Destination is added.
     *
     * @param playerColor
     */
    private static void calculateIfAPieceCanMoveFromHomeToStartField(String playerColor) {
        GameField biggestHomeField = calculateBiggestHomeField(playerColor);
        int calculationIdOfBiggestHomeField = biggestHomeField.getIdForCalculation();

        boolean stop = false;
        boolean notFound = true;
        int i = 0;
        while (notFound && !stop) {
            biggestHomeField = GameBoard.getGameFieldByCalculationId(calculationIdOfBiggestHomeField - i, "homefield");
            if (biggestHomeField.getPieceOnField() != null) {
                notFound = false;   // We found the Piece on the biggest homefield!
                // Calculate DestinationField
                GameField startField = GameBoard.getGameFieldByCalculationId(calculationIdOfBiggestHomeField + 1, "startfield");
                addToSourcesAndDestinations(biggestHomeField, startField, playerColor);
            }
            if (i == 3) {
                stop = true;
            }
            i++;
        }
    }

    /**
     * Returns the GameField with the biggest CalculationId of the Player
     *
     * @param playerColor
     * @return
     */
    private static GameField calculateBiggestHomeField(String playerColor) {
        GameField biggestHomeField = null;

        if (playerColor == "red") {
            biggestHomeField = GameBoard.getGameFieldByCalculationId(4, "homefield");  // 4 is calcID of biggest red homefield
        }
        if (playerColor == "yellow") {
            biggestHomeField = GameBoard.getGameFieldByCalculationId(20, "homefield"); // 20 is calcID of biggest yellow homefield
        }
        if (playerColor == "green") {
            biggestHomeField = GameBoard.getGameFieldByCalculationId(36, "homefield"); // 36 is calcID of biggest green homefield
        }
        if (playerColor == "blue") {
            biggestHomeField = GameBoard.getGameFieldByCalculationId(52, "homefield"); // 52 is calcID of biggest blue homefield
        }

        return biggestHomeField;
    }

    /**
     * This method checks if the sourceField and the destinationField are in List<Move> moves
     *
     * @param sourceField
     * @param destinationField
     * @return true if a move is legal
     */
    private static boolean isMoveIsLegal(String sourceField, String destinationField) {
        boolean moveIsLegal = false;
        if (moves.isEmpty()) {
            // do nothing
        } else {
            for (Move move : moves) {
                if (move.getSourceField().getCssId().equals(sourceField) && move.getDestinationField().getCssId().equals(destinationField)) {
                    moveIsLegal = true;
                }
            }
        }
        return moveIsLegal;
    }

    /**
     * Calculates the new Destination if the piece would land on a wormhole
     *
     * @return new Destination GameField
     */
    public static GameField calcDestWhenPieceOnWormhole() {
        Random r = new Random();
        //get a random number between 1 and 64
        int destinationIdForCalculation = r.nextInt(64 - 1 + 1) + 1; // (max - min + 1) + 1
        GameField newDestination = null;
        newDestination = GameBoard.getStandardStartfieldGameFieldOrWormholeByIdForCalculation(destinationIdForCalculation);
        return newDestination;
    }

    private static boolean canPlayerLandOnDestinationField(int destinationId, int idOfPassedStartField) {
        boolean playerCanLandOnDestinationField;
        if (CrazyDog.getDirection() == Direction.CLOCKWISE) {
            if (destinationId <= idOfPassedStartField + 4) {
                playerCanLandOnDestinationField = true;
            } else {
                playerCanLandOnDestinationField = false;
            }
        } else {
            if (destinationId >= idOfPassedStartField - 4) {
                playerCanLandOnDestinationField = true;
            } else {
                playerCanLandOnDestinationField = false;
            }
        }
        return playerCanLandOnDestinationField;
    }

    /**
     * calculate all moves that are possible with the piece exchange card
     * @param sourceFields      sourceField that are possible with that card
     * @param color             colour of the current player
     */
    private static void calculateDestinationFieldForExchangeCard(List<GameField> sourceFields, String color) {
        for(GameField sourceField: sourceFields)
        {
            List<GameField> destinationFields = GameBoard.getFieldsWithPieces();
            for(GameField dstField: destinationFields) {
                if(!sourceField.getPieceOnField().equals(dstField.getPieceOnField()) &&
                        !dstField.getGameFieldName().equals("homefield") &&
                        !dstField.getGameFieldName().equals("destinationfield")
                )
                {
                    addToSourcesAndDestinations(sourceField,dstField,color);
                }

            }
        }


    }

}