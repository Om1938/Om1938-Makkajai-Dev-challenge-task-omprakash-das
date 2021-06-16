import java.io.File;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        /**
         * Create receipt using a quotes Array.
         */
        String[] quotes = new String[] { "1 book at 12.49", "1 music CD at 14.99", "1 chocolate bar at 0.85" };
        Receipt receiptArray = new Receipt(quotes);
        System.out.println(receiptArray);

        /**
         * Create receipt using a file containing quote strings.
         */
        File file = new File("lib/case1.txt");
        Receipt receiptFile = new Receipt(file);
        System.out.println(receiptFile);

        file = new File("lib/case2.txt");
        receiptFile = new Receipt(file);
        System.out.println(receiptFile);

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the Number of quotes : ");
        int noOfQuotes = sc.nextInt();
        sc.nextLine(); // Consuming the <enter> from taking number of quotes
        Receipt receipt = new Receipt(); // Creating the reciept Object

        for (int i = 0; i < noOfQuotes; i++) {
            System.out.print("Enter quote " + (i + 1) + ": ");
            String quote = sc.nextLine();
            receipt.addItem(quote);
        }

        System.out.println();
        System.out.println(receipt);
        sc.close();
    }
}
