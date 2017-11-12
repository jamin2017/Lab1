import static org.junit.Assert.*;

import org.junit.Test;

public class testcase6 {

	@Test
	public void testQueryBridgeWords() throws Exception {
		DGraph graph= new DGraph("E:\\JAVA\\project1\\Sentence.txt");
		String result=graph.queryBridgeWords("the", "phase");
		System.out.println(result);
		assertEquals("The bridge word from the to phase is:second.",result);
	}

}
