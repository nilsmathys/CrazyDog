package ch.zhaw.psit3.crazydog.Model.Player;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * <h1>Player</h1>
 * Player holds necessary information about a Player<br>
 *
 * @author N. Mathys
 * @version 1.0
 * @since April 2020
 */
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

    /**
     * set email, email must contain "@" and "."
     *
     * @param email
     */
    public void setEmail(String email) {
        if (email.contains("@") && email.contains(".")) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Emailaddresse überprüfen");
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * set color, color must be green, yellow, red or blue
     *
     * @param color
     */
    public void setColor(String color) {
        if (!(color.equals("green") || color.equals("yellow") || color.equals("red") || color.equals("blue"))) {
            throw new IllegalArgumentException("Please use a right color.");
        }
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
