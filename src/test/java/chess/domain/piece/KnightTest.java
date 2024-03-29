package chess.domain.piece;


import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Board;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KnightTest {

    @DisplayName("아군을 만나기 직전까지만 이동 가능하다.")
    @Test
    void givenKingMoveWhenMeetTeamMThenStop() {
        Knight knight = Knight.WHITE;
        Position currentKingPosition = Position.from(File.A, Rank.FOUR);
        Map<Position, Piece> board = Map.of(currentKingPosition, knight,
                Position.from(File.A, Rank.FIVE), Pawn.WHITE_PAWN,
                Position.from(File.B, Rank.SIX), Queen.WHITE,
                Position.from(File.C, Rank.THREE), Queen.WHITE
        );

        Set<Position> movablePositions = knight.calculateMovablePositions(currentKingPosition, new Board(board));

        assertThat(movablePositions).isEqualTo(
                Set.of(Position.from(File.C, Rank.FIVE),
                        Position.from(File.B, Rank.TWO)));
    }

    @DisplayName("적군을 만난 위치까지 이동 가능하다.")
    @Test
    void givenKingMoveWhenMeetEnemyThenStopAtEnemyPosition() {
        Knight knight = Knight.WHITE;
        Position currentKingPosition = Position.from(File.A, Rank.FOUR);
        Map<Position, Piece> board = Map.of(currentKingPosition, knight,
                Position.from(File.A, Rank.FIVE), Pawn.BLACK_PAWN,
                Position.from(File.B, Rank.SIX), Queen.WHITE,
                Position.from(File.C, Rank.THREE), Queen.BLACK
        );

        Set<Position> movablePositions = knight.calculateMovablePositions(currentKingPosition, new Board(board));

        assertThat(movablePositions).isEqualTo(
                Set.of(Position.from(File.C, Rank.FIVE),
                        Position.from(File.B, Rank.TWO),
                        Position.from(File.C, Rank.THREE)));
    }
}
