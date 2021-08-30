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

public class BubbleSortPanel extends JPanel implements ActionListener {
	private Image sortButtonIcon = new ImageIcon("src\\resources\\images\\sortButton.png").getImage();
	private Image randomizeButtonIcon = new ImageIcon("src\\resources\\images\\randomizeButton.png").getImage();
	private int dx = 30;
	private int adjustmentFactor =13;
	private boolean beforeSorting = true;
	private boolean sorting = false;
	private boolean afterSorting = false;
	private int values[] = new int[50];
	private ArrayList<Integer[]> sortingList = new ArrayList<Integer[]>();
	private ArrayList<Integer> sortingFocus = new ArrayList<Integer>();
	private int sortingIndex = 0;
	private int blackIndex;
	private JButton sortButton = new JButton();
	public JButton randomizeButton = new JButton();
	private JLabel infoLabel = new JLabel();

	public BubbleSortPanel(int w, int h) {
		this.setSize(w, h);
		this.setLayout(null);
		this.setBackground(Color.white);
		randomlyPopulate(values);
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
		bubbleSort(values);
		blackIndex = values.length;

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

	public void printArray(int a[]) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}

	public void printArray(Integer[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}

	public void printArrayList(ArrayList<Integer[]> a) {
		for (int i = 0; i < a.size(); i++) {
			printArray(a.get(i));
		}
		System.out.println();
	}

	public void bubbleSort(int arr[]) {
		int i, j, temp;
		boolean swapped;
		for (i = 0; i < arr.length - 1; i++) {
			swapped = false;
			for (j = 0; j < arr.length - i - 1; j++) {
				if (arr[j] > arr[j + 1]) {
					temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
					swapped = true;
				}

				Integer[] newArray = new Integer[arr.length];
				for (int f = 0; f < newArray.length; f++) {
					newArray[f] = arr[f];
				}
				sortingList.add(newArray);
				sortingFocus.add(j+1);
			}
			if (swapped == false)
				break;
		}
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.translate(0, 800);
		g2d.scale(1, -1);
		if (beforeSorting) {
			Integer a[] = sortingList.get(0);
			for (int i = 0; i < a.length; i++) {
				g.drawRect(40 + i * dx, 0, dx, a[i] * adjustmentFactor);
			}
		}
		if (sorting) {
			Integer b[] = sortingList.get(sortingIndex);
			for (int i = 0; i < b.length; i++) {
				if (i == sortingFocus.get(sortingIndex)) {
					g2d.setColor(Color.black);
					g2d.fill3DRect(40 + i * dx, 0, dx, b[i] * adjustmentFactor, true);
				}
				else if (i>=blackIndex) {
					g2d.setColor(Color.black);
					g2d.fill3DRect(40 + i * dx, 0, dx, b[i] * adjustmentFactor, true);
					g2d = (Graphics2D) g;
				}
				else {
					g2d.drawRect(40 + i * dx, 0, dx, b[i] * adjustmentFactor);
				}
			}
			if (sortingFocus.get(sortingIndex)==blackIndex-1) {
				blackIndex--;
			}
			sortingIndex++;
			pause(5);
			if (sortingIndex == sortingList.size()) {
				sortingIndex = 0;
				sorting = false;
				afterSorting = true;
			}
		}
		if (afterSorting) {
			g2d.setColor(Color.black);
			Integer c[] = sortingList.get(sortingList.size() - 1);
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
