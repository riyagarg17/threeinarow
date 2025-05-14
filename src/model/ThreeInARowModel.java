package model;

/**
 * Model class that represents the game board data for Three in a Row.
 */
public class ThreeInARowModel {
    private ThreeInARowBlock[][] blocksData;
    private ThreeInARowGame game;

    /**
     * Creates a new game board model.
     *
     * @param game The game that this model belongs to
     */
    public ThreeInARowModel(ThreeInARowGame game) {
        this.game = game;
        this.blocksData = new ThreeInARowBlock[3][3];
        initializeBoard();
    }

    /**
     * Initializes the game board with empty blocks.
     */
    private void initializeBoard() {
        for(int row = 0; row < 3; row++) {
            for(int column = 0; column < 3; column++) {
                blocksData[row][column] = new ThreeInARowBlock(game);
                blocksData[row][column].setContents("");
                blocksData[row][column].setIsLegalMove(row == 2);
            }
        }
    }

    /**
     * Gets the block at the specified position.
     *
     * @param row The row index (0-2)
     * @param column The column index (0-2)
     * @return The block at the specified position
     */
    public ThreeInARowBlock getBlock(int row, int column) {
        return blocksData[row][column];
    }

    /**
     * Resets all blocks on the board to their initial state.
     */
    public void resetBoard() {
        for(int row = 0; row < 3; row++) {
            for(int column = 0; column < 3; column++) {
                blocksData[row][column].reset();
                blocksData[row][column].setIsLegalMove(row == 2);
            }
        }
    }
} 