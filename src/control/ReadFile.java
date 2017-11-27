package control;

import entity.DGraph;

public class ReadFile {
	
	private DGraph G;

	public void read(String path) throws Exception {
		G = new DGraph(path);
		G.showDirectedGraph("jpg");
	}
	
	public DGraph getG() {
		return this.G;
	}
}
