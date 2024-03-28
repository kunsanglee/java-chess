package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.database.DBConnectionUtil;
import java.sql.Connection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PieceDaoTest {

    @DisplayName("커넥션을 연결한다")
    @Test
    void whenGetConnectionThenConnectionNotNull() {
        //"jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD
        Connection connection = DBConnectionUtil.getConnection();

        assertThat(connection).isNotNull();
    }
}
