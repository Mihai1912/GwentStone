package main;

import fileio.CardInput;
import main.Cards.Card;
import main.Cards.Environment;
import main.Cards.Hero;
import main.Cards.Minion;

import java.util.ArrayList;

public class CardTypeIdentificator {

    Card card;
    public CardTypeIdentificator(CardInput cardInput) {
        String name = cardInput.getName();
        if (name.equals("Sentinel") || name.equals("Berserker") || name.equals("Goliath")
                || name.equals("Warden") || name.equals("The Ripper") || name.equals("Miraj")
                || name.equals("The Cursed One") || name.equals("Disciple")) {
            if (name.equals("Warden") || name.equals("Goliath")) {
                Minion minion = new Minion(cardInput.getMana() ,
                        cardInput.getDescription() ,
                        cardInput.getColors() ,
                        cardInput.getName() ,
                        cardInput.getHealth() ,
                        cardInput.getAttackDamage());
                minion.setTank(true);
                card = minion;
            } else {
                Minion minion = new Minion(cardInput.getMana() ,
                        cardInput.getDescription() ,
                        cardInput.getColors() ,
                        cardInput.getName() ,
                        cardInput.getHealth() ,
                        cardInput.getAttackDamage());
                minion.setTank(false);
                card = minion;
            }
        }
        if (name.equals("Firestorm") || name.equals("Winterfell") || name.equals("Heart Hound")) {
            Environment environment = new Environment(cardInput.getMana() ,
                    cardInput.getDescription() ,
                    cardInput.getColors() ,
                    cardInput.getName());
            card = environment;
        }
        if (name.equals("Lord Royce") || name.equals("Empress Thorina")
                || name.equals("King Mudface") || name.equals("General Kocioraw")) {
            Hero hero = new Hero(cardInput.getMana() ,
                    cardInput.getDescription() ,
                    cardInput.getColors() ,
                    cardInput.getName());
            card = hero;
        }
//        System.out.println(card);
    }
}
