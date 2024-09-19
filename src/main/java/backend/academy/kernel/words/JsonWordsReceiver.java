package backend.academy.kernel.words;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class JsonWordsReceiver implements WordsReceiver {
    private String pathToJson;

    protected static Optional<File> getJsonFile(String path) {
        if (path == null) {
            return Optional.empty();
        }

        File jsonFile = new File(path);
        if (!jsonFile.exists()) {
            return Optional.empty();
        }

        return Optional.of(jsonFile);
    }

    @Override
    public List<Word> getWords() {
        Optional<File> wordsJson = getJsonFile(pathToJson);

        if (wordsJson.isEmpty()) {
            return null;
        }

        try {
            return new ObjectMapper().readValue(wordsJson.get(), new TypeReference<>() {
            });
        } catch (IOException e) {
            return null;
        }
    }
}
