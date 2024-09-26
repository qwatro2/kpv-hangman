package backend.academy.kernel.client;

import backend.academy.kernel.words.Category;
import backend.academy.kernel.words.DifficultyLevel;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class ConsoleHangmanReader implements HangmanReader {
    private PrintStream printStream;
    private BufferedReader reader;

    @Override
    public HangmanReader setInputStream(InputStream inputStream) {
        this.reader = new BufferedReader(new InputStreamReader(inputStream));
        return this;
    }

    @Override
    public HangmanReader setPrintStream(PrintStream printStream) {
        this.printStream = printStream;
        return this;
    }

    @Override
    public DifficultyLevel readDifficultyLevel() {
        printStream.println("""
                Choose difficulty level:
                [1] Easy
                [2] Medium
                [3] Hard
                [other] Random""");

        try {
            String answer = reader.readLine();
            return processStringToDifficultyLevel(answer);
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public Category readCategory() {
        printStream.println("""
                Choose category:
                [1] Cars
                [2] Animals
                [3] Cities
                [other] Random""");

        try {
            String answer = reader.readLine();
            return processStringToCategory(answer);
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public Integer readNumberOfFails() {
        printStream.println("Write number of fails between 5 and 8 after which you lose.\n"
                + "If you write anything other than 5, 6, 7, 8 a random number from 5 to 8 will be selected");
        try {
            String answer = reader.readLine();
            return processStringToNumberOfFails(answer);
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public String readLetterToGuess() {
        printStream.print("Write letter to guess or 'prompt' to get prompt: ");
        try {
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    private Integer processStringToNumberOfFails(String string) {
        if (string == null || string.length() != 1) {
            return null;
        }
        char number = string.charAt(0);
        if ('5' <= number && number <= '8') {
            return number - '0';
        }
        return null;
    }

    private DifficultyLevel processStringToDifficultyLevel(String string) {
        return switch (string) {
            case "1" -> DifficultyLevel.EASY;
            case "2" -> DifficultyLevel.MEDIUM;
            case "3" -> DifficultyLevel.HARD;
            default -> null;
        };
    }

    private Category processStringToCategory(String string) {
        return switch (string) {
            case "1" -> Category.CARS;
            case "2" -> Category.ANIMALS;
            case "3" -> Category.CITIES;
            default -> null;
        };
    }
}
