package main.CommandInterpretor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.CardInput;

public class MakeCardInObj {

    CardInput card;

    ObjectNode cardToObj (ObjectMapper objectMapper) {
        ObjectNode objCard = objectMapper.createObjectNode();
        ArrayNode colors = objectMapper.createArrayNode();
        for (int i = 0 ; i < card.getColors().size() ; i++) {
            colors.add(card.getColors().get(i));
        }
        objCard.put("mana" , card.getMana())
                .put("attackDamage" , card.getAttackDamage())
                .put("health" , card.getHealth())
                .put("description" , card.getDescription())
                .set("colors" , colors);
        objCard.put("name" , card.getName());
        return objCard;
    }
}
