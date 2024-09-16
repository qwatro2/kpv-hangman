package backend.academy.kernel.hangman;

import backend.academy.kernel.dto.GameState;
import backend.academy.kernel.words.Category;
import backend.academy.kernel.words.DifficultyLevel;
import backend.academy.kernel.words.Word;

import java.util.List;

public interface Hangman {
    void run();
    List<Word> receiveWordsList();
    DifficultyLevel receiveDifficultyLevel();
    Category receiveCategory();
    Integer receiveNumberOfFails();
    String receiveLetterToGuess();
    void sendGameState(GameState state);
}
