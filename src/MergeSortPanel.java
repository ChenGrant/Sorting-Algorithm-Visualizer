
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

public class MergeSortPanel extends JPanel implements ActionListener {
	Image sortButtonIcon = new ImageIcon("src\\resources\\images\\sortButton.png").getImage();
	Image randomizeButtonIcon = new ImageIcon("src\\resources\\images\\randomizeButton.png").getImage();
	private int dx = 30;
	private int adjustmentFactor = 13;
	private int values[] = new int[50];
	private JButton sortButton = new JButton();
	public JButton randomizeButton = new JButton();
	private int sortingIndex = 0;
	private boolean beforeSorting = true;
	private boolean sorting = false;
	private boolean afterSorting = false;
	private ArrayList<Integer[]> k = new ArrayList<Integer[]>();
	private JLabel infoLabel = new JLabel();

	public MergeSortPanel(int w, int h) {
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
		
		infoLabel.setText("Best Time: O(n*log(n))                  Average Time: O(n*log(n))                  Worst Time: O(n*log(n))");
		infoLabel.setBounds(800, 50, 850, 70);
		this.add(infoLabel);
		
		randomlyPopulate(values);

		mergeSort(values, 0, values.length - 1);

		Integer[] newArray = new Integer[values.length];
		for (int f = 0; f < newArray.length; f++) {
			newArray[f] = values[f];
		}
		k.add(newArray);

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

	public void merge(int a[], int start, int middle, int end) {
		int sizeOfTemp1, sizeOfTemp2, i, j, k;
		sizeOfTemp1 = (middle - start) + 1;
		sizeOfTemp2 = (end - middle);

		int[] temp1 = new int[sizeOfTemp1];
		int[] temp2 = new int[sizeOfTemp1];

		for (i = 0; i < sizeOfTemp1; i++) {
			temp1[i] = a[start + i];
		}

		for (i = 0; i < sizeOfTemp2; i++) {
			temp2[i] = a[middle + 1 + i];
		}

		i = 0;
		j = 0;
		k = start;

		while (i < sizeOfTemp1 && j < sizeOfTemp2) {
			if (temp1[i] < temp2[j]) {
				a[k] = temp1[i];
				i++;
			} else {
				a[k] = temp2[j];
				j++;
			}
			k++;
		}

		while (i < sizeOfTemp1) {
			a[k] = temp1[i];
			k++;
			i++;
		}

		while (j < sizeOfTemp2) {
			a[k] = temp2[j];
			k++;
			j++;
		}
	}

	public void mergeSort(int a[], int start, int end) {
		if (start < end) {
			int middle = (start + end) / 2;
			mergeSort(a, start, middle);
			mergeSort(a, middle + 1, end);
			Integer[] newArray = new Integer[a.length];
			for (int f = 0; f < newArray.length; f++) {
				newArray[f] = a[f];
			}
			k.add(newArray);
			merge(a, start, middle, end);
		}
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.translate(0, 800);
		g2d.scale(1, -1);
		if (beforeSorting) {
			Integer a[] = k.get(0);
			for (int i = 0; i < a.length; i++) {
				g.drawRect(40 + i * dx, 0, dx, a[i] * adjustmentFactor);
			}
		}
		if (sorting) {
			Integer b[] = k.get(sortingIndex);
			g2d.setColor(Color.black);
			for (int i = 0; i < b.length; i++) {
				g2d.drawRect(40 + i * dx, 0, dx, b[i] * adjustmentFactor);

			}
			sortingIndex++;
			pause(100);
			if (sortingIndex == k.size()) {
				sortingIndex = 0;
				sorting = false;
				afterSorting = true;
			}
		}
		if (afterSorting) {
			Integer c[] = k.get(k.size() - 1);
			g2d.setColor(Color.black);
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
