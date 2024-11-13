package org.example;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Random;

public class PolymorphicTextModifier {
    public static void main(String[] args) {
        String folderPath = "C:\\Users\\myves\\OneDrive\\Desktop\\New folder"; // Update this with the folder path
        modifyFilesInFolder(folderPath);
    }

    public static void modifyFilesInFolder(String folderPath) {
        File folder = new File(folderPath);

        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles();

            if (files != null) {
                for (File file : files) {
                    if (file.isFile() && file.getName().endsWith(".txt")) {
                        applyRandomModification(file);
                    }
                }
            } else {
                System.out.println("No files found in the folder.");
            }
        } else {
            System.out.println("The specified path is not a valid folder.");
        }
    }

    public static void applyRandomModification(File file) {
        Random random = new Random();
        int modificationType = random.nextInt(4); // Choose one of four modification types

        try {
            String originalContent = new String(Files.readAllBytes(file.toPath()));
            String modifiedContent = "";

            switch (modificationType) {
                case 0:
                    modifiedContent = appendMessage(originalContent);
                    System.out.println("Modification: Appended a message to " + file.getName());
                    break;
                case 1:
                    modifiedContent = replaceWords(originalContent);
                    System.out.println("Modification: Replaced words in " + file.getName());
                    break;
                case 2:
                    modifiedContent = reverseContent(originalContent);
                    System.out.println("Modification: Reversed content in " + file.getName());
                    break;
                case 3:
                    modifiedContent = scrambleContent(originalContent);
                    System.out.println("Modification: Scrambled content in " + file.getName());
                    break;
            }

            // Write the modified content back to the file
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(modifiedContent);
            }

        } catch (IOException e) {
            System.out.println("An error occurred while modifying the file: " + file.getName());
            e.printStackTrace();
        }
    }

    public static String appendMessage(String content) {
        return content + "\n[Appended message by PolymorphicTextModifier]";
    }

    public static String replaceWords(String content) {
        return content.replace("example", "sample").replace("test", "trial"); // Replace specific words
    }

    public static String reverseContent(String content) {
        return new StringBuilder(content).reverse().toString(); // Reverse the entire content
    }

    public static String scrambleContent(String content) {
        char[] chars = content.toCharArray();
        Random random = new Random();
        for (int i = 0; i < chars.length; i++) {
            int j = random.nextInt(chars.length);
            // Swap characters at positions i and j
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
        }
        return new String(chars);
    }
}
