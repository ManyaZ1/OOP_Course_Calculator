package algorithms;

import java.util.Stack;

import exceptions.GeneralException;
import gui.Calculator;

public class InfixtoPostfix {
	private Stack<String> infixStack=new Stack<String>(); 
	private Stack<String> postfixStack=new Stack<String>();
	private Stack<String> operatorStack=new Stack<String>();
	//private String infixString;
	
	//constructor
	public InfixtoPostfix(String userInfix) throws GeneralException{
		createInfixStack(userInfix);
		System.out.println("infix: "+infixStack);
		//CHECK FOR ERRORS
		try {
			SyntaxCheck synta=new SyntaxCheck(infixStack);
			convertToPostfix();
			//printStack(postfixStack);
			System.out.println("postfix:"+getPostfix());
		}
		catch(Exception e) {
			String s=e.getMessage();
			//Calculator.throwError(s);
			System.out.println(s);
			//when there is an exception->display error
			throw new GeneralException(s);
		}
		
		

	}
	public Stack<String> getPostfix() {
		return postfixStack;
	}	
	public Stack<String> getInfix() {
		return infixStack;
	}
	
	
	private void createInfixStack(String userInfix){
		String numberString="";
		char c;
		char prev='\0';
		for(int i=0;i<userInfix.length();i++) {
			c=userInfix.charAt(i);
			if(Character.isDigit(c)|| c=='.') {
				numberString=numberString+c;
			}
			
			else {
				infixStack.push(numberString);//printStack(infixStack);
				if(prev=='\0'&&c=='-') {
					infixStack.push("0");
				}
				if(prev=='('&&c=='-') {infixStack.push("0");}
				infixStack.push(Character.toString(c));
				numberString="";
			}
		prev=c;
		}
		//System.out.println("\n string"+numberString);
		infixStack.push(numberString);	
	}
	
	private void printStack(Stack<String> s) {
		if (s.empty()) {System.out.println("");return;}
		String n=s.peek();
		System.out.print(n+"");
		s.pop();
		printStack(s);
		s.push(n);
		
	}
	
	private boolean PriorityorEqual(String op1, String op2) {//true for priority>=
		//priority ^>*=/>+=-
		if(op1.equalsIgnoreCase(op2)) {return false;}
		if(op1.equalsIgnoreCase("^")) {return true;}
		if((op1.equalsIgnoreCase("*")||op1.equalsIgnoreCase("/"))) {
			if(op2.equalsIgnoreCase("^")) {return false;}
			else return true;
		}
		if((op1.equalsIgnoreCase("+")||op1.equalsIgnoreCase("-"))&& (op2.equalsIgnoreCase("+")||op2.equalsIgnoreCase("-"))) {
			return true;
		}
		if(op2.equalsIgnoreCase("(")) {return true;}
		return false;
	}
	
	private void convertToPostfix() {
		for(String element:infixStack) {
			//System.out.println(element);
			if(isNumeric(element)) {    
				postfixStack.push(element);
			}
			else if(element.equalsIgnoreCase("(")) {
				operatorStack.push(element);
			}
			else if(element.equalsIgnoreCase(")")) {
				while(!operatorStack.peek().equalsIgnoreCase("("))
				   {
					 postfixStack.push(operatorStack.pop());
				   }
				 operatorStack.pop();  
			/*//bale operators sthn postfix mexri (
				while(operatorStack.size()>0 && !operatorStack.peek().equalsIgnoreCase("(")) {
					postfixStack.push(operatorStack.pop());
				}
				operatorStack.pop(); //pop (*/
			}
			else if(isOperator(element)) {
				handleOperatorsCase(element);
			}
			
		}
		//no more infix therefore put all operators in postfix
		while(operatorStack.size()>0)  {
			postfixStack.push(operatorStack.pop());	
		}
	}
	
	private void handleOperatorsCase(String element) {
		if(operatorStack.size()==0) {
			operatorStack.push(element);
			return;
		}
		else if(PriorityorEqual(element,operatorStack.peek())) {
			operatorStack.push(element);
			return;
		}
		else {
			postfixStack.push(operatorStack.pop());
			handleOperatorsCase( element);
		}
	}
	//package visibility
	static boolean isOperator(String elem) {
		if(elem.equalsIgnoreCase("+") ||elem.equalsIgnoreCase("-") ||elem.equalsIgnoreCase("*") || elem.equalsIgnoreCase("/") ||elem.equalsIgnoreCase("^")) {
			return true;}
		else return false;
	}
	 static boolean isNumeric(String str) {//package visibility
		if(str==null) {return false;}
		try {
			double d = Double.parseDouble(str);
			return true;
		}
		catch (NumberFormatException nfe) {	  
			try{
				float d=Float.parseFloat(str);//System.out.print("great");
			}
			catch(NumberFormatException fe){ 
				try {
					int i=Integer.parseInt(str);
				}
				catch(NumberFormatException e){return false;}
			}
		}
		return true;
	}
	private void success() {System.out.println("success");}
	
	
}


