package boundary;

import java.io.PrintWriter;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import control.WalkInRandom;
import entity.DGraph;

public class RandomWalkShell{

    private Shell RandomWalk;
    private Text word1;

    public RandomWalkShell(DGraph graph){
        RandomWalk = new Shell(SWT.DIALOG_TRIM);
		RandomWalk.setBackgroundImage(SWTResourceManager.getImage("timg.jpg"));
		RandomWalk.setText("随机游走");
		RandomWalk.setSize(400, 500);
		
		word1 = new Text(RandomWalk, SWT.MULTI|SWT.V_SCROLL|SWT.H_SCROLL|SWT.READ_ONLY);
		word1.setBounds(5, 10, 300, 260);
		String result;
		try {
			//随机游走算法
			result = new WalkInRandom().query(graph);
			word1.setText(result);
			RandomWalk.open();
			PrintWriter out = new PrintWriter("RandomWalk.txt");
			out.println(result);
			out.close();
		}
		catch (Exception e) {
			// TODO: handle exception
			//未生成有向图
			MessageBox dialog=new MessageBox(RandomWalk,SWT.OK|SWT.ICON_INFORMATION);
	        dialog.setText("error");
	        dialog.setMessage("未生成有向图");
	        dialog.open();
		}
    }

}