package ch.zhaw.psit3.crazydog.Model.Game;

import ch.zhaw.psit3.crazydog.Model.Player.Player;
import ch.zhaw.psit3.crazydog.Model.Player.PlayerDAO;
import ch.zhaw.psit3.crazydog.db.DBConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CrazyDogDAO {

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
            //CardsOnHand carsOnHand = CarsOnHandDAO.getCardsOnHandById(cardsOnHandId);
            //crazyDog = new CrazyDog(gameId, player1, player2, player3, player4, nextPlayer, gameboard, cardsOnHand);
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
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
    public static boolean saveGame(int gameId) {
        int i = 0;
        if(gameId == 0) {
            //saveNewGame

        } else {
            //saveExistGame
        }
        if (i == 1) {
            return true;
        } else {
            return false;
        }
    }
}
