package backend.academy.kernel.client;

import backend.academy.kernel.words.Category;
import backend.academy.kernel.words.DifficultyLevel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ConsoleHangmanReaderTest {
    ConsoleHangmanReader consoleHangmanReader = new ConsoleHangmanReader();

    private static Stream<Arguments> provideStringsForProcessStringToDifficultyLevel() {
        return Stream.of(
            Arguments.of("1", DifficultyLevel.EASY),
            Arguments.of("2", DifficultyLevel.MEDIUM),
            Arguments.of("3", DifficultyLevel.HARD)
        );
    }

    @ParameterizedTest
    @MethodSource("provideStringsForProcessStringToDifficultyLevel")
    void processStringToDifficultyLevel_ShouldReturnCorrectDifficultyLevel(String number, DifficultyLevel expected) {
        assertThat(consoleHangmanReader.processStringToDifficultyLevel(number)).isEqualTo(expected);
    }

    private static Stream<Arguments> provideStringsForProcessStringToCategory() {
        return Stream.of(
            Arguments.of("1", Category.CARS),
            Arguments.of("2", Category.ANIMALS),
            Arguments.of("3", Category.CITIES)
        );
    }

    @ParameterizedTest
    @MethodSource("provideStringsForProcessStringToDifficultyLevel")
    void processStringToCategory_ShouldReturnCorrectCategory(String number, Category expected) {
        assertThat(consoleHangmanReader.processStringToCategory(number)).isEqualTo(expected);
    }

    @Test
    void readNumberOfFails() {
    }

    @Test
    void readLetterToGuess() {
    }
}
