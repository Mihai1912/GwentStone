package main.CommandInterpretor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.ActionsInput;
import main.Player;

import java.io.File;
import java.io.IOException;

public class Interpret {

    ActionsInput cmd;

    public ArrayNode interpretation(ActionsInput command , ArrayNode output, Player player, ObjectMapper objectMapper){
        if (command.getCommand().equals("getPlayerDeck")) {
            PrintDeck print = new PrintDeck();
            print.player = player;
            output.addObject().put("command" , command.getCommand())
                    .put("playerIdx" , command.getPlayerIdx())
                    .set("output" , print.printDeck(objectMapper));
        } else if (command.getCommand().equals("getPlayerHero")) {
            MakeCardInObj print = new MakeCardInObj();
            print.card = player.getHero();
            output.addObject().put("command" , command.getCommand())
                    .put("playerIdx" , command.getPlayerIdx())
                    .set("output" , print.cardToObj(objectMapper));
        } else if (command.getCommand().equals("getPlayerTurn")) {
            output.addObject().put("command",command.getCommand()).put("output" , player.getIdx());
        }
        return output;
    }

    public ActionsInput getCmd() {
        return cmd;
    }

    public void setCmd(ActionsInput cmd) {
        this.cmd = cmd;
    }
}
