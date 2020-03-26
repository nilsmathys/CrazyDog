package ch.zhaw.psit3.crazydog.Model.Player;

import java.util.List;

public class Team {

    private List<Player> team;

    /**
     * Fügt zwei Spieler zu einem Team hinzu
     * @param player1 1. Spieler
     * @param player2 2. Spieler
     */
    public Team(Player player1, Player player2) {
        team.add(player1);
        team.add(player2);
    }

    /**
     * @return team
     */
    public List<Player> getTeam() {
        return team;
    }

    /**
     * Gibt die Spieler des Teams aus
     */
    public void getPlayerFromTeam() {
        for (Player player: team) {
            player.getId();
        }

    }
}
