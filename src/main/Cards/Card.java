package main.Cards;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fileio.ActionsInput;
import main.Player;

import java.security.PublicKey;
import java.util.ArrayList;

public class Card {
    private String description;
    private ArrayList<String> colors;
    private String name;


    public String getDescription() {
        return description;
    }

    public ArrayList<String> getColors() {
        return colors;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setColors(ArrayList<String> colors) {
        this.colors = colors;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMana() {
        return 0;
    }

//    public void setHealt(int healt) {
//    }
//    public int getHealth() {return 0; }
//
//    public int getAttackDamage() {
//        return 0;
//    }

    public Card(Card card) {
        this.description = card.getDescription();
        this.colors = card.getColors();
        this.name = card.getName();
    }

    public Card() {
    }

    public void action(Player actingPlayer, Player otherPlayer, ActionsInput command) {
    }

    @JsonIgnore
    public int getUnfrozenRound() {
        return 0;
    }

    @JsonIgnore
    public void setUnfrozenRound(int unfrozenRound) {
    }

    @JsonIgnore
    public void setFrozenForRound(boolean frozenForRound) {

    }

    @Override
    public String toString() {
        return "Card{" +
                "description='" + description + '\'' +
                ", colors=" + colors +
                ", name='" + name + '\'' +
                '}';
    }
}
