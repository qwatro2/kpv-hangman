package backend.academy.kernel.words;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import java.io.File;
import java.io.IOException;
import java.util.List;

@AllArgsConstructor
public class JsonWordsReceiver implements WordsReceiver {
    private String pathToJson;

    @Override
    public List<Word> getWords() {
        File wordsJson = new File(pathToJson);
        try {
            return new ObjectMapper().readValue(wordsJson, new TypeReference<>(){});
        } catch (IOException e) {
            return null;
        }
    }
}
