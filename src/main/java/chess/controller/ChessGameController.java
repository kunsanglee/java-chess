package chess.controller;

import chess.domain.ChessGame;
import chess.domain.ChessGameResult;
import chess.domain.ChessGameService;
import chess.domain.command.CommandCondition;
import chess.domain.command.CommandExecutor;
import chess.domain.command.GameCommand;
import chess.domain.position.Position;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class ChessGameController {
    private final Map<GameCommand, CommandExecutor> commands = new EnumMap<>(GameCommand.class);
    private final ChessGameService chessGameService;
    private final ChessGame chessGame;


    public ChessGameController(ChessGameService chessGameService, ChessGame chessGame) {
        this.chessGameService = chessGameService;
        this.chessGame = chessGame;
    }

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

        while (chessGame.isPlaying()) {
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
        chessGame.start();
        OutputView.printChessBoard(chessGame.getBoard());
    }

    private void move(CommandCondition commandCondition) {
        Position source = Position.from(commandCondition.getSource());
        Position target = Position.from(commandCondition.getTarget());
        chessGame.move(source, target);
        OutputView.printChessBoard(chessGame.getBoard());
    }

    private void end() {
        chessGame.end();
    }

    private void status() {
        ChessGameResult chessGameResult = chessGame.status();
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
