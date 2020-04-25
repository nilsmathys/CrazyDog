package ch.zhaw.psit3.crazydog.Model.Card;

import ch.zhaw.psit3.crazydog.Model.Piece.Piece;
import ch.zhaw.psit3.crazydog.Model.Game.Values;
import ch.zhaw.psit3.crazydog.Model.Player.Player;
import ch.zhaw.psit3.crazydog.Model.Player.PlayerAndHand;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PlayCard {
    static int fieldsToGo;
    static final int COLOURIDRED = 3;
    static final int COLOURIDGREEN = 4;
    static final int COLOURIDYELLOW = 5;
    static final int COLOURIDBLUE = 6;
    int colourIdPlayer;
    PlayerAndHand playerAndHand;
    int IdCardToPlay;
    List<Integer> pieceDestination;
    static List<Integer> targetDestination = new ArrayList<>();
    List<Piece> selectetPieces;
    int direction;
    Card selectetCardQuestion;
    int selectetAction;


    /**
     * @param pieceDest Position der zu spielenden Figur
     * @param cardName  Name der Karte
     * @param piece     Spielfigur die gespielt wird
     * @return true,    wenn das ein gültiger Spielzug ist, ansonsten false
     */
    public static boolean checkValidturn(int pieceDest, String cardName, Piece piece, int fieldsToGo) {
        Map<String, Integer> vals = Values.HOMEANDDESTFIELDS.get(piece.getColourId());
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
     * @param direction        Spielrichtung: 0 = Uhrzeigersinn, 1 = Gegenuhrzeigersinn
     * @param piece            Figur die bewegt werden soll (braucht es wegen der Farbid)
     * @return newDest         die neue Spielfeldnummer der Figur
     */
    private static int calculateNewDestination(int pieceDestination, int fieldsToGo, int direction, Piece piece) {
        int newDest;
        Map<String, Integer> vals = Values.STARTANDDESTFIELDS.get(piece.getColourId());
        if (direction == 0) {
            newDest = (pieceDestination + fieldsToGo) % 64;
            if (piece.getColourId() == Values.COLOURIDGREEN) {
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
            } else if (pieceDestination < vals.get("Startfield") && newDest > vals.get("Startfield") && newDest < vals.get("MaxWithToGoClockwise")) {
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
            if (piece.getColourId() == Values.COLOURIDGREEN) {
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
            } else if (pieceDestination > vals.get("Startfield") && newDest < vals.get("Startfield") && newDest > vals.get("MaxWithToGoCounterClockwise")) {
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
     * @param colourIdPlayer       FarbId des Spielers
     * @param colourIdPartner      FarbId es Partners
     * @param playerAndHand        Spieler und Hand
     * @param idCardToPlay         id der Karte die der Spieler spielen möchte.
     * @param pieceDestinations    Array mit den aktuellen Standorten der Figuren, 0=Figur1 etc.
     * @param targetDestinations   gewünschte Zieldestinationen. (wird nur bei Karte 7 benötigt)
     * @param selectetPieces       List mit den Figuren die der Spieler gewählt hat. (Mehr als eine Figur wird nur bei den Karten 7 und PieceExchange benötigt)
     * @param direction            aktuelle Spielrichtung: 0= Uhrezeigersinn, 1 = Gegenuhrzeigersinn
     * @param selectetCardQuestion Falls der Spieler die Questioncard hat, ist dies die Karte die der Spieler spielen möchte.
     * @param selectetAction       Falls der Spieler eine Spezialkarte spielen möchte die mehr als eine Aktion hat,
     *                             Karte 3: 0=3 Felder fahren, 1=Richtungswechsel;
     *                             Karte oneEleven: 0=1 Feld fahren, 1=11 Felder fahren;
     * @param ownPiecesFinished    true = eigene Figuren sind im Ziel, False eigene Figuren sind noch nicht im Ziel
     */
    public static void playSpecialCard(int colourIdPlayer, int colourIdPartner, PlayerAndHand playerAndHand, int idCardToPlay, List<Integer> pieceDestinations,
                                       List<Integer> targetDestinations, List<Piece> selectetPieces,
                                       int direction, Card selectetCardQuestion, int selectetAction, boolean ownPiecesFinished) {
        Card cardToPlay = playerAndHand.getHand().discardCard(idCardToPlay);
        if (cardToPlay.getId() != 14 && cardToPlay.getId() != 15) { //id quetsionmark und id pieceExchange
            for (int i = 0; i < selectetPieces.size(); i++) {
                if (ownPiecesFinished && colourIdPartner != selectetPieces.get(i).getColourId()) {
                    throw new IllegalArgumentException();
                } else if (colourIdPlayer != selectetPieces.get(i).getColourId()) {
                    throw new IllegalArgumentException();
                }
            }
        }
        fieldsToGo = 0;
        switch (cardToPlay.getValue()) {
            case 3:
                //ToDo: 3 online umsetzen (Figur auswälen oder Richtung ändern)
                direction = playCard3(pieceDestinations.get(0), selectetPieces.get(0), selectetAction, direction);
                break;
            case 4:
                playCard4(pieceDestinations.get(0), direction);
                break;
            case 7:
                playCard7();
                //ToDo: 7 online umsetzen(mehrere Figuren anwählen)
                break;
            case 0:
                if (cardToPlay.getName() == "oneEleven") {
                    //ToDo: oneEleven online umsetzen (falls figur auf homefield ausgewählt wird keine Aktion)
                    playCardOneEleven(selectetAction, selectetPieces.get(0), pieceDestinations.get(0), direction);
                } else if (cardToPlay.getName() == "questionmark") {
                    //ToDo: questionmarkKarte online umsetzen
                    playQuestionCard(colourIdPlayer, colourIdPartner, playerAndHand, pieceDestinations, targetDestinations, selectetPieces, direction, selectetCardQuestion, selectetAction, ownPiecesFinished);
                } else if (cardToPlay.getName() == "pieceExchange") {
                    if (!targetDestination.isEmpty()) {
                        targetDestination.clear();
                    }
                    targetDestination = pieceExchange(pieceDestinations);
                } else {
                    throw new IllegalArgumentException("KartenId ist kommisch");
                }
                break;
            case 13:
                playCard13(selectetPieces.get(0), pieceDestinations.get(0), direction);
                //ToDO: Card13 online umsetzen (falls figur auf homefiled ausgewählt wird keine Aktion)
                break;
            default:
                throw new IllegalArgumentException();

        }
        //ToDo: neue Hand, neuer Standort und Dircetion zurückgeben(List Pieces, targertdest, direction)
    }

    /**
     * @param colourIdPlayer   Farbid des Spielers
     * @param playerAndHand    Spieler und seine Hand
     * @param idCardToPlay     id der Karte die der Spieler spielen will
     * @param selectetPiece    Figur die der Spieler fahren möchte
     * @param pieceDestination aktueller Standort der Figur die der Spieler gewählt hat
     * @param direction        aktuelle Spielrichtung, 0 = Uhzeigersinn, 1 = Gegenuhrzeigersinn.
     */
    public static void playNormalCard(int colourIdPlayer, int colourIdPartner, PlayerAndHand playerAndHand, int idCardToPlay, Piece selectetPiece, int pieceDestination, int direction, boolean ownPiecesFinished) {
        Card cardToPlay = playerAndHand.getHand().discardCard(idCardToPlay);
        if (ownPiecesFinished && colourIdPartner != selectetPiece.getColourId()) {
            throw new IllegalArgumentException("ungültige Figur ausgewählt: Figur des Partners auswählen");
        } else if (colourIdPlayer != selectetPiece.getColourId()) {
            throw new IllegalArgumentException("ungültige Figur ausgewählt: eigene FIgur auswählen");
        }
        if (checkValidturn(pieceDestination, cardToPlay.getName(), selectetPiece, cardToPlay.getValue())) {
            fieldsToGo = cardToPlay.getValue();
        } else {
            fieldsToGo = 0;
        }
        if (!targetDestination.isEmpty()) {
            targetDestination.clear();
        }
        targetDestination.add(calculateNewDestination(pieceDestination, fieldsToGo, direction, selectetPiece));
        //ToDo: neuer Standort und neue Hand zurückgeben.(Piece, pieceDest)
    }

    public static void main(String[] args) {
        Piece pieceRed = new Piece(1, 2, 3, "picname"); //red
        Piece pieceGreen = new Piece(2, 2, 4, "picname"); //green
        Piece pieceYellow = new Piece(3, 2, 5, "picname"); //yellow
        Piece pieceBlue = new Piece(4, 2, 6, "picname"); //green
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
        Card card11 = new Card(11, "oneEleven", 0);
        Card card12 = new Card(12, "standard", 12);
        Card card13 = new Card(13, "thirteen", 13);
        Card card14 = new Card(14, "questionmark", 0);
        Card card15 = new Card(15, "pieceExchange", 0);
        CardsOnHand cardsOnHand = new CardsOnHand();
        List<Integer> pieceDestinations = new ArrayList<>();
        pieceDestinations.add(10);
        List<Integer> pieceDestinations11 = new ArrayList<>();
        pieceDestinations11.add(65);
        List<Piece> selectetPieces = new ArrayList<>();
        selectetPieces.add(pieceRed);
        cardsOnHand.takeCard(card2);
        cardsOnHand.takeCard(card2);
        cardsOnHand.takeCard(card3);
        cardsOnHand.takeCard(card4);
        cardsOnHand.takeCard(card5);
        cardsOnHand.takeCard(card5);
        cardsOnHand.takeCard(card6);
        cardsOnHand.takeCard(card7);
        cardsOnHand.takeCard(card8);
        cardsOnHand.takeCard(card9);
        cardsOnHand.takeCard(card10);
        cardsOnHand.takeCard(card11);
        cardsOnHand.takeCard(card11);
        cardsOnHand.takeCard(card11);
        cardsOnHand.takeCard(card12);
        cardsOnHand.takeCard(card13);
        cardsOnHand.takeCard(card13);
        cardsOnHand.takeCard(card13);
        cardsOnHand.takeCard(card14);
        cardsOnHand.takeCard(card15);
        cardsOnHand.takeCard(card15);
        PlayerAndHand playerAndHand = new PlayerAndHand(player, cardsOnHand);
        System.out.println("Handsize should be 21: " + playerAndHand.getHand().getHand().size());

        System.out.println("Play card 2");
        playNormalCard(COLOURIDRED, COLOURIDGREEN, playerAndHand, 2, pieceRed, 10, 0, false);
        System.out.println("Should be 12: " + targetDestination.get(0));
        System.out.println("Handsize should be 20: " + playerAndHand.getHand().getHand().size());

        System.out.println("Play card 2");
        playNormalCard(COLOURIDRED, COLOURIDGREEN, playerAndHand, 2, pieceRed, 10, 0, false);
        System.out.println("Should be 12: " + targetDestination.get(0));
        System.out.println("Handsize should be 19: " + playerAndHand.getHand().getHand().size());

        System.out.println("Play card Questionmark");
        playSpecialCard(COLOURIDRED, COLOURIDGREEN, playerAndHand, 14, pieceDestinations, null, selectetPieces, 0, card2, 0, false);
        System.out.println("Should be 12: " + targetDestination.get(0));
        System.out.println("Handsize should be 18: " + playerAndHand.getHand().getHand().size());

        System.out.println("Play card 11 Aktion 1 fahren");
        playSpecialCard(COLOURIDRED, COLOURIDGREEN, playerAndHand, 11, pieceDestinations, null, selectetPieces, 0, card2, 0, false);
        System.out.println("Should be 11: " + targetDestination.get(0));
        System.out.println("Handsize should be 17: " + playerAndHand.getHand().getHand().size());

        System.out.println("Play card 11 Aktion 11 fahren");
        playSpecialCard(COLOURIDRED, COLOURIDGREEN, playerAndHand, 11, pieceDestinations, null, selectetPieces, 0, card2, 1, false);
        System.out.println("Should be 21: " + targetDestination.get(0));
        System.out.println("Handsize should be 16: " + playerAndHand.getHand().getHand().size());

        System.out.println("Play card 11 Aktion auf Homefield fahren");
        playSpecialCard(COLOURIDRED, COLOURIDGREEN, playerAndHand, 11, pieceDestinations11, null, selectetPieces, 0, card2, 1, false);
        System.out.println("Should be 1: " + targetDestination.get(0));
        System.out.println("Handsize should be 15: " + playerAndHand.getHand().getHand().size());

        System.out.println("Play card 13 Aktion 13 fahren");
        playSpecialCard(COLOURIDRED, COLOURIDGREEN, playerAndHand, 13, pieceDestinations, null, selectetPieces, 0, card2, 1, false);
        System.out.println("Should be 23: " + targetDestination.get(0));
        System.out.println("Handsize should be 14: " + playerAndHand.getHand().getHand().size());

        System.out.println("Play card 13 Aktion auf Homefield fahren");
        playSpecialCard(COLOURIDRED, COLOURIDGREEN, playerAndHand, 13, pieceDestinations11, null, selectetPieces, 0, card2, 1, false);
        System.out.println("Should be 1: " + targetDestination.get(0));
        System.out.println("Handsize should be 13: " + playerAndHand.getHand().getHand().size());

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();


//        System.out.println();
//        System.out.println("NORMALETEST");
//        System.out.println("PIECERED");
//        System.out.println("Should be 15: " + calculateNewDestination(10, 5, 0, pieceRed));
//        System.out.println("Should be 1: " + calculateNewDestination(64, 1, 0, pieceRed));
//        System.out.println("Should be 3: " + calculateNewDestination(60, 7, 0, pieceRed));
//        System.out.println("Should be 4: " + calculateNewDestination(7, 3, 1, pieceRed));
//        System.out.println();
//        System.out.println();
//        System.out.println("PIECEGREEN");
//        System.out.println("Should be 15: " + calculateNewDestination(10, 5, 0, pieceGreen));
//        System.out.println("Should be 1: " + calculateNewDestination(64, 1, 0, pieceGreen));
//        System.out.println("Should be 82: " + calculateNewDestination(60, 7, 0, pieceGreen));
//        System.out.println("Should be 4: " + calculateNewDestination(7, 3, 1, pieceGreen));
//        System.out.println("Should be 83: " + calculateNewDestination(3, 5, 1, pieceGreen));
//        System.out.println("Should be 83: " + calculateNewDestination(1, 3, 1, pieceGreen));
//        System.out.println("Should be 61: " + calculateNewDestination(1, 4, 1, pieceGreen));
//        System.out.println();
//        System.out.println();
//        System.out.println("PIECEYELLOW");
//        System.out.println("Should be 15: " + calculateNewDestination(10, 5, 0, pieceYellow));
//        System.out.println("Should be 94: " + calculateNewDestination(48, 3, 0, pieceYellow));
//        System.out.println("Should be 53: " + calculateNewDestination(48, 5, 0, pieceYellow));
//        System.out.println("Should be 3: " + calculateNewDestination(60, 7, 0, pieceYellow));
//        System.out.println("Should be 4: " + calculateNewDestination(7, 3, 1, pieceYellow));
//        System.out.println("Should be 62: " + calculateNewDestination(3, 5, 1, pieceYellow));
//        System.out.println();
//        System.out.println();
//        System.out.println("PIECEBLUE");
//        System.out.println("Should be 15: " + calculateNewDestination(10, 5, 0, pieceBlue));
//        System.out.println("Should be 1: " + calculateNewDestination(64, 1, 0, pieceBlue));
//        System.out.println("Should be 3: " + calculateNewDestination(60, 7, 0, pieceBlue));
//        System.out.println("Should be 4: " + calculateNewDestination(7, 3, 1, pieceBlue));
//        System.out.println("Should be 62: " + calculateNewDestination(3, 5, 1, pieceBlue));
//        System.out.println();
//        System.out.println();
//        System.out.println();
//        System.out.println();
//        System.out.println("CHECK VALID TURN");
//        System.out.println("Should be true: ");
//        if (checkValidturn(66, "thirteen", pieceRed, 5)) {
//            System.out.println("true :)");
//        } else {
//            System.out.println("false :(");
//        }
//        System.out.println("Should be false: ");
//        if (checkValidturn(66, "one", pieceRed, 5)) {
//            System.out.println("true :)");
//        } else {
//            System.out.println("false :(");
//        }
    }

    /**
     * @param pieceDestination aktueller Standort der Figur
     * @param piece            Figur die bewegt werden soll
     * @param selectetAction   ausgeählte Aktion, 0 = 3 Felder gehen, 1 = Richtungswechsel
     * @param direction        aktuelle Spielrichtung, 0 = Uhrzeigersinn, 1 = Gegenuhrzeigersinn
     * @return gibt die neue Spielrichtung zurück
     */
    private static int playCard3(int pieceDestination, Piece piece, int selectetAction, int direction) {
        if (selectetAction == 0) {
            if (checkValidturn(pieceDestination, null, piece, 3)) {
                fieldsToGo = 3;
            } else {
                playCard3(pieceDestination, piece, 1, direction);
            }
            if (!targetDestination.isEmpty()) {
                targetDestination.clear();
            }
            targetDestination.add(calculateNewDestination(pieceDestination, fieldsToGo, direction, piece));
            //TODo: zurückgeben neuer Standort der ausgewählten Figur
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

    private static void playCard4(int pieceDestination, int direction) {
        fieldsToGo = 4;
        //targetDestination.add(neuerStandort);
        //ToDo: Fahren in die andere Richtung
    }

    public static void playCard7() {
        fieldsToGo = 7;
        //targetDestination.add(neueStandorte);
        //ToDo: Felder können einzeln gefahren werden, überprüfen ob gültiger Zug

    }

    /**
     * @param selectetAction   ausgewählte AKtion: 0 = 1 Feld fahren, 1 = 11 Felder fahren
     * @param piece            Figur die bewegt werden soll
     * @param pieceDestination aktueller Standort der gewählten Figur
     */
    private static void playCardOneEleven(int selectetAction, Piece piece, int pieceDestination, int direction) {
        movePieceOnStartField(piece, pieceDestination);
        if(targetDestination.isEmpty()) {
            if (selectetAction == 0) {
                if (checkValidturn(pieceDestination, null, piece, 1)) {
                    fieldsToGo = 1;
                } else {
                    fieldsToGo = 0;
                }
                if (!targetDestination.isEmpty()) {
                    targetDestination.clear();
                }
                targetDestination.add(calculateNewDestination(pieceDestination, fieldsToGo, direction, piece));
            } else if (selectetAction == 1) {
                if (checkValidturn(pieceDestination, null, piece, 11)) {
                    fieldsToGo = 11;
                } else {
                    playCardOneEleven(0, piece, pieceDestination, direction);
                }
                if (!targetDestination.isEmpty()) {
                    targetDestination.clear();
                }
                targetDestination.add(calculateNewDestination(pieceDestination, fieldsToGo, direction, piece));
            } else {
                throw new IllegalArgumentException();
            }
        }


    }

    private static void movePieceOnStartField(Piece piece, int pieceDestination) {
        if (!targetDestination.isEmpty()) {
            targetDestination.clear();
        }
        if (piece.getColourId() == COLOURIDRED) {
            if (pieceDestination >= Values.LOWESTHOMEFIELDRED && pieceDestination <= Values.HIGHESTHOMEFIELDRED) {
                targetDestination.add(Values.STARTFIELDRED);
            }
        } else if (piece.getColourId() == COLOURIDGREEN) {
            if (pieceDestination >= Values.LOWESTHOMEFIELDGREEN && pieceDestination <= Values.HIGHESTHOMEFIELDGREEN) {
                targetDestination.add(Values.STARTFIELDGREEN);
            }

        } else if (piece.getColourId() == COLOURIDYELLOW) {
            if (pieceDestination >= Values.LOWESTHOMEFIELDYELLOW && pieceDestination <= Values.HIGHESTHOMEFIELDYELLOW) {
                targetDestination.add(Values.STARTFIELDYELLOW);
            }

        } else if (piece.getColourId() == COLOURIDBLUE) {
            if (pieceDestination >= Values.LOWESTHOMEFIELDBLUE && pieceDestination <= Values.HIGHESTHOMEFIELDBLUE) {
                targetDestination.add(Values.STARTFIELDBLUE);
            }
        }
    }

    /**
     * @param piece            Figur die bewegt werden soll
     * @param pieceDestination aktueller Standort der gewählten Figur
     */
    private static void playCard13(Piece piece, int pieceDestination, int direction) {
        movePieceOnStartField(piece, pieceDestination);
        if (checkValidturn(pieceDestination, null, piece, 13) && targetDestination.isEmpty()) {
            fieldsToGo = 13;
        } else {
            fieldsToGo = 0;
        }
        targetDestination.add(calculateNewDestination(pieceDestination, fieldsToGo, direction, piece));

    }

    /**
     * @param pieceDestination Standort der Figuren die getauscht werdens sollen
     * @return targetDestination, neuer Standort der Figuren
     */
    private static List pieceExchange(List<Integer> pieceDestination) {
        int store = pieceDestination.get(0);
        pieceDestination.set(0, 1);
        pieceDestination.set(1, store);
        return pieceDestination;
    }

    /**
     * @param colourIdPlayer       FarbId des Spielers
     * @param playerAndHand        Spieler und Hand
     * @param pieceDestinations    Array mit den aktuellen Standorten der Figuren, 0=Figur1 etc.
     * @param targetDestination    gewünschte Zieldestinationen. (wird nur bei Karte 7 benötigt)
     * @param selectetPieces       List mit den Figuren die der Spieler gewählt hat. (Mehr als eine Figur wird nur bei den Karten 7 und PieceExchange benötigt)
     * @param direction            aktuelle Spielrichtung: 0= Uhrezeigersin, 1 = Gegenuhrzeigersinn
     * @param selectetCardQuestion Karte die der Spieleler spielen möchte
     * @param selectetAction       Falls der Spieler eine Spezialkarte spielen möchte die mehr als eine Aktion hat,
     *                             Karte 3: 0=3 Felder fahren, 1=Richtungswechsel;
     *                             Karte oneEleven: 0=1 Feld fahren, 1=11 Felder fahren, 2=Figur auf Start;
     *                             Karte13: 0=13 Felder fahren, 1=Figur auf Start
     */
    private static void playQuestionCard(int colourIdPlayer, int colourIdPartner, PlayerAndHand playerAndHand, List<Integer> pieceDestinations,
                                         List<Integer> targetDestination, List<Piece> selectetPieces,
                                         int direction, Card selectetCardQuestion, int selectetAction, boolean ownPiecesFinished) {
        playerAndHand.getHand().takeCard(selectetCardQuestion);
        if (selectetCardQuestion.getId() == Values.CARDVALUE2 || selectetCardQuestion.getId() == Values.CARDVALUE5 || selectetCardQuestion.getId() == Values.CARDVALUE6 || selectetCardQuestion.getId() == Values.CARDVALUE8 || selectetCardQuestion.getId() == Values.CARDVALUE9 || selectetCardQuestion.getId() == Values.CARDVALUE10 || selectetCardQuestion.getId() == Values.CARDVALUE12) {
            playNormalCard(colourIdPlayer, colourIdPartner, playerAndHand, selectetCardQuestion.getId(), selectetPieces.get(0), pieceDestinations.get(0), direction, ownPiecesFinished);
        } else {
            playSpecialCard(colourIdPlayer, colourIdPartner, playerAndHand, selectetCardQuestion.getId(), pieceDestinations, targetDestination, selectetPieces, direction, selectetCardQuestion, selectetAction, ownPiecesFinished);
        }

    }
}
