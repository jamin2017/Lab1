package Test;

import static org.junit.Assert.*;
import jamin_lab1.DGraph;

import org.junit.Test;

public class TestCase1 {

	@Test
	public void testGenerateNewText() throws Exception {
		DGraph graph= new DGraph("D:\\学习资料\\软件工程\\project\\Lab1\\Sentence.txt");
		String text = "all";
		String result=graph.generateNewText(text);
		System.out.println(result);
		assertEquals("all",result);
	}

}
