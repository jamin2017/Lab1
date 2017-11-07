package Test;

import static org.junit.Assert.*;
import jamin_lab1.DGraph;

import org.junit.Test;

public class TestCase5 {

	@Test
	public void testGenerateNewText() throws Exception {
		DGraph graph= new DGraph("D:\\学习资料\\软件工程\\project\\Lab1\\Sentence.txt");
		String text = " Ofallphysiologicaldifferencesin sleep";
		String result=graph.generateNewText(text);
		System.out.println(result);
		assertEquals(" Ofallphysiologicaldifferencesin sleep",result);
	}

}