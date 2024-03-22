package chess.domain.piece;

import chess.domain.Board;
import chess.domain.Color;
import chess.domain.Direction;
import chess.domain.position.Position;
import java.util.HashSet;
import java.util.Set;

public abstract class MultiStepPiece extends Piece {
    protected MultiStepPiece(Color color, PieceType pieceType, Set<Direction> directions) {
        super(color, pieceType, directions);
    }

    @Override
    public Set<Position> calculateMovablePositions(Position currentPosition, Board board) {
        Set<Position> movablePositions = new HashSet<>();
        directions.forEach(direction -> addMoves(board, direction, currentPosition, movablePositions));

        return movablePositions;
    }

    private void addMoves(Board board, Direction direction, Position position, Set<Position> movablePositions) {
        while (position.canMoveNext(direction)) {
            position = position.next(direction);
            Piece findPiece = board.findPieceByPosition(position);

            if (isSameColor(findPiece)) {
                break;
            }

            movablePositions.add(position);

            if (!findPiece.isEmpty()) {
                break;
            }
        }
    }
}
