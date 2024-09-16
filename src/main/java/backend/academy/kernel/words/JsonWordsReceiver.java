package backend.academy.kernel.words;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class JsonWordsReceiver implements WordsReceiver {
    private String pathToJson;

    protected static Optional<File> getJsonFile(String path) {
        return Optional.of(new File(path));
    }

    @Override
    public List<Word> getWords() {
        Optional<File> wordsJson = getJsonFile(pathToJson);

        if (wordsJson.isEmpty()) {
            return null;
        }

        try {
            return new ObjectMapper().readValue(wordsJson.get(), new TypeReference<>(){});
        } catch (IOException e) {
            return null;
        }
    }
}
