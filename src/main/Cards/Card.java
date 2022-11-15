package main.Cards;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fileio.ActionsInput;
import main.Player;

import java.util.ArrayList;

public class Card {
    private String description;
    private ArrayList<String> colors;
    private String name;

    /**
     * @return Extract card description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return Extract card colors
     */
    public ArrayList<String> getColors() {
        return colors;
    }

    /**
     * @return Extract card name
     */

    public String getName() {
        return name;
    }

    /**
     * @param description Desired description for setting the description of the current card
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * @param colors Desired colors for setting the colors of the current card
     */
    public void setColors(final ArrayList<String> colors) {
        this.colors = colors;
    }

    /**
     * @param name Desired name for setting the name of the current card
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @return Extract card mana
     */
    public int getMana() {
        return 0;
    }

    public Card(final Card card) {
        this.description = card.getDescription();
        this.colors = card.getColors();
        this.name = card.getName();
    }

    public Card() {
    }

    /**
     * @param actingPlayer Player whose turn it is now
     * @param otherPlayer  Player on whom the action is taken
     * @param command      Acting player command
     */
    public void action(final Player actingPlayer, final Player otherPlayer,
                       final ActionsInput command) {
    }

    /**
     *
     * @return Check if the card is frozen for the next round
     */
    @JsonIgnore
    public int getUnfrozenRound() {
        return 0;
    }

    /**
     *
     * @param unfrozenRound Round in which the card is unfrozen
     */
    @JsonIgnore
    public void setUnfrozenRound(final int unfrozenRound) {
    }

    /**
     *
     * @param frozenForRound Sets the card as frozen for the next round
     */
    @JsonIgnore
    public void setFrozenForRound(final boolean frozenForRound) {

    }

    /**
     *
     * @return This returning a string, we can get a representation of an object as a string
     */
    @Override
    public String toString() {
        return "Card{"
                +
                "description='"
                + description
                + '\''
                +
                ", colors="
                + colors
                +
                ", name='"
                + name
                + '\''
                +
                '}';
    }
}
