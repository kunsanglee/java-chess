package chess.dao;

import chess.database.DBConnectionUtil;
import chess.dto.ChessGameRequest;
import chess.dto.ChessGameResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ChessGameDaoImpl implements ChessGameDao {

    @Override
    public void save(ChessGameRequest chessGameRequest) {
        String query = "INSERT INTO chess_game (game_status) VALUES (?)";

        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, chessGameRequest.gameStatus());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<ChessGameResponse> findRecentPlayableGame() {
        String query = "SELECT * FROM chess_game WHERE game_status = ? ORDER BY id DESC LIMIT 1";

        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, "PLAYING");
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                long id = resultSet.getLong("id");
                String status = resultSet.getString("game_status");
                return Optional.of(new ChessGameResponse(id, status));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }
}
