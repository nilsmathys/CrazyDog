package ch.zhaw.psit3.crazydog.Model.Player;

/**
 * <h1>Team</h1>
 * responsible for a Team with 2 Players that are playing together<br>
 *
 * @author N. Mathys
 * @version 1.0
 * @since April 2020
 */
public class Team {

    private Player player1;
    private Player player2;
    private String colourPlayer1;
    private String colourPlayer2;

    /**
     * Constructor to create a new team
     *
     * @param player1       first player from the team
     * @param player2       second player from the team
     * @param colourPlayer1 colour from the first player
     * @param colourPlayer2 colour from the second player
     */
    public Team(Player player1, Player player2, String colourPlayer1, String colourPlayer2) {
        this.player1 = player1;
        this.player2 = player2;
        this.colourPlayer1 = colourPlayer1;
        this.colourPlayer2 = colourPlayer2;

    }

    /**
     * Get the firs player from the team
     *
     * @return player1
     */
    public Player getPlayer1() {
        return player1;
    }

    /**
     * Sets player1 for team
     *
     * @param player1 player1 who you want to set
     */
    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    /**
     * Get the second player from the team
     *
     * @return player2
     */
    public Player getPlayer2() {
        return player2;
    }

    /**
     * Sets player2 for team
     *
     * @param player2 player2 who you want to set
     */
    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    /**
     * Get the colour from player1
     *
     * @return colourPlayer1
     */
    public String getColourPlayer1() {
        return colourPlayer1;
    }

    /**
     * Sets the colour for player1
     *
     * @param colourPlayer1 colourPlayer1 who you want to set
     */
    public void setColourPlayer1(String colourPlayer1) {
        this.colourPlayer1 = colourPlayer1;
    }

    /**
     * Get the colour from player2
     *
     * @return colourPlayer2
     */
    public String getColourPlayer2() {
        return colourPlayer2;
    }

    /**
     * Sets the colour for player2
     *
     * @param colourPlayer2 colourPlayer2 who you want to set
     */
    public void setColourPlayer2(String colourPlayer2) {
        this.colourPlayer2 = colourPlayer2;
    }
}
