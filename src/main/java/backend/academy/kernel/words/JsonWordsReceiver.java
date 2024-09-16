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

    protected Optional<File> getJsonFile() {
        return Optional.of(new File(pathToJson));
    }

    @Override
    public List<Word> getWords() {
        Optional<File> wordsJson = getJsonFile();

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
