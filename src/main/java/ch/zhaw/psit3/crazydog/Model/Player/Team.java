package ch.zhaw.psit3.crazydog.Model.Player;

import java.util.List;

public class Team {

    private List<Player> team;

    public Team(Player player1, Player player2) {
        team.add(player1);
        team.add(player2);
    }

    public List<Player> getTeam() {
        return team;
    }

    public void getPlayerFromTeam() {
        for (Player player: team) {
            player.getId();
        }

    }
}
