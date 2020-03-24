package ch.zhaw.psit3.crazydog;

import java.sql.*;

public class GameFieldDAO {
    public void insert(GameField field) {

    }

    public void update(GameField field) {

    }

    public void delete(GameField field) {

    }

    public void findById(int id){


    }

    public static void findAll() {
        Connection con = null;
        try {
            con = DBConnectionFactory.getConnection();
            Statement st = con.createStatement();
            String query = "SELECT gamefieldID, startImageName,cssid , name, colourname" +
                    "  FROM dbo.Gamefields g JOIN dbo.Colour c ON g.colourID = c.colourID JOIN dbo.GamefieldName n ON n.gamefieldNameID = g.gamefieldNameID";
            ResultSet result = st.executeQuery(query);
            while (result.next()) {
                String startImageName = result.getString("startImageName"); // using column name
                String cssid = result.getString("cssid"); // using column name
                String name = result.getString("name"); // using column name
                String colourname = result.getString("colourname"); // using column name
                System.out.println("Name: "+ name + " startImageName: " + startImageName + " Color: " + colourname + " cssid: "+cssid  );
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

    public void findByName(String name) {

    }

    public static void main(String[] args) {
       findAll();
    }
}
