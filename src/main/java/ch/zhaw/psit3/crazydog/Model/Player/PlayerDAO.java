package ch.zhaw.psit3.crazydog.Model.Player;

import java.sql.SQLException;
import java.util.List;

public interface PlayerDAO {
    Player getPlayerById(Integer id);

    List<Player> getAllPlayers();

    Player getPlayerByUsernameAndPw(String username, String pw);

    boolean insertPlayer(Player player);

    boolean updatePlayer(Player player, int id);

    boolean deletePlayer(Player player);
}
