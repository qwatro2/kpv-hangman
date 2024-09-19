package backend.academy.kernel.client;

import backend.academy.kernel.words.Category;
import backend.academy.kernel.words.DifficultyLevel;

public interface HangmanReader {
    DifficultyLevel readDifficultyLevel();

    Category readCategory();

    Integer readNumberOfFails();

    String readLetterToGuess();
}
