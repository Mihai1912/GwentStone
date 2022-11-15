package main.Actions;

import main.Cards.Card;
import main.Player;

import java.util.ArrayList;

final public class PlaceCard {
    public PlaceCard() {
    }

    public PlaceCard(final Player actingPlayer, final int cardIdx,
                     final ArrayList<ArrayList<Card>> table, final Player otherplayer) {
        int minMana;
        minMana = actingPlayer.getInHandCard().get(cardIdx).getMana();

        Card cardToPlace;
        cardToPlace = actingPlayer.getInHandCard().get(cardIdx);


        if (cardToPlace.getName().equals("The Ripper")
                || cardToPlace.getName().equals("Miraj")
                || cardToPlace.getName().equals("Goliath")
                || cardToPlace.getName().equals("Warden")) {
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
