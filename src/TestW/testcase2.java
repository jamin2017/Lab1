import static org.junit.Assert.*;

import org.junit.Test;

public class testcase2 {

	@Test
	public void testQueryBridgeWords() throws Exception {
		DGraph graph= new DGraph("E:\\JAVA\\project1\\Sentence.txt");
		String result=graph.queryBridgeWords("the", "adf");
		System.out.println(result);
		assertEquals("No the or adf in the graph!",result);
	}

}
