package chess.domain;


import static chess.domain.state.ReadyState.READY_STATE;

import chess.domain.position.Position;
import chess.domain.state.GameState;
import chess.dto.Move;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChessGame {
    private final List<Move> moveHistory;
    private final Board board;
    private GameState gameState;

    public ChessGame(Board board) {
        this.moveHistory = new ArrayList<>();
        this.board = board;
        this.gameState = READY_STATE;
    }

    public boolean isPlaying() {
        return gameState.isPlaying();
    }

    public void start() {
        gameState = gameState.start();
    }

    public boolean move(Position source, Position target) {
        gameState = gameState.move(board, source, target);
        moveHistory.add(new Move(source, target));
        return board.isKingDead();
    }

    public void moveForHistory(Position source, Position target) {
        gameState = gameState.move(board, source, target);
    }

    public void end() {
        gameState = gameState.end();
    }

    public ChessGameResult status() {
        gameState = gameState.status();
        return board.calculateGameScore();
    }

    public Board getBoard() {
        return board;
    }

    public List<Move> getMoveHistory() {
        return Collections.unmodifiableList(moveHistory);
    }

    public GameStatus getGameStatusByKing() {
        if (board.isKingDead()) {
            return GameStatus.FINISHED;
        }
        return GameStatus.PLAYING;
    }
}
