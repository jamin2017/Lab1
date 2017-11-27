package control;

import entity.DGraph;
import entity.ShortestPath;

public class ShortestPathQuery{
    private String word1;
    private String word2;

    public ShortestPathQuery(String aword1, String aword2){
        this.word1 = aword1;
        this.word2 = aword2;
    }

    public String query(DGraph G){
    	ShortestPath SP = new ShortestPath();
        if(!word2.equals("")){
            SP.calcShortestPath(word1, word2, G.getVlen(), G.getVlist());
        }else{
        	SP.calcShortestPath(word1, G.getVlen(), G.getVlist());
        }
        String path = SP.getPath();
        return path;
    }
}