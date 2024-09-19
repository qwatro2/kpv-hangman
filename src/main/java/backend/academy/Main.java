package backend.academy;

import backend.academy.kernel.client.ConsoleHangmanReader;
import backend.academy.kernel.client.ConsoleHangmanWriter;
import backend.academy.kernel.hangman.Hangman;
import backend.academy.kernel.hangman.SoloHangman;
import backend.academy.kernel.words.JsonWordsReceiver;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Main {
    public static void main(String[] args) {
        Hangman game = new SoloHangman(
            new JsonWordsReceiver("src/main/resources/words.json"),
            new ConsoleHangmanReader().setPrintStream(System.out).setInputStream(System.in),
            new ConsoleHangmanWriter().setPrintStream(System.out)
        );
        game.run();
    }
}
