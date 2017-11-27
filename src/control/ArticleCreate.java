package control;

import entity.DGraph;
import entity.NewText;

public class ArticleCreate{
    
    private String word1;

    public ArticleCreate(String aword1){
        this.word1 = aword1;
    }

    public String query(DGraph G){
    	NewText NT = new NewText();
		NT.generateNewText(word1, G.getVlist(), G.getVlen());
		String text = NT.getText();
        return text;
    }

}