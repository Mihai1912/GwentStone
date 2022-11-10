package main;

import fileio.CardInput;
import fileio.Input;
import main.Cards.Card;
import main.Cards.Environment;

import java.util.ArrayList;
import java.util.Random;

import static java.util.Collections.shuffle;

public class initDecks {
    ArrayList<Card> deckPlayer1 = new ArrayList<>();
    ArrayList<Card> deckPlayer2 = new ArrayList<>();
    public initDecks(Input inputData) {
        ArrayList<ArrayList<CardInput>> decksPlayer1;
        decksPlayer1 = inputData.getPlayerOneDecks().getDecks();
        ArrayList<ArrayList<CardInput>> decksPlayer2;
        decksPlayer2 = inputData.getPlayerTwoDecks().getDecks();
        int noGames = inputData.getGames().size();
        int noGame = noGames - 1;
        int deckSize = inputData.getPlayerOneDecks().getNrCardsInDeck();
        int player1DeckIdx = inputData.getGames().get(0).getStartGame().getPlayerOneDeckIdx();
        int player2DeckIdx = inputData.getGames().get(0).getStartGame().getPlayerTwoDeckIdx();

        for (int i = 0 ; i < deckSize ; i++) {
            CardTypeIdentificator cardDeck1 = new CardTypeIdentificator(decksPlayer1.get(player1DeckIdx).get(i));
            deckPlayer1.add(cardDeck1.card);
            CardTypeIdentificator cardDeck2 = new CardTypeIdentificator(decksPlayer2.get(player2DeckIdx).get(i));
            deckPlayer2.add(cardDeck2.card);
        }
        shuffle(deckPlayer1 , new Random(inputData.getGames().get(noGame).getStartGame().getShuffleSeed()));
        shuffle(deckPlayer2 , new Random(inputData.getGames().get(noGame).getStartGame().getShuffleSeed()));
    }
}
