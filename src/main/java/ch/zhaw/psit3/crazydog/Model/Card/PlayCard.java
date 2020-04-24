package ch.zhaw.psit3.crazydog.Model.Card;

import ch.zhaw.psit3.crazydog.Model.Piece.Piece;
import ch.zhaw.psit3.crazydog.Model.Game.Values;
import ch.zhaw.psit3.crazydog.Model.Player.PlayerAndHand;

import java.util.List;

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
    static List<Integer> targetDestination;
    List<Piece> selectetPieces;
    int direction;
    Card selectetCardQuestion;
    int selectetAction;

    public static void main(String[] args) {
        Piece pieceRed = new Piece(1, 2, 3, "picname"); //red
        Piece pieceGreen = new Piece(2, 2, 4, "picname"); //green
        Piece pieceYellow = new Piece(3, 2, 5, "picname"); //yellow
        Piece pieceBlue = new Piece(4, 2, 6, "picname"); //green
        System.out.println("PIECERED");
        System.out.println("Should be 15: " + calculateNewDestination(10, 5, 0, pieceRed));
        System.out.println("Should be 1: " + calculateNewDestination(64, 1, 0, pieceRed));
        System.out.println("Should be 3: " + calculateNewDestination(60, 7, 0, pieceRed));
        System.out.println("Should be 4: " + calculateNewDestination(7, 3, 1, pieceRed));
        System.out.println();
        System.out.println();
        System.out.println("PIECEGREEN");
        System.out.println("Should be 15: " + calculateNewDestination(10, 5, 0, pieceGreen));
        System.out.println("Should be 1: " + calculateNewDestination(64, 1, 0, pieceGreen));
        System.out.println("Should be 82: " + calculateNewDestination(60, 7, 0, pieceGreen));
        System.out.println("Should be 4: " + calculateNewDestination(7, 3, 1, pieceGreen));
        System.out.println("Should be 83: " + calculateNewDestination(3, 5, 1, pieceGreen));
        System.out.println();
        System.out.println();
        System.out.println("PIECEYELLOW");
        System.out.println("Should be 15: " + calculateNewDestination(10, 5, 0, pieceYellow));
        System.out.println("Should be 94: " + calculateNewDestination(48, 3, 0, pieceYellow));
        System.out.println("Should be 53: " + calculateNewDestination(48, 5, 0, pieceYellow));
        System.out.println("Should be 3: " + calculateNewDestination(60, 7, 0, pieceYellow));
        System.out.println("Should be 4: " + calculateNewDestination(7, 3, 1, pieceYellow));
        System.out.println("Should be 62: " + calculateNewDestination(3, 5, 1, pieceYellow));
        System.out.println();
        System.out.println();
        System.out.println("PIECEBLUE");
        System.out.println("Should be 15: " + calculateNewDestination(10, 5, 0, pieceBlue));
        System.out.println("Should be 1: " + calculateNewDestination(64, 1, 0, pieceBlue));
        System.out.println("Should be 3: " + calculateNewDestination(60, 7, 0, pieceBlue));
        System.out.println("Should be 4: " + calculateNewDestination(7, 3, 1, pieceBlue));
        System.out.println("Should be 62: " + calculateNewDestination(3, 5, 1, pieceBlue));
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("CHECK VALID TURN");
        System.out.println("Should be true: ");
        if (checkValidturn(66, "thirteen", pieceRed, 5)) {
            System.out.println("true :)");
        } else {
            System.out.println("false :-(");
        }
        System.out.println("Should be false: ");
        if (checkValidturn(66, "one", pieceRed, 5)) {
            System.out.println("true :)");
        } else {
            System.out.println("false :-(");
        }
    }

    /**
     * @param pieceDest Position der zu spielenden Figur
     * @param cardName  Name der Karte
     * @param piece     Spielfigur die gespielt wird
     * @return true,    wenn das ein gültiger Spielzug ist, ansonsten false
     */
    public static boolean checkValidturn(int pieceDest, String cardName, Piece piece, int fieldsToGo) {
        int[] val = Values.HOMEANDDESTFIELDS.get(piece.getColourId());
        if (pieceDest >= val[0] && pieceDest <= val[1]) {
            return cardName.equals("thirteen") || cardName.equals("oneEleven");
        }
        if (pieceDest == val[2]) {
            return piece.getNumber() > 1 && fieldsToGo < 3;
        } else if (pieceDest == val[2] + 1) {
            return piece.getNumber() > 2 && fieldsToGo < 2;
        } else if (pieceDest == val[2] + 2) {
            return piece.getNumber() == 4 && fieldsToGo == 1;
        }
        return true;
    }

    /**
     * @param pieceDestination aktueler Standort der gewählten Figur
     * @param fieldsToGo       Anzahl der Felder die zu gehen sind
     * @param direction        Spielrichtung: 0 = Uhrzeigersinn, 1 = Gegenuhrzeigersinn
     * @param piece            Figur die bewegt werden soll
     * @return newDest         die neue Spielfeldnummer der Figur
     */
    private static int calculateNewDestination(int pieceDestination, int fieldsToGo, int direction, Piece piece) {
        int newDest;
        if (direction == 0) {
            newDest = (pieceDestination + fieldsToGo) % 64;
            int[] val = Values.DESTFIELDSCLOCKWISE.get(piece.getColourId());
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
            } else if (pieceDestination < val[0] && newDest > val[0] && newDest < val[1]) {
                if (newDest == val[0] + 1) {
                    newDest = val[2];
                }
                if (piece.getNumber() < 4) {
                    if (newDest == val[0] + 2) {
                        newDest = val[2] + 1;
                    }
                    if (piece.getNumber() < 3) {
                        if (newDest == val[0] + 3) {
                            newDest = val[2] + 2;
                        }
                        if (piece.getNumber() == 1 && newDest == val[0] + 4) {
                            newDest = val[3] + 3;
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
            int[] val = Values.DESTFIELDCOUNTERCLOCKWISE.get(piece.getColourId());
            if (piece.getColourId() == Values.COLOURIDGREEN) {
                if (pieceDestination < 15 & newDest > 60) { //
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
                                if (piece.getNumber() == 1 && newDest == 5) {
                                    newDest = Values.LOWESTDESTINATIONFIELDGREEN + 3; //Zielfeld für Piece1 von Green
                                }
                            }
                        }
                    }
                }
            } else if (pieceDestination > val[0] && newDest < val[0] && newDest > val[1]) {
                if (piece.getNumber() < 5) {
                    if (newDest == val[0] - 1) {
                        newDest = val[3];
                    }
                    if (piece.getNumber() < 4) {
                        if (newDest == val[0] - 2) {
                            newDest = val[3] + 1;
                        }
                        if (piece.getNumber() < 3) {
                            if (newDest == val[0] - 3) {
                                newDest = val[3] + 2;
                            }
                            if (piece.getNumber() == 1 && newDest == val[0] - 4) {
                                newDest = val[3] + 3;
                            }
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
     * @param targetDestination    gewünschte Zieldestinationen. (wird nur bei Karte 7 benötigt)
     * @param selectetPieces       List mit den Figuren die der Spieler gewählt hat. (Mehr als eine Figur wird nur bei den Karten 7 und PieceExchange benötigt)
     * @param direction            aktuelle Spielrichtung: 0= Uhrezeigersinn, 1 = Gegenuhrzeigersinn
     * @param selectetCardQuestion Falls der Spieler die Questioncard hat, ist dies die Karte die der Spieler spielen möchte.
     * @param selectetAction       Falls der Spieler eine Spezialkarte spielen möchte die mehr als eine Aktion hat,
     *                             Karte 3: 0=3 Felder fahren, 1=Richtungswechsel;
     *                             Karte oneEleven: 0=1 Feld fahren, 1=11 Felder fahren;
     * @param ownPiecesFinished    true = eigene Figuren sind im Ziel, False eigene Figuren sind noch nicht im Ziel
     */
    public static void playSpecialCard(int colourIdPlayer, int colourIdPartner, PlayerAndHand playerAndHand, int idCardToPlay, List<Integer> pieceDestinations,
                                       List<Integer> targetDestination, List<Piece> selectetPieces,
                                       int direction, Card selectetCardQuestion, int selectetAction, boolean ownPiecesFinished) {
        Card cardToPlay = playerAndHand.getHand().getHand().get(idCardToPlay);
        if (cardToPlay.getId() != 14 && cardToPlay.getId() != 15) { //id quetsionmark und id pieceExchange
            for (int i = 0; i < selectetPieces.size(); i++) {
                if (ownPiecesFinished && colourIdPartner != selectetPieces.get(i).getColourId()) {
                    throw new IllegalArgumentException();
                } else if (colourIdPlayer != selectetPieces.get(i).getColourId()) {
                    throw new IllegalArgumentException();
                }
            }
        }
        if (cardToPlay.getId() != 14) { //id der questioncard
            playerAndHand.getHand().discardCard(idCardToPlay);
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
                    playQuestionCard(colourIdPlayer, colourIdPartner, playerAndHand, idCardToPlay, pieceDestinations, targetDestination, selectetPieces, direction, selectetCardQuestion, selectetAction, ownPiecesFinished);
                } else if (cardToPlay.getName() == "pieceExchange") {
                    targetDestination = pieceExchange(targetDestination);
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
        Card cardToPlay = playerAndHand.getHand().getHand().get(idCardToPlay);
        if (ownPiecesFinished && colourIdPartner != selectetPiece.getColourId()) {
            throw new IllegalArgumentException();
        } else if (colourIdPlayer != selectetPiece.getColourId()) {
            throw new IllegalArgumentException();
        }
        playerAndHand.getHand().discardCard(idCardToPlay);
        if (checkValidturn(pieceDestination, cardToPlay.getName(), selectetPiece, cardToPlay.getValue())) {
            fieldsToGo = cardToPlay.getValue();
        }
        {
            fieldsToGo = 0;
        }
        if (ownPiecesFinished) {
            targetDestination.add(0, calculateNewDestination(pieceDestination, fieldsToGo, direction, selectetPiece));
        } else {
            targetDestination.add(0, calculateNewDestination(pieceDestination, fieldsToGo, direction, selectetPiece));
        }
        //ToDo: neuer Standort und neue Hand zurückgeben.(Piece, pieceDest)
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
        if (piece.getColourId() == COLOURIDRED) {
            if (pieceDestination >= 65 && pieceDestination <= 68) {
                targetDestination.add(0, 1);
            }
        } else if (piece.getColourId() == COLOURIDGREEN) {
            if (pieceDestination >= 73 && pieceDestination <= 76) {
                targetDestination.add(0, 33);
            }

        } else if (piece.getColourId() == COLOURIDYELLOW) {
            if (pieceDestination >= 69 && pieceDestination <= 72) {
                targetDestination.add(0, 17);
            }

        } else if (piece.getColourId() == COLOURIDBLUE) {
            if (pieceDestination >= 77 && pieceDestination <= 80) {
                targetDestination.add(0, 49);
            }
        } else if (selectetAction == 0) {
            if (checkValidturn(pieceDestination, null, piece, 3)) {
                fieldsToGo = 1;
            } else {
                fieldsToGo = 0;
            }
            targetDestination.add(calculateNewDestination(pieceDestination, fieldsToGo, direction, piece));
        } else if (selectetAction == 1) {
            if (checkValidturn(pieceDestination, null, piece, 3)) {
                fieldsToGo = 11;
            } else {
                playCardOneEleven(0, piece, pieceDestination, direction);
            }
            targetDestination.add(calculateNewDestination(pieceDestination, fieldsToGo, direction, piece));
        } else {
            throw new IllegalArgumentException();
        }

    }

    /**
     * @param piece            Figur die bewegt werden soll
     * @param pieceDestination aktueller Standort der gewählten Figur
     */
    private static void playCard13(Piece piece, int pieceDestination, int direction) {
        if (piece.getColourId() == COLOURIDRED) {
            if (pieceDestination >= 65 && pieceDestination <= 68) {
                targetDestination.add(0, 1);
            }
        } else if (piece.getColourId() == COLOURIDGREEN) {
            if (pieceDestination >= 73 && pieceDestination <= 76) {
                targetDestination.add(0, 33);
            }

        } else if (piece.getColourId() == COLOURIDYELLOW) {
            if (pieceDestination >= 69 && pieceDestination <= 72) {
                targetDestination.add(0, 17);
            }

        } else if (piece.getColourId() == COLOURIDBLUE) {
            if (pieceDestination >= 77 && pieceDestination <= 80) {
                targetDestination.add(0, 49);
            }
        } else {
            if (checkValidturn(pieceDestination, null, piece, 3)) {
                fieldsToGo = 13;
            } else {
                fieldsToGo = 0;
            }
            targetDestination.add(calculateNewDestination(pieceDestination, fieldsToGo, direction, piece));
        }
    }

    /**
     * @param targetDestination Standort der Figuren die getauscht werdens sollen
     * @return targetDestination, neuer Standort der Figuren
     */
    private static List pieceExchange(List<Integer> targetDestination) {
        int store = targetDestination.get(0);
        targetDestination.set(0, 1);
        targetDestination.set(1, store);
        return targetDestination;
    }

    /**
     * @param colourIdPlayer       FarbId des Spielers
     * @param playerAndHand        Spieler und Hand
     * @param idCardToPlay         id der Karte die der Spieler spielen möchte.
     * @param pieceDestinations    Array mit den aktuellen Standorten der Figuren, 0=Figur1 etc.
     * @param targetDestination    gewünschte Zieldestinationen. (wird nur bei Karte 7 benötigt)
     * @param selectetPieces       List mit den Figuren die der Spieler gewählt hat. (Mehr als eine Figur wird nur bei den Karten 7 und PieceExchange benötigt)
     * @param direction            aktuelle Spielrichtung: 0= Uhrezeigersin, 1 = Gegenuhrzeigersinn
     * @param selectetCardQuestion Falls der Spieler die Questioncard hat, ist dies die Karte die der Spieler spielen möchte.
     * @param selectetAction       Falls der Spieler eine Spezialkarte spielen möchte die mehr als eine Aktion hat,
     *                             Karte 3: 0=3 Felder fahren, 1=Richtungswechsel;
     *                             Karte oneEleven: 0=1 Feld fahren, 1=11 Felder fahren, 2=Figur auf Start;
     *                             Karte13: 0=13 Felder fahren, 1=Figur auf Start
     */
    private static void playQuestionCard(int colourIdPlayer, int colourIdPartner, PlayerAndHand playerAndHand, int idCardToPlay, List<Integer> pieceDestinations,
                                         List<Integer> targetDestination, List<Piece> selectetPieces,
                                         int direction, Card selectetCardQuestion, int selectetAction, boolean ownPiecesFinished) {
        playerAndHand.getHand().getHand().get(idCardToPlay).setId(selectetCardQuestion.getId());
        playerAndHand.getHand().getHand().get(idCardToPlay).setName(selectetCardQuestion.getName());
        playerAndHand.getHand().getHand().get(idCardToPlay).setValue(selectetCardQuestion.getValue());
        idCardToPlay = selectetCardQuestion.getId();
        if (idCardToPlay == 2 || idCardToPlay == 5 || idCardToPlay == 6 || idCardToPlay == 8 || idCardToPlay == 9 || idCardToPlay == 10 || idCardToPlay == 12) {
            playNormalCard(colourIdPlayer, colourIdPartner, playerAndHand, idCardToPlay, selectetPieces.get(0), pieceDestinations.get(0), direction, ownPiecesFinished);
        } else {
            playSpecialCard(colourIdPlayer, colourIdPartner, playerAndHand, idCardToPlay, pieceDestinations, targetDestination, selectetPieces, direction, selectetCardQuestion, selectetAction, ownPiecesFinished);
        }

    }
}
