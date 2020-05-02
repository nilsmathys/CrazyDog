package ch.zhaw.psit3.crazydog.Model.Card;

import ch.zhaw.psit3.crazydog.Model.Game.Round;
import ch.zhaw.psit3.crazydog.Model.Piece.Piece;
import ch.zhaw.psit3.crazydog.Model.Game.Values;
import ch.zhaw.psit3.crazydog.Model.Player.Player;
import ch.zhaw.psit3.crazydog.Model.Player.PlayerAndHand;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PlayCard {
    static int fieldsToGo;
    int colourIdPlayer;
    PlayerAndHand playerAndHand;
    int IdCardToPlay;
    static List<Integer> pieceDestination;
    static List<String> targetDestination = new ArrayList<>();
    List<Piece> selectetPieces;
    static int direction;
    Card selectetCardQuestion;
    int selectetAction;


    /**
     * @param pieceDest Position der zu spielenden Figur
     * @param cardName  Name der Karte
     * @param piece     Spielfigur die gespielt wird
     * @return true,    wenn das ein gültiger Spielzug ist, ansonsten false
     */
    public static boolean checkValidturn(int pieceDest, String cardName, Piece piece, int fieldsToGo) {
        Map<String, Integer> vals = Values.HOMEANDDESTFIELDS.get(piece.getColor());
        if (pieceDest >= vals.get("LowestHomefield") && pieceDest <= vals.get("HighestHomefield")) {
            return cardName.equals("thirteen") || cardName.equals("oneEleven");
        }
        if (pieceDest == vals.get("LowestDestinationField")) {
            return piece.getNumber() > 1 && fieldsToGo < 3;
        } else if (pieceDest == vals.get("LowestDestinationField") + 1) {
            return piece.getNumber() > 2 && fieldsToGo < 2;
        } else if (pieceDest == vals.get("LowestDestinationField") + 2) {
            return piece.getNumber() == 4 && fieldsToGo == 1;
        }
        return true;
    }

    /**
     * @param pieceDestination aktueler Standort der gewählten Figur
     * @param fieldsToGo       Anzahl der Felder die zu gehen sind
     * @param piece            Figur die bewegt werden soll (braucht es wegen der Farbid)
     * @return newDest         die neue Spielfeldnummer der Figur
     */
    private static int calculateNewDestination(int pieceDestination, int fieldsToGo, Piece piece) {
        int newDest;
        Map<String, Integer> vals = Values.STARTANDDESTFIELDS.get(piece.getColor());
        if (direction == 0) {
            newDest = (pieceDestination + fieldsToGo) % 64;
            if (piece.getColor().equals("green")) {
                if (pieceDestination > 50 && newDest < Values.STARTFIELDRED + 5) {
                    if (newDest == Values.STARTFIELDRED + 1) {
                        newDest = Values.LOWESTDESTINATIONFIELDGREEN;
                    }
                    if (piece.getNumber() < 4) {
                        if (newDest == Values.STARTFIELDRED + 2) {
                            newDest = Values.LOWESTDESTINATIONFIELDGREEN + 1;
                        }
                        if (piece.getNumber() < 3) {
                            if (newDest == Values.STARTFIELDRED + 3) {
                                newDest = Values.LOWESTDESTINATIONFIELDGREEN + 2;
                            }
                            if (piece.getNumber() == 1 && newDest == Values.STARTFIELDRED + 4) {
                                newDest = Values.LOWESTDESTINATIONFIELDGREEN + 3;
                            }
                        }
                    }
                }
            } else if (pieceDestination <= vals.get("Startfield") && newDest > vals.get("Startfield") && newDest < vals.get("MaxWithToGoClockwise")) {
                if (newDest == vals.get("Startfield") + 1) {
                    newDest = vals.get("DestField");
                }
                if (piece.getNumber() < 4) {
                    if (newDest == vals.get("Startfield") + 2) {
                        newDest = vals.get("DestField") + 1;
                    }
                    if (piece.getNumber() < 3) {
                        if (newDest == vals.get("Startfield") + 3) {
                            newDest = vals.get("DestField") + 2;
                        }
                        if (piece.getNumber() == 1 && newDest == vals.get("Startfield") + 4) {
                            newDest = vals.get("DestField") + 3;
                        }
                    }
                }
            }

        } else {
            if (0 > pieceDestination - fieldsToGo) {
                newDest = 64 - Math.abs(pieceDestination - fieldsToGo);
            } else {
                newDest = pieceDestination - fieldsToGo;
            }
            if (piece.getColor().equals("green")) {
                if (pieceDestination < 15 && newDest > 60) { //
                    if (piece.getNumber() < 5) {
                        if (newDest == 64) {
                            newDest = Values.LOWESTDESTINATIONFIELDGREEN; //Zielfeld für Piece4 von Green
                        }
                        if (piece.getNumber() < 4) {
                            if (newDest == 63) {
                                newDest = Values.LOWESTDESTINATIONFIELDGREEN + 1; //Zielfeld für Piece3 von Green
                            }
                            if (piece.getNumber() < 3) {
                                if (newDest == 62) {
                                    newDest = Values.LOWESTDESTINATIONFIELDGREEN + 2; //Zielfeld für Piece2 von Green
                                }
                                if (piece.getNumber() == 1 && newDest == 61) {
                                    newDest = Values.LOWESTDESTINATIONFIELDGREEN + 3; //Zielfeld für Piece1 von Green
                                }
                            }
                        }
                    }
                }
            } else if (pieceDestination >= vals.get("Startfield") && newDest < vals.get("Startfield") && newDest > vals.get("MaxWithToGoCounterClockwise")) {
                if (piece.getNumber() >= 5) {
                    return newDest;
                }
                if (newDest == vals.get("Startfield") - 1) {
                    newDest = vals.get("DestField");
                }
                if (piece.getNumber() < 4) {
                    if (newDest == vals.get("Startfield") - 2) {
                        newDest = vals.get("DestField") + 1;
                    }
                    if (piece.getNumber() < 3) {
                        if (newDest == vals.get("Startfield") - 3) {
                            newDest = vals.get("DestField") + 2;
                        }
                        if (piece.getNumber() == 1 && newDest == vals.get("Startfield") - 4) {
                            newDest = vals.get("DestField") + 3;
                        }
                    }
                }
            }
        }
        return newDest;
    }

    /**
     * @param colourPlayer     Farbe des Spielers
     * @param playerAndHand    Spieler und seine Hand
     * @param idCardToPlay     id der Karte die der Spieler spielen will
     * @param selectetPiece    Figur die der Spieler fahren möchte
     * @param pieceDestination aktueller Standort der Figur die der Spieler gewählt hat
     * @param dir              aktuelle Spielrichtung, 0 = Uhzeigersinn, 1 = Gegenuhrzeigersinn.
     */
    public static void playNormalCard(String colourPlayer, String colourPartner, PlayerAndHand playerAndHand, int idCardToPlay, Piece selectetPiece, String pieceDestination, int dir, boolean ownPiecesFinished) {
        direction = dir;
        fieldsToGo = 0;
        if (!targetDestination.isEmpty()) {
            targetDestination.clear();
        }
        String[] splitted = pieceDestination.split("field");
        int pieceDestinationInt = Integer.parseInt(splitted[1]);
        Card cardToPlay = playerAndHand.getHand().discardCard(idCardToPlay);
        if (ownPiecesFinished && colourPartner != selectetPiece.getColor()) {
            throw new IllegalArgumentException("ungültige Figur ausgewählt: Figur des Partners auswählen");
        } else if (!ownPiecesFinished && colourPlayer != selectetPiece.getColor()) {
            throw new IllegalArgumentException("ungültige Figur ausgewählt: eigene FIgur auswählen");
        }
        if (checkValidturn(pieceDestinationInt, cardToPlay.getName(), selectetPiece, cardToPlay.getValue())) {
            fieldsToGo = cardToPlay.getValue();
        } else {
            fieldsToGo = 0;
        }
        int targetDestinationInt = Round.calcDestWhenPieceOnWomrhole(calculateNewDestination(pieceDestinationInt, fieldsToGo, selectetPiece));
        String targetDestinationString = "field" + targetDestinationInt;
        targetDestination.add(targetDestinationString);
        //ToDo: neuer Standort und neue Hand zurückgeben.(Piece, pieceDest)
    }


    /**
     * @param colourPlayer         Farbe des Spielers
     * @param colourPartner        Farbe des Partners
     * @param playerAndHand        Spieler und Hand
     * @param idCardToPlay         id der Karte die der Spieler spielen möchte.
     * @param pieceDestinations    Array mit den aktuellen Standorten der Figuren, 0=Figur1 etc.
     * @param fieldToGo            gewünschte Felder zu fahren 0 = 1. gewählte Figur etc.
     * @param selectetPieces       List mit den Figuren die der Spieler gewählt hat. (Mehr als eine Figur wird nur bei den Karten 7 und PieceExchange benötigt)
     * @param dir                  aktuelle Spielrichtung: 0= Uhrezeigersinn, 1 = Gegenuhrzeigersinn
     * @param selectetCardQuestion Falls der Spieler die Questioncard hat, ist dies die Karte die der Spieler spielen möchte.
     * @param selectetAction       Falls der Spieler eine Spezialkarte spielen möchte die mehr als eine Aktion hat,
     *                             Karte 3: 0=3 Felder fahren, 1=Richtungswechsel;
     *                             Karte oneEleven: 0=1 Feld fahren, 1=11 Felder fahren;
     * @param ownPiecesFinished    true = eigene Figuren sind im Ziel, False eigene Figuren sind noch nicht im Ziel
     */
    public static void playSpecialCard(String colourPlayer, String colourPartner, PlayerAndHand playerAndHand, int idCardToPlay, List<String> pieceDestinations,
                                       List<Integer> fieldToGo, List<Piece> selectetPieces,
                                       int dir, Card selectetCardQuestion, int selectetAction, boolean ownPiecesFinished) {
        Card cardToPlay = playerAndHand.getHand().discardCard(idCardToPlay);
        if (cardToPlay.getName() != "questionmark" && cardToPlay.getName() != "pieceExchange") {
            for (int i = 0; i < selectetPieces.size(); i++) {
                if (ownPiecesFinished) {
                    if (colourPartner != selectetPieces.get(i).getColor()) {
                        throw new IllegalArgumentException("Figur gehört nicht dem Partner");
                    }
                } else if (colourPlayer != selectetPieces.get(i).getColor()) {
                    throw new IllegalArgumentException("Keine eigene Figur");
                }
            }
        }
        if (!targetDestination.isEmpty()) {
            targetDestination.clear();
        }
        direction = dir;
        fieldsToGo = 0;
        switch (cardToPlay.getValue()) {
            case 3:
                //ToDo: 3 online umsetzen (Figur auswälen oder Richtung ändern)
                direction = playCard3(pieceDestinations.get(0), selectetPieces.get(0), selectetAction);
                break;
            case 4:
                //ToDo: frontend umsetzen
                playCard4(selectetAction, pieceDestinations.get(0), selectetPieces.get(0));
                break;
            case 7:
                playCard7(pieceDestinations, fieldToGo, selectetPieces);
                //ToDo: 7 frontend umsetzen(mehrere Figuren anwählen)
                break;
            case 11:
                playCardOneEleven(selectetAction, selectetPieces.get(0), pieceDestinations.get(0), direction);
                break;

            case 13:
                playCard13(selectetPieces.get(0), pieceDestinations.get(0), direction);
                //ToDO: Card13 frontend umsetzen (falls figur auf homefiled ausgewählt wird keine Aktion)
                break;
            case 14:
                playQuestionCard(colourPlayer, colourPartner, playerAndHand, pieceDestinations, fieldToGo, selectetPieces, selectetCardQuestion, selectetAction, ownPiecesFinished);
                break;
            case 15:
                targetDestination = pieceExchange(pieceDestinations);
                break;
            default:
                throw new IllegalArgumentException("Hoppla");

        }
        //ToDo: neue Hand, neuer Standort und Dircetion zurückgeben(List Pieces, targertdest, direction)
    }


    /**
     * @param pieceDestination aktueller Standort der Figur
     * @param piece            Figur die bewegt werden soll
     * @param selectetAction   ausgeählte Aktion, 0 = 3 Felder gehen, 1 = Richtungswechsel
     * @return gibt die neue Spielrichtung zurück
     */
    private static int playCard3(String pieceDestination, Piece piece, int selectetAction) {
        String[] splitted = pieceDestination.split("field");
        int pieceDestinationInt = Integer.parseInt(splitted[1]);
        if (selectetAction == 0) {
            if (checkValidturn(pieceDestinationInt, "standard", piece, 3)) {
                fieldsToGo = Values.CARDVALUE3;
            } else {
                playCard3(pieceDestination, piece, 1);
            }
            int targetDestinationInt = Round.calcDestWhenPieceOnWomrhole(calculateNewDestination(pieceDestinationInt, fieldsToGo, piece));
            String targetDestinationString = "field" + targetDestinationInt;
            targetDestination.add(targetDestinationString);
            return direction;
        } else if (selectetAction == 1) {
            if (direction == 0) {
                direction = 1;
            } else {
                direction = 0;
            }
        } else {
            throw new IllegalArgumentException();
        }
        return direction;
    }

    /**
     * @param selectetAction ausgewählte Aktion: 0 = fahren in aktuelle Spielrichtung, 1 = fahren in andere Richtung
     * @param pieceDestination Standort der Figur die bewegt werden soll
     * @param piece            Figur die bewegt werden soll
     */
    private static void playCard4(int selectetAction, String pieceDestination, Piece piece) {
        String[] splitted = pieceDestination.split("field");
        int pieceDestinationInt = Integer.parseInt(splitted[1]);
        if(selectetAction == 0) {
            if (checkValidturn(pieceDestinationInt, "four", piece, Values.CARDVALUE4)) {
                fieldsToGo = Values.CARDVALUE4;
            }
            int targetDestinationInt = Round.calcDestWhenPieceOnWomrhole(calculateNewDestination(pieceDestinationInt, fieldsToGo, piece));
            String targetDestinationString = "field" + targetDestinationInt;
            targetDestination.add(targetDestinationString);
        } else {
            if (direction == 0) {
                direction = 1;
                if (checkValidturn(pieceDestinationInt, "four", piece, Values.CARDVALUE4)) {
                    fieldsToGo = Values.CARDVALUE4;
                    int targetDestinationInt = Round.calcDestWhenPieceOnWomrhole(calculateNewDestination(pieceDestinationInt, fieldsToGo, piece));
                    String targetDestinationString = "field" + targetDestinationInt;
                    targetDestination.add(targetDestinationString);
                }
                direction = 0;
            } else {
                direction = 0;
                if (checkValidturn(pieceDestinationInt, "four", piece, Values.CARDVALUE4)) {
                    fieldsToGo = Values.CARDVALUE4;
                    int targetDestinationInt = Round.calcDestWhenPieceOnWomrhole(calculateNewDestination(pieceDestinationInt, fieldsToGo, piece));
                    String targetDestinationString = "field" + targetDestinationInt;
                    targetDestination.add(targetDestinationString);
                }
                direction = 1;
            }
        }
    }

    /**
     * @param pieceDestinations Standorte der Figuren die bewegt werden soll: 0 = erste ausgewählte Figur
     * @param fieldToGo         Anzahl Felder die gefahren werden sollen: 0 = erste ausgewählte Figur etc.
     * @param selectetPieces    ausgewählte Figuren: 0 = erste ausgewählte Figur
     */
    public static void playCard7(List<String> pieceDestinations, List<Integer> fieldToGo, List<Piece> selectetPieces) {
        int counter = 0;

        if (pieceDestinations.size() != fieldToGo.size() || pieceDestinations.size() != selectetPieces.size()) {
            throw new IllegalArgumentException("Inputgrössen weichen voneinander ab");
        }
        for (int i = 0; i < fieldToGo.size(); i++) {
            counter += fieldToGo.get(i);
        }
        if (counter != Values.CARDVALUE7) {
            fieldsToGo = 0;
        }
        for (int i = 0; i < pieceDestinations.size(); i++) {
            String[] splitted = pieceDestinations.get(i).split("field");
            int pieceDestinationInt = Integer.parseInt(splitted[1]);
            if (checkValidturn(pieceDestinationInt, "seven", selectetPieces.get(i), fieldToGo.get(i))) {
                int targetDestinationInt = Round.calcDestWhenPieceOnWomrhole(calculateNewDestination(pieceDestinationInt, fieldToGo.get(i), selectetPieces.get(i)));
                String targetDestinationString = "field" + targetDestinationInt;
                targetDestination.add(targetDestinationString);
            }
        }
        //ToDo: Felder können einzeln gefahren werden, überprüfen ob gültiger Zug

    }

    /**
     * @param piece            Figur die auf Startfeld bewegt werden soll
     * @param pieceDestination aktueller Standort der Figur
     * @param cardname         Name der Karte, muss oneEleven oder thirteen sein
     */
    private static void movePieceOnStartField(Piece piece, int pieceDestination, String cardname) {
        if (checkValidturn(pieceDestination, cardname, piece, fieldsToGo)) {
            if (piece.getColor().equals("red")) {
                if (pieceDestination >= Values.LOWESTHOMEFIELDRED && pieceDestination <= Values.HIGHESTHOMEFIELDRED) {
                    targetDestination.add("field" + Values.STARTFIELDRED);
                }
            } else if (piece.getColor().equals("green")) {
                if (pieceDestination >= Values.LOWESTHOMEFIELDGREEN && pieceDestination <= Values.HIGHESTHOMEFIELDGREEN) {
                    targetDestination.add("field" + Values.STARTFIELDGREEN);
                }

            } else if (piece.getColor().equals("yellow")) {
                if (pieceDestination >= Values.LOWESTHOMEFIELDYELLOW && pieceDestination <= Values.HIGHESTHOMEFIELDYELLOW) {
                    targetDestination.add("field" + Values.STARTFIELDYELLOW);
                }

            } else if (piece.getColor().equals("blue")) {
                if (pieceDestination >= Values.LOWESTHOMEFIELDBLUE && pieceDestination <= Values.HIGHESTHOMEFIELDBLUE) {
                    targetDestination.add("field" + Values.STARTFIELDBLUE);
                }
            }
        }
    }

    /**
     * @param selectetAction   ausgewählte Aktion: 0 = 1 Feld fahren, 1 = 11 Felder fahren
     * @param piece            Figur die bewegt werden soll
     * @param pieceDestination aktueller Standort der gewählten Figur
     */
    private static void playCardOneEleven(int selectetAction, Piece piece, String pieceDestination, int direction) {
        String[] splitted = pieceDestination.split("field");
        int pieceDestinationInt = Integer.parseInt(splitted[1]);
        movePieceOnStartField(piece, pieceDestinationInt, "oneEleven");
        if (targetDestination.isEmpty()) {
            if (selectetAction == 0) {
                if (checkValidturn(pieceDestinationInt, null, piece, 1)) {
                    fieldsToGo = 1;
                } else {
                    fieldsToGo = 0;
                }
                if (!targetDestination.isEmpty()) {
                    targetDestination.clear();
                }
                int targetDestinationInt = Round.calcDestWhenPieceOnWomrhole(calculateNewDestination(pieceDestinationInt, fieldsToGo, piece));
                String targetDestinationString = "field" + targetDestinationInt;
                targetDestination.add(targetDestinationString);
            } else if (selectetAction == 1) {
                if (checkValidturn(pieceDestinationInt, null, piece, 11)) {
                    fieldsToGo = 11;
                } else {
                    playCardOneEleven(0, piece, pieceDestination, direction);
                }
                if (!targetDestination.isEmpty()) {
                    targetDestination.clear();
                }
                int targetDestinationInt = Round.calcDestWhenPieceOnWomrhole(calculateNewDestination(pieceDestinationInt, fieldsToGo, piece));
                String targetDestinationString = "field" + targetDestinationInt;
                targetDestination.add(targetDestinationString);
            } else {
                throw new IllegalArgumentException();
            }
        }


    }


    /**
     * @param piece            Figur die bewegt werden soll
     * @param pieceDestination aktueller Standort der gewählten Figur
     */
    private static void playCard13(Piece piece, String pieceDestination, int direction) {
        String[] splitted = pieceDestination.split("field");
        int pieceDestinationInt = Integer.parseInt(splitted[1]);
        movePieceOnStartField(piece, pieceDestinationInt, "thirteen");
        if (targetDestination.isEmpty()) {
            if (checkValidturn(pieceDestinationInt, null, piece, 13) && targetDestination.isEmpty()) {
                fieldsToGo = 13;
            } else {
                fieldsToGo = 0;
            }
            int targetDestinationInt = Round.calcDestWhenPieceOnWomrhole(calculateNewDestination(pieceDestinationInt, fieldsToGo, piece));
            String targetDestinationString = "field" + targetDestinationInt;
            targetDestination.add(targetDestinationString);
        }
    }

    /**
     * @param pieceDestination Standort der Figuren die getauscht werdens sollen
     * @return targetDestination, neuer Standort der Figuren
     */
    private static List<String> pieceExchange(List<String> pieceDestination) {
        String store = pieceDestination.get(0);
        pieceDestination.set(0, pieceDestination.get(1));
        pieceDestination.set(1, store);
        return pieceDestination;
    }

    /**
     * @param colourPlayer         Farbe des Spielers
     * @param playerAndHand        Spieler und Hand
     * @param pieceDestinations    Array mit den aktuellen Standorten der Figuren, 0=Figur1 etc.
     * @param fieldToGo            gewünschte Anzahl Felder zu fahren: 0 = 1. Figur etc.
     * @param selectetPieces       List mit den Figuren die der Spieler gewählt hat. (Mehr als eine Figur wird nur bei den Karten 7 und PieceExchange benötigt)
     * @param selectetCardQuestion Karte die der Spieleler spielen möchte
     * @param selectetAction       Falls der Spieler eine Spezialkarte spielen möchte die mehr als eine Aktion hat,
     *                             Karte 3: 0=3 Felder fahren, 1=Richtungswechsel;
     *                             Karte oneEleven: 0=1 Feld fahren, 1=11 Felder fahren, 2=Figur auf Start;
     *                             Karte13: 0=13 Felder fahren, 1=Figur auf Start
     */
    private static void playQuestionCard(String colourPlayer, String colourPartner, PlayerAndHand playerAndHand, List<String> pieceDestinations,
                                         List<Integer> fieldToGo, List<Piece> selectetPieces, Card selectetCardQuestion, int selectetAction, boolean ownPiecesFinished) {
        playerAndHand.getHand().takeCard(selectetCardQuestion);
        if (selectetCardQuestion.getId() == Values.CARDVALUE2 || selectetCardQuestion.getId() == Values.CARDVALUE5 || selectetCardQuestion.getId() == Values.CARDVALUE6 || selectetCardQuestion.getId() == Values.CARDVALUE8 || selectetCardQuestion.getId() == Values.CARDVALUE9 || selectetCardQuestion.getId() == Values.CARDVALUE10 || selectetCardQuestion.getId() == Values.CARDVALUE12) {
            playNormalCard(colourPlayer, colourPartner, playerAndHand, selectetCardQuestion.getId(), selectetPieces.get(0), pieceDestinations.get(0), direction, ownPiecesFinished);
        } else {
            playSpecialCard(colourPlayer, colourPartner, playerAndHand, selectetCardQuestion.getId(), pieceDestinations, fieldToGo, selectetPieces, direction, selectetCardQuestion, selectetAction, ownPiecesFinished);
        }

    }

    /**
     * nur zum testen
     *
     * @param args
     */
    public static void main(String[] args) {
        Piece pieceRed = new Piece(1, 2, "red", "picname"); //red
        Piece pieceGreen = new Piece(2, 2, "green", "picname"); //green
        Piece pieceYellow = new Piece(3, 2, "yellow", "picname"); //yellow
        Piece pieceBlue = new Piece(4, 2, "blue", "picname"); //green
        Player player = new Player(1, "Ted", "test@test.ch");
        Card card2 = new Card(2, "standard", 2);
        Card card3 = new Card(3, "changeDirection", 3);
        Card card4 = new Card(4, "four", 4);
        Card card5 = new Card(5, "standard", 5);
        Card card6 = new Card(6, "standard", 6);
        Card card7 = new Card(7, "seven", 7);
        Card card8 = new Card(8, "standard", 8);
        Card card9 = new Card(9, "standard", 9);
        Card card10 = new Card(10, "standard", 10);
        Card card11 = new Card(11, "oneEleven", 11);
        Card card12 = new Card(12, "standard", 12);
        Card card13 = new Card(13, "thirteen", 13);
        Card card14 = new Card(14, "questionmark", 14);
        Card card15 = new Card(15, "pieceExchange", 15);
        CardsOnHand cardsOnHand = new CardsOnHand();
        List<String> pieceDestinations = new ArrayList<>();
        pieceDestinations.add("field10");
        List<String> pieceDestinations11 = new ArrayList<>();
        pieceDestinations11.add("field65");
        List<String> pieceDestinations14 = new ArrayList<>();
        pieceDestinations14.add("field73");
        List<String> pieceDestinations15 = new ArrayList<>();
        pieceDestinations15.add("field10");
        pieceDestinations15.add("field20");
        List<String> pieceDestinations7 = new ArrayList<>();
        pieceDestinations7.add("field10");
        pieceDestinations7.add("field33");
        pieceDestinations7.add("field12");
        pieceDestinations7.add("field64");
        List<String> pieceDestinations72 = new ArrayList<>();
        pieceDestinations72.add("field34");
        pieceDestinations72.add("field33");
        pieceDestinations72.add("field64");
        List<Piece> selectetPieces = new ArrayList<>();
        selectetPieces.add(pieceRed);
        List<Piece> selectetPieces14 = new ArrayList<>();
        selectetPieces14.add(pieceGreen);
        List<Piece> selectetPieces15 = new ArrayList<>();
        selectetPieces15.add(pieceRed);
        List<Piece> selectetPieces7 = new ArrayList<>();
        selectetPieces7.add(pieceRed);
        selectetPieces7.add(pieceRed);
        selectetPieces7.add(pieceRed);
        selectetPieces7.add(pieceRed);
        List<Piece> selectetPieces72 = new ArrayList<>();
        selectetPieces72.add(pieceRed);
        selectetPieces72.add(pieceRed);
        selectetPieces72.add(pieceRed);
        List<Integer> fieldToGo7 = new ArrayList<>();
        fieldToGo7.add(1);
        fieldToGo7.add(2);
        fieldToGo7.add(1);
        fieldToGo7.add(3);
        List<Integer> fieldToGo72 = new ArrayList<>();
        fieldToGo72.add(2);
        fieldToGo72.add(3);
        fieldToGo72.add(2);
        cardsOnHand.takeCard(card2); //
        cardsOnHand.takeCard(card2); //
        cardsOnHand.takeCard(card3); //
        cardsOnHand.takeCard(card3); //
        cardsOnHand.takeCard(card3); //
        cardsOnHand.takeCard(card4); //
        cardsOnHand.takeCard(card4); //
        cardsOnHand.takeCard(card4); //
        cardsOnHand.takeCard(card5); //
        cardsOnHand.takeCard(card5); //
        cardsOnHand.takeCard(card6); //
        cardsOnHand.takeCard(card7);
        cardsOnHand.takeCard(card7);
        cardsOnHand.takeCard(card7);
        cardsOnHand.takeCard(card8); //
        cardsOnHand.takeCard(card9); //
        cardsOnHand.takeCard(card10); //
        cardsOnHand.takeCard(card11); //
        cardsOnHand.takeCard(card11); //
        cardsOnHand.takeCard(card11); //
        cardsOnHand.takeCard(card12); //
        cardsOnHand.takeCard(card13); //
        cardsOnHand.takeCard(card13); //
        cardsOnHand.takeCard(card14); //
        cardsOnHand.takeCard(card14); //
        cardsOnHand.takeCard(card14); //
        cardsOnHand.takeCard(card15); //


        PlayerAndHand playerAndHand = new PlayerAndHand(player, cardsOnHand);
        System.out.println("Handsize should be 27: " + playerAndHand.getHand().getHand().size());
        System.out.println();
        System.out.println("Play card 2");
        playNormalCard("red", "green", playerAndHand, 2, pieceRed, "field10", 0, false);
        System.out.println("Should be 12: " + targetDestination.get(0));
        System.out.println("Handsize should be 26: " + playerAndHand.getHand().getHand().size());
        System.out.println();
        System.out.println("Play card 2");
        playNormalCard("red", "green", playerAndHand, 2, pieceRed, "field10", 0, false);
        System.out.println("Should be 12: " + targetDestination.get(0));
        System.out.println("Handsize should be 25: " + playerAndHand.getHand().getHand().size());
        System.out.println();
        System.out.println("Play card Questionmark with Card 2");
        playSpecialCard("red", "green", playerAndHand, 14, pieceDestinations, null, selectetPieces, 0, card2, 0, false);
        System.out.println("Should be 12: " + targetDestination.get(0));
        System.out.println("Handsize should be 24: " + playerAndHand.getHand().getHand().size());
        System.out.println();
        System.out.println("Play card 11 Aktion 1 fahren");
        playSpecialCard("red", "green", playerAndHand, 11, pieceDestinations, null, selectetPieces, 0, card2, 0, false);
        System.out.println("Should be 11: " + targetDestination.get(0));
        System.out.println("Handsize should be 23: " + playerAndHand.getHand().getHand().size());
        System.out.println();
        System.out.println("Play card 11 Aktion 11 fahren");
        playSpecialCard("red", "green", playerAndHand, 11, pieceDestinations, null, selectetPieces, 0, card2, 1, false);
        System.out.println("Should be 21: " + targetDestination.get(0));
        System.out.println("Handsize should be 22: " + playerAndHand.getHand().getHand().size());
        System.out.println();
        System.out.println("Play card 11 Aktion auf Homefield fahren");
        playSpecialCard("red", "green", playerAndHand, 11, pieceDestinations11, null, selectetPieces, 0, card2, 1, false);
        System.out.println("Should be 1: " + targetDestination.get(0));
        System.out.println("Handsize should be 21: " + playerAndHand.getHand().getHand().size());
        System.out.println();
        System.out.println("Play card 13 Aktion 13 fahren");
        playSpecialCard("red", "green", playerAndHand, 13, pieceDestinations, null, selectetPieces, 0, card2, 1, false);
        System.out.println("Should be 23: " + targetDestination.get(0));
        System.out.println("Handsize should be 20: " + playerAndHand.getHand().getHand().size());
        System.out.println();
        System.out.println("Play card 13 Aktion auf Homefield fahren");
        playSpecialCard("red", "green", playerAndHand, 13, pieceDestinations11, null, selectetPieces, 0, card2, 1, false);
        System.out.println("Should be 1: " + targetDestination.get(0));
        System.out.println("Handsize should be 19: " + playerAndHand.getHand().getHand().size());
        System.out.println();
        System.out.println("Play card 5");
        playNormalCard("red", "green", playerAndHand, 5, pieceRed, "field10", 0, false);
        System.out.println("Should be 15: " + targetDestination.get(0));
        System.out.println("Handsize should be 18: " + playerAndHand.getHand().getHand().size());
        System.out.println();
        System.out.println("Play card 5 Counterclockwise");
        playNormalCard("red", "green", playerAndHand, 5, pieceRed, "field10", 1, false);
        System.out.println("Should be 5: " + targetDestination.get(0));
        System.out.println("Handsize should be 17: " + playerAndHand.getHand().getHand().size());
        System.out.println();
        System.out.println("Play card 6 über Grenze hinaus");
        playNormalCard("red", "green", playerAndHand, 6, pieceRed, "field62", 0, false);
        System.out.println("Should be 4: " + targetDestination.get(0));
        System.out.println("Handsize should be 16: " + playerAndHand.getHand().getHand().size());
        System.out.println();
        System.out.println("Play card 8 über Grenze hinaus Counterclockwise");
        playNormalCard("red", "green", playerAndHand, 8, pieceRed, "field2", 1, false);
        System.out.println("Should be 58: " + targetDestination.get(0));
        System.out.println("Handsize should be 15: " + playerAndHand.getHand().getHand().size());
        System.out.println();
        System.out.println("Play card 9 eigeneFiguren fertig");
        playNormalCard("red", "green", playerAndHand, 9, pieceGreen, "field2", 0, true);
        System.out.println("Should be 11: " + targetDestination.get(0));
        System.out.println("Handsize should be 14: " + playerAndHand.getHand().getHand().size());
        System.out.println();
        System.out.println("Play card 10 nach Hause");
        playNormalCard("red", "green", playerAndHand, 10, pieceRed, "field26", 0, false);
        System.out.println("Should be 91: " + targetDestination.get(0));
        System.out.println("Handsize should be 13 " + playerAndHand.getHand().getHand().size());
        System.out.println();
        System.out.println("Play card 12 eigene Figuren fertig, Partner nach Hause Counterclockwise");
        playNormalCard("red", "green", playerAndHand, 12, pieceGreen, "field11", 1, true);
        System.out.println("Should be 82: " + targetDestination.get(0));
        System.out.println("Handsize should be 12: " + playerAndHand.getHand().getHand().size());
        System.out.println();
        System.out.println("Play card 3 Aktion 3 Felder fahren");
        playSpecialCard("red", "green", playerAndHand, 3, pieceDestinations, null, selectetPieces, 0, card2, 0, false);
        System.out.println("should be 13: " + targetDestination.get(0));
        System.out.println("Handsize should be 11: " + playerAndHand.getHand().getHand().size());
        System.out.println();
        System.out.println("Play card 3 Aktion dir ändern");
        System.out.println("Richtung sollte 0 sein: " + direction);
        playSpecialCard("red", "green", playerAndHand, 3, pieceDestinations, null, selectetPieces, 0, card2, 1, false);
        System.out.println("Richtung sollte 1 sein: " + direction);
        System.out.println("Handsize should be 10: " + playerAndHand.getHand().getHand().size());
        System.out.println();
        System.out.println("Play card 3 Aktion 0 gewählt aber Richtung ändern, weil ungültiger Zug");
        System.out.println("Richtung sollte 1 sein: " + direction);
        playSpecialCard("red", "green", playerAndHand, 3, pieceDestinations11, null, selectetPieces, 1, card2, 0, false);
        System.out.println("Richtung sollte 0 sein: " + direction);
        System.out.println("Handsize should be 9: " + playerAndHand.getHand().getHand().size());
        System.out.println();
        System.out.println("Play Card PieceExchange");
        System.out.println("pieceDest0 sollte 10 sein: " + pieceDestinations15.get(0));
        System.out.println("pieceDest1 sollte 20 sein: " + pieceDestinations15.get(1));
        playSpecialCard("red", "green", playerAndHand, 15, pieceDestinations15, null, selectetPieces15, 1, card2, 0, false);
        System.out.println("pieceDest0 sollte 20 sein: " + pieceDestinations15.get(0));
        System.out.println("pieceDest1 sollte 10 sein: " + pieceDestinations15.get(1));
        System.out.println("Handsize should be 8 " + playerAndHand.getHand().getHand().size());
        System.out.println();
        System.out.println("Play card Questionmark wit Card 13 and go to startfield");
        playSpecialCard("red", "green", playerAndHand, 14, pieceDestinations11, null, selectetPieces, 0, card13, 0, false);
        System.out.println("Should be 1: " + targetDestination.get(0));
        System.out.println("Handsize should be 7: " + playerAndHand.getHand().getHand().size());
        System.out.println();
        System.out.println();
        System.out.println("Play card Questionmark wit Card 11 and go to startfield and ownPieceFinished");
        playSpecialCard("red", "green", playerAndHand, 14, pieceDestinations14, null, selectetPieces14, 0, card11, 0, true);
        System.out.println("Should be 33: " + targetDestination.get(0));
        System.out.println("Handsize should be 6: " + playerAndHand.getHand().getHand().size());
        System.out.println();
        System.out.println();
        System.out.println("Play Card 4 normal direction clockwise -> played counterclockwise");
        playSpecialCard("red", "green", playerAndHand, 4, pieceDestinations, null, selectetPieces, 0, card13, 1, false);
        System.out.println("Should be 6: " + targetDestination.get(0));
        System.out.println("Handsize should be 5: " + playerAndHand.getHand().getHand().size());
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("Play Card 4 normal direction clockwise action 0 normal fahren");
        playSpecialCard("red", "green", playerAndHand, 4, pieceDestinations, null, selectetPieces, 0, card13, 0, false);
        System.out.println("Should be 14: " + targetDestination.get(0));
        System.out.println("Handsize should be 4: " + playerAndHand.getHand().getHand().size());
        System.out.println();
        System.out.println();
        System.out.println("Play Card 4 normal direction counterclockwise -> played clockwise");
        playSpecialCard("red", "green", playerAndHand, 4, pieceDestinations, null, selectetPieces, 1, card13, 1, false);
        System.out.println("Should be 14: " + targetDestination.get(0));
        System.out.println("Handsize should be 3: " + playerAndHand.getHand().getHand().size());
        System.out.println();
        System.out.println();
        System.out.println("Play Card 7 with 4 pieces");
        playSpecialCard("red", "green", playerAndHand, 7, pieceDestinations7, fieldToGo7, selectetPieces7, 0, card13, 0, false);
        System.out.println("Should be 11: " + targetDestination.get(0));
        System.out.println("Should be 90: " + targetDestination.get(1));
        System.out.println("Should be 13: " + targetDestination.get(2));
        System.out.println("Should be 3: " + targetDestination.get(3));
        System.out.println("Handsize should be 2: " + playerAndHand.getHand().getHand().size());
        System.out.println();
        System.out.println("Play Card 7 with 3 pieces");
        playSpecialCard("red", "green", playerAndHand, 7, pieceDestinations72, fieldToGo72, selectetPieces72, 0, card13, 0, false);
        System.out.println("Should be 36: " + targetDestination.get(0));
        System.out.println("Should be 91: " + targetDestination.get(1));
        System.out.println("Should be 2: " + targetDestination.get(2));
        System.out.println("Handsize should be 1: " + playerAndHand.getHand().getHand().size());
        System.out.println();
        System.out.println("Play Card 7 with 3 pieces counterclockwise");
        playSpecialCard("red", "green", playerAndHand, 7, pieceDestinations72, fieldToGo72, selectetPieces72, 1, card13, 0, false);
        System.out.println("Should be 89: " + targetDestination.get(0));
        System.out.println("Should be 91: " + targetDestination.get(1));
        System.out.println("Should be 62: " + targetDestination.get(2));
        System.out.println("Handsize should be 0: " + playerAndHand.getHand().getHand().size());
        System.out.println();


    }

}