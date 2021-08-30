import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Frame extends JFrame implements ActionListener {
	private final static String FRAME_NAME = "Sorting Algorithm Visualizer";
	private final int FRAME_WIDTH = 1600;
	private final int FRAME_HEIGHT = 900;
	private final int MENU_ITEM_SIZE = 5;

	private Container c = getContentPane();
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem menuItems[];
	private HomePanel hp = new HomePanel(FRAME_WIDTH, FRAME_HEIGHT);
	private BubbleSortPanel bsp = new BubbleSortPanel(FRAME_WIDTH, FRAME_HEIGHT);
	private SelectionSortPanel ssp = new SelectionSortPanel(FRAME_WIDTH, FRAME_HEIGHT);
	private InsertionSortPanel isp = new InsertionSortPanel(FRAME_WIDTH, FRAME_HEIGHT);
	private MergeSortPanel msp = new MergeSortPanel(FRAME_WIDTH, FRAME_HEIGHT);

	public Frame() {
		super(FRAME_NAME);
		c.add(hp);
		setUpFrame();
	}

	public void setUpFrame() {
		this.setJMenuBar(null);
		menuBar = new JMenuBar();
		menu = new JMenu("Menu");
		menuItems = new JMenuItem[MENU_ITEM_SIZE];
		menuItems[0] = new JMenuItem("Home");
		menuItems[1] = new JMenuItem("Bubble Sort");
		menuItems[2] = new JMenuItem("Selection Sort");
		menuItems[3] = new JMenuItem("Insertion Sort");
		menuItems[4] = new JMenuItem("Merge Sort");
		for (int i = 0; i < MENU_ITEM_SIZE; i++) {
			menuItems[i].addActionListener(this);
			menu.add(menuItems[i]);
		}
		menuBar.add(menu);
		this.setJMenuBar(menuBar);
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setResizable(false);
		this.setVisible(true);
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ssp.randomizeButton.addActionListener(this);
		bsp.randomizeButton.addActionListener(this);
		isp.randomizeButton.addActionListener(this);
		msp.randomizeButton.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == menuItems[0]) {
			c.removeAll();
			c.add(hp);
			setUpFrame();
		}
		if (e.getSource() == menuItems[1]) {
			c.removeAll();
			c.add(bsp);
			setUpFrame();
		}
		if (e.getSource() == bsp.randomizeButton) {
			c.removeAll();
			bsp = new BubbleSortPanel(FRAME_WIDTH, FRAME_HEIGHT);
			bsp.randomizeButton.addActionListener(this);
			c.add(bsp);
			setUpFrame();
		}
		if (e.getSource() == menuItems[2]) {
			c.removeAll();
			c.add(ssp);
			setUpFrame();
		}
		if (e.getSource() == ssp.randomizeButton) {
			c.removeAll();
			ssp = new SelectionSortPanel(FRAME_WIDTH, FRAME_HEIGHT);
			ssp.randomizeButton.addActionListener(this);
			c.add(ssp);
			setUpFrame();
		}
		if (e.getSource() == menuItems[3]) {
			c.removeAll();
			c.add(isp);
			setUpFrame();
		}
		if (e.getSource() == isp.randomizeButton) {
			c.removeAll();
			isp = new InsertionSortPanel(FRAME_WIDTH, FRAME_HEIGHT);
			isp.randomizeButton.addActionListener(this);
			c.add(isp);
			setUpFrame();
		}
		if (e.getSource() == menuItems[4]) {
			c.removeAll();
			c.add(msp);
			setUpFrame();
		}
		if (e.getSource() == msp.randomizeButton) {
			c.removeAll();
			msp = new MergeSortPanel(FRAME_WIDTH, FRAME_HEIGHT);
			msp.randomizeButton.addActionListener(this);
			c.add(msp);
			setUpFrame();
		}
	}
}
