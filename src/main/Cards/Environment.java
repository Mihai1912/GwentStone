package main.Cards;

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
