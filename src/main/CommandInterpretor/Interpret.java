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

    public ArrayNode interpretation(ActionsInput command, ArrayNode output, Player player){
        if (command.getCommand().equals("getPlayerDeck")) {
            output.addObject().put("command" , command.getCommand())
                    .put("playerIdx" , command.getPlayerIdx())
                    .putPOJO("output" , player.getDeck());
        } else if (command.getCommand().equals("getPlayerHero")) {
            output.addObject().put("command" , command.getCommand())
                    .put("playerIdx" , command.getPlayerIdx())
                    .putPOJO("output" , player.getHero());
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
