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

public class SelectionSortPanel extends JPanel implements ActionListener{
	Image sortButtonIcon = new ImageIcon("src\\resources\\images\\sortButton.png").getImage();
	Image randomizeButtonIcon = new ImageIcon("src\\resources\\images\\randomizeButton.png").getImage();
	private int dx = 30;
	private int adjustmentFactor =13;
	private int values[] = new int[50];
	private JButton sortButton = new JButton();
	public JButton randomizeButton = new JButton();
	private ArrayList<Integer[]> sortingValues = new ArrayList<Integer[]>();
	private ArrayList<Integer> sortingCurrValueIndex = new ArrayList<Integer>();
	private ArrayList<Integer> sortingMinValueIndex = new ArrayList<Integer>();
	private ArrayList<Integer> sortingNumSorted = new ArrayList<Integer>();
	private int sortingIndex = 0;
	private boolean beforeSorting = true;
	private boolean sorting = false;
	private boolean afterSorting = false;
	private JLabel infoLabel = new JLabel();
	public SelectionSortPanel(int w, int h) {
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
		
		infoLabel.setText("Best Time: O(n squared)                  Average Time: O(n squared)                  Worst Time: O(n squared)");
		infoLabel.setBounds(800, 50, 850, 70);
		this.add(infoLabel);
		
		
		randomlyPopulate(values);
		sort();
		

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
	void sort(){
        for (int i =0; i<values.length; i++) {
        	int currMinIndex = i;
        	int currMin = values[i];
        	for (int j =i+1; j<values.length; j++) {
        		if (values[j]<values[currMinIndex]) {
        			currMin = values[j];
        			currMinIndex = j;
        			
        		}
        		Integer[] newArray = new Integer[values.length];
				for (int f = 0; f < newArray.length; f++) {
					newArray[f] = values[f];
				}
        		sortingValues.add(newArray);
        		sortingCurrValueIndex.add(j);
        		sortingMinValueIndex.add(currMinIndex);
        		sortingNumSorted.add(i);
 
        	}
			int temp = values[currMinIndex];
			values[currMinIndex]=values[i];
			values[i] = temp;
        }
        Integer[] newArray = new Integer[values.length];
		for (int f = 0; f < newArray.length; f++) {
			newArray[f] = values[f];
		}
		sortingValues.add(newArray);
		sortingCurrValueIndex.add(values.length-1);
		sortingMinValueIndex.add(values.length-1);
		sortingNumSorted.add(values.length);
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
				if (i<sortingNumSorted.get(sortingIndex))
					g2d.fill3DRect(40 + i * dx, 0, dx, b[i] * adjustmentFactor, true);
				else if (i==sortingCurrValueIndex.get(sortingIndex)) {
					g2d.fill3DRect(40 + i * dx, 0, dx, b[i] * adjustmentFactor, true);
				}
				else if (i==sortingMinValueIndex.get(sortingIndex)) {
					g2d.fill3DRect(40 + i * dx, 0, dx, b[i] * adjustmentFactor, true);
				}
				else {
					g2d.drawRect(40 + i * dx, 0, dx, b[i] * adjustmentFactor);
				}
			}
			sortingIndex++;
			pause(5);
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
