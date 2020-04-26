package ch.zhaw.psit3.crazydog.Model.Card;


public class Card {
    private int id = 0;
    private String name = "";
    private int value = 0;

    public Card(int cardID, String name, int value) {
        this.id = cardID;
        this.name = name;
        this.value = value;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

}
