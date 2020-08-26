import java.util.Scanner;
import java.util.Stack;

class InfixToPrefix
{
	public static void main(String args[])
	{
		InToPrefix x = new InToPrefix();
		x.getInput();
		x.convInToPre();
		x.display();
	}
}

class InToPrefix
{
	String expr;
	String finalExpr;
	String ans;
	
	public void getInput()
	{
		System.out.println("Enter a Infix Expression");
		Scanner kb = new Scanner(System.in);
		expr = kb.next();
	}
	
	public boolean isOperator(char symbol)
	{
		switch(symbol)
		{
			case '+' : return true;
			case '-' : return true;
			case '*' : return true;
			case '/' : return true;
			case '$' : return true;
		}
		return false;
	}
	
	public boolean isParenthesis(char symbol)
	{
		switch(symbol)
		{
			case '(' : return true;
			case ')' : return true;
		}
		return false;
	}
	
		public boolean isCloseParenthesis(char symbol)
	{
		switch(symbol)
		{
			case ')' : return true;
		}
		return false;
	}
	
	
	public int getPriority(String symbol)
	{
		switch(symbol)
		{
			case "+" : return 1;
			case "-" : return 1;
			case "*" : return 2;
			case "/" : return 2;
			case "$" : return 3;
			case "^" : return 3;
		}
		return 0;
	}
	
	public void convInToPre()
	{
		int counts=0;
		Stack<String> stk = new Stack<String>();
		for(int i = expr.length()-1;i>=0;i--)
		{
			char symbol = expr.charAt(i);
			if(! isOperator(symbol))
			{	
				if(! isParenthesis(symbol))
				{
					finalExpr = finalExpr+symbol;
				}
				else
				{	if(isCloseParenthesis(symbol))
					{
						stk.push(""+symbol);
					}
					else
					{
						while(! stk.peek().equals(")"))
						{
							finalExpr = finalExpr+stk.pop();
						}
						stk.pop();
					}
				}
			}
			else
			{	
				if(counts==0)
				{
					stk.push(""+symbol);
					counts++;
				}
			else{
				int pco = getPriority(""+symbol);
				int pots = getPriority(stk.peek());
				if(pco>pots)
				{
					stk.push(""+symbol);
				}else
				{
					if(pco==pots)
					{
						stk.push(""+symbol);
					}else
					{
						finalExpr = finalExpr+stk.pop();
						if(counts==1){
							stk.push(""+symbol);
							counts++;
							i--;
						}
						i++;
					}
				}
				}
			}	
		}
		while(! stk.isEmpty()){
		finalExpr=finalExpr+stk.pop();
		}
		StringBuffer result = new StringBuffer(finalExpr);
		ans = result.reverse().toString();
	}
	
	public void display()
	{
		System.out.println("Prefix expression equivalent to given infix expression is "+ans);
	}
}