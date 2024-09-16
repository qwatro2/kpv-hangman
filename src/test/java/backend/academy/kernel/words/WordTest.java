package backend.academy.kernel.words;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyChar;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class WordTest {
    private static Stream<Arguments> provideWordsAndLettersForContainsLetter() {
        return Stream.of(
            Arguments.of(new Word("abc", "", DifficultyLevel.EASY, Category.ANIMALS), 'a', true),
            Arguments.of(new Word("qwertyuio", "", DifficultyLevel.EASY, Category.ANIMALS), 'o', true),
            Arguments.of(new Word("abc", "", DifficultyLevel.EASY, Category.ANIMALS), 'z', false),
            Arguments.of(new Word("", "", DifficultyLevel.EASY, Category.ANIMALS), 'a', false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideWordsAndLettersForContainsLetter")
    void containsLetter_ShouldReturnTrueForWordsThatContainsLetter(Word word, char letter, boolean expected) {
        assertThat(word.containsLetter(letter)).isEqualTo(expected);
    }
}
