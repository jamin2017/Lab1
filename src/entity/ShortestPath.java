package entity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class ShortestPath {
	private String Path;
	
	public void calcShortestPath(String word, int vlen, ArrayList<VNode> Vlist) {
		String result = new String();
		int p;
		for(p=0;p<vlen;p++) {
			if(Vlist.get(p).word.equals(word.toLowerCase())) {
				break;
			}
		}
		
		if(p>=vlen) {
			Path = "涓嶅彲杈�";
			return;
		}
		
		int[] path = new int[vlen];
		int[] visit = new int[vlen];
		ArrayList<Node> dist = new ArrayList<Node>();
		Node distNode;
		for(int i=0;i<vlen;i++) {
			distNode = new Node();
			distNode.id = i;
			dist.add(distNode);
			path[i] = -1;
			visit[i] = 0;
		}
		
		Comparator<Node> orderIsdn = new Comparator<Node>() {

			@Override
			public int compare(Node node1, Node node2) {
				int num1 = node1.cost;
				int num2 = node2.cost;
				if(num1>num2) {
					return 1;
				}
				else if(num1<num2) {
					return -1;
				}
				else {
					return 0;
				}
			}
			
		};
		Queue<Node> priorityQueue = new PriorityQueue<Node>(orderIsdn);
		
		dist.get(p).cost = 0;
		priorityQueue.add(dist.get(p));
		while(!priorityQueue.isEmpty()) {
			Node cd = priorityQueue.poll();
			int cp = cd.id;
			if(visit[cp] == 1) {
				continue;
			}
			visit[cp] = 1;
			ENode edge = Vlist.get(cp).firstEdge;
			while(edge!=null) {
				int tempv = edge.num;
				int tempc = edge.cost;
				if(visit[tempv]==0 && dist.get(tempv).cost>dist.get(cp).cost+tempc) {
					dist.get(tempv).cost = dist.get(cp).cost+tempc;
					path[tempv] = cp;
					priorityQueue.add(dist.get(tempv));
				}
				edge = edge.nextEdge;
			}
		}
		
		for(int i=0;i<vlen;i++) {
			if(i==p) {
				continue;
			}
			int index = i;
			Stack<String> stack = new Stack<String>();
			while(path[index]!=-1) {
				stack.push(Vlist.get(index).word);
				index = path[index];
			}
			result = result + word + "->";
			while(!stack.isEmpty()&&!stack.peek().equals(Vlist.get(i).word)) {
				result = result + stack.pop() + "->";
			}
			result = result + Vlist.get(i).word + "\n";
		}
		Path = result;
		return;
	}
	
	public void calcShortestPath(String word1,String word2, int vlen, ArrayList<VNode> Vlist){
		String result = new String();
		int p1,p2;
		for(p1=0;p1<vlen;p1++) {
			if(Vlist.get(p1).word.equals(word1.toLowerCase())) {
				break;
			}
		}
		for(p2=0;p2<vlen;p2++) {
			if(Vlist.get(p2).word.equals(word2.toLowerCase())) {
				break;
			}
		}
		
		if(p1>=vlen || p2>=vlen) {
			Path = "涓嶅彲杈�";
			return;
		}
		
		int[][] path = new int[vlen][vlen];//鐖惰妭鐐�
		int[] visit = new int[vlen];
		ArrayList<Node> dist = new ArrayList<Node>();
		Node distNode;
		for(int i=0;i<vlen;i++) {
			distNode = new Node();
			distNode.id = i;
			dist.add(distNode);
			for(int j=0;j<vlen;j++) {
				path[i][j] = -1;
			}
			visit[i] = 0;
		}
		
		Comparator<Node> orderIsdn = new Comparator<Node>() {

			@Override
			public int compare(Node node1, Node node2) {
				int num1 = node1.cost;
				int num2 = node2.cost;
				if(num1>num2) {
					return 1;
				}
				else if(num1<num2) {
					return -1;
				}
				else {
					return 0;
				}
			}
			
		};
		Queue<Node> priorityQueue = new PriorityQueue<Node>(orderIsdn);
		
		dist.get(p1).cost = 0;
		priorityQueue.add(dist.get(p1));
		while(!priorityQueue.isEmpty()) {
			Node cd = priorityQueue.poll();
			int cp = cd.id;
			//System.out.println("CP="+cp);
			if(visit[cp] == 1) {
				continue;
			}
			visit[cp] = 1;
			//System.out.println("---------");
			ENode edge = Vlist.get(cp).firstEdge;
			while(edge!=null) {
				int tempv = edge.num;
				int tempc = edge.cost;
				if(visit[tempv]==0 && dist.get(tempv).cost==dist.get(cp).cost+tempc) {
					dist.get(tempv).cost = dist.get(cp).cost+tempc;
					for(int i=0;i<vlen;i++) {
						if(path[tempv][i] == -1) {
							path[tempv][i] = cp;
							//System.out.println(tempv+" "+i + " " + cp);
							break;
						}
					}
					//priorityQueue.add(dist.get(tempv));
				}
				if(visit[tempv]==0 && dist.get(tempv).cost>dist.get(cp).cost+tempc) {
					dist.get(tempv).cost = dist.get(cp).cost+tempc;
					path[tempv][0] = cp;
					for(int i=1;i<vlen;i++) {
						if(path[tempv][i] == -1) {
							break;
						}
						path[tempv][i] = -1;
					}
					priorityQueue.add(dist.get(tempv));
				}
				edge = edge.nextEdge;
			}
		}
		
		if(path[p2][0]==-1) {
			Path = "涓嶅彲杈�";
			return;
		}
		
		StringBuilder tempstring = new StringBuilder("");
		getSinglePath(p2,tempstring,"",path,p2,Vlist,vlen);
		result = tempstring.toString().trim();
		Path = result;
		return;
	}
	
	public void getSinglePath(int index,StringBuilder s,String locS,int path[][],int end, ArrayList<VNode> Vlist, int vlen) {
		for(int i=0;i<vlen;i++) {
			if(path[index][0]==-1) {
				s.insert(0, "\n" + Vlist.get(index).word + "->" + locS);
				return;
			}
			if(path[index][i]==-1) {
				break;
			}
			if(index == end) {
				getSinglePath(path[index][i],s,Vlist.get(index).word + locS,path,end,Vlist,vlen);
			}
			else
			{
				getSinglePath(path[index][i],s,Vlist.get(index).word + "->" + locS,path,end,Vlist,vlen);
			}			
		}
	}
	
	public String getPath() {
		return Path;
	}
}
