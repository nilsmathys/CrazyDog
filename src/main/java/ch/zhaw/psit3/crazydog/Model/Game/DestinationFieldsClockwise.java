package ch.zhaw.psit3.crazydog.Model.Game;

public enum DestinationFieldsClockwise {
    RED1(41),RED2(40),RED3(39),RED4(38),
    GREEN1(9),GREEN2(8),GREEN3(7),GREEN4(6),
    YELLOW1(57),YELLOW2(56),YELLOW3(55),YELLOW4(54),
    BLUE1(25),BLUE2(24),BLUE3(23),BLUE4(22);

    // declaring private variable for getting values
    private int value;

    // getter method
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
