package boundary;

import java.io.PrintWriter;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.ScrollBar;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.wb.swt.SWTResourceManager;

import control.ReadFile;
import entity.DGraph;

public class MainUi extends ApplicationWindow {
	private Text text;
	private DGraph graph;
	private ShowGraphShell showgraphshell;
	private BWShell bwshell;
	private BWArticle bwarticle;
	private RandomWalkShell randomwalkshell;
	private ShortPathShell shortpathshell;
	private ReadFile temp;

	/**
	 * Create the application window.
	 */
	public MainUi() {
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
		//建立控制容器
		Composite container = new Composite(parent, SWT.NONE);
		
		container.setBackgroundImage(SWTResourceManager.getImage("timg.jpg"));

		container.setLayout(null);
		container.setBackgroundMode(SWT.INHERIT_FORCE);//设置容器背景透明
//		建立文本输入框， 用于输入路径
		text = new Text(container, SWT.BORDER);
		text.setBounds(35, 115, 192, 25);
//		文件检索按钮
		Button Search = new Button(container, SWT.NONE);
		Search.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					text.setText("");
					FileDialog filedlg=new FileDialog(new Shell(), SWT.OPEN);
					//设置文件对话框的标题
					filedlg.setText("文件选择");
					//设置初始路径
					filedlg.setFilterPath("SystemRoot");
					//打开文件对话框，返回选中文件的绝对路径
					String selected=filedlg.open();
					text.insert(selected);
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		Search.setText("\u6D4F\u89C8");
		Search.setBounds(227, 114, 60, 27);
//设置确定按钮
		Button readfile = new Button(container, SWT.NONE);
		readfile.setBounds(287, 114, 60, 27);
		readfile.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String path = text.getText();
				System.out.println(path);
				try {
					//根据读取路径建立有向图
					temp = new ReadFile();
					temp.read(path);
					graph = temp.getG();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					MessageBox dialog=new MessageBox(new Shell(),SWT.OK|SWT.ICON_INFORMATION);
			        dialog.setText("error");
			        dialog.setMessage("请输入正确路径");
			        dialog.open();
				}
			}
		});
		readfile.setText("\u786E\u5B9A");
		
//建立透明按钮，实现展示有向图
		Label labShowGraph = new Label(container, SWT.NONE);
		//设置透明按钮
		labShowGraph.setImage(SWTResourceManager.getImage("\u5C55\u793A\u6709\u5411\u56FE.png"));
		labShowGraph.addListener(SWT.MouseUp, new Listener() {
			@Override
			public void handleEvent(org.eclipse.swt.widgets.Event event) {
				//展示有向图
					showgraphshell = new ShowGraphShell(graph);
					showgraphshell.open();
			}
		});
		labShowGraph.setBounds(35, 146, 313, 62);
		
		//建立透明按钮，查询桥接词
		Label labBrid = new Label(container, SWT.NONE);
		labBrid.setImage(SWTResourceManager.getImage("\u67E5\u8BE2\u6865\u63A5\u8BCD.png"));
		labBrid.addListener(SWT.MouseUp, new Listener() {
			@Override
			public void handleEvent(org.eclipse.swt.widgets.Event event) {
				//查询桥接词
				bwshell = new BWShell(graph);
				bwshell.open();
			}
		});
		labBrid.setBounds(35, 205, 313, 66);
		
		//建立透明按钮，生成桥接词文章
		Label labBridgeArtcle = new Label(container, SWT.NONE);
		labBridgeArtcle.setImage(SWTResourceManager.getImage("\u751F\u6210\u6865\u63A5\u8BCD\u6587\u7AE0.png"));
		labBridgeArtcle.addListener(SWT.MouseUp, new Listener() {
			@Override
			public void handleEvent(org.eclipse.swt.widgets.Event event) {
				//生成桥接词文章
				bwarticle = new BWArticle(graph);
				bwarticle.open();
			}
		});
		labBridgeArtcle.setBounds(36, 265, 313, 66);
		
		//建立透明按钮，实现随机游走
		Label labRandomWalk = new Label(container, SWT.NONE);
		labRandomWalk.setImage(SWTResourceManager.getImage("\u968F\u673A\u6E38\u8D70.png"));
		labRandomWalk.addListener(SWT.MouseUp, new Listener() {
			@Override
			public void handleEvent(org.eclipse.swt.widgets.Event event) {
				//随机游走
				randomwalkshell = new RandomWalkShell(graph);
			}
		});
		labRandomWalk.setBounds(35, 388, 313, 62);
		
		//建立透明按钮，实现最短路查询
		Label labShortestPath = new Label(container, SWT.NONE);
		labShortestPath.setImage(SWTResourceManager.getImage("\u67E5\u8BE2\u6700\u77ED\u8DEF.png"));
		labShortestPath.addListener(SWT.MouseUp, new Listener() {
			@Override
			public void handleEvent(org.eclipse.swt.widgets.Event event) {
				shortpathshell = new ShortPathShell(graph);
				shortpathshell.open();
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
			MainUi window = new MainUi();
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
}
