package chess.dao;

import chess.domain.position.Position;
import chess.dto.MoveRequest;
import chess.dto.MoveResponse;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoveDaoImplTest {
    private final MoveDaoImpl moveDaoImpl = new MoveDaoImpl();

    @DisplayName("moveDao를 저장할 수 있다.")
    @Test
    void saveMoveDao() {
        Position source = Position.from("a2");
        Position target = Position.from("a4");

        moveDaoImpl.save(MoveRequest.of(source, target, 1L));

        Position source2 = Position.from("b2");
        Position target2 = Position.from("b4");

        moveDaoImpl.save(MoveRequest.of(source2, target2, 1L));
    }

    @DisplayName("기물의 움직임을 전부 불러올 수 있다")
    @Test
    void findAllMoveDao() {
        List<MoveResponse> moves = moveDaoImpl.findAll(1L);

//        assertThat(moves).isEqualTo(List.of(
//                new MoveResponse(1L, "a2", "a4", 1L),
//                new MoveResponse(2L, "b2", "b4", 1L)
//        ));
    }
}
