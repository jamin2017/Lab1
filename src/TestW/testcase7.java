import static org.junit.Assert.*;

import org.junit.Test;

public class testcase7 {

	@Test
	public void testQueryBridgeWords() throws Exception {
		DGraph graph= new DGraph("E:\\JAVA\\project1\\Sentence.txt");
		String result=graph.queryBridgeWords("the", "states");
		System.out.println(result);
		assertEquals("The bridge words from the to states are:ww,ak,and united.",result);
	}

}
