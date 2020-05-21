package ch.zhaw.psit3.crazydog.Model.Game;

public enum IdForCalculation {
    //ID_FOR_CALCULATION with Value 1, 2 and 4 have more fields because they are on a normal field, destinationfield and on the homefield
    ID_FOR_CALCULATION_FIELD1(5), ID_FOR_CALCULATION_FIELD2(6), ID_FOR_CALCULATION_FIELD4(8), ID_FOR_CALCULATION_FIELD8(12),
    ID_FOR_CALCULATION_FIELD9(13), ID_FOR_CALCULATION_FIELD13(17), ID_FOR_CALCULATION_FIELD16(20), ID_FOR_CALCULATION_FIELD17(21),
    ID_FOR_CALCULATION_FIELD20(24), ID_FOR_CALCULATION_FIELD29(33), ID_FOR_CALCULATION_FIELD32(36), ID_FOR_CALCULATION_FIELD33(37),
    ID_FOR_CALCULATION_FIELD36(40), ID_FOR_CALCULATION_FIELD45(49), ID_FOR_CALCULATION_FIELD48(52), ID_FOR_CALCULATION_FIELD49(53),
    ID_FOR_CALCULATION_FIELD52(56), ID_FOR_CALCULATION_FIELD53(57), ID_FOR_CALCULATION_FIELD60(64), ID_FOR_CALCULATION_FIELD61_65_84(1),
    ID_FOR_CALCULATION_FIELD62_66_83(2), ID_FOR_CALCULATION_FIELD64_68_81(4);

    //TODO: Alles IdForCalculation, umschreiben in normalesField


    // declaring private variable for getting values
    private int value;

    /**
     * get the Value of t he Enum attribute
     *
     * @return idForCalculation for Destination Field
     */
    public int getValue() {
        return this.value;
    }

    // enum constructor - cannot be public or protected
    private IdForCalculation(int value) {
        this.value = value;
    }
}
