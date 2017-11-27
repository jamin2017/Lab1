package TestW;

import static org.junit.Assert.*;

import org.junit.Test;

import entity.BridgeWord;
import entity.DGraph;

public class testcase4 {

	@Test
	public void testQueryBridgeWords() throws Exception {
		DGraph G= new DGraph("src/TestW/WhiteBox.txt");
		BridgeWord BW = new BridgeWord();
		BW.queryBridgeWords("second", "the", G.getVlist(), G.getVlen());
		String word = BW.getWords();
		assertEquals("No bridge word from second to the!",word);
	}

}
