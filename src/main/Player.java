package main;

import main.Cards.Card;
import main.Cards.Hero;
import main.Cards.Minion;

import java.util.ArrayList;

final public class Player {
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

    private ArrayList<Card> environmentInHand = new ArrayList<>();

    public ArrayList<Card> getEnvironmentInHand() {
        return environmentInHand;
    }

    public void setEnvironmentInHand(final ArrayList<Card> environmentInHand) {
        this.environmentInHand = environmentInHand;
    }

    public void setInHandCard(final ArrayList<Card> inHandCard) {
        this.inHandCard = inHandCard;
    }

    public void setDeck(final ArrayList<Card> deck) {
        this.deck = deck;
    }

    public void setHero(final Card hero) {
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

    public void setIdx(final int idx) {
        this.idx = idx;
    }

    public void setMana(final int mana) {
        this.mana = mana;
    }

    public int getNoTurns() {
        return noTurns;
    }

    public void setNoTurns(final int noTurns) {
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

    public void setTurn(final boolean turn) {
        this.turn = turn;
    }

    public int getNoRound() {
        return noRound;
    }

    public void setNoRound(final int noRound) {
        this.noRound = noRound;
    }


    /**
     * Draw Card from deck and put in hand and in other ArrayList where is only environments
     */
    public void drawCard() {
        inHandCard.add(deck.get(0));
        if (deck.get(0).getName().equals("Winterfell") || deck.get(0).getName().equals("Firestorm")
                || deck.get(0).getName().equals("Heart Hound")) {
            environmentInHand.add(deck.get(0));
        }
        deck.remove(0);
    }

    /**
     *
     * @param player Player who finished his turn
     */
    public void endTurn(final Player player) {
        setTurn(false);
        player.setTurn(true);
        setNoTurns(0);
    }

    /**
     *
     * @param card Card for which it is checked that the player has enough hand
     * @return Truth value
     */
    public boolean enoughManaToPlaceCard(final Card card) {
        return getMana() >= card.getMana();
    }

    /**
     *
     * @param card Card that is added to the row in front of the current player
     */
    public void addInFrontRow(final Card card) {
        frontRow.add(card);
    }

    /**
     *
     * @param card Card that is added to the back row of the current player
     */
    public void addInBackRow(final Card card) {
        backRow.add(card);
    }

    /**
     *
     * @param row1 player's first row
     * @param row2 player's second row
     * @return The true value if there is a tank card on the player's rows
     */
    public boolean ifAttackTank(final ArrayList<Card> row1, final ArrayList<Card> row2) {
        for (Card card : row1) {
            if (((Minion) card).isTank()) {
                return true;
            }
        }
        for (Card card : row2) {
            if (((Minion) card).isTank()) {
                return true;
            }
        }
        return false;
    }
}
