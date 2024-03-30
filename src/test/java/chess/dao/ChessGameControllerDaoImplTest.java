package chess.dao;

import chess.domain.GameStatus;
import chess.dto.ChessGameRequest;
import chess.dto.ChessGameResponse;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessGameControllerDaoImplTest {
    private final ChessGameDaoImpl chessGameDaoImpl = new ChessGameDaoImpl();

    @DisplayName("진행중인 체스 게임을 저장할 수 있다.")
    @Test
    void savePlayingChessGame() {
        ChessGameRequest chessGameRequest = new ChessGameRequest(GameStatus.PLAYING.name());
        chessGameDaoImpl.save(chessGameRequest);
    }

    @DisplayName("종료된 체스 게임을 저장할 수 있다.")
    @Test
    void saveFinishedChessGame() {
        ChessGameRequest chessGameRequest = new ChessGameRequest(GameStatus.FINISHED.name());
        chessGameDaoImpl.save(chessGameRequest);
    }

    @DisplayName("진행중인 마지막 게임을 불러올 수 있다")
    @Test
    void findRecentGame() {
        Optional<ChessGameResponse> recentPlayableGame = chessGameDaoImpl.findRecentPlayableGame();

        recentPlayableGame.ifPresent(System.out::println);
    }
}
