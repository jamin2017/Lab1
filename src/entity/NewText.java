package entity;

import java.util.ArrayList;

public class NewText {
	private String Text;
	
	public void generateNewText(String inputText, ArrayList<VNode> Vlist, int vlen) {
		String reg1 = "\\s+";
		String str[] = inputText.split(reg1);
		//ArrayList<String> list = new ArrayList<String>();
		String newText = new String();
		int wordsNum = 0;
		for(@SuppressWarnings("unused") String s:str) {
			wordsNum++;
		}
		for(int i=0;i<wordsNum-1;i++) {
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
		Text = newText;
		return;
	}
	
	public String getText() {
		return Text;
	}
}
