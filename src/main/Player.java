package main;

import fileio.CardInput;
import main.Cards.Card;
import main.Cards.Environment;
import main.Cards.Hero;
import main.Cards.Minion;

import java.util.ArrayList;

public class Player {
    private int idx;
    private boolean turn;
    private int noTurns = 1;
    private ArrayList<Card> inHandCard = new ArrayList<>();
    private Hero hero;
    private ArrayList<Card> deck = new ArrayList<>();
    private int mana = 0;

    private int noRound = 0;

    private ArrayList<Card> frontRow = new ArrayList<>();
    private ArrayList<Card> backRow = new ArrayList<>();


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

    public int getNoTurns() {
        return noTurns;
    }

    public void setNoTurns(int noTurns) {
        this.noTurns = noTurns;
    }

    public boolean isTurn() {
        return turn;
    }

    public ArrayList<Card> getFrontRow() {
        return frontRow;
    }

    public ArrayList<Card> getBackRow() {
        return backRow;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public int getNoRound() {
        return noRound;
    }

    public void setNoRound(int noRound) {
        this.noRound = noRound;
    }



    public void drawCard () {
        inHandCard.add(deck.get(0));
        deck.remove(0);
    }

    public void endTurn (Player player) {
        setTurn(false);
        player.setTurn(true);
        setNoTurns(0);
    }

    public boolean enoughManaToPlaceCard (Card card) {
        return getMana() >= card.getMana();
    }

    public void addInFrontRow (Card card) {
        frontRow.add(card);
    }

    public  void addInBackRow (Card card) {
        backRow.add(card);
    }

    public boolean ifAttackTank (ArrayList<Card> row1 , ArrayList<Card> row2) {
        for (Card card : row1) {
            if (((Minion)card).isTank()) {
                return true;
            }
        }
        for (Card card : row2) {
            if (((Minion)card).isTank()) {
                return true;
            }
        }
        return false;
    }
}
