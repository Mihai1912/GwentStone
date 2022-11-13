package main.Actions;

import main.Player;

public class EndRound {

    public EndRound() {
    }

    public EndRound(Player actingPlayer, Player otherPlayer) {
        actingPlayer.setNoTurns(1);
        otherPlayer.setNoTurns(1);
        if (actingPlayer.isTurn()) {
            actingPlayer.setTurn(false);
            otherPlayer.setTurn(true);
        }
    }
}
