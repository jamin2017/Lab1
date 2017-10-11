import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Random;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.Comparator;
public class DGraph {
	//�ڽӱ�����ڵ�
	private class ENode{
		int num;
		int cost;
		int visit;
		boolean known;
		ENode nextEdge;
	}
	
	//�ڽӱ���
	private class VNode{
		String word;
		ENode firstEdge;
	}
	
	//���·���ڵ����
	private class Node{
		int id;
		int cost = 100000;
	}
	
	private static ArrayList<String> context = new ArrayList<String>();
	private int vlen;
	private int elen;
	private ArrayList<VNode> Vlist = new ArrayList<VNode>();
	
	//��ȡ�ļ�
	public static int readFile(String filePath) throws Exception {
		int i = 0;//��ЧӢ�ĵ�����(���ظ�)
		String result=null;
		File file=new File(filePath);
		FileReader fileReader=null;
		BufferedReader bufferedReader=null;
		try {
			fileReader=new FileReader(file);
			bufferedReader=new BufferedReader(fileReader);
			try {
				String line=null;
				while((line=bufferedReader.readLine())!=null){
					line.toLowerCase();
					result=result+line+"\r\n";
				}
				String reg1 = "\\s+";//s��ʾ�ո񡢻س������еȿհ׷�
				String reg2 = "\\w+";//w��ʾ�����ַ�(������ĸ�»���)
				String str[] = result.split(reg1);
				for(String s:str) {
					if(s.matches(reg2)) {
						//context[i] = new String(s);
						context.add(s);
						i++;
					}
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(bufferedReader!=null) {
				bufferedReader.close();
			}
			if(fileReader!=null) {
				fileReader.close();
			}
		}
		return i;
	}
	
	//���ַ���ȡ�ļ�
	public  static int readFileByChars(String filePath) {
        int i = 0;
		String result = new String();
        File file = new File(filePath);
        Reader reader = null;
        try{
            //һ�ζ�һ���ַ� �ո��ascii�� 32 ��д��ĸ65��90 Сд��ĸ97��122
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
        String reg1 = "\\s+";//s��ʾ�ո񡢻س������еȿհ׷�
        String reg2 = "\\w+";//w��ʾ�����ַ�(������ĸ�»���)
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
	
	public String queryBridgeWords(String word1, String word2) {
		int p1,p2;
		int flag1 = 0,flag2=0;
		int bridgeNum = 0;
		String result = new String();
		ArrayList<String> bridgeWords = new ArrayList<String>();
		for(p1=0;p1<vlen;p1++) {
			if(word1.matches(Vlist.get(p1).word)) {
				flag1=1;
				break;
			}
		}
		for(p2=0;p2<vlen;p2++) {
			if(word2.matches(Vlist.get(p2).word)) {
				flag2=1;
				break;
			}
		}
		if(flag1==0 || flag2==0) {
			//System.out.println("No "+word1+" or "+word2+" in the graph!");
			result = result + "No " + word1 + " or " + word2 + " in the graph!";
			return result;
		}
		
		for(ENode bridge=Vlist.get(p1).firstEdge;bridge!=null;bridge=bridge.nextEdge) {
			for(ENode node=Vlist.get(bridge.num).firstEdge;node!=null;node=node.nextEdge) {
				if(p2 == node.num) {
					bridgeWords.add(Vlist.get(bridge.num).word);
					bridgeNum++;
				}
			}
		}
		
		if(bridgeNum == 0) {
			//System.out.println("No bridge word from "+word1+" to "+word2+"!");
			result = result + "No bridge word from " + word1 + " to " + word2+"!";
		}
		else if(bridgeNum == 1){
			//System.out.println("The bridge word from "+word1+" to "+word2+" is:"+bridgeWords.get(0)+".");
			result = result + "The bridge word from " + word1 + " to " + word2 + " is:" + bridgeWords.get(0) + ".";
		}
		else if(bridgeNum > 1){
			//System.out.print("The bridge words from "+word1+" to "+word2+" are:"+bridgeWords.get(0)+",");
			result = result + "The bridge words from " + word1 + " to " + word2 + " are:" + bridgeWords.get(0) + ",";
			for(int i=1;i<bridgeNum-1;i++) {
				//System.out.print(bridgeWords.get(i)+",");
				result = result + bridgeWords.get(i) + ",";
			}
			//System.out.print("and "+bridgeWords.get(bridgeNum-1)+".");
			result = result + "and " + bridgeWords.get(bridgeNum-1) + ".";
		}
		return result;
	}
	
	public String generateNewText(String inputText) {
		String reg1 = "\\s+";//s��ʾ�ո񡢻س������еȿհ׷�
		String str[] = inputText.split(reg1);
		//ArrayList<String> list = new ArrayList<String>();
		String newText = new String();
		int wordsNum = 0;
		for(String s:str) {
			wordsNum++;
		}
		for(int i=0;i<wordsNum-1;i++) {
			//�����
			int p1,p2;
			int flag1=0,flag2=0;
			String s1 = str[i];
			String s2 = str[i+1];
			int bridgeNum = 0;
			ArrayList<String> bridgeWords = new ArrayList<String>();
			for(p1=0;p1<vlen;p1++) {
				if(Vlist.get(p1).word.equals(s1.toLowerCase())) {
					flag1=1;
					break;
				}
			}
			for(p2=0;p2<vlen;p2++) {
				if(Vlist.get(p2).word.equals(s2.toLowerCase())) {
					flag2=1;
					break;
				}
			}
			if(flag1==1 && flag2==1) {
				for(ENode bridge=Vlist.get(p1).firstEdge;bridge!=null;bridge=bridge.nextEdge) {
					for(ENode node=Vlist.get(bridge.num).firstEdge;node!=null;node=node.nextEdge) {
						if(p2 == node.num) {
							bridgeWords.add(Vlist.get(bridge.num).word);
							bridgeNum++;
						}
					}
				}
			}
			//�����ż���
			if(bridgeNum == 0) {
				newText = newText + str[i] + " ";
			}
			else {
				double p = Math.random()*(bridgeNum-1);
				int index = (new Double(p)).intValue();
				newText = newText + str[i] + " ";
				newText = newText + bridgeWords.get(index) + " ";
			}
		}
		newText = newText + str[wordsNum-1];
		return newText;
	}
	
	//������������
	public String calcShortestPath(String word1,String word2){
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
			result = "���ɴ�";
			return result;
		}
		
		int[][] path = new int[vlen][vlen];//���ڵ�
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
			result = "���ɴ�";
			return result;
		}
		
		//int index = p2;
		//System.out.println("==========");
		StringBuilder tempstring = new StringBuilder("");
		getSinglePath(p2,tempstring,"",path,p2);
		result = tempstring.toString().trim();
		return result;
	}
	
	public void getSinglePath(int index,StringBuilder s,String locS,int path[][],int end) {
		for(int i=0;i<vlen;i++) {
			//System.out.println(index+" "+vlen);
			//System.out.println("��ǰ"+Vlist.get(index).word);
			if(path[index][0]==-1) {
				//s = "\n" + Vlist.get(index).word + "->" + locS + s;
				s.insert(0, "\n" + Vlist.get(index).word + "->" + locS);
				//System.out.println(s);
				return;
			}
			if(path[index][i]==-1) {
				break;
			}
			if(index == end) {
				//locS = Vlist.get(index).word + locS;
				getSinglePath(path[index][i],s,Vlist.get(index).word + locS,path,end);
			}
			else
			{
				//locS = Vlist.get(index).word + "->" + locS;
				//System.out.println(locS);
				getSinglePath(path[index][i],s,Vlist.get(index).word + "->" + locS,path,end);
			}			
		}
	}
	
	//����һ������
	public String calcShortestPath(String word) {
		String result = new String();
		int p;
		for(p=0;p<vlen;p++) {
			if(Vlist.get(p).word.equals(word.toLowerCase())) {
				break;
			}
		}
		
		if(p>=vlen) {
			result = "���ɴ�";
			return result;
		}
		
		int[] path = new int[vlen];//���ڵ�
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
//				int num1 = dist.get(node1.id).cost;
//				int num2 = dist.get(node2.id).cost;
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
		
		return result;
	}
	
	public String randomWalk() {
		
		ENode edge;
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
		//�ж��������ֵ�һ���ظ��ı߻�����ĳ���ڵ㲻���ڳ���
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
//			System.out.println(Vlist.get(edge.num).word);
//			String p1,p2;
//			p1 = Vlist.get(foreEdge.num).word;
//			p2 = Vlist.get(edge.num).word;
//			foreEdge = edge;
//			int cost = edge.cost;
//			gv.addln(p1+" -> "+p2+"[label="+cost+","+"color=\"yellowgreen\""+"]"+";");
			s = s + Vlist.get(edge.num).word + " ";
			edge.visit = 1;
			edge = Vlist.get(edge.num).firstEdge;
		}
		if(edge!=null) {//���ظ��߽������·��
			//System.out.print(Vlist.get(edge.num).word);
			s = s + Vlist.get(edge.num).word;
		}
		//��ʼ��visitΪ0
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
		
	    //��ʼ�ڵ������ɫ����
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
		
		return s;
	}
	
	public void showDirectedGraph(String type)
	   {
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

