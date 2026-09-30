package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    private model.Board board;

    @BeforeEach
    public void setUp() {
        board = new model.Board(); // il va sexcuter before chaque test
    }

    // X commence, le jeu est en cours, gagnant est null
    @Test
    public void testInitialState() {
        assertEquals(model.Player.X, board.getCurrentTurn());
        assertTrue(board.isInProgressMode());
        assertNull(board.getWinner());
    }

    //  la case se remplit et le tour change (X -> O)
    @Test
    public void testValidMove() {
        board.mark(0, 0); // X joue
        assertEquals(model.Player.O, board.getCurrentTurn()); // tour de O
    }

    //  le coup est ignoré, le joueur ne change pas
    @Test
    public void testMarkOccupiedCell() {
        board.mark(0, 0);
        board.mark(0, 0); // O rejoue au même endroit
        assertEquals(model.Player.O, board.getCurrentTurn()); // C'est toujours a O de jouer
    }

    // Coup hors grille
    @Test
    public void testOutOfBounds() {
        board.mark(-1, 0); // prblm dans ligne
        board.mark(0, 3);  // prblm dans colonne
        assertEquals(model.Player.X, board.getCurrentTurn()); // Aucun coup valide
    }

    // le jeu passe en FINISHED et annonce X gagnant
    @Test
    public void testHorizontalWin() {
        board.mark(0, 0);
        board.mark(1, 0);
        board.mark(0, 1);
        board.mark(1, 1);
        board.mark(0, 2);

        assertTrue(board.isInFinishedMode());
        assertEquals(model.Player.X, board.getWinner());
    }

    // Impossible de jouer si la partie est terminee
    @Test
    public void testCannotPlayAfterGameIsFinished() {
        board.mark(0, 0);
        board.mark(1, 0);
        board.mark(0, 1);
        board.mark(1, 1);
        board.mark(0, 2);

        // O essaie de jouer après la fin
        board.mark(2, 2);
        assertTrue(board.isInFinishedMode());
    }
}