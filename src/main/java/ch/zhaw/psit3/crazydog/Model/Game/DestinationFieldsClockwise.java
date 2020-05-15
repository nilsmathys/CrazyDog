package ch.zhaw.psit3.crazydog.Model.Game;

/**
 * <h1>DestinationFieldsClockwise</h1>
 * DestinationFieldsClockwise Enum stores the the id's for Calculation for all destination fields of each
 *     when the Game Direction is clockwise<br>
 *
 * @author R. Somma
 * @version 1.0
 * @since April 2020
 */
public enum DestinationFieldsClockwise {
    RED1(41),RED2(40),RED3(39),RED4(38),
    GREEN1(9),GREEN2(8),GREEN3(7),GREEN4(6),
    YELLOW1(57),YELLOW2(56),YELLOW3(55),YELLOW4(54),
    BLUE1(25),BLUE2(24),BLUE3(23),BLUE4(22);

    // declaring private variable for getting values
    private int value;

    /**
     * get the Value of t he Enum attribute
     * @return  idForCalculation for Destination Field
     */
    public int getValue()
    {
        return this.value;
    }

    // enum constructor - cannot be public or protected
    private DestinationFieldsClockwise(int value)
    {
        this.value = value;
    }
}
