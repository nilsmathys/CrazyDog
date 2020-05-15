package ch.zhaw.psit3.crazydog.Model.Game;

/**
 * <h1>DestinationFieldsCounterclockwise</h1>
 * DestinationFieldsCounterclockwise Enum stores the the id's for Calculation for all destination fields of each
 *     when the Game Direction is counterclockwise<br>
 *
 * @author R. Somma
 * @version 1.0
 * @since April 2020
 */
public enum DestinationFieldsCounterclockwise {
    RED1(33), RED2(34), RED3(35), RED4(36),
    GREEN1(1), GREEN2(2), GREEN3(3), GREEN4(4),
    YELLOW1(49), YELLOW2(50), YELLOW3(51), YELLOW4(52),
    BLUE1(20), BLUE2(19), BLUE3(19), BLUE4(20);

    // declaring private variable for getting values
    private int value;

    /**
     * get the Value of t he Enum attribute
     * @return  idForCalculation for Destination Field
     */
    public int getValue() {
        return this.value;
    }

    // enum constructor - cannot be public or protected
    private DestinationFieldsCounterclockwise(int value) {
        this.value = value;
    }
}
