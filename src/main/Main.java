package main;

import checker.Checker;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import checker.CheckerConstants;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.ActionsInput;
import fileio.CardInput;
import fileio.Input;
import main.Cards.Minion;
import main.CommandInterpretor.Interpret;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;



/**
 * The entry point to this homework. It runs the checker that tests your implentation.
 */
public final class Main {
    /**
     * for coding style
     */
    private Main() {
    }

    /**
     * DO NOT MODIFY MAIN METHOD
     * Call the checker
     * @param args from command line
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void main(final String[] args) throws IOException {
        File directory = new File(CheckerConstants.TESTS_PATH);
        Path path = Paths.get(CheckerConstants.RESULT_PATH);

        if (Files.exists(path)) {
            File resultFile = new File(String.valueOf(path));
            for (File file : Objects.requireNonNull(resultFile.listFiles())) {
                file.delete();
            }
            resultFile.delete();
        }
        Files.createDirectories(path);

        for (File file : Objects.requireNonNull(directory.listFiles())) {
            String filepath = CheckerConstants.OUT_PATH + file.getName();
            File out = new File(filepath);
            boolean isCreated = out.createNewFile();
            if (isCreated) {
                action(file.getName(), filepath);
            }
        }

        Checker.calculateScore();
    }

    /**
     * @param filePath1 for input file
     * @param filePath2 for output file
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void action(final String filePath1,
                              final String filePath2) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Input inputData = objectMapper.readValue(new File(CheckerConstants.TESTS_PATH + filePath1),
                Input.class);

        ArrayNode output = objectMapper.createArrayNode();

        //TODO add here the entry point to your implementation
        int noGame = inputData.getGames().size() - 1;
        initDecks Decks = new initDecks(inputData);
        Player Player1 = new Player();
        Player1.setIdx(1);
        Player Player2 = new Player();
        Player2.setIdx(2);
        if (inputData.getGames().get(noGame).getStartGame().getStartingPlayer() == 1) {
            Player1.setTurn(true);
            Player2.setTurn(false);
        } else {
            Player1.setTurn(false);
            Player2.setTurn(true);
        }
        CardInput heroPlayer1 = inputData.getGames().get(noGame).getStartGame().getPlayerOneHero();
        CardInput heroPlayer2 = inputData.getGames().get(noGame).getStartGame().getPlayerTwoHero();
        Player1.setDeck(Decks.deckPlayer1);
        Player2.setDeck(Decks.deckPlayer2);
        CardTypeIdentificator hero1 = new CardTypeIdentificator(heroPlayer1);
        CardTypeIdentificator hero2 = new CardTypeIdentificator(heroPlayer2);
        Player1.setHero(hero1.card);
        Player2.setHero(hero2.card);
        Player1.drawCard(Player1.getDeck());
        Player2.drawCard(Player2.getDeck());

        ActionsInput commnd;
        ArrayList<ActionsInput> actions;
        actions = inputData.getGames().get(noGame).getActions();

        for (int i = 0 ; i < actions.size() ; i++) {
            commnd = actions.get(i);
            Interpret cmdint = new Interpret();
            cmdint.setCmd(commnd);
            if (commnd.getPlayerIdx() == 1) {
                cmdint.interpretation(commnd , output , Player1);
            } else {
                cmdint.interpretation(commnd , output , Player2);
            }
        }

        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File(filePath2), output);
    }
}
