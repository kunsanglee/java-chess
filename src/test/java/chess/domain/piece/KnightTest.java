package chess.domain.piece;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import chess.domain.Board;
import chess.domain.Color;
import chess.domain.File;
import chess.domain.Position;
import chess.domain.Rank;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KnightTest {
    @DisplayName("생성 테스트")
    @Test
    public void create() {
        assertThatCode(() -> new Knight(Color.BLACK)).doesNotThrowAnyException();
    }

    @DisplayName("아군을 만나기 직전까지만 이동 가능하다.")
    @Test
    public void givenKingMoveWhenMeetTeamMThenStop() {
        Knight knight = new Knight(Color.WHITE);
        Position currentKingPosition = new Position(File.A, Rank.FOUR);
        Map<Position, Piece> board = Map.of(currentKingPosition, knight,
                new Position(File.A, Rank.FIVE), new Pawn(Color.WHITE),
                new Position(File.B, Rank.SIX), new Queen(Color.WHITE),
                new Position(File.C, Rank.THREE), new Queen(Color.WHITE)
        );

        Set<Position> movablePositions = knight.calculateMovablePositions(currentKingPosition, new Board(board));

        assertThat(movablePositions).isEqualTo(
                Set.of(new Position(File.C, Rank.FIVE),
                        new Position(File.B, Rank.TWO)));
    }

    @DisplayName("적군을 만난 위치까지 이동 가능하다.")
    @Test
    public void givenKingMoveWhenMeetEnemyThenStopAtEnemyPosition() {
        Knight knight = new Knight(Color.WHITE);
        Position currentKingPosition = new Position(File.A, Rank.FOUR);
        Map<Position, Piece> board = Map.of(currentKingPosition, knight,
                new Position(File.A, Rank.FIVE), new Pawn(Color.BLACK),
                new Position(File.B, Rank.SIX), new Queen(Color.WHITE),
                new Position(File.C, Rank.THREE), new Queen(Color.BLACK)
        );

        Set<Position> movablePositions = knight.calculateMovablePositions(currentKingPosition, new Board(board));

        assertThat(movablePositions).isEqualTo(
                Set.of(new Position(File.C, Rank.FIVE),
                        new Position(File.B, Rank.TWO),
                        new Position(File.C, Rank.THREE)));
    }
}
