import static org.junit.Assert.*;

import org.junit.Test;


public class testcase {

	@Test
	public void testQueryBridgeWords() throws Exception {
		DGraph graph= new DGraph("E:\\JAVA\\project1\\Sentence.txt");
		String result=graph.queryBridgeWords("sfda", "the");
		System.out.println(result);
		assertEquals("No sfda or the in the graph!",result);	
	}

}
