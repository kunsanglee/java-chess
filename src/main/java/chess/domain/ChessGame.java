package chess.domain;

import static chess.domain.state.GameState.READY_STATE;

import chess.domain.position.Position;
import chess.domain.state.GameState;

public class ChessGame {
    private final Board board;
    private GameState gameState;

    public ChessGame(Board board) {
        this.board = board;
        this.gameState = READY_STATE;
    }

    public boolean isPlaying() {
        return gameState.isPlaying();
    }

    public void start() {
        gameState = gameState.start();
    }

    public void move(Position source, Position target) {
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
}
