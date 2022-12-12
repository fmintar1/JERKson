package io.zipcoder;

import io.zipcoder.utils.FileReader;
import io.zipcoder.utils.Item;

import java.util.*;

public class GroceryReporter {
    int milkCounter = 0;
    int breadCounter = 0;
    int cookiesCounter = 0;
    int applesCounter = 0;
    int milkPrice1Counter = 0;
    int milkPrice2Counter = 0;
    int breadPriceCounter = 0;
    int cookiesPriceCounter = 0;
    int applesPrice1Counter = 0;
    int applesPrice2Counter = 0;
    double milkPrice1 = 0;
    double milkPrice2 = 0;
    double breadPrice = 0;
    double cookiesPrice = 0;
    double applesPrice1 = 0;
    double applesPrice2 = 0;
    private final String originalFileText;
    private List<Item> storage;
    private ItemParser parser = new ItemParser();
    public GroceryReporter(String jerksonFileName) {
        this.originalFileText = FileReader.readFile(jerksonFileName);
    }
    public void counterOver9000() {
        for(int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getName().equals("milk")) milkCounter++;
            if (storage.get(i).getName().equals("bread")) breadCounter++;
            if (storage.get(i).getName().equals("cookies")) cookiesCounter++;
            if (storage.get(i).getName().equals("apples")) applesCounter++;
            if (storage.get(i).getName().equals("milk") && storage.get(i).getPrice().equals(3.23)) {
                milkPrice1Counter++;
                milkPrice1 = storage.get(i).getPrice();
            }
            if (storage.get(i).getName().equals("milk") && storage.get(i).getPrice().equals(1.23)) {
                milkPrice2Counter++;
                milkPrice2 = storage.get(i).getPrice();
            }
            if (storage.get(i).getName().equals("bread") && storage.get(i).getPrice().equals(1.23)) {
                breadPriceCounter++;
                breadPrice = storage.get(i).getPrice();
            }
            if (storage.get(i).getName().equals("cookies") && storage.get(i).getPrice().equals(2.25)) {
                cookiesPriceCounter++;
                cookiesPrice = storage.get(i).getPrice();
            }
            if (storage.get(i).getName().equals("apples") && storage.get(i).getPrice().equals(0.25)) {
                applesPrice1Counter++;
                applesPrice1 = storage.get(i).getPrice();
            }
            if (storage.get(i).getName().equals("apples") && storage.get(i).getPrice().equals(0.23)) {
                applesPrice2Counter++;
                applesPrice2 = storage.get(i).getPrice();
            }
        }
    }
    @Override
    public String toString() {
        this.storage = parser.parseItemList(originalFileText);
        counterOver9000();
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("name:%8s \t\t %s: %d %s\n","Milk","seen",milkCounter,"times"))
                .append("============= \t \t =============\n")
                .append(String.format("Price: \t%5.2f\t\t %s: %d %s\n",milkPrice1,"seen",milkPrice1Counter,"times"))
                .append("-------------\t\t -------------\n")
                .append(String.format("Price: \t%5.2f\t\t %s: %d %s\n",milkPrice2,"seen",milkPrice2Counter,"time"))
                .append("\n")
                .append(String.format("name:%8s \t\t %s: %d %s\n","Bread","seen",breadCounter,"times"))
                .append("============= \t \t =============\n")
                .append(String.format("Price: \t%5.2f\t\t %s: %d %s\n",breadPrice,"seen",breadPriceCounter,"times"))
                .append("-------------\t\t -------------\n")
                .append("\n")
                .append(String.format("name:%8s \t\t %s: %d %s\n","Cookies","seen",cookiesCounter,"times"))
                .append("============= \t \t =============\n")
                .append(String.format("Price: \t%5.2f\t\t %s: %d %s\n",cookiesPrice,"seen",cookiesPriceCounter,"times"))
                .append("-------------\t\t -------------\n")
                .append("\n")
                .append(String.format("name:%8s \t\t %s: %d %s\n","Apples","seen",applesCounter,"times"))
                .append("============= \t \t =============\n")
                .append(String.format("Price: \t%5.2f\t\t %s: %d %s\n",applesPrice1,"seen",applesPrice1Counter,"times"))
                .append("-------------\t\t -------------\n")
                .append(String.format("Price: \t%5.2f\t\t %s: %d %s\n",applesPrice2,"seen",applesPrice2Counter,"times"))
                .append("\n")
                .append(String.format("%-15s\t \t %s: %d %s","Errors","seen",parser.getErrorCount(),"times"))
                .append("\n");
        return sb.toString();
    }
}
