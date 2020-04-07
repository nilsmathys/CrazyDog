package ch.zhaw.psit3.crazydog.Model.Card;

import ch.zhaw.psit3.crazydog.db.DBConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CardDAO {
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
            PreparedStatement st = con.prepareStatement(query);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                int value = rs.getInt("value");
                card = new Card(id, name, value);
            }

            rs.close();
            st.close();
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
            Statement st = con.createStatement();
            String query = "SELECT * FROM Cards ORDER BY cardID ASC";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                int cardId = rs.getInt("cardID");
                String name = rs.getString("name");
                int value = rs.getInt("value");
                cardList.add(new Card(cardId, name, value));
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) { e.printStackTrace(); }
        }
        return cardList;
    }
}