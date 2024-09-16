package backend.academy.kernel.client;

import backend.academy.kernel.dto.GameState;

public interface HangmanWriter {
    void writeGameState(GameState state);
}
