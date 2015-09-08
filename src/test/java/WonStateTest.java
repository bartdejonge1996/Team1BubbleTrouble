import static org.junit.Assert.*;

import org.junit.Test;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class WonStateTest {

	@Test
	public void testGetID() {
		MainGame mg = new MainGame(null);
		WonState won = new WonState(mg);
		assertEquals(mg.WON_STATE, won.getID());
	}

	@Test
	public void testWonState() {
		MainGame mg = new MainGame(null);
		WonState won = new WonState(mg);
		assertEquals(mg,won.getMg());
	}

	@Test
	public void testGetMg() {
		MainGame mg = new MainGame(null);
		WonState won = new WonState(mg);
		
		assertEquals(mg,won.getMg());
	}

	@Test
	public void testSetMg() {
		MainGame mg = new MainGame(null);
		WonState won = new WonState(mg);
		MainGame mg2 = new MainGame("s");
		won.setMg(mg2);
		assertEquals(mg2,won.getMg());
	}
	
	@Test
	public void testGetId() {
		MainGame mg = new MainGame(null);
		WonState won = new WonState(mg);
		assertEquals(mg.WON_STATE,won.getID());
	}
}