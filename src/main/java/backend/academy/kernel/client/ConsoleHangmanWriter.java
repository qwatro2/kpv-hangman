package backend.academy.kernel.client;

import backend.academy.kernel.dto.GameState;
import java.io.PrintStream;

public class ConsoleHangmanWriter implements HangmanWriter {
    private PrintStream printStream;

    private void printSeparator() {
        printStream.println("~".repeat(25));
    }

    private void printPicture(int current, int maximum) {
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
            printStream.println("Something went wrong");
            printEmptyPicture(4);
        }
    }

    private void printEmptyPicture(int times) {
        for (int i = 0; i < times; ++i) {
            printStream.println();
        }
    }

    private void printHorizontal(boolean isWithPillar) {
        printStream.println("\t" + (isWithPillar ? "|" : "_") + "_".repeat(isWithPillar ? 7 : 8));
    }

    private void printPillarAndRope(int times) {
        for (int i = 0; i < times; ++i) {
            printStream.println("\t|     |");
        }
    }

    private void printPillar(int times) {
        for (int i = 0; i < times; ++i) {
            printStream.println("\t|");
        }
    }

    private void printHuman() {
        printStream.println("\t|     O ");
        printStream.println("\t|    /|\\");
        printStream.println("\t|     | ");
        printStream.println("\t|    / \\");
    }

    @Override
    public HangmanWriter setPrintStream(PrintStream printStream) {
        this.printStream = printStream;
        return this;
    }

    @Override
    public void writeGameState(GameState state) {
        printSeparator();
        printPicture(state.currentNumberOfFails(), state.chosenNumberOfFails());
        printSeparator();
        printStream.println("Difficulty level: " + state.chosenDifficultyLevel());
        printStream.println("Category: " + state.chosenCategory());
        printStream.println("Number of fails: " + state.currentNumberOfFails() + " / " + state.chosenNumberOfFails());
        printStream.println("Word: " + state.guessedLetters());
        if (state.prompt() != null) {
            printStream.println("Prompt: " + state.prompt());
        }
        if (state.message() != null) {
            printStream.println("Message: " + state.message());
        }
    }
}
