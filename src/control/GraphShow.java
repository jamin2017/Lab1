package control;

import org.eclipse.swt.widgets.Display;

public class GraphShow{
    private org.eclipse.swt.graphics.Image img;
    private String address = "c:/temp/out.jpg";

    public GraphShow(){
        img = new org.eclipse.swt.graphics.Image(Display.getCurrent(), address);
    }

    public org.eclipse.swt.graphics.Image getImg(){
        return img;
    }
}