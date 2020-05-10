package ch.zhaw.psit3.crazydog.Model.Game;

import ch.zhaw.psit3.crazydog.CrazyDog;
import ch.zhaw.psit3.crazydog.Model.Player.Player;
import ch.zhaw.psit3.crazydog.Model.Player.PlayerDAO;
import ch.zhaw.psit3.crazydog.db.DBConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CrazyDogDAO {
    private static final Logger LOGGER = Logger.getLogger(CrazyDogDAO.class.getName());

    /**
     * Lädt Spiel anhand der gameId
     * @param gameId int
     */
    public static CrazyDog loadGame(int gameId) {
        Connection con = null;
        CrazyDog crazyDog = null;
        try {
            con = DBConnectionFactory.getConnection();

            String query = "SELECT player1ID, player2ID, player3ID, player4ID, nextPlayer, gameBoardID, cardsOnHandId  FROM Games WHERE gameID=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, gameId);
            ResultSet rs = ps.executeQuery();
            int player1ID = 0;
            int player2ID = 0;
            int player3ID = 0;
            int player4ID = 0;
            int nextPlayer = 0;
            int gameBoardId = 0;
            int cardsOnHandID = 0;

            if (rs.next()) {
                player1ID = rs.getInt("player1ID");
                player2ID = rs.getInt("player2ID");
                player3ID = rs.getInt("player3ID");
                player4ID = rs.getInt("player4ID");
                nextPlayer = rs.getInt("nextPlayer");
                gameBoardId = rs.getInt("gameBoardID");
                cardsOnHandID = rs.getInt("cardsOnHandID");
            }
            Player player1 = PlayerDAO.getPlayerById(player1ID);
            Player player2 = PlayerDAO.getPlayerById(player2ID);
            Player player3 = PlayerDAO.getPlayerById(player3ID);
            Player player4 = PlayerDAO.getPlayerById(player4ID);
            //GameBoard gameBoard = GameBoardDAO.getGameBoardById(gameBoardId);
            //CardsOnHand cardsOnHand = CarsOnHandDAO.getCardsOnHandById(cardsOnHandId);
            //crazyDog = new CrazyDog(gameId, player1, player2, player3, player4, nextPlayer, gameboard, cardsOnHand);
            rs.close();
            ps.close();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Couldn't load game.", e);
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Connection error.", e);
            }
        }
        return null;
    }

    /**
     * Speichert das Spiel anhand der GameId.
     * Falls kein Spiel existiert ist die GameId = 0 und es wird ein neues Spiel in die Datenbank gespeichert.
     * @param gameId int
     * @return true falls das Speichern funktioniert hat, ansonsten false.
     */
//    public static boolean saveGame(int gameId, int playerId1, int PlayerId2, int PlayerId3, int PlayerId4, int nextPlayer,
//                                   int positionRed1, int positionRed2, int PositionRed3, int positionRed4, int positionGreen1,
//                                   int positionGreen2, int positionGreen3, int positionGreen4, int positionBlue1, int positionBlue2,
//                                   int positionBlue3, int positionBlue4, int positionYellow1, int positionYellow2, int positionYellow3,
//                                   int positionYellow4,
//                                   ) {
//        int i = 0;
//        if(gameId == 0) {
//            //ToDo: saveNewGame
//
//        } else {
//            //TODo: saveExistGame
//        }
//        if (i == 1) {
//            return true;
//        } else {
//            return false;
//        }
//    }
}
