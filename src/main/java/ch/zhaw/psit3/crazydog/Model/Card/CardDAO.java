package ch.zhaw.psit3.crazydog.Model.Card;

import ch.zhaw.psit3.crazydog.db.DBConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <h1>CardDAO</h1>
 * The CardDAO queries the database to get all of the stores cards.
 *
 * @author S. Werlin
 * @version 1.0
 * @since April 2020
 */
public class CardDAO {
    private static final Logger LOGGER = Logger.getLogger(CardDAO.class.getName());
    /**
     * Queries the database for a card with a given id
     * @param id of the card
     * @return single Card object
     */
    public static Card getCardById(int id) {
        if(!(id >= 2 && id <=15)) {
            throw new IllegalArgumentException("No card with id " + id + " exists.");
        }
        Connection con = null;
        Card card = null;
        try {
            con = DBConnectionFactory.getConnection();
            String query = "SELECT name, value FROM Cards WHERE cardID=?";
            try (PreparedStatement st = con.prepareStatement(query)) {
                st.setInt(1, id);
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        String name = rs.getString("name");
                        int value = rs.getInt("value");
                        card = new Card(id, name, value);
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Couldn't load card by id.", e);
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Connection Error.", e);
            }
        }
        return card;
    }

    /**
     * Queries the database for every card stored and returns them with their id, name and value.
     * @return list of Card objects
     */
    public static List<Card> getAllCards() {
        Connection con = null;
        List<Card> cardList = new ArrayList<>();
        try {
            con = DBConnectionFactory.getConnection();
            try (Statement st = con.createStatement()) {
                String query = "SELECT * FROM Cards ORDER BY cardID ASC";
                try (ResultSet rs = st.executeQuery(query)) {
                    while (rs.next()) {
                        int cardId = rs.getInt("cardID");
                        String name = rs.getString("name");
                        int value = rs.getInt("value");
                        cardList.add(new Card(cardId, name, value));
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Couldn't load all Cards.", e);
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Connection Error.", e);
            }
        }
        return cardList;
    }
}