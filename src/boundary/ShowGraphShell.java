package boundary;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.ScrollBar;
import org.eclipse.swt.widgets.Shell;

import control.GraphShow;
import entity.DGraph;

public class ShowGraphShell{

    private Shell ShowGraph;

    public ShowGraphShell(DGraph graph){
        ShowGraph = new Shell(SWT.DIALOG_TRIM);
		ShowGraph.setText("展示有向图");
		ShowGraph.setLayout(new FillLayout());
		

		try {
			org.eclipse.swt.graphics.Image img = new GraphShow().getImg();

	        final Point origin = new Point(0, 0);
	        Canvas canvas = new Canvas(ShowGraph, SWT.DOUBLE_BUFFERED|SWT.V_SCROLL|SWT.H_SCROLL);
	        canvas.setSize(Math.min(1600, img.getBounds().width), Math.min(900, img.getBounds().height));
	        final ScrollBar hBar = canvas.getHorizontalBar ();
//	        final Color red = new Color(null,255,0,0);
	        hBar.setMaximum(ShowGraph.getBounds().width);
	        hBar.addListener (SWT.Selection, new Listener () {
	        	public void handleEvent (org.eclipse.swt.widgets.Event e) {
	                 System.out.println("HBar listener");
	                     int hSelection = hBar.getSelection ();
	                     int destX = -hSelection - origin.x;
	                     canvas.scroll (destX, 0, 0, 0, ShowGraph.getBounds().width, ShowGraph.getBounds().height, true);
	                     origin.x = -hSelection;
	                     System.out.println("HBar listener exit");
	                 }
					
	             });
	        final ScrollBar vBar = canvas.getVerticalBar ();
	            vBar.setMaximum(ShowGraph.getBounds().width);
	            vBar.addListener (SWT.Selection, new Listener () {
	                public void handleEvent (org.eclipse.swt.widgets.Event e) {
	                    int vSelection = vBar.getSelection ();
	                    int destY = -vSelection - origin.y;
	                     canvas.scroll (0, destY, 0, 0, ShowGraph.getBounds().width, ShowGraph.getBounds().height, true);
	                     origin.y = -vSelection;
	                 }
	             });
	        canvas.addPaintListener(new PaintListener() {
	              @Override
	              public void paintControl(PaintEvent event) {
	                  // TODO Auto-generated method stub
	            	  event.gc.drawImage(img, origin.x, origin.y);
	              }
	        });
	        ShowGraph.setSize(Math.min(1600, img.getBounds().width), Math.min(900, img.getBounds().height));
	        
		} catch (Exception e) {
			// TODO: handle exception
			MessageBox dialog=new MessageBox(ShowGraph,SWT.OK|SWT.ICON_INFORMATION);
	        dialog.setText("error");
	        dialog.setMessage("失败");
	        dialog.open();
		}
    }

    public void open(){
        ShowGraph.open();
    }

}