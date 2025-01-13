package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;

public class Buttons extends JButton implements ActionListener{
	private static String message="";
	//def constr
	Buttons(String label, Color background){
		super(label);
		this.setForeground(Color.black);
		this.setBorderPainted(false);
		this.setName(label);
		this.setBackground(background);
		addActionListener(this);  
		this.setOpaque(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String butName=((JComponent) e.getSource()).getName();
		System.out.println(butName);
		if(butName.equalsIgnoreCase("C")) {Calculator.northScreen.setText("");}
		else if(butName.equalsIgnoreCase("=")) {
			try {
			double result=Calculator.calculationTime(Calculator.northScreen.getText());
				Calculator.northScreen.setText(String.valueOf(result));}
			catch(Exception ex) {
				Calculator.northScreen.setText("Error: "+ex.getMessage());
			}
		}
		else {
			Calculator.northScreen.setText(Calculator.northScreen.getText()+butName);}
	}
	static void setMessage(String s) {message=new String(s);}

}
