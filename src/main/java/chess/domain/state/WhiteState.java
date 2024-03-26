package chess.domain.state;

import chess.domain.Board;
import chess.domain.Color;
import chess.domain.position.Position;

public class WhiteState extends MoveState {

    WhiteState() {
    }

    @Override
    public GameState move(Board board, Position source, Position target) {
        board.move(source, target, Color.WHITE);

        if (board.isKingDead()) {
            return END_STATE;
        }

        return BLACK_STATE;
    }

    @Override
    public GameState status() {
        return this;
    }
}
