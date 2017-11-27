package TestB;

import static org.junit.Assert.*;

import org.junit.Test;

import entity.DGraph;
import entity.NewText;

public class TestCase1 {

	@Test
	public void testGenerateNewText() throws Exception {
		DGraph graph= new DGraph("src/TestB/BlackBox.txt");
		String text = "all";
		NewText NT = new NewText();
		NT.generateNewText(text, graph.getVlist(), graph.getVlen());
		String result=NT.getText();
		System.out.println(result);
		assertEquals("all",result);
	}

}
