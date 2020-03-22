package ch.zhaw.psit3.crazydog.Model.Player;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Player {

    @NotNull
    private Integer id;

    @NotNull
    @Size(min = 2, max = 30)
    private String username;

    @NotNull
    @Size(min = 6, max = 50)
    private String email;

    @NotNull
    @Size(min = 8, max = 30)
    private String pw;

    public Player() {

    }

    public Player(String username, String email, String pw) {
        this.username = username;
        this.email = email;
        this.pw = pw;
    }

    public Player(Integer id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public Player(Integer id, String username, String email, String pw) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.pw = pw;
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
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }
}
