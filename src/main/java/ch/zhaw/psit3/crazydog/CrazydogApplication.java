package ch.zhaw.psit3.crazydog;

import ch.zhaw.psit3.crazydog.Model.Game.GameState;
import ch.zhaw.psit3.crazydog.Model.Player.Player;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class CrazydogApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrazydogApplication.class, args);

		// Initialize Players
		List<Player> players = new ArrayList<>();
		players.add(new Player(1, "Heidi", "heidi@test.com", "red"));
		players.add(new Player(2, "Johannes", "johannes@test.com", "yellow"));
		players.add(new Player(3, "Isabella", "isabella@test.com", "green"));
		players.add(new Player(4, "Peter", "peter@test.com", "blue"));
		GameState.putPlayers(players);

		// Initialize Fields
		Map<String, String> fieldsAndPieces = new HashMap<String, String>();
		fieldsAndPieces.put("field1", "empty.png");
		fieldsAndPieces.put("field2", "empty.png");
		fieldsAndPieces.put("field3", "empty.png");
		fieldsAndPieces.put("field4", "empty.png");
		fieldsAndPieces.put("field5", "empty.png");
		fieldsAndPieces.put("field6", "empty.png");
		fieldsAndPieces.put("field7", "empty.png");
		fieldsAndPieces.put("field8", "empty.png");
		fieldsAndPieces.put("field9", "empty.png");
		fieldsAndPieces.put("field10", "empty.png");
		fieldsAndPieces.put("field11", "empty.png");
		fieldsAndPieces.put("field12", "empty.png");
		fieldsAndPieces.put("field13", "empty.png");
		fieldsAndPieces.put("field14", "empty.png");
		fieldsAndPieces.put("field15", "empty.png");
		fieldsAndPieces.put("field16", "empty.png");
		fieldsAndPieces.put("field17", "empty.png");
		fieldsAndPieces.put("field18", "empty.png");
		fieldsAndPieces.put("field19", "empty.png");
		fieldsAndPieces.put("field20", "empty.png");
		fieldsAndPieces.put("field21", "empty.png");
		fieldsAndPieces.put("field22", "empty.png");
		fieldsAndPieces.put("field23", "empty.png");
		fieldsAndPieces.put("field24", "empty.png");
		fieldsAndPieces.put("field25", "empty.png");
		fieldsAndPieces.put("field26", "empty.png");
		fieldsAndPieces.put("field27", "empty.png");
		fieldsAndPieces.put("field28", "empty.png");
		fieldsAndPieces.put("field29", "empty.png");
		fieldsAndPieces.put("field30", "empty.png");
		fieldsAndPieces.put("field31", "empty.png");
		fieldsAndPieces.put("field32", "empty.png");
		fieldsAndPieces.put("field33", "empty.png");
		fieldsAndPieces.put("field34", "empty.png");
		fieldsAndPieces.put("field35", "empty.png");
		fieldsAndPieces.put("field36", "empty.png");
		fieldsAndPieces.put("field37", "empty.png");
		fieldsAndPieces.put("field38", "empty.png");
		fieldsAndPieces.put("field39", "empty.png");
		fieldsAndPieces.put("field40", "empty.png");
		fieldsAndPieces.put("field41", "empty.png");
		fieldsAndPieces.put("field42", "empty.png");
		fieldsAndPieces.put("field43", "empty.png");
		fieldsAndPieces.put("field44", "empty.png");
		fieldsAndPieces.put("field45", "empty.png");
		fieldsAndPieces.put("field46", "empty.png");
		fieldsAndPieces.put("field47", "empty.png");
		fieldsAndPieces.put("field48", "empty.png");
		fieldsAndPieces.put("field49", "empty.png");
		fieldsAndPieces.put("field50", "empty.png");
		fieldsAndPieces.put("field51", "empty.png");
		fieldsAndPieces.put("field52", "empty.png");
		fieldsAndPieces.put("field53", "empty.png");
		fieldsAndPieces.put("field54", "empty.png");
		fieldsAndPieces.put("field55", "empty.png");
		fieldsAndPieces.put("field56", "empty.png");
		fieldsAndPieces.put("field57", "empty.png");
		fieldsAndPieces.put("field58", "empty.png");
		fieldsAndPieces.put("field59", "empty.png");
		fieldsAndPieces.put("field60", "empty.png");
		fieldsAndPieces.put("field61", "empty.png");
		fieldsAndPieces.put("field62", "empty.png");
		fieldsAndPieces.put("field63", "empty.png");
		fieldsAndPieces.put("field64", "empty.png");
		fieldsAndPieces.put("field65", "piece4red.png");
		fieldsAndPieces.put("field66", "piece3red.png");
		fieldsAndPieces.put("field67", "piece2red.png");
		fieldsAndPieces.put("field68", "piece1red.png");
		fieldsAndPieces.put("field69", "piece4yellow.png");
		fieldsAndPieces.put("field70", "piece3yellow.png");
		fieldsAndPieces.put("field71", "piece2yellow.png");
		fieldsAndPieces.put("field72", "piece1yellow.png");
		fieldsAndPieces.put("field73", "piece4green.png");
		fieldsAndPieces.put("field74", "piece3green.png");
		fieldsAndPieces.put("field75", "piece2green.png");
		fieldsAndPieces.put("field76", "piece1green.png");
		fieldsAndPieces.put("field77", "piece4blue.png");
		fieldsAndPieces.put("field78", "piece3blue.png");
		fieldsAndPieces.put("field79", "piece2blue.png");
		fieldsAndPieces.put("field80", "piece1blue.png");
		fieldsAndPieces.put("field81", "empty.png");
		fieldsAndPieces.put("field82", "empty.png");
		fieldsAndPieces.put("field83", "empty.png");
		fieldsAndPieces.put("field84", "empty.png");
		fieldsAndPieces.put("field85", "empty.png");
		fieldsAndPieces.put("field86", "empty.png");
		fieldsAndPieces.put("field87", "empty.png");
		fieldsAndPieces.put("field88", "empty.png");
		fieldsAndPieces.put("field89", "empty.png");
		fieldsAndPieces.put("field90", "empty.png");
		fieldsAndPieces.put("field91", "empty.png");
		fieldsAndPieces.put("field92", "empty.png");
		fieldsAndPieces.put("field93", "empty.png");
		fieldsAndPieces.put("field94", "empty.png");
		fieldsAndPieces.put("field95", "empty.png");
		fieldsAndPieces.put("field96", "empty.png");

		GameState.putAllFieldsAndPieces(fieldsAndPieces);
	}

}
