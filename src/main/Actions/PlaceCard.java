package main.Actions;

import com.fasterxml.jackson.databind.node.ArrayNode;
import main.Cards.Card;
import main.Player;

import java.util.ArrayList;

public class PlaceCard {
    public PlaceCard() {
    }

    public PlaceCard(Player actingPlayer, int cardIdx, ArrayList<ArrayList<Card>> table, Player otherplayer) {
        int minMana;
        minMana = actingPlayer.getInHandCard().get(cardIdx).getMana();

        Card cardToPlace = new Card();
        cardToPlace = actingPlayer.getInHandCard().get(cardIdx);


        if (cardToPlace.getName().equals("The Ripper") ||
                cardToPlace.getName().equals("Miraj") ||
                cardToPlace.getName().equals("Goliath") ||
                cardToPlace.getName().equals("Warden")) {
            actingPlayer.addInFrontRow(cardToPlace);
        } else {
            actingPlayer.addInBackRow(cardToPlace);
        }

        if (actingPlayer.getIdx() == 2) {
            table.clear();
            table.add(0, actingPlayer.getBackRow());
            table.add(1, actingPlayer.getFrontRow());
            table.add(2, otherplayer.getFrontRow());
            table.add(3, otherplayer.getBackRow());
        } else {
            table.clear();
            table.add(0, otherplayer.getBackRow());
            table.add(1, otherplayer.getFrontRow());
            table.add(2, actingPlayer.getFrontRow());
            table.add(3, actingPlayer.getBackRow());
        }

        actingPlayer.setMana(actingPlayer.getMana() - minMana);
        actingPlayer.getInHandCard().remove(cardIdx);
    }
}
