import model.ThreeInARowGame;
import model.ThreeInARowBlock;
import model.ThreeInARowModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * An example test class, which merely shows how to write JUnit tests.
 */
public class TestExample {
    private ThreeInARowGame game;

    @Before
    public void setUp() {
	game = new ThreeInARowGame();
    }

    @After
    public void tearDown() {
	game = null;
    }

    @Test
    public void testNewGame() {
        assertEquals ("1", game.player);
        assertEquals (9, game.movesLeft);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNewBlockViolatesPrecondition() {
	ThreeInARowBlock block = new ThreeInARowBlock(null);
    }

    @Test
    public void testNewModel() {
        ThreeInARowModel model = new ThreeInARowModel(game);
        assertNotNull("Model should be created", model);
        assertNotNull("Block at (0,0) should exist", model.getBlock(0, 0));
        assertTrue("Bottom row blocks should be legal moves initially", 
                  model.getBlock(2, 0).getIsLegalMove());
        assertFalse("Top row blocks should not be legal moves initially", 
                   model.getBlock(0, 0).getIsLegalMove());
    }
}
