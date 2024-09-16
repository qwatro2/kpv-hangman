package backend.academy.kernel.words;

public record Word(String word,
                   String prompt,
                   DifficultyLevel difficultyLevel,
                   Category category) {
    public boolean containsLetter(char letter) {
        return this.word.contains(Character.toString(letter));
    }
}
