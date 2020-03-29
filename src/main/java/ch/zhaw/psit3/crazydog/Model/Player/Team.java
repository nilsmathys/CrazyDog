package ch.zhaw.psit3.crazydog.Model.Player;


public class Team {

    private Player player1;
    private Player player2;
    private int colourIdPlayer1;
    private int colourIdPlayer2;

    public Team(Player player1, Player player2, int colourIdPlayer1, int colourIdPlayer2) {
        this.player1 = player1;
        this.player2 = player2;
        this.colourIdPlayer1 = colourIdPlayer1;
        this.colourIdPlayer2 = colourIdPlayer2;

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

    public int getColourIdPlayer1() {
        return colourIdPlayer1;
    }

    public void setColourIdPlayer1(int colourIdPlayer1) {
        this.colourIdPlayer1 = colourIdPlayer1;
    }

    public int getColourIdPlayer2() {
        return colourIdPlayer2;
    }

    public void setColourIdPlayer2(int colourIdPlayer2) {
        this.colourIdPlayer2 = colourIdPlayer2;
    }
}
