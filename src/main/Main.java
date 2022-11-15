package main;

import checker.Checker;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import checker.CheckerConstants;
import fileio.ActionsInput;
import fileio.CardInput;
import fileio.Input;
import main.Actions.NewRound;
import main.Cards.Card;
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
     *
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

        int noGame = inputData.getGames().size();
        for (int game = 0; game < noGame; game++) {
            Player player1 = new Player();
            Player player2 = new Player();
            InitDecks decks = new InitDecks(inputData, game);
            ArrayList<ArrayList<Card>> table = new ArrayList<>();

            for (int i = 0; i < 4; i++) {
                table.add(new ArrayList<>());
            }


            player1.setIdx(1);
            player2.setIdx(2);
            if (inputData.getGames().get(game).getStartGame().getStartingPlayer() == 1) {
                player1.setTurn(true);
                player2.setTurn(false);
            } else {
                player1.setTurn(false);
                player2.setTurn(true);
            }
            CardInput heroPlayer1 = inputData.getGames().get(game).getStartGame()
                    .getPlayerOneHero();
            CardInput heroPlayer2 = inputData.getGames().get(game).getStartGame()
                    .getPlayerTwoHero();
            player1.setDeck(decks.getDeckPlayer1());
            player2.setDeck(decks.getDeckPlayer2());
            CardTypeIdentificator hero1 = new CardTypeIdentificator(heroPlayer1);
            CardTypeIdentificator hero2 = new CardTypeIdentificator(heroPlayer2);
            player1.setHero(hero1.getCard());
            player2.setHero(hero2.getCard());

            if (player1.isTurn()) {
                NewRound newRound = new NewRound(player1, player2);
            } else {
                NewRound newRound = new NewRound(player2, player1);
            }

            ActionsInput commnd;
            ArrayList<ActionsInput> actions;
            actions = inputData.getGames().get(game).getActions();


            Interpret cmdint = new Interpret();
            for (ActionsInput action : actions) {
                commnd = action;
                cmdint.setCmd(commnd);
                if (commnd.getPlayerIdx() == 1 || (player1.isTurn())) {
                    cmdint.interpretation(commnd, output, player1, player2, table);
                } else if (player2.isTurn()) {
                    cmdint.interpretation(commnd, output, player2, player1, table);
                }
            }
        }
        Interpret.setPlayer1Win(0);
        Interpret.setPlayer2Win(0);

        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File(filePath2), output);
    }
}
