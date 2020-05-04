package ch.zhaw.psit3.crazydog.Model.Game;

import ch.zhaw.psit3.crazydog.Model.GameField.GameField;

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
