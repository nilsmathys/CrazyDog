package ch.zhaw.psit3.crazydog.Model.Player;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;


public class Player {

    @NotNull
    private Integer id;

    @NotNull
    @Size(min=2, max=30)
    private String name;

    @NotNull
    @Size(min=6, max=50)
    private String email;

    @NotNull
    @Size(min=8, max=30)
    private String pw;

    @Min(0)
    private Integer gamesWon;

    @Min(0)
    private Integer gamesLost;

    @Min(0)
    private Integer gamesPlayed;

    public Player(String name, String email, String pw) {
        this.name = name;
        this.email = email;
        this.pw = pw;
    }

    public Player(Integer id, String name, String email, String pw, Integer gamesWon, Integer gamesLost, Integer gamesPlayed) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.pw = pw;
        this.gamesWon = gamesWon;
        this.gamesLost = gamesLost;
        this.gamesPlayed = gamesPlayed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }

    public int getGamesLost() {
        return gamesLost;
    }

    public void setGamesLost(int gamesLost) {
        this.gamesLost = gamesLost;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }
}
