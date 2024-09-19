package backend.academy.kernel.client;

import backend.academy.kernel.dto.GameState;

import java.io.PrintStream;

public interface HangmanWriter {
    HangmanWriter setPrintStream(PrintStream printStream);

    void writeGameState(GameState state);
}
