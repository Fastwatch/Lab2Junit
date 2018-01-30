import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BowlingTest {

	Scoresheet game = new Scoresheet();

	//
	@Before
	public void setUp() throws Exception {
		game.newGame();
	}

	//	@After
	//	public void tearDown() throws Exception {
	//		
	//	}

	@Test
	public void testOneThrow() {
		game.bowl(8);
		assertEquals("incorrect game score", 8, game.getGameScore());
		assertEquals("incorrect frame score", 8, game.getFrameScore(0));
	}

	@Test
	public void testTwoThrows() {
		game.bowl(7);
		assertEquals("incorrect game score", 7, game.getGameScore());
		assertEquals("incorrect frame score", 7, game.getFrameScore(0));
		game.bowl(2);
		assertEquals("incorrect game score", 9, game.getGameScore());
		assertEquals("incorrect frame score", 9, game.getFrameScore(0));

		//testing two strikes in a row
		game.newGame();
		game.bowl(10);
		game.bowl(10);
		assertEquals("incorrect game score", 30, game.getGameScore());
		assertEquals("incorrect frame score", 20, game.getFrameScore(0));
	}

	@Test
	public void testThreeThrows() {
		game.bowl(7);
		game.bowl(2);
		game.bowl(4);
		assertEquals("incorrect game score", 13, game.getGameScore());
		assertEquals("incorrect frame score", 9, game.getFrameScore(0));
		assertEquals("incorrect frame score", 4, game.getFrameScore(1));
	}

	@Test
	public void testSpareCountsToNextFrame() {
		game.bowl(7);
		game.bowl(3);
		game.bowl(4);
		game.bowl(4);
		assertEquals("incorrect frame score", 18, game.getFrameScore(0));
		assertEquals("incorrect frame score", 8, game.getFrameScore(1));
	}

	@Test
	public void testStrikeMovesToNextFrame() {
		game.bowl(10);
		assertEquals("incorrect frame", 1, game.getCurrentFrame());
	}

	@Test
	public void testStrikeCountsNextFrameScores() {
		game.bowl(10);
		game.bowl(7);
		game.bowl(3);
		game.bowl(4);
		game.bowl(4);
		assertEquals("incorrect frame score", 28, game.getFrameScore(0));
	}

	@Test
	public void testSpareOnLastFrame() {
		game.bowl(3);
		game.bowl(4);
		game.bowl(4);
		game.bowl(3);
		game.bowl(4);
		game.bowl(4);
		game.bowl(3);
		game.bowl(4);
		game.bowl(4);
		game.bowl(3);
		game.bowl(4);
		game.bowl(4);
		game.bowl(3);
		game.bowl(4);
		game.bowl(4);
		game.bowl(3);
		game.bowl(4);
		game.bowl(4);
		game.bowl(3);
		game.bowl(7);
		try{
			game.bowl(4);
			assertFalse("should not bowl 11th frame",true);
		} catch (RuntimeException ex) {
			assertTrue("wrong exception thrown: " + ex,ex instanceof IllegalStateException);
		}
	}

	@Test
	public void testStrikeOnLastFrame() {
		game.bowl(3);
		game.bowl(4);
		game.bowl(4);
		game.bowl(3);
		game.bowl(4);
		game.bowl(4);
		game.bowl(3);
		game.bowl(4);
		game.bowl(4);
		game.bowl(3);
		game.bowl(4);
		game.bowl(4);
		game.bowl(3);
		game.bowl(4);
		game.bowl(10);
		game.bowl(10);
		game.bowl(10);
		assertEquals("incorrect game score", 111, game.getGameScore());	
	}

	@Test
	public void testThrowOn11thFrame() {
		game.bowl(3);
		game.bowl(4);
		game.bowl(4);
		game.bowl(3);
		game.bowl(4);
		game.bowl(4);
		game.bowl(3);
		game.bowl(4);
		game.bowl(4);
		game.bowl(3);
		game.bowl(4);
		game.bowl(4);
		game.bowl(3);
		game.bowl(4);
		game.bowl(4);
		game.bowl(3);
		game.bowl(4);
		game.bowl(4);
		game.bowl(3);
		game.bowl(4);
		try{
			game.bowl(4);
			assertFalse("should not bowl 11th frame",true);
		} catch (RuntimeException ex) {
			assertTrue("wrong exception thrown: " + ex,ex instanceof IllegalStateException);
		}
	}
}