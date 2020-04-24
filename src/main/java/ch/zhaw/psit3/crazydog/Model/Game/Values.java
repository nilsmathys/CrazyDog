package ch.zhaw.psit3.crazydog.Model.Game;

import java.util.HashMap;
import java.util.Map;

public class Values {

    public static final int COLOURIDRED = 3;
    public static final int COLOURIDGREEN = 4;
    public static final int COLOURIDYELLOW = 5;
    public static final int COLOURIDBLUE = 6;


    //red
    public static final int STARTFIELDRED = 1;
    public static final int LOWESTHOMEFIELDRED = 65;
    public static final int HIGHESTHOMEFIELDRED = 68;
    public static final int LOWESTDESTINATIONFIELDRED = 89;
    public static final int MAXWITHTOGOCLOCKWISERED = 38;
    public static final int MAXWITHTOGOCOUNTERCLOCKWISERED = 28;

    public static final int[] HOMEANDDESTFIELDSRED = {LOWESTHOMEFIELDRED, HIGHESTHOMEFIELDRED, LOWESTDESTINATIONFIELDRED};

    //green
    public static final int STARTFIELDGREEN = 33;
    public static final int LOWESTHOMEFIELDGREEN = 73;
    public static final int HIGHESTHOMEFIELDGREEN = 76;
    public static final int LOWESTDESTINATIONFIELDGREEN = 81;

    public static final int[] HOMEANDDESTFIELDSGREEN = {LOWESTHOMEFIELDGREEN, HIGHESTHOMEFIELDGREEN, LOWESTDESTINATIONFIELDGREEN};

    //yellow
    public static final int STARTFIELDYELLOW = 17;
    public static final int LOWESTHOMEFIELDYELLOW = 69;
    public static final int HIGHESTHOMEFIELDYELLOW = 72;
    public static final int LOWESTDESTINATIONFIELDYELLOW = 93;
    public static final int MAXWITHTOGOCLOCKWISEYELLOW = 54;
    public static final int MAXWITHTOGOCOUNTERCLOCKWISEYELLOW = 44;

    public static final int[] HOMEANDDESTFIELDSYELLOW = {LOWESTHOMEFIELDYELLOW, HIGHESTHOMEFIELDYELLOW, LOWESTDESTINATIONFIELDYELLOW};

    //blue
    public static final int STARTFIELDBLUE = 49;
    public static final int LOWESTHOMEFIELDBLUE = 77;
    public static final int HIGHESTHOMEFIELDBLUE = 80;
    public static final int LOWESTDESTINATIONFIELDBLUE = 85;
    public static final int MAXWITHTOGOCLOCKWISEBLUE = 22;
    public static final int MAXWITHTOGOCOUNTERCLOCKWISEBLUE = 28;

    public static final int[] HOMEANDDESTFIELDSBLUE = {LOWESTHOMEFIELDBLUE, HIGHESTHOMEFIELDBLUE, LOWESTDESTINATIONFIELDBLUE};



    public static final Map<Integer, int[]> HOMEANDDESTFIELDS= new HashMap<>() {
        {
            put(COLOURIDRED, HOMEANDDESTFIELDSRED);
            put(COLOURIDGREEN, HOMEANDDESTFIELDSGREEN);
            put(COLOURIDYELLOW, HOMEANDDESTFIELDSYELLOW);
            put(COLOURIDBLUE, HOMEANDDESTFIELDSBLUE);
        }
    };

    //CLOCKWISE
    public static final int[] CLOCKWISERED = {STARTFIELDGREEN, MAXWITHTOGOCLOCKWISERED, LOWESTDESTINATIONFIELDRED};
    public static final int[] CLOCKWISEYELLOW = {STARTFIELDBLUE, MAXWITHTOGOCLOCKWISEYELLOW, LOWESTDESTINATIONFIELDYELLOW};
    public static final int[] CLOCKWISEBLUE = {STARTFIELDYELLOW, MAXWITHTOGOCLOCKWISEBLUE, LOWESTDESTINATIONFIELDBLUE};

    public static final Map<Integer, int[]> DESTFIELDSCLOCKWISE = new HashMap<>() {
        {
            put(COLOURIDRED, CLOCKWISERED);
            put(COLOURIDYELLOW, CLOCKWISEYELLOW);
            put(COLOURIDBLUE, CLOCKWISEBLUE);
        }
    };

    //COUNTERCLOCKWISE
    public static final int[] COUNTERCLOCKWISERED = {STARTFIELDGREEN, MAXWITHTOGOCOUNTERCLOCKWISERED, LOWESTDESTINATIONFIELDRED};
    public static final int[] COUNTERCLOCKWISEYELLOW = {STARTFIELDBLUE, MAXWITHTOGOCOUNTERCLOCKWISEYELLOW, LOWESTDESTINATIONFIELDYELLOW};
    public static final int[] COUNTERCLOCKWISEBLUE = {STARTFIELDYELLOW, MAXWITHTOGOCOUNTERCLOCKWISEBLUE, LOWESTDESTINATIONFIELDBLUE};

    public static final Map<Integer, int[]> DESTFIELDCOUNTERCLOCKWISE = new HashMap<>() {
        {
            put(COLOURIDRED, COUNTERCLOCKWISERED);
            put(COLOURIDYELLOW, COUNTERCLOCKWISEYELLOW);
            put(COLOURIDBLUE, COUNTERCLOCKWISEBLUE);
        }
    };


}
