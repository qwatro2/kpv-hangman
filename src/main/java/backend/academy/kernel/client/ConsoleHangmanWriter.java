package backend.academy.kernel.client;

import backend.academy.kernel.dto.GameState;
import java.io.PrintStream;

public class ConsoleHangmanWriter implements HangmanWriter {
    private static final int LENGTH_OF_SEPARATION_LINE = 25;
    private static final int LENGTH_OF_FLOOR = 8;
    private static final int NUMBER_OF_FAILS_EMPTY_PICTURE = 0;
    private static final int NUMBER_OF_FAILS_FLOOR = 1;
    private static final int NUMBER_OF_FAILS_PILLAR = 2;
    private static final int NUMBER_OF_FAILS_CEILING = 3;

    private PrintStream printStream;

    private void printSeparator() {
        printStream.println("~".repeat(LENGTH_OF_SEPARATION_LINE));
    }

    private void printPicture(int current, int maximum) {
        if (current == NUMBER_OF_FAILS_EMPTY_PICTURE) {
            printEmptyPicture(maximum + 2);
        } else if (current == NUMBER_OF_FAILS_FLOOR) {
            printEmptyPicture(maximum + 1);
            printHorizontal(false);
        } else if (current == NUMBER_OF_FAILS_PILLAR) {
            printEmptyPicture(1);
            printPillar(maximum);
            printHorizontal(true);
        } else if (current == NUMBER_OF_FAILS_CEILING) {
            printHorizontal(false);
            printPillar(maximum);
            printHorizontal(true);
        } else if (current > NUMBER_OF_FAILS_CEILING && current < maximum) {
            printHorizontal(false);
            printPillarAndRope(current - NUMBER_OF_FAILS_CEILING);
            printPillar(maximum - current + NUMBER_OF_FAILS_CEILING);
            printHorizontal(true);
        } else if (current == maximum) {
            printHorizontal(false);
            printPillarAndRope(maximum - NUMBER_OF_FAILS_CEILING - 1);
            printHuman();
            printHorizontal(true);
        }
    }

    private void printEmptyPicture(int times) {
        for (int i = 0; i < times; ++i) {
            printStream.println();
        }
    }

    private void printHorizontal(boolean isWithPillar) {
        printStream.println("\t" + (isWithPillar ? "|" : "_")
                + "_".repeat(isWithPillar ? LENGTH_OF_FLOOR - 1 : LENGTH_OF_FLOOR));
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
