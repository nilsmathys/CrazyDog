package ch.zhaw.psit3.crazydog.Model.Game;

import ch.zhaw.psit3.crazydog.Model.Piece.FieldAndPiece;
import ch.zhaw.psit3.crazydog.Model.Player.Player;
import java.util.List;
import java.util.Map;

public class GameState {

    private static List<Player> players;
    private static Map<String, String> fieldsAndPieces;

    // Put one relationship between a field and a piece
    public static void put(FieldAndPiece fap) {
        fieldsAndPieces.put(fap.getField(), fap.getPiece());
    }

    // Get a piece via the key "field"
    public static String get(String field) {
        return fieldsAndPieces.get(field);
    }

    // Get all the relationships between Fields and Pieces
    public static Map<String, String> getAllFieldsAndPieces() {
        return fieldsAndPieces;
    }

    // Overwrite the map containing the relationships with a new map.
    public static void putAllFieldsAndPieces(Map<String, String> AllFieldsAndPieces) {
        fieldsAndPieces = AllFieldsAndPieces;
    }

    // Update the Location of a piece on a field
    //public static void updatePieceOnField(FieldAndPiece fap) {
    //    fieldsAndPieces.put(fap.getField(), fap.getPiece());
    //}

    // Overwrite the List with the players with a new List
    public static void putPlayers(List<Player> listOfplayers) {
        players = listOfplayers;
    }

    // Get all the Players
    public static List<Player> getPlayers() {
        return players;
    }
}
