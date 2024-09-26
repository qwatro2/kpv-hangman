package backend.academy.kernel.words;

import java.util.List;
import java.util.Map;

public interface WordsReceiver {
    Map<DifficultyLevel, Map<Category, List<Word>>> getWords();
}
