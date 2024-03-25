package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceTypeTest {

    @DisplayName("기물 타입에 맞는 점수를 반환한다")
    @Test
    void getScore() {
        assertAll(
                () -> assertThat(PieceType.NONE.getScore()).isEqualTo(0),
                () -> assertThat(PieceType.KING.getScore()).isEqualTo(0),
                () -> assertThat(PieceType.PAWN.getScore()).isEqualTo(1),
                () -> assertThat(PieceType.KNIGHT.getScore()).isEqualTo(2.5),
                () -> assertThat(PieceType.BISHOP.getScore()).isEqualTo(3),
                () -> assertThat(PieceType.ROOK.getScore()).isEqualTo(5),
                () -> assertThat(PieceType.QUEEN.getScore()).isEqualTo(9)
        );
    }
}
