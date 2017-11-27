package entity;

import java.util.ArrayList;

public class BridgeWord {
	private String Words;
	
	public void queryBridgeWords(String word1, String word2, ArrayList<VNode> Vlist, int vlen) {
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
			Words = result;
			return;
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
		Words = result;
		return;
	}
	
	public String getWords() {
		return Words;
	}
}
