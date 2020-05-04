package ch.zhaw.psit3.crazydog.Model.Game;

import java.util.HashMap;
import java.util.Map;

public class Values {

    public static final int CARDVALUE2 = 2;
    public static final int CARDVALUE3 = 3;
    public static final int CARDVALUE4 = 4;
    public static final int CARDVALUE5 = 5;
    public static final int CARDVALUE6 = 6;
    public static final int CARDVALUE7 = 7;
    public static final int CARDVALUE8 = 8;
    public static final int CARDVALUE9 = 9;
    public static final int CARDVALUE10 = 10;
    public static final int CARDVALUE12 = 12;


    //ColourID
    public static final int COLOURIDRED = 3;
    public static final int COLOURIDGREEN = 4;
    public static final int COLOURIDYELLOW = 5;
    public static final int COLOURIDBLUE = 6;


    //redFields
    public static final int STARTFIELDRED = 1;
    public static final int LOWESTHOMEFIELDRED = 65;
    public static final int HIGHESTHOMEFIELDRED = 68;
    public static final int LOWESTDESTINATIONFIELDRED = 89;
    public static final int MAXWITHTOGOCLOCKWISERED = 38;
    public static final int MAXWITHTOGOCOUNTERCLOCKWISERED = 28;

    public static final Map<String, Integer> HOMEANDDESTFIELDSRED = new HashMap<>() {
        {
            put("LowestHomefield", LOWESTHOMEFIELDRED);
            put("HighestHomefield", HIGHESTHOMEFIELDRED);
            put("LowestDestinationField", LOWESTDESTINATIONFIELDRED);
        }
    };

    //greenFields
    public static final int STARTFIELDGREEN = 33;
    public static final int LOWESTHOMEFIELDGREEN = 73;
    public static final int HIGHESTHOMEFIELDGREEN = 76;
    public static final int LOWESTDESTINATIONFIELDGREEN = 81;

    public static final Map<String, Integer> HOMEANDDESTFIELDSGREEN = new HashMap<>() {
        {
            put("LowestHomefield", LOWESTHOMEFIELDGREEN);
            put("HighestHomefield", HIGHESTHOMEFIELDGREEN);
            put("LowestDestinationField", LOWESTDESTINATIONFIELDGREEN);
        }
    };

    //yellowFields
    public static final int STARTFIELDYELLOW = 17;
    public static final int LOWESTHOMEFIELDYELLOW = 69;
    public static final int HIGHESTHOMEFIELDYELLOW = 72;
    public static final int LOWESTDESTINATIONFIELDYELLOW = 93;
    public static final int MAXWITHTOGOCLOCKWISEYELLOW = 54;
    public static final int MAXWITHTOGOCOUNTERCLOCKWISEYELLOW = 44;

    public static final Map<String, Integer> HOMEANDDESTFIELDSYELLOW= new HashMap<>() {
        {
            put("LowestHomefield", LOWESTHOMEFIELDYELLOW);
            put("HighestHomefield", HIGHESTHOMEFIELDYELLOW);
            put("LowestDestinationField", LOWESTDESTINATIONFIELDYELLOW);
        }
    };


    //blueFields
    public static final int STARTFIELDBLUE = 49;
    public static final int LOWESTHOMEFIELDBLUE = 77;
    public static final int HIGHESTHOMEFIELDBLUE = 80;
    public static final int LOWESTDESTINATIONFIELDBLUE = 85;
    public static final int MAXWITHTOGOCLOCKWISEBLUE = 22;
    public static final int MAXWITHTOGOCOUNTERCLOCKWISEBLUE = 28;

    public static final Map<String, Integer> HOMEANDDESTFIELDSBLUE= new HashMap<>() {
        {
            put("LowestHomefield", LOWESTHOMEFIELDBLUE);
            put("HighestHomefield", HIGHESTHOMEFIELDBLUE);
            put("LowestDestinationField", LOWESTDESTINATIONFIELDBLUE);
        }
    };

    //wormhole
    public static final int Wormhole1 = 9;
    public static final int Wormhole2 = 25;
    public static final int Wormhole3 = 41;
    public static final int Wormhole4 = 57;





    public static final Map<Integer, Map> HOMEANDDESTFIELDS = new HashMap<>() {
        {
            put(COLOURIDRED, HOMEANDDESTFIELDSRED);
            put(COLOURIDGREEN, HOMEANDDESTFIELDSGREEN);
            put(COLOURIDYELLOW, HOMEANDDESTFIELDSYELLOW);
            put(COLOURIDBLUE, HOMEANDDESTFIELDSBLUE);
        }
    };



    public static final Map<String, Integer> REDFIELDS = new HashMap<>() {
        {
            put("Startfield", STARTFIELDGREEN);
            put("MaxWithToGoClockwise", MAXWITHTOGOCLOCKWISERED);
            put("MaxWithToGoCounterClockwise", MAXWITHTOGOCOUNTERCLOCKWISERED);
            put("DestField", LOWESTDESTINATIONFIELDRED);
        }
    };

    public static final Map<String, Integer> YELLOWFIELDS = new HashMap<>() {
        {
            put("Startfield", STARTFIELDBLUE);
            put("MaxWithToGoClockwise", MAXWITHTOGOCLOCKWISEYELLOW);
            put("MaxWithToGoCounterClockwise", MAXWITHTOGOCOUNTERCLOCKWISEYELLOW);
            put("DestField", LOWESTDESTINATIONFIELDYELLOW);
        }
    };

    public static final Map<String, Integer> BLUEFIELDS = new HashMap<>() {
        {
            put("Startfield", STARTFIELDYELLOW);
            put("MaxWithToGoClockwise", MAXWITHTOGOCLOCKWISEBLUE);
            put("MaxWithToGoCounterClockwise", MAXWITHTOGOCOUNTERCLOCKWISEBLUE);
            put("DestField", LOWESTDESTINATIONFIELDBLUE);
        }
    };


    public static final Map<Integer, Map> STARTANDDESTFIELDS= new HashMap<>() {
        {
            put(COLOURIDRED, REDFIELDS);
            put(COLOURIDYELLOW, YELLOWFIELDS);
            put(COLOURIDBLUE, BLUEFIELDS);

        }
    };
}
