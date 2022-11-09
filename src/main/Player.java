package main;

import fileio.CardInput;

import java.util.ArrayList;

public class Player {
    private int idx;
    private boolean turn;
    private ArrayList<CardInput> inHandCard = new ArrayList<>();
    private CardInput hero;
    private ArrayList<CardInput> deck = new ArrayList<>();
    private int mana;


    public void setDeck(ArrayList<CardInput> deck) {
        this.deck = deck;
    }

    public void setInHandCard(ArrayList<CardInput> inHandCard) {
        this.inHandCard = inHandCard;
    }

    public void setHero(CardInput hero) {
        this.hero = hero;
    }

    public ArrayList<CardInput> getDeck() {
        return deck;
    }

    public ArrayList<CardInput> getInHandCard() {
        return inHandCard;
    }

    public CardInput getHero() {
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

    void drawCard (ArrayList<CardInput> deck) {
        inHandCard.add(deck.get(0));
        deck.remove(0);
    }
}
