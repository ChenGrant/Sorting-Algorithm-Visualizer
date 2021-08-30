import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InsertionSortPanel extends JPanel implements ActionListener {
	Image sortButtonIcon = new ImageIcon("src\\resources\\images\\sortButton.png").getImage();
	Image randomizeButtonIcon = new ImageIcon("src\\resources\\images\\randomizeButton.png").getImage();
	private int dx = 30;
	private int adjustmentFactor = 13;
	private int values[] = new int[50];
	private JButton sortButton = new JButton();
	public JButton randomizeButton = new JButton();
	private ArrayList<Integer[]> sortingValues = new ArrayList<Integer[]>();
	private int sortingIndex = 0;
	private boolean beforeSorting = true;
	private boolean sorting = false;
	private boolean afterSorting = false;
	private JLabel infoLabel = new JLabel();
	public InsertionSortPanel(int w, int h) {
		this.setSize(w, h);
		this.setLayout(null);
		this.setBackground(Color.white);
		sortButton.setIcon(new ImageIcon(sortButtonIcon.getScaledInstance(200, 70,java.awt.Image.SCALE_SMOOTH)));
		sortButton.setBounds(150, 50, 200, 70);
		sortButton.addActionListener(this);
		this.add(sortButton);
		
		randomizeButton.setIcon(new ImageIcon(randomizeButtonIcon.getScaledInstance(350, 70,java.awt.Image.SCALE_SMOOTH)));
		randomizeButton.setBounds(400, 50, 350, 70);
		randomizeButton.addActionListener(this);
		this.add(randomizeButton);
		
		infoLabel.setText("Best Time: O(n)                  Average Time: O(n squared)                  Worst Time: O(n squared)");
		infoLabel.setBounds(800, 50, 850, 70);
		this.add(infoLabel);
		
		
		randomlyPopulate(values);
		sort(values);
	}

	public void randomlyPopulate(int a[]) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < a.length; i++) {
			list.add(i + 1);
		}
		for (int i = 0; i < a.length; i++) {
			int index = (int) (Math.random() * (list.size()));
			a[i] = list.get(index);
			list.remove(list.get(index));
		}

	}

	public void pause(long timeInMilliSeconds) {

		long timestamp = System.currentTimeMillis();

		do {

		} while (System.currentTimeMillis() < timestamp + timeInMilliSeconds);

	}

	public void printArray(int b[]) {
		for (int i = 0; i < b.length; i++) {
			System.out.print(b[i] + " ");
		}
		System.out.println();
	}

	public void printArray(Integer[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}

	public void sort(int a[]) {
		for (int i = 1; i < a.length; ++i) {
			
			int key = a[i];
			int j = i - 1;
			while (j >= 0 && a[j] > key) {
				a[j + 1] = a[j];
				j = j - 1;
				
			}
			a[j + 1] = key;
			Integer[] newArray = new Integer[a.length];
			for (int f = 0; f < newArray.length; f++) {
				newArray[f] = values[f];
			}
			sortingValues.add(newArray);

		}
		Integer[] newArray = new Integer[a.length];
		for (int f = 0; f < newArray.length; f++) {
			newArray[f] = values[f];
		}
		sortingValues.add(newArray);
	}
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.translate(0, 800);
		g2d.scale(1, -1);
		if (beforeSorting) {
			Integer a[] = sortingValues.get(0);
			for (int i = 0; i < a.length; i++) {
				g.drawRect(40 + i * dx, 0, dx, a[i] * adjustmentFactor);
			}
		}
		if (sorting) {
			Integer b[] = sortingValues.get(sortingIndex);
			g2d.setColor(Color.black);
			for (int i = 0; i < b.length; i++) {
				if (i<sortingIndex+2)
					g2d.fill3DRect(40 + i * dx, 0, dx, b[i] * adjustmentFactor, true);
				else {
					g2d.drawRect(40 + i * dx, 0, dx, b[i] * adjustmentFactor);
				}
			}
			sortingIndex++;
			pause(250);
			if (sortingIndex == sortingValues.size()) {
				sortingIndex = 0;
				sorting = false;
				afterSorting = true;
			}
		}
		if (afterSorting) {
			g2d.setColor(Color.black);
			Integer c[] = sortingValues.get(sortingValues.size() - 1);
			for (int i = 0; i < c.length; i++) {
				g.fill3DRect(40 + i * dx, 0, dx, c[i] * adjustmentFactor, true);
			}
		}
		repaint();
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == sortButton) {
			beforeSorting = false;
			afterSorting = false;
			sorting = true;
		}

	}
}
