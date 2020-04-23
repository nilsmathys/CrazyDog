package ch.zhaw.psit3.crazydog.Model.Card;

import ch.zhaw.psit3.crazydog.Model.Piece.Piece;
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


    /**
     * @param pieceDest Position der zu spielenden Figur
     * @param colourId  FarbId der Figur
     * @param cardName  Name der Karte
     * @return true, wenn das ein gültiger Spielzug ist, ansonsten false
     */
    public static boolean checkValidturn(int pieceDest, String cardName, Piece piece, int fieldsToGo, int selectetAction) {
        switch (piece.getColourId()) {
            case COLOURIDRED:
                if (pieceDest == 65 || pieceDest == 66 || pieceDest == 67 || pieceDest == 68) {
                    if (cardName == "thirteen" || cardName == "oneEleven") {
                        return true;
                    } else {
                        return false;
                    }
                }
                if (pieceDest == 89) {
                    if (piece.getNumber() > 1 && fieldsToGo < 3) {
                        return true;
                    } else {
                        return false;
                    }
                } else if (pieceDest == 90) {
                    if (piece.getNumber() > 2 && fieldsToGo < 2) {
                        return true;
                    } else {
                        return false;
                    }
                } else if (pieceDest == 91) {
                    if (piece.getNumber() == 4 && fieldsToGo == 1) {
                        return true;
                    } else {
                        return false;
                    }
                }
                return true;

            case COLOURIDGREEN:
                if (pieceDest == 73 || pieceDest == 74 || pieceDest == 75 || pieceDest == 76) {
                    if (cardName == "thirteen" || cardName == "oneEleven") {
                        return true;
                    } else {
                        return false;
                    }
                }
                if (pieceDest == 81) {
                    if (piece.getNumber() > 1 && fieldsToGo < 3) {
                        return true;
                    } else {
                        return false;
                    }
                } else if (pieceDest == 82) {
                    if (piece.getNumber() > 2 && fieldsToGo < 2) {
                        return true;
                    } else {
                        return false;
                    }
                } else if (pieceDest == 83) {
                    if (piece.getNumber() == 4 && fieldsToGo == 1) {
                        return true;
                    } else {
                        return false;
                    }
                }
                return true;

            case COLOURIDYELLOW:
                if (pieceDest == 69 || pieceDest == 70 || pieceDest == 71 || pieceDest == 72) {
                    if (cardName == "thirteen" || cardName == "oneEleven") {
                        return true;
                    } else {
                        return false;
                    }
                }
                if (pieceDest == 93) {
                    if (piece.getNumber() > 1 && fieldsToGo < 3) {
                        return true;
                    } else {
                        return false;
                    }
                } else if (pieceDest == 94) {
                    if (piece.getNumber() > 2 && fieldsToGo < 2) {
                        return true;
                    } else {
                        return false;
                    }
                } else if (pieceDest == 95) {
                    if (piece.getNumber() == 4 && fieldsToGo == 1) {
                        return true;
                    } else {
                        return false;
                    }
                }
                return true;

            case COLOURIDBLUE:
                if (pieceDest == 77 || pieceDest == 78 || pieceDest == 79 || pieceDest == 80) {
                    if (cardName == "thirteen" || cardName == "oneEleven") {
                        return true;
                    } else {
                        return false;
                    }
                }
                if (pieceDest == 85) {
                    if (piece.getNumber() > 1 && fieldsToGo < 3) {
                        return true;
                    } else {
                        return false;
                    }
                } else if (pieceDest == 86) {
                    if (piece.getNumber() > 2 && fieldsToGo < 2) {
                        return true;
                    } else {
                        return false;
                    }
                } else if (pieceDest == 87) {
                    if (piece.getNumber() == 4 && fieldsToGo == 1) {
                        return true;
                    } else {
                        return false;
                    }
                }
                return true;
        }
        return false;
    }

    /**
     * @param colourIdPlayer       FarbId des Spielers
     * @param colourIdPartner      FarbId es Partners
     * @param playerAndHand        Spieler und Hand
     * @param IdCardToPlay         id der Karte die der Spieler spielen möchte.
     * @param pieceDestinations    Array mit den aktuellen Standorten der Figuren, 0=Figur1 etc.
     * @param targetDestination    gewünschte Zieldestinationen. (wird nur bei Karte 7 benötigt)
     * @param selectetPieces       List mit den Figuren die der Spieler gewählt hat. (Mehr als eine Figur wird nur bei den Karten 7 und PieceExchange benötigt)
     * @param direction            aktuelle Spielrichtung: 0= Uhrezeigersin, 1 = Gegenuhrzeigersinn
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
                direction = playCard3(pieceDestinations.get(0), selectetAction, direction);
                //ToDo: online umsetzen
                break;
            case 4:
                playCard4(pieceDestinations.get(0), direction);
                break;
            case 7:
                playCard7();
                //ToDo: 7 online umsetzen
                break;
            case 0:
                if (cardToPlay.getName() == "oneEleven") {
                    if(ownPiecesFinished) {
                        playCardOneEleven(selectetAction, colourIdPartner, pieceDestinations.get(0));
                    } else {
                        playCardOneEleven(selectetAction, colourIdPlayer, pieceDestinations.get(0));
                    }

                    //ToDo: oneEleven online umsetzen (falls figur auf homefiled ausgewählt wird keine Aktion)
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
                if(ownPiecesFinished) {
                    playCard13(colourIdPartner, pieceDestinations.get(0));
                } else {
                    playCard13(colourIdPlayer, pieceDestinations.get(0));
                }
                //ToDO: Card13 online umsetzen (falls figur auf homefiled ausgewählt wird keine Aktion)
                break;
            default:
                throw new IllegalArgumentException();

        }
        //ToDo: zurückgeben der Figuren die bewegt werden soll und neuer Standort und Dircetion (List Pieces, targertdest, direction)
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
        //ToDo: überprüfen ob es ein gültiger Zug ist
        fieldsToGo = cardToPlay.getValue();
        //berechnen neuer Standort
        //targetDestination.add(neuerStandort);
        //ToDo: Figur und neuer Standort zurückgeben.(Piece, pieceDest)
    }

    /**
     * @param pieceDest      aktueller Standort der Figur
     * @param selectetAction ausgeählte Aktion, 0 = 3 Felder gehen, 1 = Richtungswechsel
     * @param direction      aktuelle Spielrichtung, 0 = Uhrzeigersinn, 1 = Gegenuhrzeigersinn
     * @return gibt die neue Spielrichtung zurück
     */
    public static int playCard3(int pieceDest, int selectetAction, int direction) {
        if (selectetAction == 0) {
            //ToDo: überprüfen ob Validtun

            fieldsToGo = 3;
            //targetDestination.add(neuerStandort);
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

    public static void playCard4(int pieceDestination, int direction) {
        fieldsToGo = 4;
        //targetDestination.add(neuerStandort);
        //ToDo: Fahren in die andere Richtung
    }

    public static void playCard7() {
        fieldsToGo = 7;
        //targetDestination.add(neueStandorte);
        //ToDo: Felder können einzeln gefahren werden, überprüfen ob gültiger Zug

    }

    public static void playCardOneEleven(int selectetAction, int colourId, int pieceDestination) {
        if(colourId == COLOURIDRED) {
            if(pieceDestination == 65 || pieceDestination == 66 || pieceDestination == 67 || pieceDestination == 68) {
                targetDestination.add(0, 1);
            }
        } else if(colourId == COLOURIDGREEN) {
            if(pieceDestination == 73 || pieceDestination == 74 || pieceDestination == 75 || pieceDestination == 76) {
                targetDestination.add(0, 33);
            }

        } else if(colourId == COLOURIDYELLOW) {
            if(pieceDestination == 69 || pieceDestination == 70 || pieceDestination == 71 || pieceDestination == 72) {
                targetDestination.add(0, 17);
            }

        } else if(colourId == COLOURIDBLUE) {
            if(pieceDestination == 77 || pieceDestination == 78 || pieceDestination == 79 || pieceDestination == 80) {
                targetDestination.add(0, 49);
            }
        } else if (selectetAction == 0) {
            //ToDo: überprüfen ob gültiger Zug
            fieldsToGo = 1;
            //targetDestination.add(neuerStandort);
        } else if (selectetAction == 1) {
            //ToDo: überprüfen ob gültiger Zug
            fieldsToGo = 11;
            //targetDestination.add(neuerStandort);
        } else {
            throw new IllegalArgumentException();
        }

    }

    /**
     *
     * @param selectetAction ausgewählte Aktion: 0 = Figur auf Startfeld, 1 = 13 Felder fahren
     * @param colourId ColourId des Spieler oder des Partners
     * @param pieceDestination aktueller Standort der Figur die bewegt werden soll
     */
    public static void playCard13(int colourId, int pieceDestination) {
        if(colourId == COLOURIDRED) {
            if(pieceDestination == 65 || pieceDestination == 66 || pieceDestination == 67 || pieceDestination == 68) {
                targetDestination.add(0, 1);
            }
        } else if(colourId == COLOURIDGREEN) {
            if(pieceDestination == 73 || pieceDestination == 74 || pieceDestination == 75 || pieceDestination == 76) {
                targetDestination.add(0, 33);
            }

        } else if(colourId == COLOURIDYELLOW) {
            if(pieceDestination == 69 || pieceDestination == 70 || pieceDestination == 71 || pieceDestination == 72) {
                targetDestination.add(0, 17);
            }

        } else if(colourId == COLOURIDBLUE) {
            if(pieceDestination == 77 || pieceDestination == 78 || pieceDestination == 79 || pieceDestination == 80) {
                targetDestination.add(0, 49);
            }
        } else {
            //ToDO: überprüfen ob gültiger Zug
            fieldsToGo = 13;
            //targetDestination.add();
        }
    }

    /**
     * @param targetDestination Standort der Figuren die getauscht werdens sollen
     * @return targetDestination, neuer Standort der Figuren
     */
    public static List pieceExchange(List<Integer> targetDestination) {
        int store = targetDestination.get(0);
        targetDestination.set(0, 1);
        targetDestination.set(1, store);
        return targetDestination;
    }

    /**
     * @param colourIdPlayer       FarbId des Spielers
     * @param playerAndHand        Spieler und Hand
     * @param IdCardToPlay         id der Karte die der Spieler spielen möchte.
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
    public static void playQuestionCard(int colourIdPlayer, int colourIdPartner, PlayerAndHand playerAndHand, int idCardToPlay, List<Integer> pieceDestinations,
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
