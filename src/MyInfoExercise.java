import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class MyInfoExercise {

    public static final String FILE_NAME = "MyInfo.txt";

    public static void main(String[] args) {

        // Declarations
        String first = "Will";
        String last = "Craycroft";

        // File input stream
        PrintWriter writer = null;

        // Open/write file
        try {
            writer = new PrintWriter(FILE_NAME);
        }
        catch (FileNotFoundException e) {
            System.err.println("Couldn't create file "+ FILE_NAME);
            System.exit(0);
        }

        // Write first, last name to file
        writer.printf("%s %s %n", first, last);

        // On next line, print numbers 1 through 10
        for (int i = 1; i <= 10; i++) {
            writer.print(i + " ");
        }

        writer.close();

        System.out.println(FILE_NAME + " was successfully created and updated.");
    }
}
