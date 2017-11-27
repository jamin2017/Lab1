package control;

import entity.DGraph;
import entity.RandomWalk;

public class WalkInRandom{

    public String query(DGraph G){
    	RandomWalk RW = new RandomWalk();
		RW.randomWalk(G.getVlen(), G.getVlist());
		String randomPath = RW.getrandomPath();
        return randomPath;
    }

}