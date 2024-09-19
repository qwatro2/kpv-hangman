package backend.academy.kernel.hangman;

import backend.academy.kernel.client.HangmanReader;
import backend.academy.kernel.client.HangmanWriter;
import backend.academy.kernel.dto.GameState;
import backend.academy.kernel.words.Category;
import backend.academy.kernel.words.DifficultyLevel;
import backend.academy.kernel.words.Word;
import backend.academy.kernel.words.WordsReceiver;
import com.google.common.collect.Iterables;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import org.apache.commons.lang3.StringUtils;

public class SoloHangman implements Hangman {
    private final WordsReceiver wordsReceiver;
    private final HangmanReader hangmanReader;
    private final HangmanWriter hangmanWriter;

    private final Random random;

    private List<Word> words;

    protected DifficultyLevel chosenDifficultyLevel;
    protected Category chosenCategory;
    private Integer chosenNumberOfFails;

    private Word chosenWord;
    private List<Boolean> guessedLetters;

    private Integer currentNumberOfFails;
    private boolean isPromptShown;
    private String currentMessage;
    private final HashSet<Character> triedLetters;

    public SoloHangman(
        WordsReceiver wordsReceiver, HangmanReader hangmanReader, HangmanWriter hangmanWriter
    ) {
        this.wordsReceiver = wordsReceiver;
        this.hangmanReader = hangmanReader;
        this.hangmanWriter = hangmanWriter;

        this.random = new Random();

        this.currentNumberOfFails = 0;
        this.isPromptShown = false;
        this.currentMessage = null;
        this.triedLetters = new HashSet<>();
    }

    @Override public void run() {
        receiveInfo();

        this.chosenWord = chooseWord();
        constructGuessedLetters();

        while (!isGameEnded()) {
            GameState currentGameState = constructCurrentGameState();
            sendGameState(currentGameState);

            String attempt = receiveLetterToGuess();
            if (!validateAttempt(attempt)) {
                continue;
            }

            char letter = convertOneCharStringToUppercaseChar(attempt);
            if (!validateLetter(letter)) {
                continue;
            }

            processCorrectLetter(letter);
        }

        setGamesResult();

        GameState currentGameState = constructCurrentGameState();
        sendGameState(currentGameState);
    }

    @Override public List<Word> receiveWordsList() {
        return wordsReceiver.getWords();
    }

    @Override public DifficultyLevel receiveDifficultyLevel() {
        return hangmanReader.readDifficultyLevel();
    }

    @Override public Category receiveCategory() {
        return hangmanReader.readCategory();
    }

    @Override public Integer receiveNumberOfFails() {
        return hangmanReader.readNumberOfFails();
    }

    @Override public String receiveLetterToGuess() {
        return hangmanReader.readLetterToGuess();
    }

    @Override public void sendGameState(GameState state) {
        hangmanWriter.writeGameState(state);
    }

    private void receiveInfo() {
        this.words = receiveWordsList();

        this.chosenDifficultyLevel = receiveDifficultyLevel();
        this.chosenCategory = receiveCategory();
        this.chosenNumberOfFails = receiveNumberOfFails();
    }

    private void constructGuessedLetters() {
        this.guessedLetters = new ArrayList<>(this.chosenWord.length());
        for (int i = 0; i < this.chosenWord.length(); ++i) {
            this.guessedLetters.add(false);
        }
    }

    protected boolean validateAttempt(String attempt) {
        if (attempt == null) {
            this.currentMessage = "Error due reading your answer. Repeat please.";
            return false;
        }

        if (StringUtils.lowerCase(attempt).equals("prompt")) {
            this.currentMessage = null;
            this.isPromptShown = true;
            return false;
        }

        if (attempt.length() != 1) {
            this.currentMessage = "You should write exactly one character. Repeat please.";
            return false;
        }

        return true;
    }

    protected char convertOneCharStringToUppercaseChar(String string) {
        return StringUtils.upperCase(string).charAt(0);
    }

    protected boolean validateLetter(char letter) {
        if (letter < 'A' || letter > 'Z') {
            this.currentMessage = "You should write english letter. Repeat please.";
            return false;
        }

        if (this.triedLetters.contains(letter)) {
            this.currentMessage = "You already tried letter " + letter + ". Repeat please.";
            return false;
        }

        return true;
    }

    private void processCorrectLetter(char letter) {
        if (this.chosenWord.containsLetter(letter)) {
            processLetterThatExists(letter);
        } else {
            processLetterThatNotExists(letter);
        }
        this.triedLetters.add(letter);
    }

    private void processLetterThatExists(char letter) {
        List<Integer> indexesOfLetter = this.chosenWord.getIndexesOfLetter(letter);
        for (Integer index : indexesOfLetter) {
            this.guessedLetters.set(index, true);
        }
        this.currentMessage = "Great! Letter " + letter + " is in word.";
    }

    private void processLetterThatNotExists(char letter) {
        ++this.currentNumberOfFails;
        this.currentMessage = "Oops! Letter " + letter + " isn't in word";
    }

    private void setGamesResult() {
        this.isPromptShown = false;
        if (isPlayerWon()) {
            this.currentMessage = "Congratulations! You won";
        } else {
            this.currentMessage = "Don't be upset! You lose, the word is " + this.chosenWord.word();
        }
    }

    private Word chooseWord() {
        List<Word> suitableWords = words.stream().filter(
                word -> word.difficultyLevel() == this.chosenDifficultyLevel && word.category() == this.chosenCategory)
            .toList();
        int indexOfChosenWord = this.random.nextInt(suitableWords.size());
        return suitableWords.get(indexOfChosenWord);
    }

    private String constructViewString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < this.chosenWord.length(); ++i) {
            if (this.guessedLetters.get(i)) {
                builder.append(this.chosenWord.charAt(i));
            } else {
                builder.append('_');
            }
        }
        return builder.toString();
    }

    private GameState constructCurrentGameState() {
        return new GameState(
            this.chosenDifficultyLevel,
            this.chosenCategory,
            this.chosenNumberOfFails,
            this.currentNumberOfFails,
            constructViewString(),
            this.isPromptShown ? this.chosenWord.prompt() : null,
            this.currentMessage
        );
    }

    private boolean isPlayerWon() {
        return Iterables.all(this.guessedLetters, it -> it);
    }

    private boolean isPlayerLose() {
        return Objects.equals(this.currentNumberOfFails, this.chosenNumberOfFails);
    }

    private boolean isGameEnded() {
        return isPlayerWon() || isPlayerLose();
    }
}
