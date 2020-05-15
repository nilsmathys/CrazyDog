package ch.zhaw.psit3.crazydog.Model.Game;

import ch.zhaw.psit3.crazydog.Model.GameField.GameField;

/**
 * <h1>Move</h1>
 * The Move handles a single move that could be made from one source field to a destination field
 *
 * @author R. Bertschinger
 * @version 1.0
 * @since April 2020
 */
public class Move {
    private GameField sourceField;
    private GameField destinationField;

    public Move(GameField sourceField, GameField destinationField) {
        this.sourceField = sourceField;
        this.destinationField = destinationField;
    }

    public GameField getSourceField() {
        return sourceField;
    }

    public GameField getDestinationField() {
        return destinationField;
    }
}
