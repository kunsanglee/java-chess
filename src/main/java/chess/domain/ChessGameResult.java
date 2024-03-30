package chess.domain;

import java.util.Map;

public class ChessGameResult {
    private final Map<Color, Double> result;

    private ChessGameResult(Map<Color, Double> result) {
        this.result = result;
    }

    public static ChessGameResult of(double whiteScore, double blackScore) {
        return new ChessGameResult(Map.of(Color.WHITE, whiteScore, Color.BLACK, blackScore));
    }

    public Map<Color, Double> getResult() {
        return result;
    }

    public String getWinner() {
        double whiteScore = result.get(Color.WHITE);
        double blackScore = result.get(Color.BLACK);

        if (whiteScore == blackScore) {
            return Color.NONE.name();
        }
        if (whiteScore > blackScore) {
            return Color.WHITE.name();
        }
        return Color.BLACK.name();
    }

    public boolean isDraw() {
        return getWinner().equals(Color.NONE.name());
    }
}
