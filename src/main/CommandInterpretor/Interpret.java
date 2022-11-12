package main.CommandInterpretor;

import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionsInput;
import main.Actions.EndRound;
import main.Actions.NewRound;
import main.Actions.PlaceCard;
import main.Cards.Card;
import main.Cards.Environment;
import main.Cards.Minion;
import main.Player;
import main.RemoveDeadCard;

import java.util.ArrayList;

public class Interpret {

    ActionsInput cmd;

    public ArrayNode interpretation(ActionsInput command, ArrayNode output, Player actingPlayer , Player otherPlayer ,
                                    ArrayList<ArrayList<Card>> table){

//        System.out.println("acting " + actingPlayer.getIdx() + " outher " + otherPlayer.getIdx());

        if (command.getCommand().equals("getPlayerDeck")) {
            if (actingPlayer.getIdx() == command.getPlayerIdx()) {
                output.addObject().put("command" , command.getCommand())
                        .put("playerIdx" , command.getPlayerIdx())
                        .putPOJO("output" , actingPlayer.getDeck());
            } else {
                output.addObject().put("command" , command.getCommand())
                        .put("playerIdx" , command.getPlayerIdx())
                        .putPOJO("output" , otherPlayer.getDeck());
            }
        } else if (command.getCommand().equals("getPlayerHero")) {
            output.addObject().put("command" , command.getCommand())
                    .put("playerIdx" , command.getPlayerIdx())
                    .putPOJO("output" , actingPlayer.getHero());
        } else if (command.getCommand().equals("getPlayerTurn")) {
            output.addObject().put("command",command.getCommand()).put("output" , actingPlayer.getIdx());
        } else if (command.getCommand().equals("getCardsInHand")) {
            ArrayList<Card> hand = new ArrayList<>();
            if (actingPlayer.getIdx() == command.getPlayerIdx()) {
                for ( Card card : actingPlayer.getInHandCard()) {
                    String name = card.getName();
                    if (name.equals("Sentinel") || name.equals("Berserker") || name.equals("Goliath")
                            || name.equals("Warden") || name.equals("The Ripper") || name.equals("Miraj")
                            || name.equals("The Cursed One") || name.equals("Disciple")) {
                        hand.add(new Minion((Minion) card));
                    } else {
                        hand.add(new Environment((Environment) card));
                    }
                }
                output.addObject().put("command",command.getCommand()).put("playerIdx" , actingPlayer.getIdx())
                        .putPOJO("output" , hand);
            } else {
                for ( Card card : otherPlayer.getInHandCard()) {
                    String name = card.getName();
                    if (name.equals("Sentinel") || name.equals("Berserker") || name.equals("Goliath")
                            || name.equals("Warden") || name.equals("The Ripper") || name.equals("Miraj")
                            || name.equals("The Cursed One") || name.equals("Disciple")) {
                        hand.add(new Minion((Minion) card));
                    } else {
                        hand.add(new Environment((Environment) card));
                    }
                }
                output.addObject().put("command",command.getCommand()).put("playerIdx" , otherPlayer.getIdx())
                        .putPOJO("output" , hand);
            }
        } else if (command.getCommand().equals("getPlayerMana")) {
            if (actingPlayer.getIdx() == command.getPlayerIdx()) {
                output.addObject().put("command",command.getCommand()).put("playerIdx" , actingPlayer.getIdx())
                        .putPOJO("output" , actingPlayer.getMana());
            } else {
                output.addObject().put("command",command.getCommand()).put("playerIdx" , otherPlayer.getIdx())
                        .putPOJO("output" , otherPlayer.getMana());
            }
        } else if (command.getCommand().equals("getCardsOnTable")) {
            output.addObject().put("command",command.getCommand()).putPOJO("output" , table);
        } else if (command.getCommand().equals("getCardAtPosition")) {
            Card aux = table.get(command.getX()).get(command.getY());
            Minion card = new Minion((Minion)aux);
            output.addObject().put("command" , command.getCommand())
                    .putPOJO("output" , card);
        } else if (command.getCommand().equals("getEnvironmentCardsInHand")) {
            ArrayList<Card> environments = new ArrayList<>();
            for (Card card : actingPlayer.getEnvironmentInHand()) {
                environments.add((Environment)card);
            }
            output.addObject().put("command" , command.getCommand())
                    .put("playerIdx" , actingPlayer.getIdx())
                    .putPOJO("output" , environments);
        } else if (command.getCommand().equals("getFrozenCardsOnTable")) {
            ArrayList<Card> frozenCards = new ArrayList<>();
            for (ArrayList<Card> row : table) {
                for (Card card : row) {
                    if (((Minion)card).isFrozenForRound() || ((Minion)card).isFrozen()) {
                        frozenCards.add(card);
                    }
                }
            }
            output.addObject().put("command" , command.getCommand())
                    .putPOJO("output" , frozenCards);
        }

        if ((actingPlayer.getNoTurns()==1 && otherPlayer.getNoTurns()==1) ||
                (actingPlayer.getNoTurns()==1 && otherPlayer.getNoTurns()==0) ||
                (actingPlayer.getNoTurns()==0 && otherPlayer.getNoTurns()==1)){

            if (command.getCommand().equals("endPlayerTurn")) {
                actingPlayer.endTurn(otherPlayer);
//                System.out.println("-endPlayerTurn- "+ actingPlayer.getIdx() + ": " +actingPlayer.getNoTurns()+ " "+ otherPlayer.getIdx() + ": " + otherPlayer.getNoTurns());
//                System.out.println();
                if (actingPlayer.getNoTurns()==0 && otherPlayer.getNoTurns()==0) {
//                    System.out.println("===endRound===");
                    EndRound endRound = new EndRound(actingPlayer , otherPlayer);
                    NewRound newRound = new NewRound(actingPlayer , otherPlayer);
                }
            } else if (command.getCommand().equals("placeCard")) {
//                System.out.println(actingPlayer.getIdx()+"-placeCard-");
                String name = actingPlayer.getInHandCard().get(command.getHandIdx()).getName();
                if (name.equals("Firestorm") || name.equals("Winterfell") || name.equals("Heart Hound")) {
                    output.addObject().put("command" , command.getCommand())
                            .put("handIdx" , command.getHandIdx())
                            .put("error" , "Cannot place environment card on table.");
                } else {
                    Card card = new Card();
                    card = actingPlayer.getInHandCard().get(command.getHandIdx());
//                    System.out.println(card.getName());
                    if (actingPlayer.enoughManaToPlaceCard(card)) {
                        if (card.getName().equals("The Ripper") ||
                                card.getName().equals("Miraj") ||
                                card.getName().equals("Goliath") ||
                                card.getName().equals("Warden")) {
                            if (actingPlayer.getFrontRow().size() < 5) {
                                PlaceCard placeCard = new PlaceCard(actingPlayer , command.getHandIdx() , table , otherPlayer);
                            } else {
                                output.addObject().put("command" , command.getCommand())
                                        .put("handIdx" , command.getHandIdx())
                                        .put("error" , "Cannot place card on table since row is full.");
                            }
                        } else {
                            if (actingPlayer.getBackRow().size() < 5) {
                                PlaceCard placeCard = new PlaceCard(actingPlayer , command.getHandIdx() , table , otherPlayer);
                            } else {
                                output.addObject().put("command" , command.getCommand())
                                        .put("handIdx" , command.getHandIdx())
                                        .put("error" , "Cannot place card on table since row is full.");
                            }
                        }
                    } else {
                        output.addObject().put("command" , command.getCommand())
                                .put("handIdx" , command.getHandIdx())
                                .put("error" , "Not enough mana to place card on table.");
                    }
                }
            } else if (command.getCommand().equals("cardUsesAttack")) {
//                System.out.println("==================================");
                if (command.getCardAttacker().getX() != command.getCardAttacked().getX()) {
                    if (command.getCardAttacker().getX() <= 3 && command.getCardAttacker().getY() <= 4) {
                        Card cardAttacker = new Card();
                        cardAttacker = table.get(command.getCardAttacker().getX()).get(command.getCardAttacker().getY());
                        if (!(((Minion) cardAttacker).isFrozen())) {
                            if (!(((Minion) cardAttacker).isFrozenForRound())) {
                                Card cardAttacked = new Card();
                                cardAttacked = table.get(command.getCardAttacked().getX()).get(command.getCardAttacked().getY());
                                if (((Minion) cardAttacked).isTank()) {
//                                    System.out.println("------------------------------------");
                                    ((Minion) cardAttacked).setHealt(((Minion) cardAttacked).getHealth() - ((Minion) cardAttacker).getAttackDamage());
//                                    System.out.println(table);
//                                    if (((Minion) cardAttacked).getHealth() <= 0) {
//                                        table.get(command.getCardAttacked().getX()).remove(command.getCardAttacked().getY());
//                                    }
                                    ((Minion) cardAttacker).setFrozen(true);
                                } else {
//                                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                                    int x = command.getCardAttacked().getX();
                                    boolean value1 = false;
                                    switch (x) {
                                        case 0:
                                        case 1:
                                            value1 = actingPlayer.ifAttackTank(table.get(0), table.get(1));
                                            break;
                                        case 2:
                                        case 3:
                                            value1 = actingPlayer.ifAttackTank(table.get(2), table.get(3));
                                            break;
                                    }
                                    if (!value1) {
//                                        System.out.println("attacker " + cardAttacker.getName() + ((Minion) cardAttacker).getAttackDamage());
//                                        System.out.println("attacked " + cardAttacked.getName() + ((Minion) cardAttacked).getAttackDamage());
                                        ((Minion) cardAttacked).setHealt(((Minion) cardAttacked).getHealth() - ((Minion) cardAttacker).getAttackDamage());
//                                        System.out.println(table);
//                                        if (((Minion) cardAttacked).getHealth() <= 0) {
//                                            table.get(command.getCardAttacked().getX()).remove(command.getCardAttacked().getY());
//                                        }
                                        ((Minion) cardAttacker).setFrozen(true);
                                    } else {
                                        output.addObject().put("command", command.getCommand())
                                                .putPOJO("cardAttacker", command.getCardAttacker())
                                                .putPOJO("cardAttacked", command.getCardAttacked())
                                                .put("error", "Attacked card is not of type 'Tank'.");
                                    }
                                }
                            } else if (((Minion) cardAttacker).isFrozenForRound()) {
                                output.addObject().put("command", command.getCommand())
                                        .putPOJO("cardAttacker", command.getCardAttacker())
                                        .putPOJO("cardAttacked", command.getCardAttacked())
                                        .put("error", "Attacker card is frozen.");
                            }
                        } else {
                            output.addObject().put("command", command.getCommand())
                                    .putPOJO("cardAttacker", command.getCardAttacker())
                                    .putPOJO("cardAttacked", command.getCardAttacked())
                                    .put("error", "Attacker card has already attacked this turn.");
                        }
                    }
                } else {
                    output.addObject().put("command", command.getCommand())
                            .putPOJO("cardAttacker", command.getCardAttacker())
                            .putPOJO("cardAttacked", command.getCardAttacked())
                            .put("error", "Attacked card does not belong to the enemy.");
                }
//                RemoveDeadCard removeDeadCard = new RemoveDeadCard(table);
            } else if (command.getCommand().equals("useEnvironmentCard")) {
                Card card = new Card();
                card = actingPlayer.getInHandCard().get(command.getHandIdx());
                if (card.getName().equals("Winterfell") ||
                        card.getName().equals("Firestorm") ||
                        card.getName().equals("Heart Hound")) {
                    if (command.getHandIdx() < actingPlayer.getInHandCard().size()) {
                        if (actingPlayer.getMana() >= card.getMana()) {
                            if (actingPlayer.getIdx() == 1) {
                                if (command.getAffectedRow() == 0 || command.getAffectedRow() == 1) {
                                    if (card.getName().equals("Heart Hound")) {
                                        if (command.getAffectedRow() == 0) {
                                            if (actingPlayer.getBackRow().size() <= 4) {
                                                ((Environment)card).action(actingPlayer , otherPlayer , command);
                                            } else {
                                                output.addObject().put("command" , command.getCommand())
                                                        .put("handIdx" , command.getHandIdx())
                                                        .put("affectedRow" , command.getAffectedRow())
                                                        .put("error" , "Cannot steal enemy card since the player's row is full.");
                                            }
                                        } else if (command.getAffectedRow() == 1) {
                                            if (actingPlayer.getFrontRow().size() <= 4) {
                                                ((Environment)card).action(actingPlayer , otherPlayer , command);
                                            } else {
                                                output.addObject().put("command" , command.getCommand())
                                                        .put("handIdx" , command.getHandIdx())
                                                        .put("affectedRow" , command.getAffectedRow())
                                                        .put("error" , "Cannot steal enemy card since the player's row is full.");
                                            }
                                        }
                                    } else {
                                        ((Environment)card).action(actingPlayer , otherPlayer , command);
                                    }
                                } else {
                                    output.addObject().put("command" , command.getCommand())
                                            .put("handIdx" , command.getHandIdx())
                                            .put("affectedRow" , command.getAffectedRow())
                                            .put("error" , "Chosen row does not belong to the enemy.");
                                }
                            } else {
                                if (command.getAffectedRow() == 2 || command.getAffectedRow() == 3) {
                                    if (card.getName().equals("Heart Hound")) {
                                        if (command.getAffectedRow() == 3) {
                                            if (actingPlayer.getBackRow().size() <= 4) {
                                                ((Environment)card).action(actingPlayer , otherPlayer , command);
                                            } else {
                                                output.addObject().put("command" , command.getCommand())
                                                        .put("handIdx" , command.getHandIdx())
                                                        .put("affectedRow" , command.getAffectedRow())
                                                        .put("error" , "Cannot steal enemy card since the player's row is full.");
                                            }
                                        } else if (command.getAffectedRow() == 2) {
                                            if (actingPlayer.getFrontRow().size() <= 4) {
                                                ((Environment)card).action(actingPlayer , otherPlayer , command);
                                            } else {
                                                output.addObject().put("command" , command.getCommand())
                                                        .put("handIdx" , command.getHandIdx())
                                                        .put("affectedRow" , command.getAffectedRow())
                                                        .put("error" , "Cannot steal enemy card since the player's row is full.");
                                            }
                                        }
                                    } else {
                                        ((Environment)card).action(actingPlayer , otherPlayer , command);
                                    }
                                } else {
                                    output.addObject().put("command" , command.getCommand())
                                            .put("handIdx" , command.getHandIdx())
                                            .put("affectedRow" , command.getAffectedRow())
                                            .put("error" , "Chosen row does not belong to the enemy.");
                                }
                            }
                        } else {
                            output.addObject().put("command" , command.getCommand())
                                    .put("handIdx" , command.getHandIdx())
                                    .put("affectedRow" , command.getAffectedRow())
                                    .put("error" , "Not enough mana to use environment card.");
                        }
                    }
                } else {
                    output.addObject().put("command" , command.getCommand())
                            .put("handIdx" , command.getHandIdx())
                            .put("affectedRow" , command.getAffectedRow())
                            .put("error" , "Chosen card is not of type environment.");
                }
            } else if (command.getCommand().equals("cardUsesAbility")) {
                Card attacker = new Card();
                attacker = table.get(command.getCardAttacker().getX()).get(command.getCardAttacker().getY());
                Card attacked = new Card();
                attacked = table.get(command.getCardAttacked().getX()).get(command.getCardAttacked().getY());
                if (!(((Minion)attacker).isFrozen())) {
                    if (!(((Minion)attacker).isFrozenForRound())) {
                        if (((Minion)attacker).getName().equals("Disciple")) {
                            if (actingPlayer.getFrontRow().contains(attacked) ||
                                    actingPlayer.getBackRow().contains(attacked)) {
                                attacker.action(actingPlayer , otherPlayer , command);
                                ((Minion) attacker).setFrozen(true);
                            } else {
                                output.addObject().put("command" , command.getCommand())
                                        .putPOJO("cardAttacker" , command.getCardAttacker())
                                        .putPOJO("cardAttacked" , command.getCardAttacked())
                                        .put("error" , "Attacked card does not belong to the current player.");
                            }
                        } else {
                            if (otherPlayer.getFrontRow().contains(attacked) ||
                                    otherPlayer.getBackRow().contains(attacked)) {
                                if (((Minion)attacked).isTank()) {
                                    attacker.action(actingPlayer , otherPlayer , command);
                                    ((Minion) attacker).setFrozen(true);
                                } else {
                                    int x = command.getCardAttacked().getX();
                                    boolean value1 = false;
                                    switch (x) {
                                        case 0:
                                        case 1:
                                            value1 = actingPlayer.ifAttackTank(table.get(0), table.get(1));
                                            break;
                                        case 2:
                                        case 3:
                                            value1 = actingPlayer.ifAttackTank(table.get(2), table.get(3));
                                            break;
                                    }
                                    if (!value1) {
                                        attacker.action(actingPlayer , otherPlayer , command);
                                        ((Minion) attacker).setFrozen(true);
                                    } else {
                                        output.addObject().put("command", command.getCommand())
                                                .putPOJO("cardAttacker", command.getCardAttacker())
                                                .putPOJO("cardAttacked", command.getCardAttacked())
                                                .put("error", "Attacked card is not of type 'Tank'.");
                                    }
                                }
                            } else {
                                output.addObject().put("command" , command.getCommand())
                                        .putPOJO("cardAttacker" , command.getCardAttacker())
                                        .putPOJO("cardAttacked" , command.getCardAttacked())
                                        .put("error" , "Attacked card does not belong to the enemy.");
                            }
                        }
                    } else {
                        output.addObject().put("command" , command.getCommand())
                                .putPOJO("cardAttacker" , command.getCardAttacker())
                                .putPOJO("cardAttacked" , command.getCardAttacked())
                                .put("error" , "Attacker card is frozen.");
                    }
                } else {
                    output.addObject().put("command" , command.getCommand())
                                    .putPOJO("cardAttacker" , command.getCardAttacker())
                                    .putPOJO("cardAttacked" , command.getCardAttacked())
                                    .put("error" , "Attacker card has already attacked this turn.");
                }
            }
        }
        RemoveDeadCard removeDeadCard = new RemoveDeadCard(table);
        return output;
    }
    public ActionsInput getCmd() {
        return cmd;
    }

    public void setCmd(ActionsInput cmd) {
        this.cmd = cmd;
    }
}
