package control;

import entity.BridgeWord;
import entity.DGraph;

public class BridgeWordQuery{
    
    private String word1;
    private String word2;

    public BridgeWordQuery(String aword1, String aword2){
        this.word1 = aword1;
        this.word2 = aword2;
    }

    public String query(DGraph G){
    	BridgeWord BW = new BridgeWord();
		BW.queryBridgeWords(word1, word2, G.getVlist(), G.getVlen());
		String word = BW.getWords();
        return word;
    }

}