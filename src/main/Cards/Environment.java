package main.Cards;

import fileio.ActionsInput;
import main.Player;

import java.util.ArrayList;

public class Environment extends Card{
    private int mana;

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }


    public Environment() { }

    public Environment(int mana , String description , ArrayList<String> colors , String name) {
        setColors(colors);
        setDescription(description);
        setName(name);
        setMana(mana);
    }

    public Environment(Environment environment) {
        super(environment);
        this.mana = environment.getMana();
    }

    public void action(Player actingPlayer , Player otherPlayer , ActionsInput command) {
        if (getName().equals("Winterfell")) {
//            System.out.println("use Winterfell");
            command.getAffectedRow();
            if (command.getAffectedRow() == 2 || command.getAffectedRow() == 1) {
                for (Card card : otherPlayer.getFrontRow()) {
                    ((Minion)card).setFrozen(true);
                    ((Minion)card).setFrozenForRound(true);
                    ((Minion)card).setUnfrozenRound(actingPlayer.getNoRound()+2);
                }
            } else {
                for (Card card : otherPlayer.getBackRow()) {
                    ((Minion)card).setFrozen(true);
                    ((Minion)card).setFrozenForRound(true);
                    ((Minion)card).setUnfrozenRound(actingPlayer.getNoRound()+2);
                }
            }
            actingPlayer.setMana(actingPlayer.getMana()-getMana());
            actingPlayer.getInHandCard().remove(command.getHandIdx());
//            System.out.println(getName());
        } else if (getName().equals("Firestorm")) {
//            System.out.println(getName());
        } else if (getName().equals("Heart Hound")) {
//            System.out.println(getName());
        }
    }

    @Override
    public String toString() {
        return "Environment{" +
                "mana=" + getMana() +
                "name=" + getName() +
                "description=" + getDescription() +
                "colors=" + getColors() +
                '}';
    }
}
