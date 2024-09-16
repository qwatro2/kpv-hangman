package backend.academy.kernel.dto;

import backend.academy.kernel.words.Category;
import backend.academy.kernel.words.DifficultyLevel;

public record GameState(DifficultyLevel chosenDifficultyLevel,
                        Category chosenCategory,
                        Integer chosenNumberOfFails,
                        Integer currentNumberOfFails,
                        String guessedLetters,
                        String prompt,
                        String message) {
}
