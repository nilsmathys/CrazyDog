package ch.zhaw.psit3.crazydog.Model.Player;

import java.sql.SQLException;
import java.util.List;

public interface PlayerDAO {
    Player getPlayerById(Integer id);
    Player getPlayerByIdNeu(Integer id);
    List<Player> getAllPlayers();
    List<Player> getAllPlayersNeu();
    Player getPlayerByUsernameAndPw(String username, String pw);
    Player getPlayerByUsernameAndPwNeu(String username, String pw);
    boolean insertPlayer(Player player);
    boolean insertPlayerNeu(Player player);
    boolean updatePlayer(Player player, int id);
    boolean updatePlayerNeu(Player player, int id);
    boolean deletePlayer(Player player);
    boolean deletePlayerNeu(Player player);
}
