/* Lab5.java
 * Author:  William Craycroft
 * Module:  5
 * Project: Lab 5
 * Problem Statement: This class reads stock information for an input text file. It then modifies the information by
 *      dividing each individual stock price by 1/3. The modified information is written to a second text file.
 *      The second text file is the read back in, modified back to its original value (multiplied by 3).
 *      This third set of values is then written to a third text file.
 *
 * Algorithm / Plan:
 *      1. Instantiate array of stocks of size 100
 *
 *      2. Instantiate Scanner using FileInputStream linked to stocks1.txt
 *      3. For each line in Scanner:
 *          Use comma as a delimiter
 *          Store next String as company name
 *          Store next String as stock symbol
 *          Store next Double as stock price
 *          Create new Stock object with these parameters and add it to the stock array
 *      4. Close Scanner
 *
 *      5. For each value in stock array
 *          Get stock price using accessor method
 *          Multiply stock price by the desired factor (in this case 1/3)
 *          Set the stock price using mutator method
 *
 *      6. Instantiate PrintWriter lined to stocks2.txt
 *      7. For each value in stock array
 *          Print company name, stock symbol and stock price, each delimited by a comma (no spaces)
 *      8. Close PrintWriter
 *
 *      9. Clear stock array by assigning its values to null
 *      10. Repeat Steps 2-4 using file name stocks2.txt (reading modified information back into array)
 *      11. Repeat Step 5, using a multiplier of 3.
 *      12. Repeat Steps 6-8, using file name stocks3.txt
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Lab5 {

    public static final String INPUT_FILE_NAME = "stocks1.txt";             // Input stock file
    public static final String OUTPUT_FILE_NAME = "stocks2.txt";            // Output file 1: original stock data
    public static final String MODIFIED_OUTPUT_FILE_NAME = "stocks3.txt";   // Output file 2: modified stock data

    public static void main(String[] args) {

        //Create array of 100 Stock objects
        Stock[] stockArray = new Stock[100];

        // Using a Scanner, read stocks file
        readStocksWithScanner(stockArray, INPUT_FILE_NAME);

        // Decrease price of all stocky be 1/3
        updateStockPrices(stockArray, (1.0 / 3.0));

        // Write Stock array to first output text file
        writeStocks(stockArray, OUTPUT_FILE_NAME);

        // Clear stockArray
        stockArray = new Stock[100];

        // Using BufferedReader, repopulate array from first output text file
        readStocksWithBufferedReader(stockArray, OUTPUT_FILE_NAME);

        // Restore prices to original value by multiplying by 3
        updateStockPrices(stockArray, 3.0);

        // Write updated stock array to second output file
        writeStocks(stockArray, MODIFIED_OUTPUT_FILE_NAME);
    }

    /*
     * This method uses a Scanner to read a text file containing stock information and stores the
     * data into the passed array.
     */
    public static void readStocksWithScanner(Stock[] stockArray, String fileName) {
        // Declarations
        Scanner fileIn = null;
        FileInputStream inputStream = null;
        int companyCount = 0;   // Tracks number of companies copied to array to avoid exceeding array bound.

        // Stock instance variables;
        String name, stockSymbol;
        double stockPrice;

        // Try to open file
        try {
            inputStream = new FileInputStream(fileName);
        }
        catch (FileNotFoundException e) {
            System.err.println("Failed to open file " + fileName);
            e.printStackTrace();
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
     * This method uses a BufferedReader to read a text file containing stock information and stores the
     * data into the passed array.
     */
    public static void readStocksWithBufferedReader(Stock[] stockArray, String fileName) {
        // Declarations
        BufferedReader fileIn = null;
        FileReader inputStream;
        String currentLine = "";
        int companyCount = 0;           // Tracks number of companies copied to array to avoid exceeding array bound.

        // Stock instance variables
        String name, stockSymbol;
        double stockPrice;

        // Instantiate and connect BufferedReader to FileReader
        try {
            inputStream = new FileReader(fileName);
            fileIn = new BufferedReader(inputStream);
        } catch (FileNotFoundException e) {
            System.err.println("Error opening file " + fileName);
            System.exit(0);
        }

        // Loop through each line until readLine returns null
        do {
            // Check if stockArray is full
            if (companyCount >= stockArray.length) {
                System.err.println("Could not store stock information past line "
                        + companyCount + ". Stock array is full.");
                break;
            }

            try {
                currentLine = fileIn.readLine();
            }
            catch (IOException e) {
                System.err.println("Error reading line " + companyCount);
            }
            // If there is a line to be read
            if (currentLine != null) {
                // Use StringTokenizer to parse information from line
                StringTokenizer tokenizer = new StringTokenizer(currentLine, ",");
                // Read name, symbol and price from tokenizer
                name = tokenizer.nextToken();
                stockSymbol = tokenizer.nextToken();
                stockPrice = Double.parseDouble(tokenizer.nextToken());

                // Create Stock object and send to array, increment array counter
                stockArray[companyCount++] = new Stock(name, stockSymbol, stockPrice);
            }

        // end of currentLine loop
        } while (currentLine != null);

        // When all files have been read, close reader
        try {
            fileIn.close();
        } catch (IOException e) {
            System.err.println("Error closing file " + fileName);
        }
    }

    /*
     * This method multiplies the price of all Stock objects in the given array by a given factor.
     * Takes in an array of Stock objects (Stock[]) and the multiplier (double).
     */
    public static void updateStockPrices(Stock[] stockArray, double multiplier) {
        double stockPrice;

        // Loop through Stock array
        for (Stock currentStock : stockArray) {
            // If element is null, skip
            if (currentStock != null) {
                // Rounded to 2 decimal places
                stockPrice = Math.round(multiplier * currentStock.getStockPrice() * 100.0) / 100.0;
                // set updated Stock price
                currentStock.setStockPrice(stockPrice);
            }
        }
    }

    /*
     * This method uses a PrintWriter to write data from an array of Stock objects to a file.
     * Takes in an array of Stock objects (Stock[]) and a file name (String).
     */
    public static void writeStocks(Stock[] stockArray, String fileName) {
        // Declarations
        PrintWriter writer = null;
        // Stock instance variables
        String name, stockSymbol;
        double stockPrice;
        // Formatter
        DecimalFormat twoDP = new DecimalFormat("0.00");

        // Instantiate writer
        try {
            // Not appending, so only String parameter
            writer = new PrintWriter(fileName);

            // Loop through stockArray
            for (Stock currentStock : stockArray) {

                if (currentStock != null) {
                    // Print each stock on new line
                    name = currentStock.getCompanyName();
                    stockSymbol = currentStock.getStockSymbol();
                    stockPrice = currentStock.getStockPrice();

                    writer.printf("%s,%s,%s\n", name, stockSymbol, twoDP.format(stockPrice));
                }
            }
            // Close writer
            writer.close();

        } catch (FileNotFoundException e) {
            System.err.println("Error creating file " + fileName);
            System.exit(0);
        }

    }
}
