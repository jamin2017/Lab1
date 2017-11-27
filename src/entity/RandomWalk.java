package entity;

import java.io.File;
import java.util.ArrayList;

public class RandomWalk {
	private String randomPath;
	
	public void randomWalk(int vlen, ArrayList<VNode> Vlist) {
		
		ENode edge;
		@SuppressWarnings("unused")
		ENode foreEdge;
		GraphViz gv = new GraphViz();
		gv.addln(gv.start_graph());
		int Path[] = new int[1000];
	      
		double p;
		int startIndex;
		int index = 1;
		String s = new String();
		p = Math.random()*vlen;
		startIndex = (new Double(p)).intValue();
		//System.out.print(Vlist.get(startIndex).word+" ");
		s = s + Vlist.get(startIndex).word + " ";
		Path[0] = startIndex;
		edge = Vlist.get(startIndex).firstEdge;
		foreEdge = Vlist.get(startIndex).firstEdge;
		
		while(edge!=null && edge.visit == 0) {
			while(edge.nextEdge!=null) {
				double randomNum = Math.random();
				//System.out.println(randomNum);
				if(randomNum>=0.5) {
					edge = edge.nextEdge;
				}
				else {
					break;
				}
			}
			Path[index]=edge.num;
			index++;
			s = s + Vlist.get(edge.num).word + " ";
			edge.visit = 1;
			edge = Vlist.get(edge.num).firstEdge;
		}
		if(edge!=null) {
			//System.out.print(Vlist.get(edge.num).word);
			s = s + Vlist.get(edge.num).word;
		}
		
		for(int i=0;i<vlen;i++) {
			edge = Vlist.get(i).firstEdge;
			while(edge!=null) {
				edge.visit = 0;
				edge = edge.nextEdge;
			}
		}
		
		for(int i=0;i<index;i++) {
			System.out.println(Path[i]);
		}
		
	    
		gv.addln(Vlist.get(Path[0]).word+"[style=\"filled\",fillcolor=\"chartreuse\"];");
		for(int i = 0;i<vlen;i++) {
	    	  String p1,p2;
	    	  p1 = Vlist.get(i).word;
	    	  edge = Vlist.get(i).firstEdge;
	    	  while(edge!=null) {
	    		  int cost = edge.cost;
	    		  p2 = Vlist.get(edge.num).word;
	    		  int flag = 1;
	    		  for(int j=0;j<index-1;j++) {
	    			  if(Path[j]==i && Path[j+1]==edge.num) {
	    				  flag = 0;
	    				  break;
	    			  }
	    		  }
	    		  if(flag == 1) {
	    			   gv.addln(p1+" -> "+p2+"[label="+cost+"]"+";");
	    		  }	
	    		  else if(flag == 0) {
	    			   gv.addln(p1+" -> "+p2+"[label="+cost+","+"color=\"yellowgreen\""+"]"+";");
	    		  }
	    		  edge = edge.nextEdge;
	    	  }
	      }
		
	    gv.addln(gv.end_graph());
	    System.out.println(gv.getDotSource());
	    
	    File out = new File("c:/temp/randomPath." + "jpg");    // Windows
	    gv.writeGraphToFile( gv.getGraph( gv.getDotSource(), "jpg" ), out );
		
	    randomPath = s;
		return;
	}
	
	public String getrandomPath() {
		return randomPath;
	}
}
