// TODO: extra credit for using Scanner to read first and BufferReader to read second

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class StockDemo {

    public static final String INPUT_FILE_NAME = "stocks1.txt";
    public static final String OUTPUT_FILE_NAME = "stocks2.txt";

    public static void main(String[] args) {

        //Create array of 100 Stock objects
        Stock[] stockArray = new Stock[100];

        // Using a Scanner, read stocks file
        readStocksWithScanner(stockArray, INPUT_FILE_NAME);

    }

    /*
     * This method uses a Scanner to read a text file containing stock information and stores the
     * data into the passed array.
     */
    public static void readStocksWithScanner (Stock[] stockArray, String fileName) {
        // Declarations
        Scanner fileIn;
        FileInputStream inputStream = null;
        int companyCount = 0;   // Tracks number of companies copied to array to avoid exceeding array bound.

        // Stock instance variables;
        String name;
        String stockSymbol;
        double stockPrice;

        // Try to open file
        try {
            inputStream = new FileInputStream(fileName);
        }
        catch (FileNotFoundException e) {
            System.err.println("Failed to open file " + fileName);
            System.exit(0);
        }
        // Instantiate Scanner
        fileIn = new Scanner (inputStream);

        // Start of line read loop
        while (fileIn.hasNextLine()) {

            // Check if stockArray is full
            if (companyCount >= stockArray.length) {
                System.err.println("Could not store stock information past line "
                        + companyCount + ". Stock array is full.");
                break;
            }
            // Set delimiter to ","
            fileIn.useDelimiter(",");
            // Read company name, stock symbol (String)
            name = fileIn.next();
            stockSymbol = fileIn.next();
            // Set delimiter to white space before reading last item
            fileIn.useDelimiter("[,\\s]");
            // Read stock price (double)
            stockPrice = fileIn.nextDouble();
            // Clear newline char
            fileIn.nextLine();

            // Create Stock object and send to array, increment array counter
            stockArray[companyCount++] = new Stock(name, stockSymbol, stockPrice);

        }  // end of hastNextLine() while loop

        // Close Scanner
        fileIn.close();
    }

    /*
     * This method multiplies the price of all Stock objects in the given array by a given factor.
     * Takes in an array of Stock objects (Stock[]) and the multiplier (double).
     */
    public static void updateStockPrices(Stock[] stockArray, double multiplier) {

    }

    /*
     * This method uses a PrintWriter to write data from an array of Stock objects to a file.
     * Takes in an array of Stock objects (Stock[]) and a file name (String).
     */
    public static void writeStocks(Stock[] stockArray, String fileName) {

    }
}
