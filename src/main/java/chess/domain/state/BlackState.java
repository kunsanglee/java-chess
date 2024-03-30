package chess.domain.state;

import static chess.domain.state.WhiteState.WHITE_STATE;

import chess.domain.Board;
import chess.domain.Color;
import chess.domain.position.Position;

public class BlackState extends MoveState {
    static BlackState BLACK_STATE = new BlackState();

    private BlackState() {
    }

    @Override
    public GameState move(Board board, Position source, Position target) {
        board.move(source, target, Color.BLACK);

        return WHITE_STATE;
    }

    @Override
    public GameState status() {
        return this;
    }
}
