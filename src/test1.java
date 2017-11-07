import java.awt.Event;
import java.awt.Image;
import java.awt.PageAttributes.OriginType;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.security.acl.Group;
import java.util.Scanner;

import javax.management.loading.PrivateClassLoader;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.internal.win32.SHELLEXECUTEINFO;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.ScrollBar;
import org.eclipse.swt.widgets.Text;
//import org.eclipse.ui.internal.dnd.SwtUtil;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;    

public class test1 extends ApplicationWindow {
	private Text text;
	private DGraph graph;

	/**
	 * Create the application window.
	 */
	public test1() {
		super(null);
		setShellStyle(SWT.DIALOG_TRIM);
		createActions();
		addToolBar(SWT.FLAT | SWT.WRAP);
		addMenuBar();
		addStatusLine();
	}

	/**
	 * Create contents of the application window.
	 * @param parent
	 */
	@Override
	protected Control createContents(Composite parent) {
		//parent.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		//������������
		Composite container = new Composite(parent, SWT.NONE);
		
		container.setBackgroundImage(SWTResourceManager.getImage("timg.jpg"));

		container.setLayout(null);
		container.setBackgroundMode(SWT.INHERIT_FORCE);//������������͸��
//		�����ı������ ��������·��
		text = new Text(container, SWT.BORDER);
		text.setBounds(35, 115, 192, 25);
//		�ļ�������ť
		Button Search = new Button(container, SWT.NONE);
		Search.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					text.setText("");
					FileDialog filedlg=new FileDialog(new Shell(), SWT.OPEN);
					//�����ļ��Ի���ı���
					filedlg.setText("�ļ�ѡ��");
					//���ó�ʼ·��
					filedlg.setFilterPath("SystemRoot");
					//���ļ��Ի��򣬷���ѡ���ļ��ľ���·��
					String selected=filedlg.open();
					text.insert(selected);
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		Search.setText("\u6D4F\u89C8");
		Search.setBounds(227, 114, 60, 27);
//����ȷ����ť
		Button readfile = new Button(container, SWT.NONE);
		readfile.setBounds(287, 114, 60, 27);
		readfile.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String path = text.getText();
				System.out.println(path);
				try {
					//���ݶ�ȡ·����������ͼ
					
					graph = new DGraph(path);
					graph.showDirectedGraph("jpg");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					MessageBox dialog=new MessageBox(new Shell(),SWT.OK|SWT.ICON_INFORMATION);
			        dialog.setText("error");
			        dialog.setMessage("��������ȷ·��");
			        dialog.open();
				}
			}
		});
		readfile.setText("\u786E\u5B9A");
		
//����͸����ť��ʵ��չʾ����ͼ
		Label labShowGraph = new Label(container, SWT.NONE);
		//����͸����ť
		labShowGraph.setImage(SWTResourceManager.getImage("\u5C55\u793A\u6709\u5411\u56FE.png"));
		labShowGraph.addListener(SWT.MouseUp, new Listener() {
			@Override
			public void handleEvent(org.eclipse.swt.widgets.Event event) {
				//չʾ����ͼ
						ShowGraphShell();
			}
		});
		labShowGraph.setBounds(35, 146, 313, 62);
		
		//����͸����ť����ѯ�ŽӴ�
		Label labBrid = new Label(container, SWT.NONE);
		labBrid.setImage(SWTResourceManager.getImage("\u67E5\u8BE2\u6865\u63A5\u8BCD.png"));
		labBrid.addListener(SWT.MouseUp, new Listener() {
			@Override
			public void handleEvent(org.eclipse.swt.widgets.Event event) {
				//��ѯ�ŽӴ�
				BWShell();
			}
		});
		labBrid.setBounds(35, 205, 313, 66);
		
		//����͸����ť�������ŽӴ�����
		Label labBridgeArtcle = new Label(container, SWT.NONE);
		labBridgeArtcle.setImage(SWTResourceManager.getImage("\u751F\u6210\u6865\u63A5\u8BCD\u6587\u7AE0.png"));
		labBridgeArtcle.addListener(SWT.MouseUp, new Listener() {
			@Override
			public void handleEvent(org.eclipse.swt.widgets.Event event) {
				//�����ŽӴ�����
				BWArtcle();
			}
		});
		labBridgeArtcle.setBounds(36, 265, 313, 66);
		
		//����͸����ť��ʵ���������
		Label labRandomWalk = new Label(container, SWT.NONE);
		labRandomWalk.setImage(SWTResourceManager.getImage("\u968F\u673A\u6E38\u8D70.png"));
		labRandomWalk.addListener(SWT.MouseUp, new Listener() {
			@Override
			public void handleEvent(org.eclipse.swt.widgets.Event event) {
				//�������
				RandomWalkShell();
			}
		});
		labRandomWalk.setBounds(35, 388, 313, 62);
		

		//����͸����ť��ʵ�����·��ѯ
		Label labShortestPath = new Label(container, SWT.NONE);
		labShortestPath.setImage(SWTResourceManager.getImage("\u67E5\u8BE2\u6700\u77ED\u8DEF.png"));
		labShortestPath.addListener(SWT.MouseUp, new Listener() {
			@Override
			public void handleEvent(org.eclipse.swt.widgets.Event event) {
				ShortPathShell();
			}
		});
		labShortestPath.setBounds(35, 327, 313, 62);
		
		
		return container;
	}

	/**
	 * Create the actions.
	 */
	private void createActions() {
		// Create the actions
	}

	
	@Override
	protected StatusLineManager createStatusLineManager() {
		StatusLineManager statusLineManager = new StatusLineManager();
		return statusLineManager;
	}

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			test1 window = new test1();
			window.setBlockOnOpen(true);
			window.open();
			Display.getCurrent().dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Configure the shell.
	 * @param newShell
	 */
	@Override
	protected void configureShell(Shell newShell) {
		newShell.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		super.configureShell(newShell);
		newShell.setText("New Application");
	}

	/**
	 * Return the initial size of the window.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(387, 527);
	}
	/*
	 * �����´��ڣ���չʾ����ͼ����ʵ�ֹ���������
	 */
	protected void ShowGraphShell()
	{
		Shell ShowGraph = new Shell(SWT.DIALOG_TRIM);
		ShowGraph.setText("չʾ����ͼ");
		ShowGraph.setLayout(new FillLayout());
		
//		��ȡͼƬ
		try {
			org.eclipse.swt.graphics.Image img = new org.eclipse.swt.graphics.Image(Display.getCurrent(), "c:/temp/out.jpg");
//			����������
	        final Point origin = new Point(0, 0);
	        Canvas canvas = new Canvas(ShowGraph, SWT.DOUBLE_BUFFERED|SWT.V_SCROLL|SWT.H_SCROLL);
	        canvas.setSize(Math.min(1600, img.getBounds().width), Math.min(900, img.getBounds().height));
	        final ScrollBar hBar = canvas.getHorizontalBar ();
	        final Color red = new Color(null,255,0,0);
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
	        ShowGraph.open();
		} catch (Exception e) {
			// TODO: handle exception
			MessageBox dialog=new MessageBox(ShowGraph,SWT.OK|SWT.ICON_INFORMATION);
	        dialog.setText("error");
	        dialog.setMessage("�Ҳ�������ͼ");
	        dialog.open();
		}
		
	}
	//��ѯ�ŽӴ�
	protected void BWShell() {
		// TODO Auto-generated method stub
		Shell BridgeWord = new Shell(SWT.DIALOG_TRIM);
		// ����ͼƬ����
		BridgeWord.setBackgroundImage(SWTResourceManager.getImage("timg.jpg"));
		BridgeWord.setText("��ѯ�ŽӴ�");
		BridgeWord.setSize(400, 500);
		BridgeWord.open();
		// �����һ������
		Text word1 = new Text(BridgeWord, SWT.BORDER);
		word1.setBounds(5, 10, 73, 26);
		word1.pack();
		// ����ڶ�������
		Text word2 = new Text(BridgeWord, SWT.BORDER);
		word2.setBounds(5, 40, 73, 26);
		word2.pack();
		// �ŽӴ�
		Text word3 = new Text(BridgeWord, SWT.MULTI|SWT.V_SCROLL|SWT.H_SCROLL|SWT.READ_ONLY);
		word3.setBounds(5, 200, 300, 260);
		Button btnBW = new Button(BridgeWord, SWT.NONE);
		btnBW.setBounds(5, 141, 98, 30);
		btnBW.setText("ok");
		btnBW.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					String result;
				// ��ѯ�Ž��㷨�� resultΪ�ŽӴ�
				result = graph.queryBridgeWords(word1.getText(), word2.getText());
				word3.setText(result);
				} catch (Exception e2) {
					// TODO: handle exception
					MessageBox dialog=new MessageBox(BridgeWord,SWT.OK|SWT.ICON_INFORMATION);
			        dialog.setText("error");
			        dialog.setMessage("����ȷ����");
			        dialog.open();
				}
			}
		});
	}
	//�����ŽӴ�����
	protected void BWArtcle() {
		// TODO Auto-generated method stub
		Shell BridgeArtcle = new Shell(SWT.DIALOG_TRIM);
		BridgeArtcle.setBackgroundImage(SWTResourceManager.getImage("timg.jpg"));
		BridgeArtcle.setText("�����ŽӴ�����");
		BridgeArtcle.setSize(400, 500);
		BridgeArtcle.open();
		//�������ŽӴ�����
		Text word1 = new Text(BridgeArtcle, SWT.MULTI|SWT.V_SCROLL|SWT.H_SCROLL);
		word1.setBounds(5, 10, 300, 260);
		Button btnBA = new Button(BridgeArtcle, SWT.NONE);
		btnBA.setBounds(5, 400, 98, 30);
		btnBA.setText("ok");
		btnBA.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String result;
				//�����ŽӴ������㷨�� resultΪ���ɽ��
				result = graph.generateNewText(word1.getText());
				word1.setText(result);
				System.out.println(result);
			}
		});
	}
	//���·��ѯ
	protected void ShortPathShell() {
		// TODO Auto-generated method stub
		Shell ShortPath = new Shell(SWT.DIALOG_TRIM);
		ShortPath.setBackgroundImage(SWTResourceManager.getImage("timg.jpg"));
		ShortPath.setText("���·��ѯ");
		ShortPath.setSize(400, 500);
		ShortPath.open();
		//word1 �����ѯ���
		Text word1 = new Text(ShortPath, SWT.BORDER);
		word1.setBounds(5, 10, 73, 26);
		word1.pack();
		// word2 �����ѯ�ص�
		Text word2 = new Text(ShortPath, SWT.BORDER);
		word2.setBounds(5, 40, 73, 26);
		word2.pack();
		// word3 ����������·
		Text word3 = new Text(ShortPath, SWT.MULTI|SWT.V_SCROLL|SWT.H_SCROLL|SWT.READ_ONLY);
		word3.setBounds(5, 200, 300, 260);
		Button btnBW = new Button(ShortPath, SWT.NONE);
		btnBW.setBounds(5, 141, 98, 30);
		btnBW.setText("ok");
		btnBW.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String result;
				//���·�㷨�� resultΪ��ѯ���
				System.out.println("word1="+word1.getText()+"\nword2="+word2.getText());
				if(!word2.getText().equals(""))result = graph.calcShortestPath(word1.getText(), word2.getText());
				else result = graph.calcShortestPath(word1.getText());
				word3.setText(result);
			}
		});
	}
	//�������
	protected void RandomWalkShell() {
		// TODO Auto-generated method stub
		Shell RandomWalk = new Shell(SWT.DIALOG_TRIM);
		RandomWalk.setBackgroundImage(SWTResourceManager.getImage("timg.jpg"));
		RandomWalk.setText("�������");
		RandomWalk.setSize(400, 500);
		
		Text word1 = new Text(RandomWalk, SWT.MULTI|SWT.V_SCROLL|SWT.H_SCROLL|SWT.READ_ONLY);
		word1.setBounds(5, 10, 300, 260);
		String result;
		try {
			//��������㷨
			result = graph.randomWalk();
			word1.setText(result);
			RandomWalk.open();
			System.out.print(result);
			@SuppressWarnings("resource")
			PrintWriter out = new PrintWriter("RandomWalk.txt");
			out.println(result);
			out.close();
		}
		catch (Exception e) {
			// TODO: handle exception
			//δ��������ͼ
			MessageBox dialog=new MessageBox(RandomWalk,SWT.OK|SWT.ICON_INFORMATION);
	        dialog.setText("error");
	        dialog.setMessage("δ��������ͼ");
	        dialog.open();
		}
		
		
	}
}
