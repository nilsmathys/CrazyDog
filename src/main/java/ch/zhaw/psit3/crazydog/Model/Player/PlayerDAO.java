package ch.zhaw.psit3.crazydog.Model.Player;

import java.util.List;

public interface PlayerDAO {
    Player getPlayerById();
    List<Player> getAllPlayers();
    Player getPlayerByUsernameAndPw();
    boolean insertUser();
}
