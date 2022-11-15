package main.Cards;

import fileio.ActionsInput;
import main.Player;

import java.util.ArrayList;

final public class Environment extends Card {
    private int mana;

    /**
     *
     * @return Extract card mana
     */
    public int getMana() {
        return mana;
    }

    /**
     *
     * @param mana Desired mana for setting the mana of the current card
     */
    public void setMana(final int mana) {
        this.mana = mana;
    }


    public Environment() {
    }

    public Environment(final int mana, final String description,
                       final ArrayList<String> colors, final String name) {
        setColors(colors);
        setDescription(description);
        setName(name);
        setMana(mana);
    }

    public Environment(final Environment environment) {
        super(environment);
        this.mana = environment.getMana();
    }

    /**
     *
     * @param actingPlayer Player whose turn it is now
     * @param otherPlayer  Player on whom the action is taken
     * @param command      Acting player command
     */
    public void action(final Player actingPlayer, final Player otherPlayer,
                       final ActionsInput command) {
        switch (getName()) {
            case "Winterfell" -> {
                if (command.getAffectedRow() == 2 || command.getAffectedRow() == 1) {
                    for (Card card : otherPlayer.getFrontRow()) {
                        if (otherPlayer.getNoTurns() == 1) {
                            card.setFrozenForRound(true);
                            card.setUnfrozenRound(actingPlayer.getNoRound() + 1);
                        } else {
                            card.setFrozenForRound(true);
                            card.setUnfrozenRound(actingPlayer.getNoRound() + 2);
                        }
                    }
                } else {
                    for (Card card : otherPlayer.getBackRow()) {
//                    ((Minion)card).setFrozen(true);
                        if (otherPlayer.getNoTurns() == 1) {
                            card.setFrozenForRound(true);
                            card.setUnfrozenRound(actingPlayer.getNoRound() + 1);
                        } else {
                            card.setFrozenForRound(true);
                            card.setUnfrozenRound(actingPlayer.getNoRound() + 2);
                        }
                    }
                }
                actingPlayer.setMana(actingPlayer.getMana() - getMana());
                actingPlayer.getInHandCard().remove(command.getHandIdx());
                actingPlayer.getEnvironmentInHand().remove(command.getHandIdx());
            }
            case "Firestorm" -> {
                if (command.getAffectedRow() == 2 || command.getAffectedRow() == 1) {
                    for (Card card : otherPlayer.getFrontRow()) {
                        ((Minion) card).setHealt(((Minion) card).getHealth() - 1);
                    }
                } else {
                    for (Card card : otherPlayer.getBackRow()) {
                        ((Minion) card).setHealt(((Minion) card).getHealth() - 1);
                    }
                }
                actingPlayer.setMana(actingPlayer.getMana() - getMana());
                actingPlayer.getInHandCard().remove(command.getHandIdx());
                actingPlayer.getEnvironmentInHand().remove(command.getHandIdx());
            }
            case "Heart Hound" -> {
                int affectedRow = command.getAffectedRow();
                Card cardToSteal = new Card();
                if (affectedRow == 1 || affectedRow == 2) {
                    int maxHealth = 0;
                    for (Card card : otherPlayer.getFrontRow()) {
                        if (maxHealth < ((Minion) card).getHealth()) {
                            maxHealth = ((Minion) card).getHealth();
                            cardToSteal = card;
                        }
                    }
                    actingPlayer.getFrontRow().add(cardToSteal);
                    otherPlayer.getFrontRow().remove(cardToSteal);
                }
                if (affectedRow == 0 || affectedRow == 3) {
                    int maxHealth = 0;
                    for (Card card : otherPlayer.getBackRow()) {
                        if (maxHealth < ((Minion) card).getHealth()) {
                            maxHealth = ((Minion) card).getHealth();
                            cardToSteal = card;
                        }
                    }
                    actingPlayer.getBackRow().add(cardToSteal);
                    otherPlayer.getBackRow().remove(cardToSteal);
                }
            }
            default -> { }
        }
    }

    /**
     *
     * @return This returning a string, we can get a representation of an object as a string
     */
    @Override
    public String toString() {
        return "Environment{"
                +
                "mana="
                + getMana()
                +
                "name="
                + getName()
                +
                "description="
                + getDescription()
                +
                "colors="
                + getColors()
                +
                '}';
    }
}
