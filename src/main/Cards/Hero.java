package main.Cards;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fileio.ActionsInput;
import main.Player;

import java.util.ArrayList;

public class Hero extends Card{
    private int health = 30;
    private int mana;
    @JsonIgnore
    private boolean frozen = false;

    public boolean isFrozen() {
        return frozen;
    }

    public void setFrozen(boolean frozen) {
        this.frozen = frozen;
    }

    public int getHealth() {
        return health;
    }

    public int getMana() {
        return mana;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public Hero() { }

    public Hero(int mana , String description , ArrayList<String> colors,
                String name) {
        setColors(colors);
        setDescription(description);
        setName(name);
        setMana(mana);
    }

    public Hero(Hero hero) {
        setHealth(hero.getHealth());
        setMana(hero.getMana());
        setDescription(hero.getDescription());
        setName(hero.getName());
        setColors(hero.getColors());
    }

    public void action (Player actingPlayer , Player otherPlayer , ActionsInput command) {
        if (getName().equals("Lord Royce")) {
            if (otherPlayer.getIdx() == 1) {
                if (command.getAffectedRow() == 2) {
                    int maxAttack = 0;
                    Card targetCard = new Card();
                    for (Card card : otherPlayer.getFrontRow()) {
                        if (((Minion)card).getAttackDamage() > maxAttack) {
                            maxAttack = ((Minion)card).getAttackDamage();
                            targetCard = card;
                        }
                    }
                    targetCard.setFrozenForRound(true);
                } else {
                    int maxAttack = 0;
                    Card targetCard = new Card();
                    for (Card card : otherPlayer.getBackRow()) {
                        if (((Minion)card).getAttackDamage() > maxAttack) {
                            maxAttack = ((Minion)card).getAttackDamage();
                            targetCard = card;
                        }
                    }
                    targetCard.setFrozenForRound(true);
                }
            } else {
                if (command.getAffectedRow() == 1) {
                    int maxAttack = 0;
                    Card targetCard = new Card();
                    for (Card card : otherPlayer.getFrontRow()) {
                        if (((Minion)card).getAttackDamage() > maxAttack) {
                            maxAttack = ((Minion)card).getAttackDamage();
                            targetCard = card;
                        }
                    }
                    targetCard.setFrozenForRound(true);
                } else {
                    int maxAttack = 0;
                    Card targetCard = new Card();
                    for (Card card : otherPlayer.getBackRow()) {
                        if (((Minion)card).getAttackDamage() > maxAttack) {
                            maxAttack = ((Minion)card).getAttackDamage();
                            targetCard = card;
                        }
                    }
                    targetCard.setFrozenForRound(true);
                }
            }
        } else if (getName().equals("Empress Thorina")) {
            if (otherPlayer.getIdx() == 1) {
                if (command.getAffectedRow() == 2) {
                    int maxHealth = 0;
                    Card targetCard = new Card();
                    for (Card card : otherPlayer.getFrontRow()) {
                        if (((Minion)card).getHealth() > maxHealth) {
                            maxHealth = ((Minion)card).getHealth();
                            targetCard = card;
                        }
                    }
                    ((Minion)targetCard).setHealt(0);
                } else {
                    int maxHealth = 0;
                    Card targetCard = new Card();
                    for (Card card : otherPlayer.getBackRow()) {
                        if (((Minion)card).getHealth() > maxHealth) {
                            maxHealth = ((Minion)card).getHealth();
                            targetCard = card;
                        }
                    }
                    ((Minion)targetCard).setHealt(0);
                }
            } else {
                if (command.getAffectedRow() == 1) {
                    int maxHealth = 0;
                    Card targetCard = new Card();
                    for (Card card : otherPlayer.getFrontRow()) {
                        if (((Minion)card).getHealth() > maxHealth) {
                            maxHealth = ((Minion)card).getHealth();
                            targetCard = card;
                        }
                    }
                    ((Minion)targetCard).setHealt(0);
                } else {
                    int maxHealth = 0;
                    Card targetCard = new Card();
                    for (Card card : otherPlayer.getBackRow()) {
                        if (((Minion)card).getHealth() > maxHealth) {
                            maxHealth = ((Minion)card).getHealth();
                            targetCard = card;
                        }
                    }
                    ((Minion)targetCard).setHealt(0);
                }
            }
        } else if (getName().equals("General Kocioraw")) {
//            if (otherPlayer.getIdx() == 1) {
//                if (command.getAffectedRow() == 2) {
//                    for (Card card : otherPlayer.getFrontRow()) {
//                        ((Minion)card).setAttackDamage(((Minion)card).getAttackDamage()+1);
//                    }
//                } else if (command.getAffectedRow() == 3){
//                    for (Card card : otherPlayer.getBackRow()) {
//                        ((Minion)card).setAttackDamage(((Minion)card).getAttackDamage()+1);
//                    }
//                }
//            } else if (otherPlayer.getIdx() == 2){
//                if (command.getAffectedRow() == 1) {
//                    for (Card card : otherPlayer.getFrontRow()) {
//                        ((Minion)card).setAttackDamage(((Minion)card).getAttackDamage()+1);
//                    }
//                } else if (command.getAffectedRow() == 0){
//                    for (Card card : otherPlayer.getBackRow()) {
//                        ((Minion)card).setAttackDamage(((Minion)card).getAttackDamage()+1);
//                    }
//                }
//            }
            if (actingPlayer.getIdx() == 1) {
                if (command.getAffectedRow() == 2) {
                    for (Card card : actingPlayer.getFrontRow()) {
                        ((Minion)card).setAttackDamage(((Minion)card).getAttackDamage()+1);
                    }
                } else if (command.getAffectedRow() == 3){
                    for (Card card : actingPlayer.getBackRow()) {
                        ((Minion)card).setAttackDamage(((Minion)card).getAttackDamage()+1);
                    }
                }
            } else if (actingPlayer.getIdx() == 2){
                if (command.getAffectedRow() == 1) {
                    for (Card card : actingPlayer.getFrontRow()) {
                        ((Minion)card).setAttackDamage(((Minion)card).getAttackDamage()+1);
                    }
                } else if (command.getAffectedRow() == 0){
                    for (Card card : actingPlayer.getBackRow()) {
                        ((Minion)card).setAttackDamage(((Minion)card).getAttackDamage()+1);
                    }
                }
            }
        } else if (getName().equals("King Mudface")) {
//            if (otherPlayer.getIdx() == 1) {
//                if (command.getAffectedRow() == 2) {
//                    for (Card card : otherPlayer.getFrontRow()) {
//                        ((Minion)card).setHealt(((Minion)card).getHealth()+1);
//                    }
//                } else {
//                    for (Card card : otherPlayer.getBackRow()) {
//                        ((Minion)card).setHealt(((Minion)card).getHealth()+1);
//                    }
//                }
//            } else {
//                if (command.getAffectedRow() == 1) {
//                    for (Card card : otherPlayer.getFrontRow()) {
//                        ((Minion)card).setHealt(((Minion)card).getHealth()+1);
//                    }
//                } else {
//                    for (Card card : otherPlayer.getBackRow()) {
//                        ((Minion)card).setHealt(((Minion)card).getHealth()+1);
//                    }
//                }
//            }
            if (actingPlayer.getIdx() == 1) {
                if (command.getAffectedRow() == 2) {
                    for (Card card : actingPlayer.getFrontRow()) {
                        ((Minion)card).setHealt(((Minion)card).getHealth()+1);
                    }
                } else {
                    for (Card card : actingPlayer.getBackRow()) {
                        ((Minion)card).setHealt(((Minion)card).getHealth()+1);
                    }
                }
            } else {
                if (command.getAffectedRow() == 1) {
                    for (Card card : actingPlayer.getFrontRow()) {
                        ((Minion)card).setHealt(((Minion)card).getHealth()+1);
                    }
                } else {
                    for (Card card : actingPlayer.getBackRow()) {
                        ((Minion)card).setHealt(((Minion)card).getHealth()+1);
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Hero{" +
                "name=" + getName() +
                ", description=" + getDescription() +
                ", health=" + getHealth() +
                ", mana=" + getMana() +
                ", colors=" + getColors() +
                '}';
    }
}
