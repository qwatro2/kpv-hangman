package backend.academy.kernel.hangman;

import backend.academy.kernel.client.ConsoleHangmanReader;
import backend.academy.kernel.client.ConsoleHangmanWriter;
import backend.academy.kernel.words.JsonWordsReceiver;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class SoloHangmanTest {
    private final SoloHangman soloHangman = new SoloHangman(
        new JsonWordsReceiver("src/test/resource/words.json"),
        new ConsoleHangmanReader(),
        new ConsoleHangmanWriter()
    );

    private static Stream<Arguments> provideStringsForValidateAttempt() {
        return Stream.of(
            Arguments.of("a", true),
            Arguments.of("F", true),
            Arguments.of("з", true),
            Arguments.of("Ж", true),
            Arguments.of("0", true),
            Arguments.of("aB", false),
            Arguments.of(null, false),
            Arguments.of("prompt", false),
            Arguments.of("PROMPT", false),
            Arguments.of("pRoMpT", false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideStringsForValidateAttempt")
    void validateAttempt_ShouldReturnFalseForIncorrectStrings(String string, boolean expected) {
        assertThat(soloHangman.validateAttempt(string)).isEqualTo(expected);
    }

    private static Stream<Arguments> provideStringsForConvertOneCharStringToUppercaseChar() {
        return Stream.of(
            Arguments.of("a", 'A'),
            Arguments.of("A", 'A')
        );
    }

    @ParameterizedTest
    @MethodSource("provideStringsForConvertOneCharStringToUppercaseChar")
    void convertOneCharStringToUppercaseChar(String string, char expected) {
        assertThat(soloHangman.convertOneCharStringToUppercaseChar(string)).isEqualTo(expected);
    }

    private static Stream<Arguments> provideStringsForValidateLetter() {
        return Stream.of(
            Arguments.of('A', true),
            Arguments.of('Z', true),
            Arguments.of('N', true),
            Arguments.of('a', false),
            Arguments.of('m', false),
            Arguments.of('z', false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideStringsForValidateLetter")
    void validateLetter(char letter, boolean expected) {
        assertThat(soloHangman.validateLetter(letter)).isEqualTo(expected);
    }
}
