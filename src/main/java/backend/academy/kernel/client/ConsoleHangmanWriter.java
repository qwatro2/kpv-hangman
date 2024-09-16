package backend.academy.kernel.client;

import backend.academy.kernel.dto.GameState;

public class ConsoleHangmanWriter implements HangmanWriter {
    @Override
    public void writeGameState(GameState state) {
        printSeparator();
        printPicture(state.currentNumberOfFails(), state.chosenNumberOfFails());
        printSeparator();
        System.out.println("Difficulty level: " + state.chosenDifficultyLevel());
        System.out.println("Category: " + state.chosenCategory());
        System.out.println("Number of fails: " + state.currentNumberOfFails() + " / " + state.chosenNumberOfFails());
        System.out.println("Word: " + state.guessedLetters());
        if (state.prompt() != null) {
            System.out.println("Prompt: " + state.prompt());
        }
        if (state.message() != null) {
            System.out.println("Message: " + state.message());
        }
    }

    private static void printSeparator() {
        System.out.println("~".repeat(25));
    }

    private static void printPicture(int current, int maximum) {
        if (current == 0) {
            printEmptyPicture(maximum + 2);
        } else if (current == 1) {
            printEmptyPicture(maximum + 1);
            printHorizontal(false);
        } else if (current == 2) {
            printEmptyPicture(1);
            printPillar(maximum);
            printHorizontal(true);
        } else if (current == 3) {
            printHorizontal(false);
            printPillar(maximum);
            printHorizontal(true);
        } else if (current > 3 && current < maximum) {
            printHorizontal(false);
            printPillarAndRope(current - 3);
            printPillar(maximum - current + 3);
            printHorizontal(true);
        } else if (current == maximum) {
            printHorizontal(false);
            printPillarAndRope(maximum - 4);
            printHuman();
            printHorizontal(true);
        } else {
            printEmptyPicture(4);
            System.out.println("Something went wrong");
            printEmptyPicture(4);
        }
    }

    private static void printEmptyPicture(int times) {
        for (int i = 0; i < times; ++i) {
            System.out.println();
        }
    }

    private static void printHorizontal(boolean isWithPillar) {
        System.out.println("\t" + (isWithPillar ? "|" : "_") + "_".repeat(isWithPillar ? 7 : 8));
    }

    private static void printPillarAndRope(int times) {
        for (int i = 0; i < times; ++i) {
            System.out.println("\t|     |");
        }
    }

    private static void printPillar(int times) {
        for (int i = 0; i < times; ++i) {
            System.out.println("\t|");
        }
    }

    private static void printHuman() {
        System.out.println("\t|     O ");
        System.out.println("\t|    /|\\");
        System.out.println("\t|     | ");
        System.out.println("\t|    / \\");
    }
}
