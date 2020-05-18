import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {

	JPanel panelContent = new JPanel();
	JPanel panelTemp = new JPanel();
	JPanel panelHeight = new JPanel();
	JPanel panelNew2 = new JPanel();
	JPanel panelNew3 = new JPanel();
	JPanel panelButtons = new JPanel();
	JButton buttonTemp = new JButton("Temperature");
	JButton buttonNew = new JButton("Height");
	JButton buttonNew2 = new JButton("Coming Soon");
	JButton buttonNew3 = new JButton("Coming Soon");
	CardLayout cl = new CardLayout();
	
	public MainFrame() {
		
		super("Converter App");
		setLayout(new BorderLayout());
		
		panelContent.setLayout(cl);
		
		panelButtons.setLayout(new GridLayout(0 ,1, 0 ,20));
		panelButtons.add(buttonTemp);
		panelButtons.add(buttonNew);
		panelButtons.add(buttonNew2);
		panelButtons.add(buttonNew3);
		add(panelButtons, BorderLayout.WEST);
		
		panelTemp = new PanelTemp();
		panelHeight = new PanelHeight();
		panelNew2.setBackground(Color.red);
		panelNew3.setBackground(Color.yellow);
		
		panelContent.add(panelTemp, "Temp");
		panelContent.add(panelHeight, "Height");
		panelContent.add(panelNew2, "New2");
		panelContent.add(panelNew3, "New3");
		
		cl.show(panelContent, "Temp");
		
		buttonTemp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(panelContent, "Temp");
			}
		});
		
		buttonNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(panelContent, "Height");
			}
		});
		
		buttonNew2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(panelContent, "New2");
			}
		});
		
		buttonNew3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(panelContent, "New3");
			}
		});
		
		
		add(panelContent, BorderLayout.CENTER);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
		setSize(500, 300);
	}
}
