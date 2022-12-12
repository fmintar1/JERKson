package io.zipcoder;

import io.zipcoder.utils.Item;
import io.zipcoder.utils.ItemParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ItemParser {
    private int errorCount;

    public List<Item> parseItemList(String valueToParse) {
        List<Item> result = new ArrayList<>();
        String[] line = valueToParse.toLowerCase().split("##");
        for (String l : line) {
            String[] sentence = l.split("[:;@^*%!]");
            if (l.contains("name") || l.contains("price") ||
                    l.contains("type") || l.contains("expiration") ||
                    sentence.length == 8) {
                for (int i = 0; i < sentence.length; i++) {
                    if (Objects.equals(sentence[i], "")) {
                        this.errorCount++;
                        break;
                    } else if (Objects.equals(sentence[i], "co0kies")) {
                        sentence[i] = "cookies";
                    } else if (i == sentence.length - 1) {
                        result.add(new Item(sentence[1], Double.parseDouble(sentence[3]), sentence[5], sentence[7]));
                    }
                }
            }
        }
        return result;
    }
    public int getErrorCount() {
        return errorCount;
    }

    public Item parseSingleItem(String singleItem) throws ItemParseException {
        String[] splitter = singleItem.toLowerCase().split("[:;@^*%#]");
        if (splitter.length != 8) {
            throw new ItemParseException();
        }
        return new Item(splitter[1], Double.parseDouble(splitter[3]), splitter[5], splitter[7]);
    }
    //(?<=:)[;#] does not work
    //((?<=:);#) does not work
    //[(?<=:)]; does not work
    //[(?<=:);] removes : as well
    //[(?<=:),;] removes : as well
    //[(?<=:)]|; removes : as well
    //(?<=:); does not work
    //(?<=:)|; works only if it's not next to : (naMe:;price:)
    //;|#|(?<=:) WORKS! Split ; then # then delimiter :
}
