package ch.zhaw.psit3.crazydog.Model.GameField;

import ch.zhaw.psit3.crazydog.Model.GameField.GameField;
import ch.zhaw.psit3.crazydog.db.DBConnectionFactory;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class GameFieldDAO {

     /**
     * Methode für um ein GameField mit einer spezifischen ID aus der DB auszulesen.
     *
     * @param id als Integer - Id des GameField welches aus der DB geholt werden soll
     * @return GameField welches erstellt wurde.
     */
    public static GameField findById(int id){
        Connection con = null;
        GameField field = null;
        try {
            con = DBConnectionFactory.getConnection();

            String query = "SELECT gamefieldID, startImageName,cssid , name, colourname" +
                    "  FROM dbo.Gamefields g JOIN dbo.Colour c ON g.colourID = c.colourID JOIN dbo.GamefieldName n ON n.gamefieldNameID = g.gamefieldNameID" +
                    " WHERE gamefieldID = ?";
            PreparedStatement st = con.prepareStatement(query);
            st.setInt(1, id);
            ResultSet result = st.executeQuery();

            String startImageName = null;
            String cssId = null;
            String gameFieldName = null;
            String colourName = null;
            while (result.next()) {
                startImageName = result.getString("startImageName"); // using column name
                cssId = result.getString("cssid"); // using column name
                gameFieldName = result.getString("name"); // using column name
                colourName = result.getString("colourname"); // using column name
                //System.out.println("Name: "+ gameFieldName + " startImageName: " + startImageName + " Color: " + colourName + " cssid: "+cssId  );
            }

            field = new GameField(startImageName,cssId, gameFieldName, colourName);
            result.close(); st.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) { e.printStackTrace(); }
        }
        return field;
    }

    /**
     * Methode für um alle Spielfelder aus der Datenbank auszulesen.
     *
     * @return List<GameField> Liste aller Felder aus der Datenbank.
     */
    public static List<GameField> findAll() {
        Connection con = null;
        List<GameField> fieldList = new ArrayList<>();
        try {
            con = DBConnectionFactory.getConnection();
            Statement st = con.createStatement();
            String query = "SELECT gamefieldID, startImageName,cssid , name, colourname" +
                    "  FROM dbo.Gamefields g JOIN dbo.Colour c ON g.colourID = c.colourID JOIN dbo.GamefieldName n ON n.gamefieldNameID = g.gamefieldNameID" +
                    " ORDER BY gamefieldID ASC";
            ResultSet result = st.executeQuery(query);

            while (result.next()) {
                fieldList.add(new GameField(result.getString("startImageName"),result.getString("cssid"),
                        result.getString("name"), result.getString("colourname")));
            }
            result.close(); st.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return fieldList;
    }

    /**
     * Methode für um alle Spielfelder aus der Datenbank auszulesen mit einem bestimmten Namen
     *
     * @param name als String - Name der Felder, welche ausgelesen werden sollen.
     * @return List<GameField> Liste aller Felder aus der Datenbank mit einem bestimmten Namen.
     */
    public static List<GameField> findByName(String name) {
        if(!(name.equals("wormhole") || name.equals("standard") || name.equals("startfield") || name.equals("destinationfield") || name.equals("homefield")))
        {
            throw new IllegalArgumentException("Please use a right parameter.");
        }
        Connection con = null;
        List<GameField> fieldList = new ArrayList<>();
        try {
            con = DBConnectionFactory.getConnection();

            String query = "SELECT gamefieldID, startImageName,cssid , name, colourname" +
                    "  FROM dbo.Gamefields g JOIN dbo.Colour c ON g.colourID = c.colourID JOIN dbo.GamefieldName n ON n.gamefieldNameID = g.gamefieldNameID" +
                    " WHERE name = ?";
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, name);
            ResultSet result = st.executeQuery();

            while (result.next()) {
                /*String startImageName = result.getString("startImageName"); // using column name
                String cssId = result.getString("cssid"); // using column name
                String gameFieldName = result.getString("name"); // using column name
                String colourName = result.getString("colourname"); // using column name*/
                //System.out.println("Name: "+ gameFieldName + " startImageName: " + startImageName + " Color: " + colourName + " cssid: "+cssId  );
               fieldList.add(new GameField(result.getString("startImageName"),result.getString("cssid"),
                        result.getString("name"), result.getString("colourname")));
            }
            result.close(); st.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) { e.printStackTrace(); }
        }
        return fieldList;
    }

    /**
     * Methode für um alle Spielfelder aus der Datenbank auszulesen mit einer bestimmten Farbe
     *
     * @param name als String - Name der Farbe
     * @return List<GameField> Liste aller Felder aus der Datenbank mit einer bestimmten Farbe
     */
    public static List<GameField> findByColour(String name) {
        if(!(name.equals("white") || name.equals("black") || name.equals("green") ||
                name.equals("yellow") || name.equals("red") || name.equals("blue")))
        {
            throw new IllegalArgumentException("Please use a right parameter.");
        }
        Connection con = null;
        List<GameField> fieldList = new ArrayList<>();
        try {
            con = DBConnectionFactory.getConnection();

            String query = "SELECT gamefieldID, startImageName,cssid , name, colourname" +
                    "  FROM dbo.Gamefields g JOIN dbo.Colour c ON g.colourID = c.colourID JOIN dbo.GamefieldName n ON n.gamefieldNameID = g.gamefieldNameID" +
                    " WHERE colourname = ?";
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, name);
            ResultSet result = st.executeQuery();

            while (result.next()) {
                fieldList.add(new GameField(result.getString("startImageName"),result.getString("cssid"),
                        result.getString("name"), result.getString("colourname")));
            }
            result.close(); st.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) { e.printStackTrace(); }
        }
        return fieldList;
    }

    /**
     * Methode um die ID eines bestimmten FeldNamens auszulesen.
     *
     * @param name als String - Name der des Feldes
     * @return Integer ID des GameFieldName.
    */
    public static int getGameFieldNameId(String name) {
        if(!(name.equals("wormhole") || name.equals("standard") || name.equals("startfield") || name.equals("destinationfield") || name.equals("homefield")))
        {
            throw new IllegalArgumentException("Please use a right parameter.");
        }
        Connection con = null;
        int gamefieldNameID = 0;
        try {
            con = DBConnectionFactory.getConnection();

            String query = "SELECT gamefieldNameID FROM dbo.GamefieldName WHERE name = ?";
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, name);
            ResultSet result = st.executeQuery();

            while (result.next()) {
                gamefieldNameID = result.getInt("gamefieldNameID"); // using column name
            }
            result.close(); st.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) { e.printStackTrace(); }
        }
        return gamefieldNameID;
    }

    /**
     * Methode um die ID einer bestimmten Farbe auszulesen.
     *
     * @param name als String - Name der Farbe
     * @return Integer ID der Farbe.
    */
    public static int getColourId(String name) {
        if(!(name.equals("white") || name.equals("black") || name.equals("green") ||
                name.equals("yellow") || name.equals("red") || name.equals("blue")))
        {
            throw new IllegalArgumentException("Please use a right parameter.");
        }

        Connection con = null;
        int colourID = 0;
        try {
            con = DBConnectionFactory.getConnection();

            String query = "SELECT colourID FROM dbo.Colour WHERE colourname = ?";
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, name);
            ResultSet result = st.executeQuery();

            while (result.next()) {
                colourID = result.getInt("colourID"); // using column name
            }
            result.close(); st.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) { e.printStackTrace(); }
        }
        return colourID;
    }

    public static void main(String[] args) {
        List<GameField> fieldListAll = findAll();
       GameField field = findById(1);
       List<GameField> fieldList1 = findByName("wormhole");
       List<GameField> fieldList2 = findByColour("red");
    }
}
