package main;

import main.Cards.Card;
import main.Cards.Minion;

import java.util.ArrayList;

public class RemoveDeadCard {
    public RemoveDeadCard(ArrayList<ArrayList<Card>> table) {
//        System.out.println("============================");
        for (int i = 0 ; i < table.size() ; i++) {
            for (int j = 0 ; j < table.get(i).size() ; j++) {
                Card card = new Card();
                card = table.get(i).get(j);
                if (((Minion)card).getHealth() <= 0) {
//                    System.out.println(card);
                    table.get(i).remove(j);
                }
            }

//            System.out.println(table.get(i));
        }
    }
}
