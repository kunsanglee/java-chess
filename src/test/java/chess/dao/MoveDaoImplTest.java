package chess.dao;

import chess.domain.position.Position;
import chess.dto.MoveRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoveDaoImplTest {
    private final MoveDaoImpl moveDaoImpl = new MoveDaoImpl();

    @DisplayName("moveDao를 저장할 수 있다.")
    @Test
    void saveMoveDao() {
        Position source = Position.from("a2");
        Position target = Position.from("a4");

        moveDaoImpl.save(new MoveRequest(source.getValue(), target.getValue(), 1L));

        Position source2 = Position.from("b2");
        Position target2 = Position.from("b4");

        moveDaoImpl.save(new MoveRequest(source2.getValue(), target2.getValue(), 1L));
    }
}
