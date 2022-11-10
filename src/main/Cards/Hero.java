package main.Cards;

import java.util.ArrayList;

public class Hero extends Card{
    private int health = 30;
    private int mana;

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
