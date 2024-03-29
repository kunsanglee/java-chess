package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Board;
import chess.domain.ScoreCalculator;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RookTest {

    @DisplayName("아군을 만나기 직전까지만 이동 가능하다.")
    @Test
    void givenRookMoveWhenMeetTeamMThenStop() {
        Rook rook = Rook.WHITE;
        Position currentRookPosition = Position.of(File.A, Rank.ONE);
        Map<Position, Piece> board = Map.of(
                currentRookPosition, rook,
                Position.of(File.A, Rank.FOUR), Bishop.WHITE,
                Position.of(File.E, Rank.ONE), Knight.WHITE
        );

        Set<Position> movablePositions = rook.calculateMovablePositions(currentRookPosition,
                new Board(board, new ScoreCalculator()));

        assertThat(movablePositions).isEqualTo(
                Set.of(Position.of(File.A, Rank.TWO), Position.of(File.A, Rank.THREE),
                        Position.of(File.B, Rank.ONE),
                        Position.of(File.C, Rank.ONE), Position.of(File.D, Rank.ONE)));
    }

    @DisplayName("적군을 만난 위치까지 이동 가능하다.")
    @Test
    void givenRookMoveWhenMeetEnemyThenStopAtEnemyPosition() {
        Rook rook = Rook.WHITE;
        Position currentRookPosition = Position.of(File.H, Rank.EIGHT);
        Map<Position, Piece> board = Map.of(
                currentRookPosition, rook,
                Position.of(File.G, Rank.EIGHT), Bishop.BLACK,
                Position.of(File.H, Rank.FIVE), Knight.BLACK
        );

        Set<Position> movablePositions = rook.calculateMovablePositions(currentRookPosition,
                new Board(board, new ScoreCalculator()));

        assertThat(movablePositions).isEqualTo(
                Set.of(Position.of(File.G, Rank.EIGHT), Position.of(File.H, Rank.FIVE),
                        Position.of(File.H, Rank.SIX), Position.of(File.H, Rank.SEVEN))
        );
    }

    @DisplayName("킹인지 물어보면 false를 반환한다")
    @Test
    void givenRookWhenIsKingThenReturnFalse() {
        Rook rook = Rook.WHITE;

        assertThat(rook.isKing()).isFalse();
    }
}
