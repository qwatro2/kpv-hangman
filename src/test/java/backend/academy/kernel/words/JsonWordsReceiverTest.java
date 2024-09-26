package backend.academy.kernel.words;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class JsonWordsReceiverTest {
    private final static String basePath = "src/test/resources/";

    private static Stream<Arguments> provideCorrectReceiversForGetWords() {
        return Stream.of(
                Arguments.of(new JsonWordsReceiver(basePath + "words.json")),
                Arguments.of(new JsonWordsReceiver(basePath + "few_words.json"))
        );
    }

    @ParameterizedTest
    @MethodSource("provideCorrectReceiversForGetWords")
    void getWords_ShouldReturnCorrectList(JsonWordsReceiver jsonWordsReceiver) {
        assertThat(jsonWordsReceiver.getWords()).isNotNull();
    }

    private static Stream<Arguments> provideIncorrectReceiversForGetWords() {
        return Stream.of(
                Arguments.of(new JsonWordsReceiver(basePath + "wrong_data.json")),
                Arguments.of(new JsonWordsReceiver(basePath + "empty.json")),
                Arguments.of(new JsonWordsReceiver(basePath + "not_exists.json")),
                Arguments.of(new JsonWordsReceiver(null))
        );
    }

    @ParameterizedTest
    @MethodSource("provideIncorrectReceiversForGetWords")
    void getWords_ShouldReturnNull(JsonWordsReceiver jsonWordsReceiver) {
        assertThat(jsonWordsReceiver.getWords()).isNull();
    }
}
