package io.zipcoder;
import io.zipcoder.utils.FileReader;
import io.zipcoder.utils.Item;
import java.util.*;
public class GroceryReporter {
    double milkPrice1 = 3.23, milkPrice2 = 1.23;
    double breadPrice = 1.23;
    double cookiesPrice = 2.25;
    double applesPrice1 = 0.25, applesPrice2 = 0.23;
    private final String originalFileText;
    private ItemParser parser;
    private List<Item> storage;
    StringBuilder sb = new StringBuilder();
    public GroceryReporter(String jerksonFileName) {
        this.originalFileText = FileReader.readFile(jerksonFileName);
        this.parser = new ItemParser();
        this.storage = parser.parseItemList(originalFileText);
    }
    public int itemCounter(String name) {
        int counter = 0;
        for (Item item : storage) {
            if (item.getName().equals(name)) counter++;
        }
        return counter;
    }
    public int priceCounter(String name, Double price) {
        int counter = 0;
        for (Item item : storage) {
            if (item.getName().equals(name) && item.getPrice().equals(price)) counter++;
        }
        return counter;
    }
    @Override
    public String toString() {
        sb.append(firstPriceItem("Milk", itemCounter("milk"), milkPrice1, priceCounter("milk",milkPrice1))
                .append(secondPriceItem(milkPrice2, priceCounter("milk", milkPrice2)))
                .append(firstPriceItem("Bread", itemCounter("bread"), breadPrice, priceCounter("bread",breadPrice)))
                .append("\n")
                .append(firstPriceItem("Cookies", itemCounter("cookies"), cookiesPrice, priceCounter("cookies", cookiesPrice)))
                .append("\n")
                .append(firstPriceItem("Apples", itemCounter("apples"), applesPrice1, priceCounter("apples", applesPrice1)))
                .append(secondPriceItem(applesPrice2, priceCounter("apples", applesPrice2)))
                .append(String.format("%-15s\t \t %s: %d %s", "Errors", "seen", parser.getErrorCount(), "times"))
                .append("\n"));
        return sb.toString();
    }
    public StringBuilder firstPriceItem(String name, int itemsCounter, double itemsPrice, int itemsPriceCounter) {
        StringBuilder temp = new StringBuilder();
        temp.append(String.format("name:%8s \t\t %s: %d %s\n", name, "seen", itemsCounter, "times"))
                .append("============= \t \t =============\n")
                .append(String.format("Price: \t%5.2f\t\t %s: %d %s\n", itemsPrice, "seen", itemsPriceCounter, "times"))
                .append("-------------\t\t -------------\n");
        return temp;
    }
    public StringBuilder secondPriceItem(double itemsPrice, int itemsPriceCounter) {
        String times = "times";
        if(itemsPriceCounter == 1) times = "time";
        StringBuilder temp = new StringBuilder();
        temp.append(String.format("Price: \t%5.2f\t\t %s: %d %s\n", itemsPrice, "seen", itemsPriceCounter, times))
                .append("\n");
        return temp;
    }
}