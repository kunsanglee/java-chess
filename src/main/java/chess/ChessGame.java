package chess;

import static chess.domain.state.GameState.READY_STATE;

import chess.domain.Board;
import chess.domain.BoardFactory;
import chess.domain.ChessGameResult;
import chess.domain.command.CommandCondition;
import chess.domain.command.CommandExecutor;
import chess.domain.command.GameCommand;
import chess.domain.position.Position;
import chess.domain.state.GameState;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class ChessGame {
    private final Map<GameCommand, CommandExecutor> commands = new EnumMap<>(GameCommand.class);
    private final Board board = BoardFactory.createInitialBoard();
    private GameState gameState = READY_STATE;

    public void run() {
        try {
            registerCommands();
            playChess();
        } catch (RuntimeException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
    }

    private void registerCommands() {
        commands.put(GameCommand.START, args -> start());
        commands.put(GameCommand.MOVE, this::move);
        commands.put(GameCommand.END, args -> end());
        commands.put(GameCommand.STATUS, args -> status());
    }

    private void playChess() {
        OutputView.printGameStartMessage();

        while (gameState.isPlaying()) {
            repeatUntilValidCommand();
        }
    }

    private void executeCommand() {
        List<String> inputCommand = InputView.readGameCommand();
        GameCommand gameCommand = GameCommand.from(inputCommand);
        CommandExecutor commandExecutor = commands.get(gameCommand);
        commandExecutor.execute(new CommandCondition(inputCommand));
    }

    private void start() {
        gameState = gameState.start();
        OutputView.printChessBoard(board);
    }

    private void move(CommandCondition commandCondition) {
        Position source = Position.from(commandCondition.getSource());
        Position target = Position.from(commandCondition.getTarget());
        gameState = gameState.move(board, source, target);
        OutputView.printChessBoard(board);
    }

    private void end() {
        gameState = gameState.end();
    }

    private void status() {
        ChessGameResult chessGameResult = board.calculateGameScore();
        OutputView.printChessGameScore(chessGameResult);
    }

    private void repeatUntilValidCommand() {
        try {
            executeCommand();
        } catch (RuntimeException e) {
            OutputView.printErrorMessage(e.getMessage());
            repeatUntilValidCommand();
        }
    }
}
