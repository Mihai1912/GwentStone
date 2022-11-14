package main.CommandInterpretor;

import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionsInput;
import fileio.Input;
import main.Actions.EndRound;
import main.Actions.NewRound;
import main.Actions.PlaceCard;
import main.Cards.Card;
import main.Cards.Environment;
import main.Cards.Hero;
import main.Cards.Minion;
import main.Player;
import main.RemoveDeadCard;

import java.util.ArrayList;

public class Interpret {

    ActionsInput cmd;

    public static int player1Win  = 0;
    public static int player2Win  = 0;

    public static int getPlayer1Win() {
        return player1Win;
    }

    public static int getPlayer2Win() {
        return player2Win;
    }

    public static void setPlayer1Win(int player1Win) {
        Interpret.player1Win = player1Win;
    }

    public static void setPlayer2Win(int player2Win) {
        Interpret.player2Win = player2Win;
    }

    public void interpretation(ActionsInput command, ArrayNode output, Player actingPlayer, Player otherPlayer,
                               ArrayList<ArrayList<Card>> table , Input inputData) {

//        System.out.println("acting " + actingPlayer.getIdx() + " outher " + otherPlayer.getIdx());

//        ArrayList<Card> row1Aux = new ArrayList<>();
//        ArrayList<Card> row2Aux = new ArrayList<>();
//        ArrayList<Card> row3Aux = new ArrayList<>();
//        ArrayList<Card> row4Aux = new ArrayList<>();
//
//        for (Card card : table.get(0)) {
//            row1Aux.add(new Minion((Minion)card));
//        }
//        for (Card card : table.get(1)) {
//            row2Aux.add(new Minion((Minion)card));
//        }
//        for (Card card : table.get(2)) {
//            row3Aux.add(new Minion((Minion)card));
//        }
//        for (Card card : table.get(3)) {
//            row4Aux.add(new Minion((Minion)card));
//        }
//
//        ArrayList<ArrayList<Card>> table1 = new ArrayList<>();
//        table1.add(row1Aux);
//        table1.add(row2Aux);
//        table1.add(row3Aux);
//        table1.add(row4Aux);


        switch (command.getCommand()) {
            case "getPlayerDeck":
                if (actingPlayer.getIdx() == command.getPlayerIdx()) {
                    output.addObject().put("command", command.getCommand())
                            .put("playerIdx", command.getPlayerIdx())
                            .putPOJO("output", actingPlayer.getDeck());
                } else {
                    output.addObject().put("command", command.getCommand())
                            .put("playerIdx", command.getPlayerIdx())
                            .putPOJO("output", otherPlayer.getDeck());
                }
                break;
            case "getPlayerHero":
                if (actingPlayer.getIdx() == command.getPlayerIdx()) {
                    Hero heroAux = new Hero(actingPlayer.getHero());
                    output.addObject().put("command", command.getCommand())
                            .put("playerIdx", command.getPlayerIdx())
                            .putPOJO("output", heroAux);
                } else {
                    Hero heroAux = new Hero(otherPlayer.getHero());
                    output.addObject().put("command", command.getCommand())
                            .put("playerIdx", command.getPlayerIdx())
                            .putPOJO("output", heroAux);
                }
                break;
            case "getPlayerTurn":
                output.addObject().put("command", command.getCommand()).put("output", actingPlayer.getIdx());
                break;
            case "getCardsInHand":
                ArrayList<Card> hand = new ArrayList<>();
                if (actingPlayer.getIdx() == command.getPlayerIdx()) {
                    for (Card card : actingPlayer.getInHandCard()) {
                        String name = card.getName();
                        if (name.equals("Sentinel") || name.equals("Berserker") || name.equals("Goliath")
                                || name.equals("Warden") || name.equals("The Ripper") || name.equals("Miraj")
                                || name.equals("The Cursed One") || name.equals("Disciple")) {
                            hand.add(new Minion((Minion) card));
                        } else {
                            hand.add(new Environment((Environment) card));
                        }
                    }
                    output.addObject().put("command", command.getCommand()).put("playerIdx", actingPlayer.getIdx())
                            .putPOJO("output", hand);
                } else {
                    for (Card card : otherPlayer.getInHandCard()) {
                        String name = card.getName();
                        if (name.equals("Sentinel") || name.equals("Berserker") || name.equals("Goliath")
                                || name.equals("Warden") || name.equals("The Ripper") || name.equals("Miraj")
                                || name.equals("The Cursed One") || name.equals("Disciple")) {
                            hand.add(new Minion((Minion) card));
                        } else {
                            hand.add(new Environment((Environment) card));
                        }
                    }
                    output.addObject().put("command", command.getCommand()).put("playerIdx", otherPlayer.getIdx())
                            .putPOJO("output", hand);
                }
                break;
            case "getPlayerMana":
                if (actingPlayer.getIdx() == command.getPlayerIdx()) {
                    output.addObject().put("command", command.getCommand()).put("playerIdx", actingPlayer.getIdx())
                            .putPOJO("output", actingPlayer.getMana());
                } else {
                    output.addObject().put("command", command.getCommand()).put("playerIdx", otherPlayer.getIdx())
                            .putPOJO("output", otherPlayer.getMana());
                }
                break;
            case "getCardsOnTable":

                ArrayList<Card> row1Aux = new ArrayList<>();
                ArrayList<Card> row2Aux = new ArrayList<>();
                ArrayList<Card> row3Aux = new ArrayList<>();
                ArrayList<Card> row4Aux = new ArrayList<>();

                for (Card card : table.get(0)) {
                    row1Aux.add(new Minion((Minion)card));
                }
                for (Card card : table.get(1)) {
                    row2Aux.add(new Minion((Minion)card));
                }
                for (Card card : table.get(2)) {
                    row3Aux.add(new Minion((Minion)card));
                }
                for (Card card : table.get(3)) {
                    row4Aux.add(new Minion((Minion)card));
                }

                ArrayList<ArrayList<Card>> table1 = new ArrayList<>();
                table1.add(row1Aux);
                table1.add(row2Aux);
                table1.add(row3Aux);
                table1.add(row4Aux);

                output.addObject().put("command", command.getCommand()).putPOJO("output", table1);
                break;
            case "getCardAtPosition":
                if (command.getX() < table.size() && command.getY() < table.get(command.getX()).size()) {
                    Card aux = table.get(command.getX()).get(command.getY());
                    Minion card = new Minion((Minion) aux);
                    output.addObject().put("command", command.getCommand())
                            .put("x", command.getX())
                            .put("y", command.getY())
                            .putPOJO("output", card);
                }
                else {
                    output.addObject().put("command", command.getCommand())
                            .put("x", command.getX())
                            .put("y", command.getY())
                            .put("output", "No card available at that position.");
                }
                break;
            case "getEnvironmentCardsInHand":
                ArrayList<Card> environments = new ArrayList<>();
                ArrayList<Card> environmentsInHand = new ArrayList<>();
                for (Card card : actingPlayer.getEnvironmentInHand()) {
                    environmentsInHand.add((Environment) card);
                }
                for (Card card : environmentsInHand) {
                    environments.add((Environment) card);
                }
                output.addObject().put("command", command.getCommand())
                        .put("playerIdx", actingPlayer.getIdx())
                        .putPOJO("output", environments);
                break;
            case "getFrozenCardsOnTable":
                ArrayList<Card> frozenCards = new ArrayList<>();
                for (ArrayList<Card> row : table) {
                    for (Card card : row) {
                        if (((Minion) card).isFrozenForRound()) {
                            frozenCards.add(new Minion((Minion) card));
                        }
                    }
                }
                output.addObject().put("command", command.getCommand())
                        .putPOJO("output", frozenCards);
                break;
            case "getTotalGamesPlayed":
                output.addObject().put("command", command.getCommand())
                        .put("output", player1Win+player2Win);
                break;
            case "getPlayerOneWins":
                if (actingPlayer.getIdx() == 1) {
                    output.addObject().put("command", command.getCommand())
                            .put("output", player1Win);
                } else {
                    output.addObject().put("command", command.getCommand())
                            .put("output", player1Win);
                }
                break;
            case "getPlayerTwoWins":
                if (actingPlayer.getIdx() == 2) {
                    output.addObject().put("command", command.getCommand())
                            .put("output", player2Win);
                } else {
                    output.addObject().put("command", command.getCommand())
                            .put("output", player2Win);
                }
                break;
        }

        if ((actingPlayer.getNoTurns() == 1 && otherPlayer.getNoTurns() == 1) ||
                (actingPlayer.getNoTurns() == 1 && otherPlayer.getNoTurns() == 0) ||
                (actingPlayer.getNoTurns() == 0 && otherPlayer.getNoTurns() == 1)) {

            switch (command.getCommand()) {
                case "endPlayerTurn":
                    actingPlayer.endTurn(otherPlayer);
//                System.out.println("-endPlayerTurn- "+ actingPlayer.getIdx() + ": " +actingPlayer.getNoTurns()+ " "+ otherPlayer.getIdx() + ": " + otherPlayer.getNoTurns());
//                System.out.println();
                    if (actingPlayer.getNoTurns() == 0 && otherPlayer.getNoTurns() == 0) {
//                    System.out.println("===endRound===");
                        EndRound endRound = new EndRound(actingPlayer, otherPlayer);
                        NewRound newRound = new NewRound(actingPlayer, otherPlayer);
                    }
                    break;
                case "placeCard":
//                System.out.println(actingPlayer.getIdx()+"-placeCard-");
                    String name = actingPlayer.getInHandCard().get(command.getHandIdx()).getName();
                    if (name.equals("Firestorm") || name.equals("Winterfell") || name.equals("Heart Hound")) {
                        output.addObject().put("command", command.getCommand())
                                .put("handIdx", command.getHandIdx())
                                .put("error", "Cannot place environment card on table.");
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
                                    PlaceCard placeCard = new PlaceCard(actingPlayer, command.getHandIdx(), table, otherPlayer);
                                } else {
                                    output.addObject().put("command", command.getCommand())
                                            .put("handIdx", command.getHandIdx())
                                            .put("error", "Cannot place card on table since row is full.");
                                }
                            } else {
                                if (actingPlayer.getBackRow().size() < 5) {
                                    PlaceCard placeCard = new PlaceCard(actingPlayer, command.getHandIdx(), table, otherPlayer);
                                } else {
                                    output.addObject().put("command", command.getCommand())
                                            .put("handIdx", command.getHandIdx())
                                            .put("error", "Cannot place card on table since row is full.");
                                }
                            }
                        } else {
                            output.addObject().put("command", command.getCommand())
                                    .put("handIdx", command.getHandIdx())
                                    .put("error", "Not enough mana to place card on table.");
                        }
                    }
                    break;
                case "cardUsesAttack":
//                System.out.println("==================================");
                    if (command.getCardAttacker().getX() != command.getCardAttacked().getX()) {
                        if (command.getCardAttacker().getX() <= 3 && command.getCardAttacker().getY() <= 4) {
                            Card cardAttacker = new Card();
                            if (command.getCardAttacker().getX() < table.size() &&
                                    command.getCardAttacker().getY() < table.get(command.getCardAttacker().getX()).size()) {

                                cardAttacker = table.get(command.getCardAttacker().getX()).get(command.getCardAttacker().getY());
                                if (!(((Minion) cardAttacker).isFrozen())) {
                                    if (!(((Minion) cardAttacker).isFrozenForRound())) {
                                        Card cardAttacked = new Card();
                                        if (command.getCardAttacked().getX() < table.size() &&
                                                command.getCardAttacked().getY() < table.get(command.getCardAttacked().getX()).size()) {

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
                        }
                    } else {
                        output.addObject().put("command", command.getCommand())
                                .putPOJO("cardAttacker", command.getCardAttacker())
                                .putPOJO("cardAttacked", command.getCardAttacked())
                                .put("error", "Attacked card does not belong to the enemy.");
                    }
//                RemoveDeadCard removeDeadCard = new RemoveDeadCard(table);
                    break;
                case "useEnvironmentCard":
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
                                                    ((Environment) card).action(actingPlayer, otherPlayer, command);
                                                } else {
                                                    output.addObject().put("command", command.getCommand())
                                                            .put("handIdx", command.getHandIdx())
                                                            .put("affectedRow", command.getAffectedRow())
                                                            .put("error", "Cannot steal enemy card since the player's row is full.");
                                                }
                                            } else if (command.getAffectedRow() == 1) {
                                                if (actingPlayer.getFrontRow().size() <= 4) {
                                                    ((Environment) card).action(actingPlayer, otherPlayer, command);
                                                } else {
                                                    output.addObject().put("command", command.getCommand())
                                                            .put("handIdx", command.getHandIdx())
                                                            .put("affectedRow", command.getAffectedRow())
                                                            .put("error", "Cannot steal enemy card since the player's row is full.");
                                                }
                                            }
                                        } else {
                                            ((Environment) card).action(actingPlayer, otherPlayer, command);
                                        }
                                    } else {
                                        output.addObject().put("command", command.getCommand())
                                                .put("handIdx", command.getHandIdx())
                                                .put("affectedRow", command.getAffectedRow())
                                                .put("error", "Chosen row does not belong to the enemy.");
                                    }
                                } else {
                                    if (command.getAffectedRow() == 2 || command.getAffectedRow() == 3) {
                                        if (card.getName().equals("Heart Hound")) {
                                            if (command.getAffectedRow() == 3) {
                                                if (actingPlayer.getBackRow().size() <= 4) {
                                                    ((Environment) card).action(actingPlayer, otherPlayer, command);
                                                } else {
                                                    output.addObject().put("command", command.getCommand())
                                                            .put("handIdx", command.getHandIdx())
                                                            .put("affectedRow", command.getAffectedRow())
                                                            .put("error", "Cannot steal enemy card since the player's row is full.");
                                                }
                                            } else if (command.getAffectedRow() == 2) {
                                                if (actingPlayer.getFrontRow().size() <= 4) {
                                                    ((Environment) card).action(actingPlayer, otherPlayer, command);
                                                } else {
                                                    output.addObject().put("command", command.getCommand())
                                                            .put("handIdx", command.getHandIdx())
                                                            .put("affectedRow", command.getAffectedRow())
                                                            .put("error", "Cannot steal enemy card since the player's row is full.");
                                                }
                                            }
                                        } else {
                                            ((Environment) card).action(actingPlayer, otherPlayer, command);
                                        }
                                    } else {
                                        output.addObject().put("command", command.getCommand())
                                                .put("handIdx", command.getHandIdx())
                                                .put("affectedRow", command.getAffectedRow())
                                                .put("error", "Chosen row does not belong to the enemy.");
                                    }
                                }
                            } else {
                                output.addObject().put("command", command.getCommand())
                                        .put("handIdx", command.getHandIdx())
                                        .put("affectedRow", command.getAffectedRow())
                                        .put("error", "Not enough mana to use environment card.");
                            }
                        }
                    } else {
                        output.addObject().put("command", command.getCommand())
                                .put("handIdx", command.getHandIdx())
                                .put("affectedRow", command.getAffectedRow())
                                .put("error", "Chosen card is not of type environment.");
                    }
                    break;
                case "cardUsesAbility": {
                    Card attacker = new Card();
                    Card attacked = new Card();
                    if (command.getCardAttacked().getX() < table.size() &&
                            command.getCardAttacked().getY() < table.get(command.getCardAttacked().getX()).size() &&
                            command.getCardAttacker().getX() < table.size() &&
                            command.getCardAttacker().getY() < table.get(command.getCardAttacker().getX()).size()) {
                        attacker = table.get(command.getCardAttacker().getX()).get(command.getCardAttacker().getY());
                        attacked = table.get(command.getCardAttacked().getX()).get(command.getCardAttacked().getY());
                        if (!(((Minion) attacker).isFrozen())) {
                            if (!(((Minion) attacker).isFrozenForRound())) {
                                if (((Minion) attacker).getName().equals("Disciple")) {
                                    if (actingPlayer.getFrontRow().contains(attacked) ||
                                            actingPlayer.getBackRow().contains(attacked)) {
                                        attacker.action(actingPlayer, otherPlayer, command);
                                        ((Minion) attacker).setFrozen(true);
                                    } else {
                                        output.addObject().put("command", command.getCommand())
                                                .putPOJO("cardAttacker", command.getCardAttacker())
                                                .putPOJO("cardAttacked", command.getCardAttacked())
                                                .put("error", "Attacked card does not belong to the current player.");
                                    }
                                } else {
                                    if (otherPlayer.getFrontRow().contains(attacked) ||
                                            otherPlayer.getBackRow().contains(attacked)) {
                                        if (((Minion) attacked).isTank()) {
                                            attacker.action(actingPlayer, otherPlayer, command);
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
                                                attacker.action(actingPlayer, otherPlayer, command);
                                                ((Minion) attacker).setFrozen(true);
                                            } else {
                                                output.addObject().put("command", command.getCommand())
                                                        .putPOJO("cardAttacker", command.getCardAttacker())
                                                        .putPOJO("cardAttacked", command.getCardAttacked())
                                                        .put("error", "Attacked card is not of type 'Tank'.");
                                            }
                                        }
                                    } else {
                                        output.addObject().put("command", command.getCommand())
                                                .putPOJO("cardAttacker", command.getCardAttacker())
                                                .putPOJO("cardAttacked", command.getCardAttacked())
                                                .put("error", "Attacked card does not belong to the enemy.");
                                    }
                                }
                            } else {
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
                    break;
                }
                case "useAttackHero": {
//                System.out.println(command.getCardAttacker().getX() + " " + command.getCardAttacker().getY());
                    Card attacker = new Card();
                    if (command.getCardAttacker().getX() < table.size() &&
                            command.getCardAttacker().getY() < table.get(command.getCardAttacker().getX()).size()) {

                        attacker = table.get(command.getCardAttacker().getX()).get(command.getCardAttacker().getY());
                        if (!(((Minion) attacker).isFrozen())) {
                            if (!(((Minion) attacker).isFrozenForRound())) {
                                boolean value1 = false;
                                if (actingPlayer.getIdx() == 1) {
                                    value1 = actingPlayer.ifAttackTank(table.get(0), table.get(1));
                                } else {
                                    value1 = actingPlayer.ifAttackTank(table.get(2), table.get(3));
                                }
                                if (!value1) {
                                    otherPlayer.getHero().setHealth(otherPlayer.getHero().getHealth() - ((Minion) attacker).getAttackDamage());
                                    ((Minion) attacker).setFrozen(true);
                                    if (otherPlayer.getHero().getHealth() <= 0) {
                                        if (otherPlayer.getIdx() == 1){
                                            output.addObject().put("gameEnded", "Player two killed the enemy hero.");
                                            player2Win++;
                                            System.out.println("Player two killed the enemy hero.");
                                        } else {
                                            output.addObject().put("gameEnded", "Player one killed the enemy hero.");
                                            player1Win++;
                                            System.out.println("Player one killed the enemy hero.");
                                        }
//                                        actingPlayer.setWin((actingPlayer.getWin()+1));
                                        System.out.println( "win player 2 = "  + player2Win);
                                        System.out.println( "win player 1 = "  + player1Win);
                                    }
                                } else {
                                    output.addObject().put("command", command.getCommand())
                                            .putPOJO("cardAttacker", command.getCardAttacker())
                                            .put("error", "Attacked card is not of type 'Tank'.");
                                }
                            } else {
                                output.addObject().put("command", command.getCommand())
                                        .putPOJO("cardAttacker", command.getCardAttacker())
                                        .put("error", "Attacker card is frozen.");
                            }
                        } else {
                            output.addObject().put("command", command.getCommand())
                                    .putPOJO("cardAttacker", command.getCardAttacker())
                                    .put("error", "Attacker card has already attacked this turn.");
                        }
                    }
                    break;
                }
                case "useHeroAbility":
                    if (actingPlayer.getMana() >= actingPlayer.getHero().getMana()) {
                        if (!(actingPlayer.getHero().isFrozen())) {
                            if (actingPlayer.getHero().getName().equals("Lord Royce") ||
                                    actingPlayer.getHero().getName().equals("Empress Thorina")) {
                                if (actingPlayer.getIdx() == 1) {
                                    if (command.getAffectedRow() == 1 || command.getAffectedRow() == 0) {
                                        actingPlayer.getHero().action(actingPlayer, otherPlayer, command);
                                        actingPlayer.getHero().setFrozen(true);
                                        actingPlayer.setMana(actingPlayer.getMana() - actingPlayer.getHero().getMana());
                                    } else {
                                        output.addObject().put("command", command.getCommand())
                                                .put("affectedRow", command.getAffectedRow())
                                                .put("error", "Selected row does not belong to the enemy.");
                                    }
                                } else {
                                    if (command.getAffectedRow() == 3 || command.getAffectedRow() == 2) {
                                        actingPlayer.getHero().action(actingPlayer, otherPlayer, command);
                                        actingPlayer.getHero().setFrozen(true);
                                        actingPlayer.setMana(actingPlayer.getMana() - actingPlayer.getHero().getMana());
                                    } else {
                                        output.addObject().put("command", command.getCommand())
                                                .put("affectedRow", command.getAffectedRow())
                                                .put("error", "Selected row does not belong to the enemy.");
                                    }
                                }
                            } else if (actingPlayer.getHero().getName().equals("King Mudface") ||
                                    actingPlayer.getHero().getName().equals("General Kocioraw")) {
                                if (actingPlayer.getIdx() == 1) {
                                    if (command.getAffectedRow() == 3 || command.getAffectedRow() == 2) {
                                        actingPlayer.getHero().action(actingPlayer, otherPlayer, command);
                                        actingPlayer.getHero().setFrozen(true);
                                        actingPlayer.setMana(actingPlayer.getMana() - actingPlayer.getHero().getMana());
                                    } else {
                                        output.addObject().put("command", command.getCommand())
                                                .put("affectedRow", command.getAffectedRow())
                                                .put("error", "Selected row does not belong to the current player.");
                                    }
                                } else {
                                    if (command.getAffectedRow() == 1 || command.getAffectedRow() == 0) {
                                        actingPlayer.getHero().action(actingPlayer, otherPlayer, command);
                                        actingPlayer.getHero().setFrozen(true);
                                        actingPlayer.setMana(actingPlayer.getMana() - actingPlayer.getHero().getMana());
                                    } else {
                                        output.addObject().put("command", command.getCommand())
                                                .put("affectedRow", command.getAffectedRow())
                                                .put("error", "Selected row does not belong to the current player.");
                                    }
                                }
                            }
                        } else {
                            output.addObject().put("command", command.getCommand())
                                    .put("affectedRow", command.getAffectedRow())
                                    .put("error", "Hero has already attacked this turn.");
                        }
                    } else {
                        output.addObject().put("command", command.getCommand())
                                .put("affectedRow", command.getAffectedRow())
                                .put("error", "Not enough mana to use hero's ability.");
                    }
                    break;
            }
        }
        RemoveDeadCard removeDeadCard = new RemoveDeadCard(table);
    }

    public ActionsInput getCmd() {
        return cmd;
    }

    public void setCmd(ActionsInput cmd) {
        this.cmd = cmd;
    }
}
