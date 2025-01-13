package algorithms;
import  java.lang.Math;
import java.util.Stack;

public class PostfixtoResult {
	private Stack<String> postfixStackreverse=new Stack<>();
	private Stack<Double> result=new Stack<>();
	private double finalresult;
	//constructor
	public PostfixtoResult(Stack<String> postfix) {
		postfixStackreverse=reverseStack(postfix,postfixStackreverse);
		//System.out.println("postfixStackreverse"+postfixStackreverse);
		calcResult();
		//Math.round(value * scale) / scale;
		finalresult=(double)Math.round(finalresult*1000)/1000;
	}
	private Stack<String> reverseStack(Stack<String> oldstack, Stack<String> newstack){
		if(oldstack.size()==0) {return newstack;}
		newstack.push(oldstack.pop());
		reverseStack(oldstack,newstack);
		return newstack;
	}
	public void calcResult() {
		double a,b,newNum;
		//postfixStack=postfix;
		while(postfixStackreverse.size()>0) {//check if number
			//System.out.println("elem"+postfixStackreverse.peek());
			//System.out.println(InfixtoPostfix.isNumeric(postfixStackreverse.peek()));
			if(InfixtoPostfix.isNumeric(postfixStackreverse.peek())) {
				newNum=Double.parseDouble(postfixStackreverse.pop());
				//System.out.println("result"+newNum);
				result.push(newNum);
				//System.out.println("result stack"+result);
			}
			else{//not numeric
			//System.out.println("result"+result);
			b=result.pop();
			a=result.pop();
			String operator=postfixStackreverse.pop();
			if(operator.equalsIgnoreCase("+")) {

				result.push(a+b);
				//System.out.println("result="+result);
			}
			else if(operator.equalsIgnoreCase("-")) {
				result.push(a-b);
			}
			else if(operator.equalsIgnoreCase("*")) {
				result.push(a*b);
			}
			else if(operator.equalsIgnoreCase("/")) {
				result.push(a/b);
			}
			else if(operator.equalsIgnoreCase("^")) {
				result.push(Math.pow(a,b));
			}
			}
		}
		//empty postfixStackreverse
		if(result.size()>0) {
			this.finalresult=result.pop();
		}
		else {this.finalresult=0;}
	}
	public double getFinalresult() {
		return finalresult;
	}
}
