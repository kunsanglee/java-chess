package chess.domain.state;

import chess.domain.Board;
import chess.domain.Color;
import chess.domain.position.Position;

public class BlackState extends MoveState {

    BlackState() {
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
