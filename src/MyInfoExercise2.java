import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MyInfoExercise2 {

    public static final String FILE_NAME = "MyInfo.txt";

    public static void main (String[] args) {

        // Declarations
        Scanner fileIn = null;
        FileInputStream inputStream = null;

        String fullName;

        // Open file
        try {
            inputStream = new FileInputStream(FILE_NAME);
        }
        catch (FileNotFoundException e) {
            System.err.println("Couldn't open file: " + FILE_NAME);
            System.exit(0);
        }

        fileIn = new Scanner(inputStream);

        // Read first line using nextLine()
        if (fileIn.hasNextLine()) {
            System.out.println(fileIn.nextLine());
        }

        // Read integers on second line using nextInt in while loop
        while (fileIn.hasNextInt()) {
            System.out.print(fileIn.nextInt() + " ");
        }

        fileIn.close();

        System.out.println("\nFile reading is complete.");

    }
}
