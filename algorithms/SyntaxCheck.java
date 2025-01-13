package algorithms;

import java.util.Stack;

import exceptions.DotException;
import exceptions.ParenthesisException;
import exceptions.SymbolsException;

public class SyntaxCheck {
	private String current;
	private Stack<String> infixStackClone=new Stack<String>();
	//constructor
	public SyntaxCheck(Stack<String> infixStack) throws DotException,SymbolsException,ParenthesisException{
		infixStackClone=(Stack<String>) infixStack.clone();
		Stack<String> infixStackClone2=(Stack<String>) infixStack.clone();
		removeblanks();
		//System.out.println("infixStackClone"+infixStackClone);
		if(checkfordots()) {
			throw new DotException("cannot have two dots in the same number");
		}
		else {//no dot exceptions were thrown
			if(symbolCheck()) {throw new SymbolsException("wrong symbol order "+current);}
		}
		if(!parenthesisCheck(infixStackClone2)) {throw new ParenthesisException("unbalanced parentheses");}
	}
	private boolean symbolCheck() throws SymbolsException {
		int i=0;
		String previous="";
		while(infixStackClone.size()>0) {
			
			current=infixStackClone.peek();
			
			if(InfixtoPostfix.isNumeric(current)) {
				if(!previous.equalsIgnoreCase("")&&!InfixtoPostfix.isOperator(previous)&&!previous.equalsIgnoreCase(")")) {
					return true;}		
			}
			if(InfixtoPostfix.isOperator(current)) {
				if(previous.equalsIgnoreCase(")")||InfixtoPostfix.isOperator((previous))){return true;}
			}
			if(current.equalsIgnoreCase("(")) {
				if(!InfixtoPostfix.isNumeric(previous)&& !previous.equalsIgnoreCase("(")) {return true;}
				//if(previous.equalsIgnoreCase(previous))
			}
			
		previous=infixStackClone.pop();
		
		}
		return false;
	}
	
	private void removeblanks() {
		boolean flag;
		 flag=infixStackClone.remove("");
		 while(flag) {
			 flag=infixStackClone.remove("");
		 }
		System.out.println("infixStackClone"+infixStackClone);
	}
	
	//make parenthesis Function
	private boolean parenthesisCheck(Stack<String> infixStackClone2) {
		String s="";
		int count=0;
		boolean balance=true;
		//put all parentheses in a string
		for(String element:infixStackClone2) {
			if(element.equalsIgnoreCase("(")||element.equalsIgnoreCase(")")){
				s=s.concat(element);
			}
		}
		//System.out.println("string"+s);
		//		
		for(int i=0;i<s.length();i++) {
			char c=s.charAt(i);
			if(c=='(') {count++;}
			else if(c==')') {count--;}
			if(count<0) {return false;}
		}
		if(count!=0) {return false;}
		return true;
	}
	
	private boolean checkfordots() {
		for(String element:infixStackClone) {
			int dots=0;
			String numSt=element;
			for(int i=0;i<numSt.length();i++) {
				char c=numSt.charAt(i);
				if(c=='.') {dots++;}
			}
			if(dots>1) {
				return true;
			}
		}
		return false;
	}
	//isNumeric() anD Isoperators from InfixtoPostfix class (create utility class?)

}
