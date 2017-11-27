package boundary;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import control.ShortestPathQuery;
import entity.DGraph;

public class ShortPathShell{

    private Shell ShortPath;
    private Text word1;
    private Text word2;
    private Text word3;
    private Button btnBW;


    public ShortPathShell(DGraph graph){
        ShortPath = new Shell(SWT.DIALOG_TRIM);
		ShortPath.setBackgroundImage(SWTResourceManager.getImage("timg.jpg"));
		ShortPath.setText("最短路查询");
		ShortPath.setSize(400, 500);

		word1 = new Text(ShortPath, SWT.BORDER);
		word1.setBounds(5, 10, 73, 26);
		word1.pack();

		word2 = new Text(ShortPath, SWT.BORDER);
		word2.setBounds(5, 40, 73, 26);
		word2.pack();

		word3 = new Text(ShortPath, SWT.MULTI|SWT.V_SCROLL|SWT.H_SCROLL|SWT.READ_ONLY);
		word3.setBounds(5, 200, 300, 260);
		btnBW = new Button(ShortPath, SWT.NONE);
		btnBW.setBounds(5, 141, 98, 30);
		btnBW.setText("ok");
		btnBW.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String result;

				result = new ShortestPathQuery(word1.getText(), word2.getText()).query(graph);
				
				word3.setText(result);
			}
		});
    }

    public void open(){
        ShortPath.open();
    }

}