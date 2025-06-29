package task1;

import java.io.*;
import java.util.*;

public class FileOperations {

    // Write content to a file (overwrites if it exists)
    public static void writeToFile(String fileName, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(content);
            System.out.println("File written successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to file.");
        }
    }

    // Read and print content from a file
    public static void readFromFile(String fileName) {
        System.out.println("\nReading file:");
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }
    }

    // Replace a specific line in the file
    public static void modifyFile(String fileName, int lineNumber, String newLine) {
        try {
            List<String> lines = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            reader.close();

            if (lineNumber - 1 < lines.size()) {
                lines.set(lineNumber - 1, newLine);
            } else {
                System.out.println("Line number out of range.");
                return;
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            for (String l : lines) {
                writer.write(l);
                writer.newLine();
            }
            writer.close();

            System.out.println("Line modified successfully.");
        } catch (IOException e) {
            System.out.println("Error modifying file.");
        }
    }

    // Main method to test all operations
    public static void main(String[] args) {
        String file = "example.txt";
        String content = "Line 1: Hello\nLine 2: Original line\nLine 3: Bye";

        writeToFile(file, content);
        readFromFile(file);
        modifyFile(file, 2, "Line 2: This line has been changed.");
        readFromFile(file);

        // Open the file after writing (only works on systems with Desktop support)
        try {
            java.awt.Desktop.getDesktop().open(new File(file));
        } catch (Exception e) {
            System.out.println("Can't open file automatically.");
        }

    }
}

