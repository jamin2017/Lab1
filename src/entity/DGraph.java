package entity;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.Comparator;

public class DGraph {	
	private static ArrayList<String> context = new ArrayList<String>();
	private int vlen;
	private int elen;
	private ArrayList<VNode> Vlist = new ArrayList<VNode>();
	
	public int getVlen() {
		return vlen;
	}
	
	public int getElen() {
		return elen;
	}
	
	public ArrayList<VNode> getVlist(){
		return Vlist;
	}
	
	public static int readFileByChars(String filePath) {
        int i = 0;
		String result = new String();
        File file = new File(filePath);
        Reader reader = null;
        try{
            reader = new InputStreamReader(new FileInputStream(file));
            int tempchar;

            while((tempchar = reader.read()) != -1){
                if (tempchar == 10)
                    result = result+" ";

                else if (tempchar == 32 || (tempchar >= 97 && tempchar <= 122)){
                    result = result+(char)tempchar;
                }

                else if((tempchar >= 65 && tempchar <= 90)){
                    result = result+(char)(tempchar+32);
                }
            }
            reader.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
        String reg1 = "\\s+";
        String reg2 = "\\w+";
		String str[] = result.split(reg1);
		for(String s:str) {
			if(s.matches(reg2)) {
				//context[i] = new String(s);
				context.add(s);
				i++;
			}
		}
		return i;
    }
	
	public DGraph(String filePath) throws Exception {	
		int i = 0;
		vlen = 1;
		VNode node;
		elen = DGraph.readFileByChars(filePath) - 1;
		node = new VNode();
		node.word = context.get(0);
		node.firstEdge = null;
		Vlist.add(node);
		for(String s:context) {
			int flag=0;
			for(VNode p:Vlist) {
				if(p.word.equals(s)) {
					flag = 1;
					break;
				}
			}
			if(flag == 0) {
				node = new VNode();
				node.word = context.get(i);
				node.firstEdge = null;
				//System.out.println(node.word);
				Vlist.add(node);
				vlen++;
			}
			i++;
		}
		
		for(i = 0;i<elen;i++) {
			String s1 = context.get(i);
			String s2 = context.get(i+1);
			for(int j = 0;j<vlen;j++) {
				if(s1.matches(Vlist.get(j).word)) {
					if(Vlist.get(j).firstEdge==null) {
						int p;
						for(p = 0;p<vlen;p++) {
							if(s2.matches(Vlist.get(p).word)) {
								break;
							}
						}
						ENode newNode = new ENode();
						newNode.cost = 1;
						newNode.num = p;
						newNode.visit = 0;
						newNode.nextEdge = null;
						Vlist.get(j).firstEdge = newNode;
						break;
					}
					else {
						ENode nextNode = Vlist.get(j).firstEdge;
						while(nextNode!=null) {
							if(s2.matches(Vlist.get(nextNode.num).word)) {
								nextNode.cost++;
								break;
							}
							else {
								nextNode = nextNode.nextEdge;
							}
						}
						if(nextNode==null) {
							int p;
							for(p = 0;p<vlen;p++) {
								if(s2.matches(Vlist.get(p).word)) {
									break;
								}
							}
							ENode newNode = new ENode();
							newNode.num = p;
							newNode.cost = 1;
							newNode.visit = 0;
							newNode.nextEdge = Vlist.get(j).firstEdge;
							Vlist.get(j).firstEdge = newNode;
							break;
						}
					}
				}
			}
		}      
	}
	
	public void showDirectedGraph(String type){
	      GraphViz gv = new GraphViz();
	      gv.addln(gv.start_graph());
	      for(int i = 0;i<vlen;i++) {
	    	  String p1,p2;
	    	  p1 = Vlist.get(i).word;
	    	  ENode edge = Vlist.get(i).firstEdge;
	    	  while(edge!=null) {
	    		  int cost = edge.cost;
	    		  p2 = Vlist.get(edge.num).word;
	    		  gv.addln(p1+" -> "+p2+"[label="+cost+"]"+";");
	    		  edge = edge.nextEdge;
	    	  }
	      }
	      gv.addln(gv.end_graph());
	      System.out.println(gv.getDotSource());
	      
	      //File out = new File("/tmp/out." + type);   // Linux
	      File out = new File("c:/temp/out." + type);    // Windows
	      gv.writeGraphToFile( gv.getGraph( gv.getDotSource(), type ), out );
	}
}
