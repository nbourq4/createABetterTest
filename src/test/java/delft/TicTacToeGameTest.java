package delft;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TicTacToeGameTest {
    private TicTacToeGame game;

    @BeforeEach
    void setUp() {
        game = new TicTacToeGame();
    }

    @Test
    void testInitialPlayerIsX() {
        assertEquals('X', game.getCurrentPlayer(), "Initial player should be X.");
    }

    @Test
    void testMakeMove() {
        assertTrue(game.makeMove(0, 0), "Move should be successful.");
        assertEquals('X', game.getBoard()[0][0], "Board should have X at position (0, 0).");
        game.switchPlayer();
        assertTrue(game.makeMove(0, 1), "Move should be successful.");
        assertEquals('O', game.getBoard()[0][1], "Board should have O at position (0, 1).");
    }

    @Test
    void testInvalidMove() {
        game.makeMove(0, 0);
        assertFalse(game.makeMove(0, 0), "Move should be invalid as the cell is already occupied.");
    }

    @Test
    void testSwitchPlayer() {
        game.switchPlayer();
        assertEquals('O', game.getCurrentPlayer(), "Current player should switch to O.");
    }

    @Test
    void testWinner() {
        game.makeMove(0, 0); // X
        game.switchPlayer();
        game.makeMove(1, 0); // O
        game.switchPlayer();
        game.makeMove(0, 1); // X
        game.switchPlayer();
        game.makeMove(1, 1); // O
        game.switchPlayer();
        game.makeMove(0, 2); // X
        assertTrue(game.isWinner('X'), "X should be the winner with a row of three.");
    }

    @Test
    void testNoWinner() {
        game.makeMove(0, 0); // X
        game.switchPlayer();
        game.makeMove(0, 1); // O
        game.switchPlayer();
        game.makeMove(0, 2); // X
        assertFalse(game.isWinner('X'), "There should be no winner yet.");
        assertFalse(game.isWinner('O'), "There should be no winner yet.");
    }

    @Test
    void testBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                game.makeMove(i, j);
                game.switchPlayer();
            }
        }
        assertTrue(game.isBoardFull(), "The board should be full.");
    }

    @Test
    void testResetGame() {
        game.makeMove(0, 0);
        game.resetGame();
        assertEquals(' ', game.getBoard()[0][0], "Board should be reset to empty.");
        assertEquals('X', game.getCurrentPlayer(), "Player should reset to X.");
    }

    @Test
    void testIsWinnerDiagonal() {
        game.makeMove(0, 0); // X
        game.switchPlayer();
        game.makeMove(1, 0); // O
        game.switchPlayer();
        game.makeMove(1, 1); // X
        game.switchPlayer();
        game.makeMove(2, 0); // O
        game.switchPlayer();
        game.makeMove(2, 2); // X
        assertTrue(game.isWinner('X'), "X should win with a diagonal from top-left to bottom-right.");
    }

    @Test
    void testAIInvalidMove() {
        assertThrows(IllegalStateException.class, () -> game.getBestMove(), "AI should only move as player 'O'.");
    }
}

