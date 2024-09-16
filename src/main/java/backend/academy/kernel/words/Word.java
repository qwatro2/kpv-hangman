package backend.academy.kernel.words;

import lombok.Getter;

@Getter
public class Word {
    private final String word;
    private final String prompt;
    private final DifficultyLevel difficultyLevel;
    private final Category category;

    protected Word(
        String word,
        String prompt,
        DifficultyLevel difficultyLevel,
        Category category
    ) {
        this.word = word;
        this.prompt = prompt;
        this.difficultyLevel= difficultyLevel;
        this.category = category;
    }

    public boolean containsLetter(char letter) {
        return this.word.contains(Character.toString(letter));
    }
}
