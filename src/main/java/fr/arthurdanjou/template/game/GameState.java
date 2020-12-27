package fr.arthurdanjou.template.game;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

public enum GameState {

    LOBBY,
    GAME,
    END,
    ;

    public boolean isGame() {
        return this == GAME;
    }

    public boolean isNotGame() {
        return this == LOBBY || this == END;
    }

    @Getter @Setter
    private static GameState state;

    public static boolean isState(GameState... states) {
        return Arrays.stream(states).anyMatch(gameState -> gameState == state);
    }
}
