package TestW;

import static org.junit.Assert.*;

import org.junit.Test;

import entity.BridgeWord;
import entity.DGraph;

public class testcase7 {

	@Test
	public void testQueryBridgeWords() throws Exception {
		DGraph G= new DGraph("src/TestW/WhiteBox.txt");
		BridgeWord BW = new BridgeWord();
		BW.queryBridgeWords("the", "states", G.getVlist(), G.getVlen());
		String word = BW.getWords();
		assertEquals("The bridge words from the to states are:ww,ak,and united.",word);
	}

}
