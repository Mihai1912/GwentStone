package main.Cards;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fileio.ActionsInput;
import main.Player;

import java.util.ArrayList;

final public class Minion extends Card {
    private int attackDamage;
    private int health;
    private int mana;

    @JsonIgnore
    private boolean frozen = false;

    @JsonIgnore
    private boolean frozenForRound = false;

    @JsonIgnore
    private int unfrozenRound = 0;

    @JsonIgnore
    private boolean tank;

    public int getAttackDamage() {
        return attackDamage;
    }

    public int getHealth() {
        return health;
    }

    public int getMana() {
        return mana;
    }

    public void setAttackDamage(final int attackDamage) {
        this.attackDamage = attackDamage;
    }

    public void setHealt(final int healt) {
        this.health = healt;
    }

    public void setMana(final int mana) {
        this.mana = mana;
    }

    public boolean isTank() {
        return tank;
    }

    public void setTank(final boolean tank) {
        this.tank = tank;
    }


    public boolean isFrozen() {
        return frozen;
    }

    public void setFrozen(final boolean frozen) {
        this.frozen = frozen;
    }

    public boolean isFrozenForRound() {
        return frozenForRound;
    }

    public int getUnfrozenRound() {
        return unfrozenRound;
    }

    public void setFrozenForRound(final boolean frozenForRound) {
        this.frozenForRound = frozenForRound;
    }

    public void setUnfrozenRound(final int unfrozenRound) {
        this.unfrozenRound = unfrozenRound;
    }

    public Minion() {
    }

    public Minion(final int mana, final String description, final ArrayList<String> colors,
                  final String name, final int healt, final int attackDamage) {
        setColors(colors);
        setDescription(description);
        setName(name);
        setMana(mana);
        setHealt(healt);
        setAttackDamage(attackDamage);
    }

    public Minion(final Minion minion) {
        super(minion);
        this.mana = minion.getMana();
        this.health = minion.getHealth();
        this.attackDamage = minion.getAttackDamage();
    }

    public void action(final Player actingPlayer, final Player otherPlayer,
                       final ActionsInput command) {
        Card cardAttacked = new Card();
        Card cardAttacker = new Card();
        int aux = 0;
        if (getName().equals("Disciple")) {
            int xAttacked = command.getCardAttacked().getX();
            int yAttacked = command.getCardAttacked().getY();
            if (xAttacked == 1 || xAttacked == 2) {
                cardAttacked = actingPlayer.getFrontRow().get(yAttacked);
                ((Minion) cardAttacked).setHealt(((Minion) cardAttacked).getHealth() + 2);
            } else {
                cardAttacked = actingPlayer.getBackRow().get(yAttacked);
                ((Minion) cardAttacked).setHealt(((Minion) cardAttacked).getHealth() + 2);
            }
        } else if (getName().equals("The Ripper")) {
            int xAttacked = command.getCardAttacked().getX();
            int yAttacked = command.getCardAttacked().getY();
            if (xAttacked == 1 || xAttacked == 2) {
                cardAttacked = otherPlayer.getFrontRow().get(yAttacked);
                if (((Minion) cardAttacked).getAttackDamage() < 2) {
                    ((Minion) cardAttacked).setAttackDamage(0);
                } else {
                    ((Minion) cardAttacked).setAttackDamage(((Minion) cardAttacked)
                            .getAttackDamage() - 2);
                }
            } else {
                cardAttacked = otherPlayer.getBackRow().get(yAttacked);
                if (((Minion) cardAttacked).getAttackDamage() < 2) {
                    ((Minion) cardAttacked).setAttackDamage(0);
                } else {
                    ((Minion) cardAttacked).setAttackDamage(((Minion) cardAttacked)
                            .getAttackDamage() - 2);
                }
            }
        } else if (getName().equals("Miraj")) {
            int xAttacker = command.getCardAttacker().getX();
            int yAttacker = command.getCardAttacker().getY();
            int xAttacked = command.getCardAttacked().getX();
            int yAttacked = command.getCardAttacked().getY();
            if (xAttacked == 1 || xAttacked == 2) {
                cardAttacked = otherPlayer.getFrontRow().get(yAttacked);
            } else {
                cardAttacked = otherPlayer.getBackRow().get(yAttacked);
            }
            if (xAttacker == 1 || xAttacker == 2) {
                cardAttacker = actingPlayer.getFrontRow().get(yAttacker);
            } else {
                cardAttacker = actingPlayer.getBackRow().get(yAttacker);
            }
            aux = ((Minion) cardAttacker).getHealth();
            ((Minion) cardAttacker).setHealt(((Minion) cardAttacked).getHealth());
            ((Minion) cardAttacked).setHealt(aux);
        } else if (getName().equals("The Cursed One")) {
            int xAttacked = command.getCardAttacked().getX();
            int yAttacked = command.getCardAttacked().getY();
            if (xAttacked == 1 || xAttacked == 2) {
                cardAttacked = otherPlayer.getFrontRow().get(yAttacked);
                aux = ((Minion) cardAttacked).getHealth();
                ((Minion) cardAttacked).setHealt(((Minion) cardAttacked).getAttackDamage());
                ((Minion) cardAttacked).setAttackDamage(aux);
            } else {
                cardAttacked = otherPlayer.getBackRow().get(yAttacked);
                aux = ((Minion) cardAttacked).getHealth();
                ((Minion) cardAttacked).setHealt(((Minion) cardAttacked).getAttackDamage());
                ((Minion) cardAttacked).setAttackDamage(aux);
            }
        }
    }

    @Override
    public String toString() {
        return "Minion{"
                +
                "attackDamage="
                + getAttackDamage()
                +
                ", health="
                + getHealth()
                +
                ", mana="
                + getMana()
                +
                ", name="
                + getName()
                +
                ", colors="
                + getColors()
                +
                ", description="
                + getDescription()
                +
                '}';
    }
}
