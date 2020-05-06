import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelTemp extends JPanel {

	//GUI
	private JTextField f1 = new JTextField(10);
	private JTextField f2 = new JTextField(10);
	private JButton convert = new JButton("Convert");
	private JLabel l1 = new JLabel("Temperature 1");
	private JLabel l2 = new JLabel("Temperature 2");
	GridBagConstraints gbc = new GridBagConstraints();
	String[] tempOpt = new String[] {"Celsius", "Fahrenheit", "Kelvin"};
	private JComboBox<String> tempList1 = new JComboBox<String>(tempOpt);
	private JComboBox<String> tempList2 = new JComboBox<String>(tempOpt);
	
	//Logic
	private Float temp = null;
	private int isLeft = 0;
	private String tFrom;
	private String tTo;
	private Float tempOut;
	
	public PanelTemp() {

		setLayout(new GridBagLayout());
		
		gbc.insets = new Insets(5, 5, 5, 5);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(l1, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 0;
		add(l2, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		add(f1, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 1;
		f2.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!f2.getText().isEmpty() && !f1.getText().isEmpty()) {
					f1.setText("");
					f2.setText("");
				}
			}
		});
		add(f2, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		f1.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {
				if(!f1.getText().isEmpty() && !f2.getText().isEmpty()) {
					f1.setText("");
					f2.setText("");
				}
			}
		});
		add(tempList1, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 2;
		add(tempList2, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		convert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//whichSide(f1,f2); //returns the number
				//System.out.println(temp);
				isLeft = checkSide(f1, f2);
				temp = (checkValue(f1, f2, isLeft));
				tFrom = getFrom(tempList1, tempList2, isLeft);
				tTo = getTo(tempList1,tempList2, isLeft);
				tempOut = logic(temp, tFrom, tTo);
				outTemp(isLeft, f1, f2, tempOut);
			}
			});
		add(convert, gbc);

	}
	// Check which field not empty - 0 = Invalid, 1 = Left, 2 = Right
	public int checkSide(JTextField a, JTextField b) {
		if (a.getText().isEmpty() && !b.getText().isEmpty()) {
			isLeft = 2;
		} else if (b.getText().isEmpty() && !a.getText().isEmpty()) {
			isLeft = 1;
		} else {
			isLeft = 0;
		}
		return isLeft;
	}
	
	//Check if the value is valid
	public Float checkValue(JTextField a, JTextField b, int i) {
		if(i < 1) {
			return null;
		} else if (i ==1) {
			try {
				temp = Float.parseFloat(a.getText());
				return temp;
			}
			catch (NumberFormatException e1) {
				System.out.println("Not a number");
			}
		} else if(i == 2) {
			try {
				temp = Float.parseFloat(b.getText());
				return temp;
			}
			catch (NumberFormatException e2) {
				System.out.println("Not a number");
			}
		}
		return null;
	}
	
	//Reads drop down box from inputed side
	public String getFrom(JComboBox<String> a, JComboBox<String> b, int i) {
		if (i == 1) {
			tFrom = (String)a.getSelectedItem().toString();
		} else if (i == 2) {
			tFrom = (String)b.getSelectedItem().toString();
		}
		return tFrom;
	}
	
	//Reads drop down box from empty side
	public String getTo(JComboBox<String> a, JComboBox<String> b, int i) {
		if (i == 1) {
			tTo = (String)b.getSelectedItem();
		} else if (i == 2) {
			tTo = (String)a.getSelectedItem();
		}
		return tTo;
	}
	
	//Does the Math
	public Float logic(Float temp, String from, String to) {
		if (from == "Celsius" && to == "Fahrenheit") {
			tempOut = (float) ((temp*1.8)+32);
		} else if (from == "Celsius" && to == "Kelvin") {
			tempOut = (float) (temp + 273.15);
		} else if (from == "Fahrenheit" && to == "Celsius") {
			tempOut = (float) ((temp -32)/1.8);
		} else if (from == "Fahrenheit" && to == "Kelvin") {
			tempOut = (float) ((((temp-32)*5)/9)+273.15);
		} else if (from == "Kelvin" && to == "Celsius") {
			tempOut = (float) (temp -273.15);
		} else if (from == "Kelvin" && to == "Fahrenheit") {
			tempOut = (float) ((((temp -273.15)*9)/5) +32);
		}
		return tempOut;
	}
	
	//Inputs result into empty box
	public void outTemp(int i, JTextField a, JTextField b, Float f) {
		if (i == 1) {
			b.setText(String.valueOf(roundTwoDP(f)));
		} else if (i == 2) {
			a.setText(String.valueOf(roundTwoDP(f)));
		}
	}
	
	//Rounds results to 2DP
	public float roundTwoDP (float f) { //Rounds floats to 2DP
		DecimalFormat twoDP = new DecimalFormat("#.##");
		return Float.valueOf(twoDP.format(f));
	}

}
