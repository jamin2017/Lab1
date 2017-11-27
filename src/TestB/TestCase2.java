package TestB;

import static org.junit.Assert.*;


import org.junit.Test;

import entity.DGraph;
import entity.NewText;

public class TestCase2 {

	@Test
	public void testGenerateNewText() throws Exception {
		DGraph graph= new DGraph("src/TestB/BlackBox.txt");
		String text = "Of all physiological differences in sleep";
		NewText NT = new NewText();
		NT.generateNewText(text, graph.getVlist(), graph.getVlen());
		String result=NT.getText();
		System.out.println(result);
		assertEquals("Of all the physiological differences in human sleep",result);
	}

}