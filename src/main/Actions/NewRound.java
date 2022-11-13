package main.Actions;

import main.Cards.Card;
import main.Cards.Minion;
import main.Player;

public class NewRound {

    public NewRound() {
    }

    public NewRound(Player actingPlayer, Player otherPlayer) {
        actingPlayer.setNoRound((actingPlayer.getNoRound() + 1));
        otherPlayer.setNoRound((otherPlayer.getNoRound() + 1));
//        System.out.println("nr Runde " + actingPlayer.getNoRound());
        if (actingPlayer.getDeck().size() > 0) {
            actingPlayer.drawCard();
        }
        if (otherPlayer.getDeck().size() > 0) {
            otherPlayer.drawCard();
        }
        actingPlayer.setMana(actingPlayer.getMana() + actingPlayer.getNoRound());
        otherPlayer.setMana(otherPlayer.getMana() + actingPlayer.getNoRound());
        for (Card card : actingPlayer.getFrontRow()) {
            ((Minion) card).setFrozen(false);
        }
        for (Card card : actingPlayer.getBackRow()) {
            ((Minion) card).setFrozen(false);
        }
        for (Card card : otherPlayer.getFrontRow()) {
            ((Minion) card).setFrozen(false);
        }
        for (Card card : otherPlayer.getBackRow()) {
            ((Minion) card).setFrozen(false);
        }
        if (actingPlayer.getFrontRow().size() != 0) {
            for (Card cardAux : actingPlayer.getFrontRow()) {
                if (cardAux.getUnfrozenRound() == actingPlayer.getNoRound()) {
                    cardAux.setUnfrozenRound(0);
                    cardAux.setFrozenForRound(false);
                }
            }
        }
        if (actingPlayer.getBackRow().size() != 0) {
            for (Card cardAux : actingPlayer.getBackRow()) {
                if (cardAux.getUnfrozenRound() == actingPlayer.getNoRound()) {
                    cardAux.setUnfrozenRound(0);
                    cardAux.setFrozenForRound(false);
                }
            }
        }
        if (otherPlayer.getFrontRow().size() != 0) {
            for (Card cardAux : otherPlayer.getFrontRow()) {
                if (cardAux.getUnfrozenRound() == otherPlayer.getNoRound()) {
                    cardAux.setUnfrozenRound(0);
                    cardAux.setFrozenForRound(false);
                }
            }
        }

        if (otherPlayer.getBackRow().size() != 0) {
            for (Card cardAux : otherPlayer.getBackRow()) {
                if (cardAux.getUnfrozenRound() == otherPlayer.getNoRound()) {
                    cardAux.setUnfrozenRound(0);
                    cardAux.setFrozenForRound(false);
                }
            }
        }
        actingPlayer.getHero().setFrozen(false);
        otherPlayer.getHero().setFrozen(false);
    }
}
