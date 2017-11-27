package TestW;

import static org.junit.Assert.*;

import org.junit.Test;

import entity.BridgeWord;
import entity.DGraph;

public class testcase5 {

	@Test
	public void testQueryBridgeWords() throws Exception {
		DGraph G= new DGraph("src/TestW/WhiteBox.txt");
		BridgeWord BW = new BridgeWord();
		BW.queryBridgeWords("railroads", "in", G.getVlist(), G.getVlen());
		String word = BW.getWords();
		assertEquals("No bridge word from railroads to in!",word);
	}

}
