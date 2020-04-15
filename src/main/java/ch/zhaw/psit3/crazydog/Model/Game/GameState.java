package ch.zhaw.psit3.crazydog.Model.Game;

import ch.zhaw.psit3.crazydog.Model.Card.CardsOnHand;
import ch.zhaw.psit3.crazydog.Model.Piece.FieldAndPiece;
import ch.zhaw.psit3.crazydog.Model.Player.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameState {

    private static List<Player> players = new ArrayList<>();
    private static Map<String, String> fieldsAndPieces = new HashMap<>();;
    private static Map<Integer, CardsOnHand> playerAndHand = new HashMap<>();


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

    // Overwrite the List with the players with a new List
    public static void putPlayers(List<Player> listOfplayers) {
        players = listOfplayers;
    }

    // Get all the Players
    public static List<Player> getPlayers() {
        return players;
    }

    // Set the map with objects of players with a hand
    public static void setAllPlayersAndHand(Map<Integer, CardsOnHand> AllPlayersAndHand) { playerAndHand = AllPlayersAndHand;}

    // Get every player with its hand
    public static Map<Integer, CardsOnHand> getAllPlayerAndHand() {return playerAndHand;}
}
