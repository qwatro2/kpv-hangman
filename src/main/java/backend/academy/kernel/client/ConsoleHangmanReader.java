package backend.academy.kernel.client;

import backend.academy.kernel.words.Category;
import backend.academy.kernel.words.DifficultyLevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class ConsoleHangmanReader implements HangmanReader {
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private final Random random = new Random();

    @Override
    public DifficultyLevel readDifficultyLevel() {
        System.out.println("""
                Choose difficulty level:
                [1] Easy
                [2] Medium
                [3] Hard
                [other] Random""");

        try {
            String answer = reader.readLine();
            return processStringToDifficultyLevel(answer);
        } catch (IOException e) {
            return chooseRandomDifficultyLevel();
        }
    }

    @Override
    public Category readCategory() {
        System.out.println("""
                Choose category:
                [1] Cars
                [2] Animals
                [3] Cities
                [other] Random""");

        try {
            String answer = reader.readLine();
            return switch (answer) {
                case "1" -> Category.CARS;
                case "2" -> Category.ANIMALS;
                case "3" -> Category.CITIES;
                default -> chooseRandomCategory();
            };
        } catch (IOException e) {
            return chooseRandomCategory();
        }
    }

    @Override
    public Integer readNumberOfFails() {
        System.out.println("Write number of fails between 5 and 8 after which you lose.\n" +
                "If you write anything other than 5, 6, 7, 8 a random number from 5 to 8 will be selected");
        try {
            String answer = reader.readLine();
            return switch (answer) {
                case "5" -> 5;
                case "6" -> 6;
                case "7" -> 7;
                case "8" -> 8;
                default -> chooseRandomNumberOfFails();
            };
        } catch (IOException e) {
            return chooseRandomNumberOfFails();
        }
    }

    @Override
    public String readLetterToGuess() {
        System.out.print("Write letter to guess or 'prompt' to get prompt: ");
        try {
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    protected DifficultyLevel processStringToDifficultyLevel(String string) {
        return switch (string) {
            case "1" -> DifficultyLevel.EASY;
            case "2" -> DifficultyLevel.MEDIUM;
            case "3" -> DifficultyLevel.HARD;
            default -> chooseRandomDifficultyLevel();
        };
    }

    private DifficultyLevel chooseRandomDifficultyLevel() {
        DifficultyLevel[] difficultyLevels = DifficultyLevel.values();
        int numberOfDifficultyLevels = difficultyLevels.length;
        return difficultyLevels[random.nextInt(numberOfDifficultyLevels)];
    }

    private Category chooseRandomCategory() {
        Category[] categories = Category.values();
        int numberOfCategories = categories.length;
        return categories[random.nextInt(numberOfCategories)];
    }

    private int chooseRandomNumberOfFails() {
        return 5 + random.nextInt(4);
    }

}
