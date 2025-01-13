package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import algorithms.InfixtoPostfix;
import algorithms.PostfixtoResult;
import algorithms.SyntaxCheck;
import exceptions.DotException;
import exceptions.GeneralException;

public class Calculator extends JFrame {
	static JTextField northScreen=new JTextField("");
	private static final long serialVersionUID = 1L;
	//public constr used in main
	public Calculator() {
		this.setSize(300, 300);
		this.setTitle("Week 9");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null); 
		this.setFocusable(true);
		BorderLayout border=new BorderLayout();
		this.setLayout(border);
		this.add(northScreen,BorderLayout.NORTH);
		//northScreen.setBackground(Color.lightGray);
		this.add(createCenterPanel(),BorderLayout.CENTER);
		this.setVisible(true);
		this.setBackground(Color.blue);
	}
	//centerpanel
	private Component createCenterPanel() {
		JPanel p=new JPanel();
		p.setLayout(new GridLayout(5,4));
		p.setBackground(new java.awt.Color(190,255,240));
		p.add(new Buttons("C",new java.awt.Color(92,245,179))); 
		p.add(new Buttons("(",new java.awt.Color(92,245,179))); 
		p.add(new Buttons(")",new java.awt.Color(92,245,179))); 
		p.add(new Buttons("/",new java.awt.Color(92,245,179))); 
		p.add(new Buttons("7",new java.awt.Color(181,250,216))); 
		p.add(new Buttons("8",new java.awt.Color(181,250,216))); 
		p.add(new Buttons("9",new java.awt.Color(181,250,216))); 
		p.add(new Buttons("*",new java.awt.Color(92,245,179))); 
		p.add(new Buttons("4",new java.awt.Color(181,250,216))); 
		p.add(new Buttons("5",new java.awt.Color(181,250,216))); 
		p.add(new Buttons("6",new java.awt.Color(181,250,216)));
		p.add(new Buttons("-",new java.awt.Color(92,245,179))); 
		p.add(new Buttons("1",new java.awt.Color(181,250,216))); 
		p.add(new Buttons("2",new java.awt.Color(181,250,216))); 
		p.add(new Buttons("3",new java.awt.Color(181,250,216))); 
		p.add(new Buttons("+",new java.awt.Color(92,245,179))); 
		p.add(new Buttons("0",new java.awt.Color(181,250,216))); 
		p.add(new Buttons(".",new java.awt.Color(181,250,216))); 
		p.add(new Buttons("^",new java.awt.Color(181,250,216))); 
		p.add(new Buttons("=",new java.awt.Color(92,245,179))); 
		return p;
	}
	static double calculationTime(String s) throws GeneralException{
		try {
		InfixtoPostfix converter=new InfixtoPostfix(s);
		PostfixtoResult calc=new PostfixtoResult(converter.getPostfix());
		System.out.println("result: "+calc.getFinalresult());
		return calc.getFinalresult();
		}
		catch(Exception e) {
			throw new GeneralException(e.getMessage());
			//northScreen.setText("Error "+e.getMessage());
		
		}
	}
	public static void throwError(String s) {
		northScreen.setText("Error "+s);
	}
}
