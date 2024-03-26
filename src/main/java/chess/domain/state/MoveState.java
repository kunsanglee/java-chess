package chess.domain.state;

public abstract class MoveState implements GameState {

    protected MoveState() {
    }

    @Override
    public GameState start() {
        throw new UnsupportedOperationException("이미 시작한 상태입니다.");
    }

    @Override
    public GameState end() {
        return END_STATE;
    }

    @Override
    public boolean isPlaying() {
        return true;
    }
}
