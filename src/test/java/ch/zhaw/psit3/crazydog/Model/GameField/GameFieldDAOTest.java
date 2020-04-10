package ch.zhaw.psit3.crazydog.Model.GameField;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GameFieldDAOTest {

    static GameFieldDAO gameFieldDAO;
    static GameField newGamefield;
    static GameField updateGamefield;
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
        gameFieldDAO = new GameFieldDAO();
        newGamefield = new GameField("standardImg", "standardCssId", "standard", "white");
        updateGamefield = new GameField("updateImg", "updateCssId", "homefield", "red");
        gamefieldDbLocal1 = new GameField("empty.png", "field1", "startfield", "green");
        gamefieldDbLocal11 = new GameField("empty.png", "field11", "standard", "white");
        gamefieldDbLocal29 = new GameField("empty.png", "field29", "standard", "white");
        gamefieldDbLocal57 = new GameField("empty.png", "field57", "wormhole", "black");
        gamefieldDbLocal61 = new GameField("empty.png", "field61", "standard", "white");
        gamefieldDbLocal65 = new GameField("piece4red.png", "field65", "homefield", "red");
        gamefieldDbLocal84 = new GameField("empty.png", "field84", "destinationfield", "green");
        gamefieldDbLocal85 = new GameField("empty.png", "field85", "destinationfield", "blue");
        gamefieldDbLocal86 = new GameField("empty.png", "field86", "destinationfield", "blue");
        gamefieldDbLocal96 = new GameField("empty.png", "field96", "destinationfield", "yellow");
    }


    @Test
    void insert() {
        // assertTrue(GameFieldDAO.insert(newGamefield));
        // GameFieldDAO.delete(insertGamefield); TODO: delete implementieren
    }

    @Test
    void update() {
        // GameFieldDAO.insert(newGameField);
        // assertTrue(GamefieldDAO.update(updateGameField));
        // GameFieldDAO.delete(updateGamefield);
        //TODO: update implementieren
    }

    @Test
    void delete() {
        // GamefieldDAO.insert(newGamefield);
        // assertTrue(GamefieldDAO.delete(newGamefield));
        //TODO: delete implementieren
    }

    @Test
    void findById() {
        assertEquals(gamefieldDbLocal1.getImageName(), GameFieldDAO.findById(1).getImageName());
        assertEquals(gamefieldDbLocal1.getCssId(), GameFieldDAO.findById(1).getCssId());
        assertEquals(gamefieldDbLocal1.getGameFieldName(), GameFieldDAO.findById(1).getGameFieldName());
        assertEquals(gamefieldDbLocal1.getColor(), GameFieldDAO.findById(1).getColor());
    }

    @Test
    void findAll() {
        List<GameField> dbGameFields = GameFieldDAO.findAll();
        assertEquals(gamefieldDbLocal1.getImageName(), dbGameFields.get(0).getImageName());
        assertEquals(gamefieldDbLocal1.getCssId(), dbGameFields.get(0).getCssId());
        assertEquals(gamefieldDbLocal11.getImageName(), dbGameFields.get(10).getImageName());
        assertEquals(gamefieldDbLocal11.getCssId(), dbGameFields.get(10).getCssId());
        assertEquals(gamefieldDbLocal29.getImageName(), dbGameFields.get(28).getImageName());
        assertEquals(gamefieldDbLocal29.getCssId(), dbGameFields.get(28).getCssId());
        assertEquals(gamefieldDbLocal29.getGameFieldName(), dbGameFields.get(28).getGameFieldName());
        assertEquals(gamefieldDbLocal29.getColor(), dbGameFields.get(28).getColor());
        assertEquals(gamefieldDbLocal57.getImageName(), dbGameFields.get(56).getImageName());
        assertEquals(gamefieldDbLocal61.getCssId(), dbGameFields.get(60).getCssId());
        assertEquals(gamefieldDbLocal61.getGameFieldName(), dbGameFields.get(60).getGameFieldName());
        assertEquals(gamefieldDbLocal61.getColor(), dbGameFields.get(60).getColor());
        assertEquals(gamefieldDbLocal84.getImageName(), dbGameFields.get(83).getImageName());
        assertEquals(gamefieldDbLocal84.getCssId(), dbGameFields.get(83).getCssId());
        assertEquals(gamefieldDbLocal84.getGameFieldName(), dbGameFields.get(83).getGameFieldName());
        assertEquals(gamefieldDbLocal84.getColor(), dbGameFields.get(83).getColor());
        assertEquals(gamefieldDbLocal85.getImageName(), dbGameFields.get(84).getImageName());
        assertEquals(gamefieldDbLocal85.getColor(), dbGameFields.get(84).getColor());
        assertEquals(gamefieldDbLocal86.getCssId(), dbGameFields.get(85).getCssId());
        assertEquals(gamefieldDbLocal86.getGameFieldName(), dbGameFields.get(85).getGameFieldName());
        assertEquals(gamefieldDbLocal86.getColor(), dbGameFields.get(85).getColor());
        assertEquals(gamefieldDbLocal96.getImageName(), dbGameFields.get(95).getImageName());
        assertEquals(gamefieldDbLocal96.getColor(), dbGameFields.get(95).getColor());
    }

    @Test
    void findByName() {
        List<GameField> findStandard = GameFieldDAO.findByName("standard");
        List<GameField> findWormehole = GameFieldDAO.findByName("wormhole");
        List<GameField> findDestinationfield = GameFieldDAO.findByName("destinationfield");
        List<GameField> findHomefield = GameFieldDAO.findByName("homefield");

        //standard 11
        assertEquals(gamefieldDbLocal11.getImageName(), findStandard.get(8).getImageName());
        assertEquals(gamefieldDbLocal11.getCssId(), findStandard.get(8).getCssId());
        assertEquals(gamefieldDbLocal11.getGameFieldName(), findStandard.get(8).getGameFieldName());
        assertEquals(gamefieldDbLocal11.getColor(), findStandard.get(8).getColor());

        //wormhole 57
        assertEquals(gamefieldDbLocal57.getImageName(), findWormehole.get(3).getImageName());
        assertEquals(gamefieldDbLocal57.getCssId(), findWormehole.get(3).getCssId());
        assertEquals(gamefieldDbLocal57.getGameFieldName(), findWormehole.get(3).getGameFieldName());
        assertEquals(gamefieldDbLocal57.getColor(), findWormehole.get(3).getColor());

        //destinanionfield 84
        assertEquals(gamefieldDbLocal84.getImageName(), findDestinationfield.get(3).getImageName());
        assertEquals(gamefieldDbLocal84.getCssId(), findDestinationfield.get(3).getCssId());
        assertEquals(gamefieldDbLocal84.getGameFieldName(), findDestinationfield.get(3).getGameFieldName());
        assertEquals(gamefieldDbLocal84.getColor(), findDestinationfield.get(3).getColor());

        //homefield 65
        assertEquals(gamefieldDbLocal65.getImageName(), findHomefield.get(0).getImageName());
        assertEquals(gamefieldDbLocal65.getCssId(), findHomefield.get(0).getCssId());
        assertEquals(gamefieldDbLocal65.getGameFieldName(), findHomefield.get(0).getGameFieldName());
        assertEquals(gamefieldDbLocal65.getColor(), findHomefield.get(0).getColor());
    }


    @Test
    void findByColour() {
        List<GameField> findWhite = GameFieldDAO.findByColour("white");
        List<GameField> findBlack = GameFieldDAO.findByColour("black");
        List<GameField> findGreen = GameFieldDAO.findByColour("green");
        List<GameField> findYellow = GameFieldDAO.findByColour("yellow");
        List<GameField> findRed = GameFieldDAO.findByColour("red");
        List<GameField> findBlue = GameFieldDAO.findByColour("blue");

        //white 11
        assertEquals(gamefieldDbLocal11.getImageName(), findWhite.get(8).getImageName());
        assertEquals(gamefieldDbLocal11.getCssId(), findWhite.get(8).getCssId());
        assertEquals(gamefieldDbLocal11.getGameFieldName(), findWhite.get(8).getGameFieldName());
        assertEquals(gamefieldDbLocal11.getColor(), findWhite.get(8).getColor());

        //black 57
        assertEquals(gamefieldDbLocal57.getImageName(), findBlack.get(3).getImageName());
        assertEquals(gamefieldDbLocal57.getCssId(), findBlack.get(3).getCssId());
        assertEquals(gamefieldDbLocal57.getGameFieldName(), findBlack.get(3).getGameFieldName());
        assertEquals(gamefieldDbLocal57.getColor(), findBlack.get(3).getColor());

        //green 84
        assertEquals(gamefieldDbLocal84.getImageName(), findGreen.get(8).getImageName());
        assertEquals(gamefieldDbLocal84.getCssId(), findGreen.get(8).getCssId());
        assertEquals(gamefieldDbLocal84.getGameFieldName(), findGreen.get(8).getGameFieldName());
        assertEquals(gamefieldDbLocal84.getColor(), findGreen.get(8).getColor());

        //yellow 96
        assertEquals(gamefieldDbLocal96.getImageName(), findYellow.get(8).getImageName());
        assertEquals(gamefieldDbLocal96.getCssId(), findYellow.get(8).getCssId());
        assertEquals(gamefieldDbLocal96.getGameFieldName(), findYellow.get(8).getGameFieldName());
        assertEquals(gamefieldDbLocal96.getColor(), findYellow.get(8).getColor());

        //red 65
        assertEquals(gamefieldDbLocal65.getImageName(), findRed.get(8).getImageName());
        assertEquals(gamefieldDbLocal65.getCssId(), findRed.get(8).getCssId());
        assertEquals(gamefieldDbLocal65.getGameFieldName(), findRed.get(8).getGameFieldName());
        assertEquals(gamefieldDbLocal65.getColor(), findRed.get(8).getColor());

        //blue 85
        assertEquals(gamefieldDbLocal85.getImageName(), findBlue.get(8).getImageName());
        assertEquals(gamefieldDbLocal85.getCssId(), findBlue.get(8).getCssId());
        assertEquals(gamefieldDbLocal85.getGameFieldName(), findBlue.get(8).getGameFieldName());
        assertEquals(gamefieldDbLocal85.getColor(), findBlue.get(8).getColor());

    }
}