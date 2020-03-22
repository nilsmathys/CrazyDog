package ch.zhaw.psit3.crazydog.Model.Player;

public class Player {
    private int id;
    private String name;
    private String email;
    private String pw;
    private int gamesWon;
    private int gamesLost;
    private int gamesPlayed;

    public Player(String name, String email, String pw) {
        this.name = name;
        this.email = email;
        this.pw = pw;
    }

    public void savePlayer() {

    }
}
