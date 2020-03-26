package ch.zhaw.psit3.crazydog.Model.GameField;

import ch.zhaw.psit3.crazydog.Model.GameField.GameField;
import ch.zhaw.psit3.crazydog.db.DBConnectionFactory;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class GameFieldDAO {
    public Boolean insert(GameField field) {
        Connection con = null;
        Boolean insertOk = false;
        try {
            con = DBConnectionFactory.getConnection();
            int gameFieldNameId = getGameFieldNameId(field.getGameFieldName());
            int colourID = getGameFieldNameId(field.getColor());

            String query = "INSERT INTO dbo.Gamefields (startimagename, cssid, gamefieldNameID, colourID) VALUES (?,?,?,?)";
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, field.getStartImageName());
            st.setString(2, field.getCssId());
            st.setInt(3, gameFieldNameId);
            st.setInt(4, colourID);
            int result = st.executeUpdate();

            if(result == 1) {
                insertOk = true;
            }
            st.close();
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
        return insertOk;
    }

    public void update(GameField field) {

    }

    public void delete(GameField field) {

    }

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
                System.out.println("Name: "+ gameFieldName + " startImageName: " + startImageName + " Color: " + colourName + " cssid: "+cssId  );
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

    public static void findAll() {
        Connection con = null;
        try {
            con = DBConnectionFactory.getConnection();
            Statement st = con.createStatement();
            String query = "SELECT gamefieldID, startImageName,cssid , name, colourname" +
                    "  FROM dbo.Gamefields g JOIN dbo.Colour c ON g.colourID = c.colourID JOIN dbo.GamefieldName n ON n.gamefieldNameID = g.gamefieldNameID" +
                    " ORDER BY gamefieldID ASC";
            ResultSet result = st.executeQuery(query);

            while (result.next()) {
                String startImageName = result.getString("startImageName"); // using column name
                String cssId = result.getString("cssid"); // using column name
                String name = result.getString("name"); // using column name
                String colourName = result.getString("colourname"); // using column name

                System.out.println("Name: "+ name + " startImageName: " + startImageName + " Color: " + colourName + " cssid: "+cssId  );
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
    }

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
                String startImageName = result.getString("startImageName"); // using column name
                String cssId = result.getString("cssid"); // using column name
                String gameFieldName = result.getString("name"); // using column name
                String colourName = result.getString("colourname"); // using column name
                System.out.println("Name: "+ gameFieldName + " startImageName: " + startImageName + " Color: " + colourName + " cssid: "+cssId  );
               fieldList.add(new GameField(startImageName,cssId, gameFieldName, colourName));
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
                String startImageName = result.getString("startImageName"); // using column name
                String cssId = result.getString("cssid"); // using column name
                String gameFieldName = result.getString("name"); // using column name
                String colourName = result.getString("colourname"); // using column name
                System.out.println("Name: "+ gameFieldName + " startImageName: " + startImageName + " Color: " + colourName + " cssid: "+cssId  );
                fieldList.add(new GameField(startImageName,cssId, gameFieldName, colourName));
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

    private int getGameFieldNameId(String name) {
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
                System.out.println("gamefieldNameID: "+ gamefieldNameID);
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

    private int getColourId(String name) {
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
                System.out.println("colourID: "+ colourID);
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
       //findAll();
       GameField field = findById(1);
       List<GameField> fieldList1 = findByName("wormhole");
       List<GameField> fieldList2 = findByColour("red");
    }
}
