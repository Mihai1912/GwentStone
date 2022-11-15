package main;

import fileio.CardInput;
import fileio.Input;
import main.Cards.Card;

import java.util.ArrayList;
import java.util.Random;

import static java.util.Collections.shuffle;

final public class InitDecks {
    private ArrayList<Card> deckPlayer1 = new ArrayList<>();
    private ArrayList<Card> deckPlayer2 = new ArrayList<>();

    public ArrayList<Card> getDeckPlayer1() {
        return deckPlayer1;
    }

    public ArrayList<Card> getDeckPlayer2() {
        return deckPlayer2;
    }

    public InitDecks(final Input inputData, final int game) {
        ArrayList<ArrayList<CardInput>> decksPlayer1;
        decksPlayer1 = inputData.getPlayerOneDecks().getDecks();
        ArrayList<ArrayList<CardInput>> decksPlayer2;
        decksPlayer2 = inputData.getPlayerTwoDecks().getDecks();
        int deckSize = inputData.getPlayerOneDecks().getNrCardsInDeck();
        int player1DeckIdx = inputData.getGames().get(game).getStartGame().getPlayerOneDeckIdx();
        int player2DeckIdx = inputData.getGames().get(game).getStartGame().getPlayerTwoDeckIdx();

        for (int i = 0; i < deckSize; i++) {
            CardTypeIdentificator cardDeck1 =
                    new CardTypeIdentificator(decksPlayer1.get(player1DeckIdx).get(i));
            deckPlayer1.add(cardDeck1.getCard());
            CardTypeIdentificator cardDeck2 =
                    new CardTypeIdentificator(decksPlayer2.get(player2DeckIdx).get(i));
            deckPlayer2.add(cardDeck2.getCard());
        }
        shuffle(deckPlayer1, new Random(inputData.getGames().get(game)
                .getStartGame().getShuffleSeed()));
        shuffle(deckPlayer2, new Random(inputData.getGames().get(game)
                .getStartGame().getShuffleSeed()));
    }
}
