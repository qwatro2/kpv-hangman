package backend.academy.kernel.client;

import backend.academy.kernel.words.Category;
import backend.academy.kernel.words.DifficultyLevel;
import java.io.InputStream;
import java.io.PrintStream;

public interface HangmanReader {
    HangmanReader setInputStream(InputStream inputStream);

    HangmanReader setPrintStream(PrintStream printStream);

    DifficultyLevel readDifficultyLevel();

    Category readCategory();

    Integer readNumberOfFails();

    String readLetterToGuess();
}
