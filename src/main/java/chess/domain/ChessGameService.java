package chess.domain;

import chess.dao.ChessGameDao;
import chess.dao.MoveDao;
import chess.dto.ChessGameRequest;
import chess.dto.ChessGameResponse;
import chess.dto.Move;
import chess.dto.MoveResponse;
import java.util.List;
import java.util.Optional;

public class ChessGameService {
    private final ChessGameDao chessGameDao;
    private final MoveDao moveDao;

    public ChessGameService(ChessGameDao chessGameDao, MoveDao moveDao) {
        this.chessGameDao = chessGameDao;
        this.moveDao = moveDao;
    }

    public void save(GameStatus gameStatus) {
        ChessGameRequest chessGameRequest = ChessGameRequest.from(gameStatus);
        chessGameDao.save(chessGameRequest);
    }

    public Optional<ChessGameResponse> getRecentPlayableGame() {
        return chessGameDao.findRecentPlayableGame();
    }

    public List<Move> getMovesByChessGameId(long chessGameId) {
        return moveDao.findAll(chessGameId).stream()
                .map(MoveResponse::from)
                .toList();
    }
}
