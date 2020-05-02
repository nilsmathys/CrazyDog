package ch.zhaw.psit3.crazydog.Model.GameField;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameFieldDAOTest {

    static GameField gamefieldDbLocal1;
    static GameField gamefieldDbLocal11;
    static GameField gamefieldDbLocal29;
    static GameField gamefieldDbLocal57;
    static GameField gamefieldDbLocal61;
    static GameField gamefieldDbLocal65;
    static GameField gamefieldDbLocal84;
    static GameField gamefieldDbLocal85;
    static GameField gamefieldDbLocal86;
    static GameField gamefieldDbLocal96;


    @BeforeAll
    static void beforeAll() {
        gamefieldDbLocal1 = new GameField("empty.png", "field1", "startfield", "red",5);
        gamefieldDbLocal11 = new GameField("empty.png", "field11", "standard", "white",15);
        gamefieldDbLocal29 = new GameField("empty.png", "field29", "standard", "white",33);
        gamefieldDbLocal57 = new GameField("empty.png", "field57", "wormhole", "black",61);
        gamefieldDbLocal61 = new GameField("empty.png", "field61", "standard", "white",1);
        gamefieldDbLocal65 = new GameField("piece4red.png", "field65", "homefield", "red",1);
        gamefieldDbLocal84 = new GameField("empty.png", "field84", "destinationfield", "green",1);
        gamefieldDbLocal85 = new GameField("empty.png", "field85", "destinationfield", "blue",20);
        gamefieldDbLocal86 = new GameField("empty.png", "field86", "destinationfield", "blue",19);
        gamefieldDbLocal96 = new GameField("empty.png", "field96", "destinationfield", "yellow",49);
    }


}