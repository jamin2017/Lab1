import static org.junit.Assert.*;

import org.junit.Test;

public class testcase5 {

	@Test
	public void testQueryBridgeWords() throws Exception {
		DGraph graph= new DGraph("E:\\JAVA\\project1\\Sentence.txt");
		String result=graph.queryBridgeWords("railroads", "in");
		System.out.println(result);
		assertEquals("No bridge word from railroads to in!",result);
	}

}
