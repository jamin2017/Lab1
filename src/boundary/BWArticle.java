package boundary;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import control.ArticleCreate;
import entity.DGraph;

public class BWArticle{

    private Shell BridgeArtcle;
    private Text word1;
    private Button btnBA;

    public BWArticle(DGraph graph){
        BridgeArtcle = new Shell(SWT.DIALOG_TRIM);
		BridgeArtcle.setBackgroundImage(SWTResourceManager.getImage("timg.jpg"));
		BridgeArtcle.setText("生成新文本");
		BridgeArtcle.setSize(400, 500);
		
		word1 = new Text(BridgeArtcle, SWT.MULTI|SWT.V_SCROLL|SWT.H_SCROLL);
		word1.setBounds(5, 10, 300, 260);
		btnBA = new Button(BridgeArtcle, SWT.NONE);
		btnBA.setBounds(5, 400, 98, 30);
		btnBA.setText("ok");
		btnBA.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String result;
				result = new ArticleCreate(word1.getText()).query(graph);
				word1.setText(result);
				System.out.println(result);
			}
		});
    }

    public void open(){
        BridgeArtcle.open();
    }

}