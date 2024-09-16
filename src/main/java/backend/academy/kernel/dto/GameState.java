package backend.academy.kernel.dto;

import backend.academy.kernel.words.Category;
import backend.academy.kernel.words.DifficultyLevel;

public record GameState(DifficultyLevel chosenDifficultyLevel,
                        Category chosenCategory,
                        Integer chosenNumberOfFails,
                        Integer currentNumberOfFails,
                        String guessedLetters,
                        boolean isGameEnded,
                        boolean isPlayerWon,
                        boolean isPlayerLose,
                        String prompt,
                        String message) {
}
