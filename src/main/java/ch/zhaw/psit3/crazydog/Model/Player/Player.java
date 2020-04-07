package ch.zhaw.psit3.crazydog.Model.Player;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Player {

    private Integer id;
    @NotNull
    private String color;

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
        if(email.contains("@") && email.contains(".")) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Emailaddresse überprüfen");
        }
        this.password = password;
    }

    public Player(Integer id, String username, String email) {
        this.id = id;
        this.username = username;
        if(email.contains("@") && email.contains(".")) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Emailaddresse überprüfen");
        }
    }

    public Player(Integer id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        if(email.contains("@") && email.contains(".")) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Emailaddresse überprüfen");
        }
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
        if(email.contains("@") && email.contains(".")) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Emailaddresse überprüfen");
        }
    }

    public String getPw() {
        return password;
    }

    public void setPw(String password) {
        this.password = password;
    }
}
