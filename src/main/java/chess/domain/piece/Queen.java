package chess.domain.piece;


import chess.domain.Color;
import chess.domain.Direction;

public class Queen extends MultiStepPiece {
    public static final Queen WHITE = new Queen(Color.WHITE);
    public static final Queen BLACK = new Queen(Color.BLACK);

    private Queen(Color color) {
        super(color, Direction.ALL);
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.QUEEN;
    }
}
