package backend.academy.kernel.words;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;

class WordTest {
    private static Stream<Arguments> provideWordsAndLettersForContainsLetter() {
        return Stream.of(
            Arguments.of(new Word("abc", ""), 'a', true),
            Arguments.of(new Word("qwertyuio", ""), 'o', true),
            Arguments.of(new Word("abc", ""), 'z', false),
            Arguments.of(new Word("", ""), 'a', false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideWordsAndLettersForContainsLetter")
    void containsLetter_ShouldReturnTrueForWordsThatContainsLetter(Word word, char letter, boolean expected) {
        assertThat(word.containsLetter(letter)).isEqualTo(expected);
    }
}
