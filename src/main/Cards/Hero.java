package main.Cards;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fileio.ActionsInput;
import main.Player;

import java.util.ArrayList;

final public class Hero extends Card {
    private int health = 30;
    private int mana;
    @JsonIgnore
    private boolean frozen = false;



    public boolean isFrozen() {
        return frozen;
    }

    public void setFrozen(final boolean frozen) {
        this.frozen = frozen;
    }

    public int getHealth() {
        return health;
    }

    public int getMana() {
        return mana;
    }

    public void setHealth(final int health) {
        this.health = health;
    }

    public void setMana(final int mana) {
        this.mana = mana;
    }

    public Hero() {
    }

    public Hero(final int mana, final String description, final ArrayList<String> colors,
                final String name) {
        setColors(colors);
        setDescription(description);
        setName(name);
        setMana(mana);
    }

    public Hero(final Hero hero) {
        setHealth(hero.getHealth());
        setMana(hero.getMana());
        setDescription(hero.getDescription());
        setName(hero.getName());
        setColors(hero.getColors());
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
            case "Lord Royce":
                if (otherPlayer.getIdx() == 1) {
                    int maxAttack = 0;
                    Card targetCard = new Card();
                    if (command.getAffectedRow() == 2) {
                        for (Card card : otherPlayer.getFrontRow()) {
                            if (((Minion) card).getAttackDamage() > maxAttack) {
                                maxAttack = ((Minion) card).getAttackDamage();
                                targetCard = card;
                            }
                        }
                    } else {
                        for (Card card : otherPlayer.getBackRow()) {
                            if (((Minion) card).getAttackDamage() > maxAttack) {
                                maxAttack = ((Minion) card).getAttackDamage();
                                targetCard = card;
                            }
                        }
                    }
                    targetCard.setFrozenForRound(true);
                } else {
                    int maxAttack = 0;
                    Card targetCard = new Card();
                    if (command.getAffectedRow() == 1) {
                        for (Card card : otherPlayer.getFrontRow()) {
                            if (((Minion) card).getAttackDamage() > maxAttack) {
                                maxAttack = ((Minion) card).getAttackDamage();
                                targetCard = card;
                            }
                        }
                    } else {
                        for (Card card : otherPlayer.getBackRow()) {
                            if (((Minion) card).getAttackDamage() > maxAttack) {
                                maxAttack = ((Minion) card).getAttackDamage();
                                targetCard = card;
                            }
                        }
                    }
                    targetCard.setFrozenForRound(true);
                }
                break;
            case "Empress Thorina":
                if (otherPlayer.getIdx() == 1) {
                    int maxHealth = 0;
                    Card targetCard = new Card();
                    if (command.getAffectedRow() == 2) {
                        for (Card card : otherPlayer.getFrontRow()) {
                            if (((Minion) card).getHealth() > maxHealth) {
                                maxHealth = ((Minion) card).getHealth();
                                targetCard = card;
                            }
                        }
                    } else {
                        for (Card card : otherPlayer.getBackRow()) {
                            if (((Minion) card).getHealth() > maxHealth) {
                                maxHealth = ((Minion) card).getHealth();
                                targetCard = card;
                            }
                        }
                    }
                    ((Minion) targetCard).setHealt(0);
                } else {
                    int maxHealth = 0;
                    Card targetCard = new Card();
                    if (command.getAffectedRow() == 1) {
                        for (Card card : otherPlayer.getFrontRow()) {
                            if (((Minion) card).getHealth() > maxHealth) {
                                maxHealth = ((Minion) card).getHealth();
                                targetCard = card;
                            }
                        }
                    } else {
                        for (Card card : otherPlayer.getBackRow()) {
                            if (((Minion) card).getHealth() > maxHealth) {
                                maxHealth = ((Minion) card).getHealth();
                                targetCard = card;
                            }
                        }
                    }
                    ((Minion) targetCard).setHealt(0);
                }
                break;
            case "General Kocioraw":
                if (actingPlayer.getIdx() == 1) {
                    if (command.getAffectedRow() == 2) {
                        for (Card card : actingPlayer.getFrontRow()) {
                            ((Minion) card).setAttackDamage(((Minion) card).getAttackDamage() + 1);
                        }
                    } else if (command.getAffectedRow() == 3) {
                        for (Card card : actingPlayer.getBackRow()) {
                            ((Minion) card).setAttackDamage(((Minion) card).getAttackDamage() + 1);
                        }
                    }
                } else if (actingPlayer.getIdx() == 2) {
                    if (command.getAffectedRow() == 1) {
                        for (Card card : actingPlayer.getFrontRow()) {
                            ((Minion) card).setAttackDamage(((Minion) card).getAttackDamage() + 1);
                        }
                    } else if (command.getAffectedRow() == 0) {
                        for (Card card : actingPlayer.getBackRow()) {
                            ((Minion) card).setAttackDamage(((Minion) card).getAttackDamage() + 1);
                        }
                    }
                }
                break;
            case "King Mudface":
                if (actingPlayer.getIdx() == 1) {
                    if (command.getAffectedRow() == 2) {
                        for (Card card : actingPlayer.getFrontRow()) {
                            ((Minion) card).setHealt(((Minion) card).getHealth() + 1);
                        }
                    } else {
                        for (Card card : actingPlayer.getBackRow()) {
                            ((Minion) card).setHealt(((Minion) card).getHealth() + 1);
                        }
                    }
                } else {
                    if (command.getAffectedRow() == 1) {
                        for (Card card : actingPlayer.getFrontRow()) {
                            ((Minion) card).setHealt(((Minion) card).getHealth() + 1);
                        }
                    } else {
                        for (Card card : actingPlayer.getBackRow()) {
                            ((Minion) card).setHealt(((Minion) card).getHealth() + 1);
                        }
                    }
                }
                break;
            default:
                break;
        }
    }

    @Override
    public String toString() {
        return "Hero{"
                +
                "name="
                + getName()
                +
                ", description="
                + getDescription()
                +
                ", health="
                + getHealth()
                +
                ", mana="
                + getMana()
                +
                ", colors="
                + getColors()
                +
                '}';
    }
}
