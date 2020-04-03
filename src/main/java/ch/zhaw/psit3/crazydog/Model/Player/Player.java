package ch.zhaw.psit3.crazydog.Model.Player;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Player {

    private Integer id;

    @NotNull
    @Size(min = 2, max = 30)
    private String username;

    @NotNull
    @Size(min = 6, max = 50)
    private String email;

    @NotNull
    @Size(min = 8, max = 30)
    private String password;

    public Player() {

    }

    public Player(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Player(Integer id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public Player(Integer id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPw() {
        return password;
    }

    public void setPw(String password) {
        this.password = password;
    }
}
