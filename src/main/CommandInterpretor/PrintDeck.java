package main.CommandInterpretor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.ActionsInput;
import main.Player;

public class PrintDeck {

    Player player;

    public ArrayNode printDeck(ObjectMapper objectMapper){

        ArrayNode cardList = objectMapper.createArrayNode();

        for (int i = 0 ; i < player.getDeck().size() ; i++) {
            ObjectNode aux = objectMapper.createObjectNode();
            MakeCardInObj cardInObj = new MakeCardInObj();
            cardInObj.card = player.getDeck().get(i);
            aux = cardInObj.cardToObj(objectMapper);
            cardList.addPOJO(aux);
        }
        return cardList;
    }
}
