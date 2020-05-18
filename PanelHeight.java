import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelHeight extends JPanel {
	
	//GUI
	private JTextField f1 = new JTextField(5);
	private JTextField f2 = new JTextField(5);
	private JTextField f3 = new JTextField(8);
	private JButton convert = new JButton("Convert");
	private JLabel l1 = new JLabel("Feet");
	private JLabel l2 = new JLabel("Inches");
	private JLabel l3 = new JLabel("Centimeters");
	GridBagConstraints gbc = new GridBagConstraints();
	
	//LOGIC
	private Float cm;
	private int feet;
	private Float inch;
	private Float temp;
	
	public PanelHeight() {
		
		setLayout(new GridBagLayout());
		
		gbc.insets = new Insets(5, 5, 5, 5);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(l1, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		f1.addMouseListener(new MouseListener() {
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
				if(!f3.getText().isEmpty() && !f2.getText().isEmpty() && !f1.getText().isEmpty()) {
					f1.setText("");
					f2.setText("");
					f3.setText("");
				}
			}
		});
		add(f1, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		add(l2, gbc);
		
		gbc.gridx = 1;
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
				if(!f3.getText().isEmpty() && !f2.getText().isEmpty() && !f1.getText().isEmpty()) {
					f1.setText("");
					f2.setText("");
					f3.setText("");
				}
			}
		});
		add(f2, gbc);
		
		gbc.gridx = 3;
		gbc.gridy = 0;
		add(l3, gbc);
		
		gbc.gridx = 3;
		gbc.gridy = 1;
		f3.addMouseListener(new MouseListener() {
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
				if(!f3.getText().isEmpty() && !f2.getText().isEmpty() && !f1.getText().isEmpty()) {
					f1.setText("");
					f2.setText("");
					f3.setText("");
				}
			}
		});
		add(f3, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 1;
		convert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (f1.getText().isEmpty() && f2.getText().isEmpty() && !f3.getText().isEmpty()) {
					fromCM();
				} else {
					toCM();
				}
			}
		});
		add(convert, gbc);
	}
	
	
	public void fromCM() {
		cm = Float.parseFloat(f3.getText());
		inch = (float) (cm * 0.39370079);
		feet = (int) (inch/12);
		inch = (float) (inch % 12);
		f1.setText(String.valueOf(feet));
		f2.setText(String.valueOf(roundTwoDP(inch)));
		cm = (float) 0;
		inch = (float) 0;
		feet = 0;
	}
	
	public void toCM () {
		if (f1.getText().isEmpty()) {
			feet = 0;
		} else {
			feet = Integer.valueOf(f1.getText());
		}
		if (f2.getText().isEmpty() ) {
			inch = (float) 0;
		} else {
			inch = Float.parseFloat(f2.getText());
		}
		temp = (float) (feet * 12);
		inch = inch + temp;
		cm = (float) (inch * 2.54);
		f3.setText(String.valueOf(cm));
		feet = 0;
		inch = (float) 0;
		temp = (float) 0;
	}

	
	public float roundTwoDP (float f) { //Rounds floats to 2DP
		DecimalFormat twoDP = new DecimalFormat("#.##");
		return Float.valueOf(twoDP.format(f));
	}

}
