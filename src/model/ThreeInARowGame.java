package model;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;

/**
 * Java implementation of the 3 in a row game, using the Swing framework.
 *
 * This quick-and-dirty implementation violates a number of software engineering
 * principles and needs a thorough overhaul to improve readability,
 * extensibility, and testability.
 */
public class ThreeInARowGame {
    public static final String GAME_END_NOWINNER = "Game ends in a draw";

    public JFrame gui = new JFrame("Three in a Row");
    private ThreeInARowModel gameModel;
    public JButton[][] blocks = new JButton[3][3];
    public JButton reset = new JButton("Reset");
    public JTextArea playerturn = new JTextArea();
    /**
     * The current player taking their turn
     */
    public String player = "1";
    public int movesLeft = 9;

    /**
     * Starts a new game in the GUI.
     */
    public static void main(String[] args) {
        ThreeInARowGame game = new ThreeInARowGame();
        game.gui.setVisible(true);
    }

    /**
     * Creates a new game initializing the GUI.
     */
    public ThreeInARowGame() {
        gameModel = new ThreeInARowModel(this);
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setSize(new Dimension(500, 350));
        gui.setResizable(true);

        JPanel gamePanel = new JPanel(new FlowLayout());
        JPanel game = new JPanel(new GridLayout(3,3));
        gamePanel.add(game, BorderLayout.CENTER);

        JPanel options = new JPanel(new FlowLayout());
        options.add(reset);
        JPanel messages = new JPanel(new FlowLayout());
        messages.setBackground(Color.white);

        gui.add(gamePanel, BorderLayout.NORTH);
        gui.add(options, BorderLayout.CENTER);
        gui.add(messages, BorderLayout.SOUTH);

        messages.add(playerturn);
        playerturn.setText("Player 1 to play 'X'");

        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });

        // Initialize a JButton for each cell of the 3x3 game board.
        for(int row = 0; row<3; row++) {
            for(int column = 0; column<3 ;column++) {
                blocks[row][column] = new JButton();
                blocks[row][column].setPreferredSize(new Dimension(75,75));
                updateBlock(row,column);
                game.add(blocks[row][column]);
                blocks[row][column].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        move((JButton)e.getSource());
                    }
                });
            }
        }
    }

    /**
     * Moves the current player into the given block.
     *
     * @param block The block to be moved to by the current player
     */
    protected void move(JButton block) {
        --movesLeft;
        if(movesLeft%2 == 1) {
            playerturn.setText("'X': Player 1");
        } else{
            playerturn.setText("'O': Player 2");
        }
        
        if(player.equals("1")) {
            // Check whether player 1 won
            if(block==blocks[0][0]) {
                gameModel.getBlock(0,0).setContents("X");
                gameModel.getBlock(0,0).setIsLegalMove(false);
                updateBlock(0,0);
                player = "2";
                if(movesLeft<7) {
                    if((gameModel.getBlock(0,0).getContents().equals(gameModel.getBlock(0,1).getContents()) &&
                        gameModel.getBlock(0,1).getContents().equals(gameModel.getBlock(0,2).getContents())) ||
                       (gameModel.getBlock(0,0).getContents().equals(gameModel.getBlock(1,0).getContents()) &&
                        gameModel.getBlock(1,0).getContents().equals(gameModel.getBlock(2,0).getContents())) ||
                       (gameModel.getBlock(0,0).getContents().equals(gameModel.getBlock(1,1).getContents()) &&
                        gameModel.getBlock(1,1).getContents().equals(gameModel.getBlock(2,2).getContents()))) {
                        playerturn.setText("Player 1 wins!");
                        endGame();
                    } else if(movesLeft==0) {
                        playerturn.setText(GAME_END_NOWINNER);
                    }
                }
            } else if(block==blocks[0][1]) {
                gameModel.getBlock(0,1).setContents("X");
                gameModel.getBlock(0,1).setIsLegalMove(false);
                updateBlock(0,1);
                player = "2";
                if(movesLeft<7) {
                    if((gameModel.getBlock(0,1).getContents().equals(gameModel.getBlock(0,0).getContents()) &&
                        gameModel.getBlock(0,0).getContents().equals(gameModel.getBlock(0,2).getContents())) ||
                       (gameModel.getBlock(0,1).getContents().equals(gameModel.getBlock(1,1).getContents()) &&
                        gameModel.getBlock(1,1).getContents().equals(gameModel.getBlock(2,1).getContents()))) {
                        playerturn.setText("Player 1 wins!");
                        endGame();
                    } else if(movesLeft==0) {
                        playerturn.setText(GAME_END_NOWINNER);
                    }
                }
            } else if(block==blocks[0][2]) {
                gameModel.getBlock(0,2).setContents("X");
                gameModel.getBlock(0,2).setIsLegalMove(false);
                updateBlock(0,2);
                player = "2";
                if(movesLeft<7) {
                    if((gameModel.getBlock(0,2).getContents().equals(gameModel.getBlock(0,1).getContents()) &&
                        gameModel.getBlock(0,1).getContents().equals(gameModel.getBlock(0,0).getContents())) ||
                       (gameModel.getBlock(0,2).getContents().equals(gameModel.getBlock(1,2).getContents()) &&
                        gameModel.getBlock(1,2).getContents().equals(gameModel.getBlock(2,2).getContents())) ||
                       (gameModel.getBlock(0,2).getContents().equals(gameModel.getBlock(1,1).getContents()) &&
                        gameModel.getBlock(1,1).getContents().equals(gameModel.getBlock(2,0).getContents()))) {
                        playerturn.setText("Player 1 wins!");
                        endGame();
                    } else if(movesLeft==0) {
                        playerturn.setText(GAME_END_NOWINNER);
                    }
                }
            } else if(block==blocks[1][0]) {
                gameModel.getBlock(1,0).setContents("X");
                gameModel.getBlock(1,0).setIsLegalMove(false);
                // Enable the space on top of this one
                gameModel.getBlock(0,0).setIsLegalMove(true);
                updateBlock(1,0);
                updateBlock(0,0);
                player = "2";
                if(movesLeft<7) {
                    if((gameModel.getBlock(1,0).getContents().equals(gameModel.getBlock(1,1).getContents()) &&
                        gameModel.getBlock(1,1).getContents().equals(gameModel.getBlock(1,2).getContents())) ||
                       (gameModel.getBlock(1,0).getContents().equals(gameModel.getBlock(0,0).getContents()) &&
                        gameModel.getBlock(0,0).getContents().equals(gameModel.getBlock(2,0).getContents()))) {
                        playerturn.setText("Player 1 wins!");
                        endGame();
                    } else if(movesLeft==0) {
                        playerturn.setText(GAME_END_NOWINNER);
                    }
                }
            } else if(block==blocks[1][1]) {
                gameModel.getBlock(1,1).setContents("X");
                gameModel.getBlock(1,1).setIsLegalMove(false);
                // Enable the space on top of this one
                gameModel.getBlock(0,1).setIsLegalMove(true);
                updateBlock(1,1);
                updateBlock(0,1);
                player = "2";
                if(movesLeft<7) {
                    if((gameModel.getBlock(1,1).getContents().equals(gameModel.getBlock(1,0).getContents()) &&
                        gameModel.getBlock(1,0).getContents().equals(gameModel.getBlock(1,2).getContents())) ||
                       (gameModel.getBlock(1,1).getContents().equals(gameModel.getBlock(0,1).getContents()) &&
                        gameModel.getBlock(0,1).getContents().equals(gameModel.getBlock(2,1).getContents())) ||
                       (gameModel.getBlock(1,1).getContents().equals(gameModel.getBlock(0,0).getContents()) &&
                        gameModel.getBlock(0,0).getContents().equals(gameModel.getBlock(2,2).getContents())) ||
                       (gameModel.getBlock(1,1).getContents().equals(gameModel.getBlock(0,2).getContents()) &&
                        gameModel.getBlock(0,2).getContents().equals(gameModel.getBlock(2,0).getContents()))) {
                        playerturn.setText("Player 1 wins!");
                        endGame();
                    } else if(movesLeft==0) {
                        playerturn.setText(GAME_END_NOWINNER);
                    }
                }
            } else if(block==blocks[1][2]) {
                gameModel.getBlock(1,2).setContents("X");
                gameModel.getBlock(1,2).setIsLegalMove(false);
                // Enable the space on top of this one
                gameModel.getBlock(0,2).setIsLegalMove(true);
                updateBlock(1,2);
                updateBlock(0,2);
                player = "2";
                if(movesLeft<7) {
                    if((gameModel.getBlock(1,2).getContents().equals(gameModel.getBlock(1,1).getContents()) &&
                        gameModel.getBlock(1,1).getContents().equals(gameModel.getBlock(1,0).getContents())) ||
                       (gameModel.getBlock(1,2).getContents().equals(gameModel.getBlock(0,2).getContents()) &&
                        gameModel.getBlock(0,2).getContents().equals(gameModel.getBlock(2,2).getContents()))) {
                        playerturn.setText("Player 1 wins!");
                        endGame();
                    } else if(movesLeft==0) {
                        playerturn.setText(GAME_END_NOWINNER);
                    }
                }
            } else if(block==blocks[2][0]) {
                gameModel.getBlock(2,0).setContents("X");
                gameModel.getBlock(2,0).setIsLegalMove(false);
                // Enable the space on top of this one
                gameModel.getBlock(1,0).setIsLegalMove(true);
                updateBlock(2,0);
                updateBlock(1,0);
                player = "2";
                if(movesLeft<7) {
                    if((gameModel.getBlock(2,0).getContents().equals(gameModel.getBlock(2,1).getContents()) &&
                        gameModel.getBlock(2,1).getContents().equals(gameModel.getBlock(2,2).getContents())) ||
                       (gameModel.getBlock(2,0).getContents().equals(gameModel.getBlock(1,0).getContents()) &&
                        gameModel.getBlock(1,0).getContents().equals(gameModel.getBlock(0,0).getContents())) ||
                       (gameModel.getBlock(2,0).getContents().equals(gameModel.getBlock(1,1).getContents()) &&
                        gameModel.getBlock(1,1).getContents().equals(gameModel.getBlock(0,2).getContents()))) {
                        playerturn.setText("Player 1 wins!");
                        endGame();
                    } else if(movesLeft==0) {
                        playerturn.setText(GAME_END_NOWINNER);
                    }
                }
            } else if(block==blocks[2][1]) {
                gameModel.getBlock(2,1).setContents("X");
                gameModel.getBlock(2,1).setIsLegalMove(false);
                // Enable the space on top of this one
                gameModel.getBlock(1,1).setIsLegalMove(true);
                updateBlock(2,1);
                updateBlock(1,1);
                player = "2";
                if(movesLeft<7) {
                    if((gameModel.getBlock(2,1).getContents().equals(gameModel.getBlock(2,0).getContents()) &&
                        gameModel.getBlock(2,0).getContents().equals(gameModel.getBlock(2,2).getContents())) ||
                       (gameModel.getBlock(2,1).getContents().equals(gameModel.getBlock(1,1).getContents()) &&
                        gameModel.getBlock(1,1).getContents().equals(gameModel.getBlock(0,1).getContents()))) {
                        playerturn.setText("Player 1 wins!");
                        endGame();
                    } else if(movesLeft==0) {
                        playerturn.setText(GAME_END_NOWINNER);
                    }
                }
            } else if(block==blocks[2][2]) {
                gameModel.getBlock(2,2).setContents("X");
                gameModel.getBlock(2,2).setIsLegalMove(false);
                // Enable the space on top of this one
                gameModel.getBlock(1,2).setIsLegalMove(true);
                updateBlock(2,2);
                updateBlock(1,2);
                player = "2";
                if(movesLeft<7) {
                    if((gameModel.getBlock(2,2).getContents().equals(gameModel.getBlock(2,1).getContents()) &&
                        gameModel.getBlock(2,1).getContents().equals(gameModel.getBlock(2,0).getContents())) ||
                       (gameModel.getBlock(2,2).getContents().equals(gameModel.getBlock(1,2).getContents()) &&
                        gameModel.getBlock(1,2).getContents().equals(gameModel.getBlock(0,2).getContents())) ||
                       (gameModel.getBlock(2,2).getContents().equals(gameModel.getBlock(1,1).getContents()) &&
                        gameModel.getBlock(1,1).getContents().equals(gameModel.getBlock(0,0).getContents()))) {
                        playerturn.setText("Player 1 wins!");
                        endGame();
                    } else if(movesLeft==0) {
                        playerturn.setText(GAME_END_NOWINNER);
                    }
                }
            }
        } else {
            // Check whether player 2 won
            if(block==blocks[0][0]) {
                gameModel.getBlock(0,0).setContents("O");
                gameModel.getBlock(0,0).setIsLegalMove(false);
                updateBlock(0,0);
                player = "1";
                if(movesLeft<7) {
                    if((gameModel.getBlock(0,0).getContents().equals(gameModel.getBlock(0,1).getContents()) &&
                        gameModel.getBlock(0,1).getContents().equals(gameModel.getBlock(0,2).getContents())) ||
                       (gameModel.getBlock(0,0).getContents().equals(gameModel.getBlock(1,0).getContents()) &&
                        gameModel.getBlock(1,0).getContents().equals(gameModel.getBlock(2,0).getContents())) ||
                       (gameModel.getBlock(0,0).getContents().equals(gameModel.getBlock(1,1).getContents()) &&
                        gameModel.getBlock(1,1).getContents().equals(gameModel.getBlock(2,2).getContents()))) {
                        playerturn.setText("Player 2 wins!");
                        endGame();
                    } else if(movesLeft==0) {
                        playerturn.setText(GAME_END_NOWINNER);
                    }
                }
            } else if(block==blocks[0][1]) {
                gameModel.getBlock(0,1).setContents("O");
                gameModel.getBlock(0,1).setIsLegalMove(false);
                updateBlock(0,1);
                player = "1";
                if(movesLeft<7) {
                    if((gameModel.getBlock(0,1).getContents().equals(gameModel.getBlock(0,0).getContents()) &&
                        gameModel.getBlock(0,0).getContents().equals(gameModel.getBlock(0,2).getContents())) ||
                       (gameModel.getBlock(0,1).getContents().equals(gameModel.getBlock(1,1).getContents()) &&
                        gameModel.getBlock(1,1).getContents().equals(gameModel.getBlock(2,1).getContents()))) {
                        playerturn.setText("Player 2 wins!");
                        endGame();
                    } else if(movesLeft==0) {
                        playerturn.setText(GAME_END_NOWINNER);
                    }
                }
            } else if(block==blocks[0][2]) {
                gameModel.getBlock(0,2).setContents("O");
                gameModel.getBlock(0,2).setIsLegalMove(false);
                updateBlock(0,2);
                player = "1";
                if(movesLeft<7) {
                    if((gameModel.getBlock(0,2).getContents().equals(gameModel.getBlock(0,1).getContents()) &&
                        gameModel.getBlock(0,1).getContents().equals(gameModel.getBlock(0,0).getContents())) ||
                       (gameModel.getBlock(0,2).getContents().equals(gameModel.getBlock(1,2).getContents()) &&
                        gameModel.getBlock(1,2).getContents().equals(gameModel.getBlock(2,2).getContents())) ||
                       (gameModel.getBlock(0,2).getContents().equals(gameModel.getBlock(1,1).getContents()) &&
                        gameModel.getBlock(1,1).getContents().equals(gameModel.getBlock(2,0).getContents()))) {
                        playerturn.setText("Player 2 wins!");
                        endGame();
                    } else if(movesLeft==0) {
                        playerturn.setText(GAME_END_NOWINNER);
                    }
                }
            } else if(block==blocks[1][0]) {
                gameModel.getBlock(1,0).setContents("O");
                gameModel.getBlock(1,0).setIsLegalMove(false);
                // Enable the space on top of this one
                gameModel.getBlock(0,0).setIsLegalMove(true);
                updateBlock(1,0);
                updateBlock(0,0);
                player = "1";
                if(movesLeft<7) {
                    if((gameModel.getBlock(1,0).getContents().equals(gameModel.getBlock(1,1).getContents()) &&
                        gameModel.getBlock(1,1).getContents().equals(gameModel.getBlock(1,2).getContents())) ||
                       (gameModel.getBlock(1,0).getContents().equals(gameModel.getBlock(0,0).getContents()) &&
                        gameModel.getBlock(0,0).getContents().equals(gameModel.getBlock(2,0).getContents()))) {
                        playerturn.setText("Player 2 wins!");
                        endGame();
                    } else if(movesLeft==0) {
                        playerturn.setText(GAME_END_NOWINNER);
                    }
                }
            } else if(block==blocks[1][1]) {
                gameModel.getBlock(1,1).setContents("O");
                gameModel.getBlock(1,1).setIsLegalMove(false);
                // Enable the space on top of this one
                gameModel.getBlock(0,1).setIsLegalMove(true);
                updateBlock(1,1);
                updateBlock(0,1);
                player = "1";
                if(movesLeft<7) {
                    if((gameModel.getBlock(1,1).getContents().equals(gameModel.getBlock(1,0).getContents()) &&
                        gameModel.getBlock(1,0).getContents().equals(gameModel.getBlock(1,2).getContents())) ||
                       (gameModel.getBlock(1,1).getContents().equals(gameModel.getBlock(0,1).getContents()) &&
                        gameModel.getBlock(0,1).getContents().equals(gameModel.getBlock(2,1).getContents())) ||
                       (gameModel.getBlock(1,1).getContents().equals(gameModel.getBlock(0,0).getContents()) &&
                        gameModel.getBlock(0,0).getContents().equals(gameModel.getBlock(2,2).getContents())) ||
                       (gameModel.getBlock(1,1).getContents().equals(gameModel.getBlock(0,2).getContents()) &&
                        gameModel.getBlock(0,2).getContents().equals(gameModel.getBlock(2,0).getContents()))) {
                        playerturn.setText("Player 2 wins!");
                        endGame();
                    } else if(movesLeft==0) {
                        playerturn.setText(GAME_END_NOWINNER);
                    }
                }
            } else if(block==blocks[1][2]) {
                gameModel.getBlock(1,2).setContents("O");
                gameModel.getBlock(1,2).setIsLegalMove(false);
                // Enable the space on top of this one
                gameModel.getBlock(0,2).setIsLegalMove(true);
                updateBlock(1,2);
                updateBlock(0,2);
                player = "1";
                if(movesLeft<7) {
                    if((gameModel.getBlock(1,2).getContents().equals(gameModel.getBlock(1,1).getContents()) &&
                        gameModel.getBlock(1,1).getContents().equals(gameModel.getBlock(1,0).getContents())) ||
                       (gameModel.getBlock(1,2).getContents().equals(gameModel.getBlock(0,2).getContents()) &&
                        gameModel.getBlock(0,2).getContents().equals(gameModel.getBlock(2,2).getContents()))) {
                        playerturn.setText("Player 2 wins!");
                        endGame();
                    } else if(movesLeft==0) {
                        playerturn.setText(GAME_END_NOWINNER);
                    }
                }
            } else if(block==blocks[2][0]) {
                gameModel.getBlock(2,0).setContents("O");
                gameModel.getBlock(2,0).setIsLegalMove(false);
                // Enable the space on top of this one
                gameModel.getBlock(1,0).setIsLegalMove(true);
                updateBlock(2,0);
                updateBlock(1,0);
                player = "1";
                if(movesLeft<7) {
                    if((gameModel.getBlock(2,0).getContents().equals(gameModel.getBlock(2,1).getContents()) &&
                        gameModel.getBlock(2,1).getContents().equals(gameModel.getBlock(2,2).getContents())) ||
                       (gameModel.getBlock(2,0).getContents().equals(gameModel.getBlock(1,0).getContents()) &&
                        gameModel.getBlock(1,0).getContents().equals(gameModel.getBlock(0,0).getContents())) ||
                       (gameModel.getBlock(2,0).getContents().equals(gameModel.getBlock(1,1).getContents()) &&
                        gameModel.getBlock(1,1).getContents().equals(gameModel.getBlock(0,2).getContents()))) {
                        playerturn.setText("Player 2 wins!");
                        endGame();
                    } else if(movesLeft==0) {
                        playerturn.setText(GAME_END_NOWINNER);
                    }
                }
            } else if(block==blocks[2][1]) {
                gameModel.getBlock(2,1).setContents("O");
                gameModel.getBlock(2,1).setIsLegalMove(false);
                // Enable the space on top of this one
                gameModel.getBlock(1,1).setIsLegalMove(true);
                updateBlock(2,1);
                updateBlock(1,1);
                player = "1";
                if(movesLeft<7) {
                    if((gameModel.getBlock(2,1).getContents().equals(gameModel.getBlock(2,0).getContents()) &&
                        gameModel.getBlock(2,0).getContents().equals(gameModel.getBlock(2,2).getContents())) ||
                       (gameModel.getBlock(2,1).getContents().equals(gameModel.getBlock(1,1).getContents()) &&
                        gameModel.getBlock(1,1).getContents().equals(gameModel.getBlock(0,1).getContents()))) {
                        playerturn.setText("Player 2 wins!");
                        endGame();
                    } else if(movesLeft==0) {
                        playerturn.setText(GAME_END_NOWINNER);
                    }
                }
            } else if(block==blocks[2][2]) {
                gameModel.getBlock(2,2).setContents("O");
                gameModel.getBlock(2,2).setIsLegalMove(false);
                // Enable the space on top of this one
                gameModel.getBlock(1,2).setIsLegalMove(true);
                updateBlock(2,2);
                updateBlock(1,2);
                player = "1";
                if(movesLeft<7) {
                    if((gameModel.getBlock(2,2).getContents().equals(gameModel.getBlock(2,1).getContents()) &&
                        gameModel.getBlock(2,1).getContents().equals(gameModel.getBlock(2,0).getContents())) ||
                       (gameModel.getBlock(2,2).getContents().equals(gameModel.getBlock(1,2).getContents()) &&
                        gameModel.getBlock(1,2).getContents().equals(gameModel.getBlock(0,2).getContents())) ||
                       (gameModel.getBlock(2,2).getContents().equals(gameModel.getBlock(1,1).getContents()) &&
                        gameModel.getBlock(1,1).getContents().equals(gameModel.getBlock(0,0).getContents()))) {
                        playerturn.setText("Player 2 wins!");
                        endGame();
                    } else if(movesLeft==0) {
                        playerturn.setText(GAME_END_NOWINNER);
                    }
                }
            }
        }
    }

    /**
     * Updates the text that appears on the block at the given row and column
     * of the game board.
     *
     * @param row The row of the block to update (0-2)
     * @param column The column of the block to update (0-2)
     */
    protected void updateBlock(int row, int column) {
        if(gameModel.getBlock(row, column).getIsLegalMove()) {
            blocks[row][column].setText("legal");
        } else {
            blocks[row][column].setText(gameModel.getBlock(row, column).getContents());
        }
    }

    /**
     * Ends the current game, disabling all blocks on the board.
     */
    public void endGame() {
        for(int row = 0; row<3; row++) {
            for(int column = 0; column<3; column++) {
                gameModel.getBlock(row, column).setIsLegalMove(false);
                updateBlock(row, column);
            }
        }
    }

    /**
     * Resets the game.
     */
    public void resetGame() {
        gameModel.resetBoard();
        for(int row = 0; row<3; row++) {
            for(int column = 0; column<3; column++) {
                updateBlock(row, column);
            }
        }
        movesLeft = 9;
        player = "1";
        playerturn.setText("Player 1 to play 'X'");
    }
} 