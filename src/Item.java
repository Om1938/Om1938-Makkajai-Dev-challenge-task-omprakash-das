import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

@SuppressWarnings("unused")
public class Item {
    private static final String UNUSED = "unused";

    /**
     * Static list for checking exempted items.
     */
    static String[] exemptedItems = new String[] { "book", "books", "chocolate", "chocolates", "pill", "pills",
            "medicine", "medicines" };

    private String name;
    private int quantity;
    private float price;

    private boolean isImported;
    private boolean isExempted;

    private boolean isValid = false;

    /**
     * Private constructor so that objects cannot be creating using Default.
     * constructor.
     */
    @SuppressWarnings(UNUSED)
    private Item() {
        this.quantity = 0;
        this.name = "";
        this.isImported = false;
        this.price = 0;

        this.isExempted = false;
    }

    /**
     * Constructor to parse item from the given quote.
     * 
     * @param quote - string to parse into Item.
     */
    public Item(String quote) {
        try {
            String[] string = quote.split(" ");
            this.quantity = Integer.parseInt(string[0]);
            isImported = quote.contains("imported");
            isExempted = isExempted(quote);
            if (!quote.contains(" at ")) {
                throw new Exception("Invalid Quote");
            } else {
                int atIndex = quote.lastIndexOf("at");
                name = quote.substring(1, atIndex - 1);
                price = Float.parseFloat((quote.substring(atIndex + 2)));
                isValid = true;
            }
        } catch (Exception e) {
            isValid = false;
            System.out.println("Item not Added");
        }
    }

    @Override
    public String toString() {
        return quantity + name + ": " + Utility.format(calculatePrice());
    }

    /**
     * Utility function for check if the quote is in exempted items.
     * 
     * @param quote - string to parse and check if its exempted.
     * @return true if item is exempted from sales tax, else false.
     */
    private static boolean isExempted(String quote) {
        for (String string : exemptedItems) {
            if (quote.indexOf(string) != -1)
                return true;
        }
        return false;
    }

    /**
     * Method to calculate the total Price of Item.
     * 
     * @return returns the price of items formatted to 2 precision decimal.
     */
    public float calculatePrice() {
        DecimalFormat df = new DecimalFormat("#.##");
        return Float.valueOf(df.format((quantity * price + salesTax())));
    }

    /**
     * 
     * @return the sales tax applied on the item.
     * 
     *         BigDecimal is used for rounding of to next 0.05.
     */
    public float salesTax() {
        BigDecimal tax = new BigDecimal("0");
        if (!isExempted) {
            tax = tax.add(BigDecimal.valueOf(quantity * price).multiply(new BigDecimal("0.10")));
        }
        if (isImported) {
            tax = tax.add(BigDecimal.valueOf(quantity * price).multiply(new BigDecimal("0.05")));
        }
        /**
         * Dividing and Multiplying by 0.05 to round the tax to 0.05
         */
        BigDecimal rounding = BigDecimal.valueOf(0.05);
        tax = tax.divide(rounding, 0, RoundingMode.UP);
        tax = tax.multiply(rounding);
        tax = tax.setScale(2, RoundingMode.UNNECESSARY);
        return tax.floatValue();
    }

    /**
     * Getter for Price.
     * 
     * @return price of individual item.
     */

    public float getPrice() {
        return price;
    }

    /**
     * Getter for Name
     * 
     * @return name of item.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for if the item is imported
     *
     * @return true if item is Imported , else false.
     */
    public boolean isImported() {
        return this.isImported;
    }

    /**
     * Getter for if the item is exempted from tax
     *
     * @return true if item is exempted from sales tax.
     */
    public boolean isExempted() {
        return isExempted;
    }

    /**
     * Getter for if the item is created from a valid quote
     *
     * @return true if item is created from a valid quote, else false;
     */
    public boolean isValid() {
        return isValid;
    }

}
