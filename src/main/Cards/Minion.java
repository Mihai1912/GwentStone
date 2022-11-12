package main.Cards;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;

public class Minion extends Card{
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

    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    public void setHealt(int healt) {
        this.health = healt;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public boolean isTank() {
        return tank;
    }

    public void setTank(boolean tank) {
        this.tank = tank;
    }


    public boolean isFrozen() {
        return frozen;
    }

    public void setFrozen(boolean frozen) {
        this.frozen = frozen;
    }

    public boolean isFrozenForRound() {
        return frozenForRound;
    }

    public int getUnfrozenRound() {
        return unfrozenRound;
    }

    public void setFrozenForRound(boolean frozenForRound) {
        this.frozenForRound = frozenForRound;
    }

    public void setUnfrozenRound(int unfrozenRound) {
        this.unfrozenRound = unfrozenRound;
    }

    public Minion() { }

    public Minion(int mana , String description , ArrayList<String> colors,
                  String name , int healt , int attackDamage) {
        setColors(colors);
        setDescription(description);
        setName(name);
        setMana(mana);
        setHealt(healt);
        setAttackDamage(attackDamage);
    }

    public Minion(Minion minion) {
        super(minion);
        this.mana = minion.getMana();
        this.health = minion.getHealth();
        this.attackDamage = minion.getAttackDamage();
    }

    @Override
    public void action() {
        super.action();
    }

    @Override
    public String toString() {
        return "Minion{" +
                "attackDamage=" + getAttackDamage() +
                ", health=" + getHealth() +
                ", mana=" + getMana() +
                ", name=" + getName() +
                ", colors=" + getColors() +
                ", description=" + getDescription() +
                '}';
    }
}
