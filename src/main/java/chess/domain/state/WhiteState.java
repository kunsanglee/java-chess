package chess.domain.state;

import static chess.domain.state.BlackState.BLACK_STATE;

import chess.domain.Board;
import chess.domain.Color;
import chess.domain.position.Position;

public class WhiteState extends MoveState {
    static WhiteState WHITE_STATE = new WhiteState();

    private WhiteState() {
    }

    @Override
    public GameState move(Board board, Position source, Position target) {
        board.move(source, target, Color.WHITE);

        return BLACK_STATE;
    }

    @Override
    public GameState status() {
        return this;
    }
}
