import static org.junit.Assert.*;

import org.junit.Test;

public class testcase3 {

	@Test
	public void testQueryBridgeWords() throws Exception {
		DGraph graph= new DGraph("E:\\JAVA\\project1\\Sentence.txt");
		String result=graph.queryBridgeWords("phase", "the");
		System.out.println(result);
		assertEquals("No bridge word from phase to the!",result);
	}

}
