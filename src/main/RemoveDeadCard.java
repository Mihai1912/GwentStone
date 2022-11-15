package main;

import main.Cards.Card;
import main.Cards.Minion;

import java.util.ArrayList;

final public class RemoveDeadCard {
    public RemoveDeadCard(final ArrayList<ArrayList<Card>> table) {
        for (int i = 0; i < table.size(); i++) {
            for (int j = 0; j < table.get(i).size(); j++) {
                Card card = new Card();
                card = table.get(i).get(j);
                if (((Minion) card).getHealth() <= 0) {
                    table.get(i).remove(j);
                }
            }
        }
    }
}
