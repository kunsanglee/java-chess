package chess.dto;

public record MoveRequest(String source, String target, Long chessGameId) {
}
