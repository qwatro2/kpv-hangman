package backend.academy.kernel.words;

import java.util.ArrayList;
import java.util.List;

public record Word(String word,
                   String prompt) {
    public int length() {
        return word.length();
    }

    public char charAt(int index) throws IndexOutOfBoundsException {
        return word.charAt(index);
    }

    public boolean containsLetter(char letter) {
        return this.word.contains(Character.toString(letter));
    }

    public List<Integer> getIndexesOfLetter(char letter) {
        List<Integer> indexesOfLetter = new ArrayList<>();
        for (int i = 0; i < word.length(); ++i) {
            if (word.charAt(i) == letter) {
                indexesOfLetter.add(i);
            }
        }
        return indexesOfLetter;
    }
}
