package boundary;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import control.BridgeWordQuery;
import entity.DGraph;

public class BWShell{

private Shell BridgeWord;
private Text word1;
private Text word2;
private Text word3;
private Button btnBW;

    public BWShell(DGraph graph){
        // TODO Auto-generated method stub
		BridgeWord = new Shell(SWT.DIALOG_TRIM);

		BridgeWord.setBackgroundImage(SWTResourceManager.getImage("timg.jpg"));
		BridgeWord.setText("查询桥接词");
		BridgeWord.setSize(400, 500);
		

		word1 = new Text(BridgeWord, SWT.BORDER);
		word1.setBounds(5, 10, 73, 26);
		word1.pack();

		word2 = new Text(BridgeWord, SWT.BORDER);
		word2.setBounds(5, 40, 73, 26);
		word2.pack();

		word3 = new Text(BridgeWord, SWT.MULTI|SWT.V_SCROLL|SWT.H_SCROLL|SWT.READ_ONLY);
		word3.setBounds(5, 200, 300, 260);
		btnBW = new Button(BridgeWord, SWT.NONE);
		btnBW.setBounds(5, 141, 98, 30);
		btnBW.setText("ok");
		btnBW.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					String result;
				
				result = new BridgeWordQuery(word1.getText(), word2.getText()).query(graph);
				word3.setText(result);
				} catch (Exception e2) {
					// TODO: handle exception
					MessageBox dialog=new MessageBox(BridgeWord,SWT.OK|SWT.ICON_INFORMATION);
			        dialog.setText("error");
			        dialog.setMessage("失败");
			        dialog.open();
				}
			}
		});
    }

    public void open(){
        BridgeWord.open();
    }

}