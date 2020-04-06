package ch.zhaw.psit3.crazydog.Model.Game;
import ch.zhaw.psit3.crazydog.Model.Piece.FieldAndPiece;
import ch.zhaw.psit3.crazydog.Model.Player.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameState {
    private static List<Player> players;

    private static Map<String, String> fieldsAndPieces;

    // Store the relationship between a field and a piece
    public static void put(FieldAndPiece fap) {
        fieldsAndPieces.put(fap.getField(), fap.getPiece());
    }

    // Get piece with key "field"
    public static String get(String field) {
        return fieldsAndPieces.get(field);
    }

    public static Map<String, String> getAllFieldsAndPieces() {
        return fieldsAndPieces;
    }

    public static void putAllFieldsAndPieces(Map<String, String> AllFieldsAndPieces) {
        fieldsAndPieces = AllFieldsAndPieces;
    }

    public static void putPlayers(List<Player> listOfplayers) {
        players = listOfplayers;
    }

    public static List<Player> getPlayers() {
        return players;
    }
}
