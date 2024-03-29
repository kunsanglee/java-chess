package chess.domain.state;

import chess.domain.Board;
import chess.domain.position.Position;

public class ReadyState implements GameState {
    ReadyState() {
    }

    @Override
    public GameState start() {
        return WHITE_STATE;
    }

    @Override
    public GameState move(Board board, Position source, Position target) {
        throw new UnsupportedOperationException("준비 상태에서는 움직일 수 없습니다.");
    }

    @Override
    public GameState end() {
        return END_STATE;
    }

    @Override
    public GameState status() {
        throw new UnsupportedOperationException("준비 상태에서는 점수를 계산할 수 없습니다.");
    }

    @Override
    public boolean isPlaying() {
        return true;
    }
}
