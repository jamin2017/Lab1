package TestB;

import static org.junit.Assert.*;

import org.junit.Test;

import entity.DGraph;
import entity.NewText;

public class TestCase4 {

	@Test
	public void testGenerateNewText() throws Exception {
		DGraph graph= new DGraph("src/TestB/BlackBox.txt");
		String text = "5 robots are in control soon";
		NewText NT = new NewText();
		NT.generateNewText(text, graph.getVlist(), graph.getVlen());
		String result=NT.getText();
		System.out.println(result);
		assertEquals("5 robots are in respiratory control soon",result);
	}

}
