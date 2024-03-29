package chess.domain.piece;

import chess.domain.Board;
import chess.domain.Color;
import chess.domain.Direction;
import chess.domain.position.Position;
import java.util.Set;

public abstract class Piece {
    protected final Set<Direction> directions;
    private final Color color;

    protected Piece(Color color, Set<Direction> directions) {
        this.color = color;
        this.directions = directions;
    }

    public abstract Set<Position> calculateMovablePositions(Position currentPosition, Board board);

    public boolean isWhite() {
        return color.isWhite();
    }

    public boolean isEmpty() {
        return getPieceType() == PieceType.NONE;
    }

    public boolean isNotSameColor(Piece otherPiece) {
        return !color.isSame(otherPiece.color);
    }

    public boolean isSameColor(Color other) {
        return color.isSame(other);
    }

    public abstract boolean isKing();

    public abstract PieceType getPieceType();

    public Color getColor() {
        return color;
    }
}
