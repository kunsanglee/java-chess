package chess.domain.state;

import chess.domain.Board;
import chess.domain.position.Position;

public interface GameState {
    GameState BLACK_STATE = new BlackState();
    GameState WHITE_STATE = new WhiteState();
    GameState END_STATE = new EndState();
    GameState READY_STATE = new ReadyState();

    GameState start();

    GameState move(Board board, Position source, Position target);

    GameState end();

    GameState status();

    boolean isPlaying();
}
