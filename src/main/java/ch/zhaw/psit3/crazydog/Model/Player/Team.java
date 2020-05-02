package ch.zhaw.psit3.crazydog.Model.Player;


public class Team {

    private Player player1;
    private Player player2;
    private String colourPlayer1;
    private String colourPlayer2;

    public Team(Player player1, Player player2, String colourPlayer1, String colourPlayer2) {
        this.player1 = player1;
        this.player2 = player2;
        this.colourPlayer1 = colourPlayer1;
        this.colourPlayer2 = colourPlayer2;

    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public String getColourPlayer1() {
        return colourPlayer1;
    }

    public void setColourPlayer1(String colourPlayer1) {
        this.colourPlayer1 = colourPlayer1;
    }

    public String getColourPlayer2() {
        return colourPlayer2;
    }

    public void setColourPlayer2(String colourPlayer2) {
        this.colourPlayer2 = colourPlayer2;
    }
}
