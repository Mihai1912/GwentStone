package main.Cards;

import java.util.ArrayList;

public class Minion extends Card{
    private int attackDamage;
    private int health;
    private int mana;

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
