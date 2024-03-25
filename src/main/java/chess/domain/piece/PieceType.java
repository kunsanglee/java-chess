package chess.domain.piece;

public enum PieceType {
    KING(0),
    PAWN(1),
    KNIGHT(2.5),
    BISHOP(3),
    ROOK(5),
    QUEEN(9),
    NONE(0);

    private final double score;

    PieceType(double score) {
        this.score = score;
    }

    public double getScore() {
        return score;
    }
}
