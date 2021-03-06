package ch.zhaw.psit3.crazydog.Model.GameField;

import ch.zhaw.psit3.crazydog.Model.Game.DestinationFieldsClockwise;
import ch.zhaw.psit3.crazydog.Model.Game.DestinationFieldsCounterclockwise;
import ch.zhaw.psit3.crazydog.Model.Game.Direction;
import ch.zhaw.psit3.crazydog.Model.Piece.Piece;

import java.util.*;

/**
 * <h1>GameBoard</h1>
 * The GameBoard handles all fields and returns information about the placement of the pieces. <br>
 *
 * @author R. Somma
 * @version 1.0
 * @since March 2020
 */
public class GameBoard {

    private List<GameField> fields;

    public GameBoard() {
        this.fields = GameFieldDAO.getFieldsFromJSON();
        Collections.sort(fields);
    }

    // *** GENERAL ***

    public List<GameField> getFields() {
        return fields;
    }

    /**
     * Get all the GameFields where there is a piece with a certain color on it
     *
     * @param color name of color
     * @return list of GameFields of pieces of a certain color
     */
    public List<GameField> getGameFieldsWithPiecesOfPlayersColor(String color) {
        List<GameField> GameFieldsWithAPieceOfPlayersColor = new ArrayList<GameField>();
        for (GameField gamefield : fields) {
            Piece piece = gamefield.getPieceOnField();
            if (piece != null) {
                if (piece.getColor().equals(color)) {
                    GameFieldsWithAPieceOfPlayersColor.add(gamefield);
                }
            }
        }
        return GameFieldsWithAPieceOfPlayersColor;
    }

    /**
     * Returns a gamefield if we know its CalculationId
     *
     * @param calculationId of a certain game field
     * @param fieldName     of a certain game field
     * @return a GameField object with the corresponding calculationId
     */
    public GameField getGameFieldByCalculationId(int calculationId, String fieldName) {
        GameField gamefield = null;
        boolean stop = false;
        boolean notFound = true;
        int i = 0;
        while (notFound && !stop) {
            gamefield = fields.get(i);
            if (gamefield.getIdForCalculation() == calculationId && (gamefield.getGameFieldName().equals(fieldName) || gamefield.getGameFieldName().equals("wormhole"))) {
                notFound = false;   // The Gamefield is found!
            }
            if (i == 95) { // gameFieldList has a maximum of 96 Pieces. (0-95)
                stop = true;
                if (notFound) {
                    gamefield = null;
                }
            }
            i++;
        }
        return gamefield;
    }

    /**
     * Returns a gamefield if we know its CSS-Id
     *
     * @param cssId of a certain game field
     * @return a GameField object with the corresponding cssId
     */
    public GameField getGameFieldByCSSId(String cssId) {
        GameField gamefield = null;
        boolean stop = false;
        boolean notFound = true;
        int i = 0;
        while (notFound && !stop) {
            gamefield = fields.get(i);
            if (gamefield.getCssId().equals(cssId)) {
                notFound = false;   // The Gamefield is found!
            }
            if (i == 95) { // gameFieldList has a maximum of 96 Pieces. (0-95)
                stop = true;
                if (notFound) {
                    gamefield = null;
                }
            }
            i++;
        }
        return gamefield;
    }


    // *** HOMEFIELD ***

    /**
     * Sets the Piece on a specific Home Field
     *
     * @param idForCalculation ID of the Gamefield which is used for the Calculation
     * @param piece            piece which should be placed on that field
     */
    public void setPieceOnHomefield(int idForCalculation, Piece piece) {
        for (GameField field : fields) {
            if (field.getGameFieldName().equals("homefield") && field.getIdForCalculation() == idForCalculation) {
                field.setPieceOnField(piece);
                break;
            }
        }
    }


    // *** DESTINATIONFIELD ***

    /**
     * renumber all destination fields. will be run after a direction change
     *
     * @param newDirection the new direction after the change
     */
    public void renumberDestinationFields(Direction newDirection) {
        List<GameField> destinationFieldsRed = getListOfDestinationFieldsByColor("red");
        List<GameField> destinationFieldsGreen = getListOfDestinationFieldsByColor("green");
        List<GameField> destinationFieldsYellow = getListOfDestinationFieldsByColor("yellow");
        List<GameField> destinationFieldsBlue = getListOfDestinationFieldsByColor("blue");
        if (newDirection == Direction.CLOCKWISE) {

            int i = 1;
            for (GameField field : destinationFieldsRed) {
                //set the new number for each field
                int value = 0;
                switch (i) {
                    case 1:
                        value = DestinationFieldsClockwise.RED1.getValue();
                        break;
                    case 2:
                        value = DestinationFieldsClockwise.RED2.getValue();
                        break;
                    case 3:
                        value = DestinationFieldsClockwise.RED3.getValue();
                        break;
                    case 4:
                        value = DestinationFieldsClockwise.RED4.getValue();
                        break;
                }
                field.setIdForCalculation(value);
                i++;
            }

            i = 1;
            for (GameField field : destinationFieldsGreen) {
                //set the new number for each field
                int value = 0;
                switch (i) {
                    case 1:
                        value = DestinationFieldsClockwise.GREEN1.getValue();
                        break;
                    case 2:
                        value = DestinationFieldsClockwise.GREEN2.getValue();
                        break;
                    case 3:
                        value = DestinationFieldsClockwise.GREEN3.getValue();
                        break;
                    case 4:
                        value = DestinationFieldsClockwise.GREEN4.getValue();
                        break;
                }
                field.setIdForCalculation(value);
                i++;
            }

            i = 1;
            for (GameField field : destinationFieldsYellow) {
                //set the new number for each field
                int value = 0;
                switch (i) {
                    case 1:
                        value = DestinationFieldsClockwise.YELLOW1.getValue();
                        break;
                    case 2:
                        value = DestinationFieldsClockwise.YELLOW2.getValue();
                        break;
                    case 3:
                        value = DestinationFieldsClockwise.YELLOW3.getValue();
                        break;
                    case 4:
                        value = DestinationFieldsClockwise.YELLOW4.getValue();
                        break;
                }
                field.setIdForCalculation(value);
                i++;
            }

            i = 1;
            for (GameField field : destinationFieldsBlue) {
                //set the new number for each field
                int value = 0;
                switch (i) {
                    case 1:
                        value = DestinationFieldsClockwise.BLUE1.getValue();
                        break;
                    case 2:
                        value = DestinationFieldsClockwise.BLUE2.getValue();
                        break;
                    case 3:
                        value = DestinationFieldsClockwise.BLUE3.getValue();
                        break;
                    case 4:
                        value = DestinationFieldsClockwise.BLUE4.getValue();
                        break;
                }
                field.setIdForCalculation(value);
                i++;
            }

        } else {
            int i = 1;
            for (GameField field : destinationFieldsRed) {
                //set the new number for each field
                int value = 0;
                switch (i) {
                    case 1:
                        value = DestinationFieldsCounterclockwise.RED1.getValue();
                        break;
                    case 2:
                        value = DestinationFieldsCounterclockwise.RED2.getValue();
                        break;
                    case 3:
                        value = DestinationFieldsCounterclockwise.RED3.getValue();
                        break;
                    case 4:
                        value = DestinationFieldsCounterclockwise.RED4.getValue();
                        break;
                }
                field.setIdForCalculation(value);
                i++;
            }

            i = 1;
            for (GameField field : destinationFieldsGreen) {
                //set the new number for each field
                int value = 0;
                switch (i) {
                    case 1:
                        value = DestinationFieldsCounterclockwise.GREEN1.getValue();
                        break;
                    case 2:
                        value = DestinationFieldsCounterclockwise.GREEN2.getValue();
                        break;
                    case 3:
                        value = DestinationFieldsCounterclockwise.GREEN3.getValue();
                        break;
                    case 4:
                        value = DestinationFieldsCounterclockwise.GREEN4.getValue();
                        break;
                }
                field.setIdForCalculation(value);
                i++;
            }

            i = 1;
            for (GameField field : destinationFieldsYellow) {
                //set the new number for each field
                int value = 0;
                switch (i) {
                    case 1:
                        value = DestinationFieldsCounterclockwise.YELLOW1.getValue();
                        break;
                    case 2:
                        value = DestinationFieldsCounterclockwise.YELLOW2.getValue();
                        break;
                    case 3:
                        value = DestinationFieldsCounterclockwise.YELLOW3.getValue();
                        break;
                    case 4:
                        value = DestinationFieldsCounterclockwise.YELLOW4.getValue();
                        break;
                }
                field.setIdForCalculation(value);
                i++;
            }

            i = 1;
            for (GameField field : destinationFieldsBlue) {
                //set the new number for each field
                int value = 0;
                switch (i) {
                    case 1:
                        value = DestinationFieldsCounterclockwise.BLUE1.getValue();
                        break;
                    case 2:
                        value = DestinationFieldsCounterclockwise.BLUE2.getValue();
                        break;
                    case 3:
                        value = DestinationFieldsCounterclockwise.BLUE3.getValue();
                        break;
                    case 4:
                        value = DestinationFieldsCounterclockwise.BLUE4.getValue();
                        break;
                }
                field.setIdForCalculation(value);
                i++;
            }
        }
    }

    /**
     * Get a unsorted list with all destination fields from a specific color
     *
     * @param color color of the destination fields you want to know
     * @return List with all 4 destination fields of a specific color
     */
    public List<GameField> getListOfDestinationFieldsByColor(String color) {
        if (!(color.equals("green") || color.equals("yellow") || color.equals("red") || color.equals("blue"))) {
            throw new IllegalArgumentException("Please use a right color.");
        }

        List<GameField> destinationFields = new ArrayList<>();
        for (GameField field : fields) {
            if (field.getGameFieldName().equals("destinationfield") && field.getColor().equals(color)) {
                destinationFields.add(field);
            }
        }
        return destinationFields;
    }

    /**
     * Gets a map with cssIds of the four destination fields of a certain color, mapped by the order the pieces are
     * placed on.
     *
     * @param playerColor color of a certain player
     * @return Map with 4 values (key: number (order of destination field, e.g. 1 is where piece1 needs to be placed);
     * value: cssId of destination field)
     */
    public Map<Integer, String> getMapOfDestinationFieldsByColor(String playerColor) {
        Map<Integer, String> destFieldMap = new HashMap<>();
        switch (playerColor) {
            case "red":
                destFieldMap.put(1, "field92");
                destFieldMap.put(2, "field91");
                destFieldMap.put(3, "field90");
                destFieldMap.put(4, "field89");
                break;
            case "yellow":
                destFieldMap.put(1, "field96");
                destFieldMap.put(2, "field95");
                destFieldMap.put(3, "field94");
                destFieldMap.put(4, "field93");
                break;
            case "green":
                destFieldMap.put(1, "field84");
                destFieldMap.put(2, "field83");
                destFieldMap.put(3, "field82");
                destFieldMap.put(4, "field81");
                break;
            case "blue":
                destFieldMap.put(1, "field88");
                destFieldMap.put(2, "field87");
                destFieldMap.put(3, "field86");
                destFieldMap.put(4, "field85");
                break;
        }
        return destFieldMap;
    }

    /**
     * Get the the Game Field object with the idForCalculation and the gameFieldName must be 'standard', 'startfield' or 'wormhole'
     *
     * @param idForCalculation idForCalculation for which we have to search in the list
     * @return GameField with the idForCalculation and name standard or wormhole
     */
    public GameField getStandardStartfieldGameFieldOrWormholeByIdForCalculation(int idForCalculation) {
        GameField returnField = null;
        for (GameField field : fields) {
            if (field.getIdForCalculation() == idForCalculation &&
                    (field.getGameFieldName().equals("standard") || field.getGameFieldName().equals("wormhole") || field.getGameFieldName().equals("startfield"))) {
                returnField = field;
            }
        }
        return returnField;
    }

    // *** PIECES ***

    /**
     * Gives current position of the player's four pieces back according to its color
     *
     * @param color of specific player
     * @return Map with 4 objects (Key: Piece number, Value: cssId of current field the piece stands)
     */
    public Map<Integer, String> getPlacesOfPiecesByColor(String color) {
        Map<Integer, String> allPieces = new HashMap<>();
        for (GameField gf : fields) {
            if (gf.getPieceOnField() != null && gf.getPieceOnField().getColor().equals(color)) {
                allPieces.put(gf.getPieceOnField().getNumber(), gf.getCssId());
            }
        }
        return allPieces;
    }

    /**
     * returns a list of GameFields that currently have a piece on them
     *
     * @return a list of all GameFields with a piece
     */
    public List<GameField> getFieldsWithPieces() {
        List<GameField> fieldsWithPieces = new ArrayList<>();
        for (GameField field : fields) {
            if (field.getPieceOnField() != null) {
                fieldsWithPieces.add(field);
            }
        }
        return fieldsWithPieces;
    }

}
