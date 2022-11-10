package main;

import fileio.CardInput;
import main.Cards.Card;
import main.Cards.Environment;
import main.Cards.Hero;

import java.util.ArrayList;

public class Player {
    private int idx;
    private boolean turn;
    private ArrayList<Card> inHandCard = new ArrayList<>();
    private Hero hero;
    private ArrayList<Card> deck = new ArrayList<>();
    private int mana;


    public void setInHandCard(ArrayList<Card> inHandCard) {
        this.inHandCard = inHandCard;
    }

    public void setDeck(ArrayList<Card> deck) {
        this.deck = deck;
    }

    public void setHero(Card hero) {
        this.hero = (Hero) hero;
    }

    public ArrayList<Card> getInHandCard() {
        return inHandCard;
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public Hero getHero() {
        return hero;
    }

    public int getIdx() {
        return idx;
    }

    public int getMana() {
        return mana;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    void drawCard (ArrayList<Card> deck) {
        inHandCard.add(deck.get(0));
        deck.remove(0);
    }
}
