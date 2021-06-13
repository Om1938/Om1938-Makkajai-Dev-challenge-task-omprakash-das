import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Receipt {
    private List<Item> items = new ArrayList<>();
    private float totalAmount;
    private float totalTax;

    Scanner input = new Scanner(System.in);

    public Receipt() {
        System.out.println("Welcome to the store");
    }

    /**
     * Constructor to take quotes from a given file.
     * 
     * @param file file object to read quotes from
     */
    public Receipt(File file) {
        try {

            input = new Scanner(file);

            while (input.hasNextLine()) {
                String quote = input.nextLine();
                addItem(quote);
            }
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Constructor to take quotes from quotes array
     * 
     * @param quotes Array of String containing quotes
     */
    public Receipt(String[] quotes) {
        for (String quote : quotes) {
            addItem(quote);
        }
    }

    private void addItem(String quote) {
        Item item = new Item(quote);
        boolean added = items.add(item);
        if (added) {
            totalAmount += item.calculatePrice();
            totalTax += item.salesTax();
        }
    }

    /**
     * Overriding toString to get formatted output on System.out.println
     */
    @Override
    public String toString() {
        StringBuilder bld = new StringBuilder();
        for (Item item : items) {
            bld.append(item.toString());
            bld.append(System.lineSeparator());
        }
        bld.append("Sales Taxes: " + Utility.format(totalTax) + System.lineSeparator());
        bld.append("Total: " + Utility.format(totalAmount) + System.lineSeparator());
        return bld.toString();
    }

}
