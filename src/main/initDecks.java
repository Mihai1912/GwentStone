package main;

import fileio.CardInput;
import fileio.Input;

import java.util.ArrayList;
import java.util.Random;

import static java.util.Collections.shuffle;

public class initDecks {
    ArrayList<CardInput> deckPlayer1;
    ArrayList<CardInput> deckPlayer2;
    public initDecks(Input inputData) {
        ArrayList<ArrayList<CardInput>> decksPlayer1;
        decksPlayer1 = inputData.getPlayerOneDecks().getDecks();
        ArrayList<ArrayList<CardInput>> decksPlayer2;
        decksPlayer2 = inputData.getPlayerTwoDecks().getDecks();
        int noGames = inputData.getGames().size();
        int noGame = noGames - 1;

        deckPlayer1 = decksPlayer1.get(inputData.getGames().get(noGame).getStartGame().getPlayerOneDeckIdx());
        shuffle(deckPlayer1 , new Random(inputData.getGames().get(noGame).getStartGame().getShuffleSeed()));

        deckPlayer2 = decksPlayer2.get(inputData.getGames().get(noGame).getStartGame().getPlayerTwoDeckIdx());
        shuffle(deckPlayer2 , new Random(inputData.getGames().get(noGame).getStartGame().getShuffleSeed()));
    }
}
