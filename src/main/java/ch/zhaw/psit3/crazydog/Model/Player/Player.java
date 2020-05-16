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
    private String color;
    private String username;
    private String email;
    private String password;

    /**
     * Constructor to create a new Player
     */
    public Player() {

    }

    /**
     * Constructor to create a new Player
     *
     * @param username username from the player
     * @param email    email from the player
     * @param password password from the player
     */
    public Player(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    /**
     * Constructor to create a new Player
     *
     * @param id       id from the player
     * @param username username from the player
     * @param email    email from the player
     */
    public Player(Integer id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    /**
     * Constructor to create a new Player
     *
     * @param id       id from the player
     * @param username username from the player
     * @param email    email from the player
     * @param password password from the player
     */
    public Player(Integer id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    /**
     * Get id from player
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id for player
     *
     * @param id Id who you wat to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get username from player
     *
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username for player
     *
     * @param username username who you wat to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get email from player
     *
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * set email, email must contain "@" and "."
     *
     * @param email email who you wat to set
     */
    public void setEmail(String email) {
        if (email.contains("@") && email.contains(".")) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Emailaddresse überprüfen");
        }
    }

    /**
     * Get password from player
     *
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password for player
     *
     * @param password password who you wat to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Set color, color must be green, yellow, red or blue
     *
     * @param color color who you wat to set
     */
    public void setColor(String color) {
        if (!(color.equals("green") || color.equals("yellow") || color.equals("red") || color.equals("blue"))) {
            throw new IllegalArgumentException("Please use a right color.");
        }
        this.color = color;
    }

    /**
     * Get color from player
     *
     * @return color
     */
    public String getColor() {
        return color;
    }
}
