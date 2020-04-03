package classes;

import eg.edu.alexu.csd.datastructure.stack.cs.IExpressionEvaluator;

public class stackApp implements IExpressionEvaluator {

	
	
	public String infixToPostfix(String expression) {
		
		expression =	isValidInfix(expression); //expression without spaces .
		
		stack conv = new stack() ;
		String Postfix = "" ;
		
		for(int i=0;i<expression.length();i++)
		{
			if(Character.isLetter(expression.charAt(i)))
			{
				Postfix =Postfix+ expression.charAt(i)+' ' ;
			} 
			else if(Character.isDigit(expression.charAt(i)))
			{
				String num = "" +expression.charAt(i) ;
				for(int j = i+1; j<expression.length() && Character.isDigit(expression.charAt(j));j++)
						num = num+expression.charAt(j) ;
				
				i+=num.length()-1;
				Postfix = Postfix + num + ' ' ;
			} 
			else if(isOpenedPare(expression.charAt(i)))
			{
				conv.push(expression.charAt(i));
			}
			else if(isOperator(expression.charAt(i)))
			{
				if(conv.isEmpty())
					conv.push(expression.charAt(i));
				else
				{
					
					if(isOperator((char)conv.peek()))
					{
						if(isHighPrece((char)conv.peek(),expression.charAt(i)))
							conv.push(expression.charAt(i));
						else
						{
							Postfix = Postfix+(char)conv.pop()+' ';
							i--;
						}
					}
					else
					{
						conv.push(expression.charAt(i));
					}
						
				}
			} 
			else  //encounter closed Parenthesis .
			{
				while(!conv.isEmpty() && !isOpenedPare((char)conv.peek()))
				{
							Postfix= Postfix + (char)conv.pop()+' ' ;
				}
				conv.pop(); // pop the opened Parenthesis.
			}
		}
		// pop all elements left in the stack.
		while(!conv.isEmpty())				 
			Postfix= Postfix +(char) conv.pop() +' ' ;
		// remove the last space because it is not needed.
		Postfix = Postfix.substring(0,Postfix.length()-1) ;
		return Postfix;
	}

	
	public int evaluate(String expression) {
		stack evaluate = new stack() ;
		for (int i =0;i<expression.length();i++)
		{
			
			 if (isOperator(expression.charAt(i)))
			{
				if(i<expression.length()-1&&expression.charAt(i)=='-'&&Character.isLetterOrDigit(expression.charAt(i+1)) ) // if the operand was negative.
				{ 	
					int endInex = expression.indexOf(' ',i+1) ;
					if( Character.isDigit(expression.charAt(i+1)) )
					{
						evaluate.push(Double.parseDouble(expression.substring(i,endInex)));	 
					}
					i = endInex ;
				}
				else
				{
					double a = (double) evaluate.pop() ;
					double	b = (double) evaluate.pop() ;
					evaluate.push( doOperation(b,a, expression.charAt(i)) );
				}
			}
			else if (Character.isDigit(expression.charAt(i)))
			{
				int endIndex = expression.indexOf(' ',i+1);
				evaluate.push(Double.parseDouble((expression.substring(i, endIndex))));
				i = endIndex ;
			}
		}
		if(evaluate.size()>1)
			throw new RuntimeException("It is a wrong expression.");
		return (int)((double)evaluate.pop()) ;
	}
	/**
	 * do an Operation.
	 * @param a
	 * 	the first operand.
	 * @param b
	 * the second operand.
	 * @param c
	 * the operator .
	 * @return the result or throw exception if divided by zero.
	 */
	public double doOperation(double a,double b,char c)
	{
		if(c=='+')
			return a+b ;
		else if(c=='-')
			return a-b ;
		else if(c=='*')
				return a * b ;
		else
		{
			if(b==0)
				throw new RuntimeException("Error,in valid Operation.Can't divide by zero");
			else
				return a/b ;
		}
	}
	
	/**
	 * Check the precedence of operators.
	 * @param a
	 * the top of the stack.
	 * @param b
	 * the operator was scanned.
	 * @return true if it is high else return false.
	 */
	public boolean isHighPrece(char a,char b)
	{
		if( (a=='+'||a=='-') && (b=='/' || b=='*'))
				return true ;
		else 
			return false ;
	}
	/**
	 * Check the validity of an Infix expression.
	 * @param Infix
	 * the expression.
	 * @return the string without spaces if it is valid or throw exception if it is not.
	 */
	public String isValidInfix(String Infix)
	{
		//remove spaces & taps to ease checking the validity.
		for(int i=0;i<Infix.length();i++)
		{
			if(Infix.charAt(i)==' ' || Infix.charAt(i)=='\t')
				{
					Infix = Infix.substring(0,i) + Infix.substring(i+1);
					i--;
				}
		}
		// check the symbolics is letters or digits.
		for(int i=0;i<Infix.length();i++)
		{
			char c = Infix.charAt(i);
			if(!(Character.isLetterOrDigit(c)||isOperator(c)||isOpenedPare(c)|| isClosedPare(c)))
				throw new RuntimeException("Error,it is a wrong expression.");
		}
		//check Parentheses :
		if( ! isValidParentheses(Infix))
			throw new RuntimeException("Error,the parentheses is not balanced. ");
		// handle unary operator(-) using a dummy zero.
		for(int i = 0;i<Infix.length();i++)
		{
			if((Infix.charAt(0)=='-')||(i>0&&(isOperator(Infix.charAt(i-1))||isOpenedPare(Infix.charAt(i-1)))&&Infix.charAt(i)=='-'))
			{
				String unary = "(0-" ;
				//determine the operand has unary operator.
				String help = "" ;
				for(int j = i+1 ;j<Infix.length() && Character.isLetterOrDigit(Infix.charAt(j));j++)
				{
					help = help + Infix.charAt(j) ;
				}
				unary = unary + help + ')' ;
				Infix = Infix.substring(0,i) + unary + Infix.substring(i+1+help.length()) ; 
				i += unary.length()-1 ; 
			}
		}		
		//check expression .
		if(isOperator(Infix.charAt(0)))
			throw new RuntimeException("Error,it is a wrong expression.");
		if(isOperator(Infix.charAt(Infix.length()-1)))
			throw new RuntimeException("Error,it is a wrong expression.");
    
		int size = Infix.length();
		for(int i=0;i<Infix.length();i++)
		{	
			//check for letters .
			if(Character.isLetter(Infix.charAt(i)))
			{
				if(i>0 && !(isOperator(Infix.charAt(i-1)) || isOpenedPare(Infix.charAt(i-1))) )
				{
					throw new RuntimeException("Error,it is a wrong expression.");
				}
				if(i<size-1 && !(isOperator(Infix.charAt(i+1)) || isClosedPare(Infix.charAt(i+1))) )
				{
				throw new RuntimeException("Error,it is a wrong expression.");
				}
			} 
			//check for digits or numeric numbers .
			else if(Character.isDigit(Infix.charAt(i)))
			{
				if(i>0&&!(isOperator(Infix.charAt(i-1)) ||isOpenedPare(Infix.charAt(i-1))||Character.isDigit(Infix.charAt(i-1)) ) )
				{
					throw new RuntimeException("Error,it is a wrong expression.");
				}
				if(i<size-1&&!(isOperator(Infix.charAt(i+1)) || isClosedPare(Infix.charAt(i+1))||Character.isDigit(Infix.charAt(i+1)) ) )
				{
					throw new RuntimeException("Error,it is a wrong expression.");
				}
			}
			// check for operators before openPare or after closePare .
			else if(isOpenedPare(Infix.charAt(i)))
			{
				if(i>0&&!(isOperator(Infix.charAt(i-1))|| isOpenedPare(Infix.charAt(i-1))) )
					throw new RuntimeException("Error,it is a wrong expression.");
				if(i<size-1 && !(Character.isLetterOrDigit(Infix.charAt(i+1))|| isOpenedPare(Infix.charAt(i+1))) )
					throw new RuntimeException("Error,it is a wrong expression.");
			}
			else if(isClosedPare(Infix.charAt(i))) 
			{
				if(i>0 && !(Character.isLetterOrDigit(Infix.charAt(i-1))|| isClosedPare(Infix.charAt(i-1))) )
					throw new RuntimeException("Error,it is a wrong expression.");
				if(i<size-1 && !(isOperator(Infix.charAt(i+1))|| isClosedPare(Infix.charAt(i+1))))
					throw new RuntimeException("Error,it is a wrong expression."); 
			}
			else if(isOperator(Infix.charAt(i)))
			{
				if(i>0 && !(Character.isLetterOrDigit(Infix.charAt(i-1))|| isClosedPare(Infix.charAt(i-1))) )
					throw new RuntimeException("Error,it is a wrong expression.");
				if(i<size-1 && !(Character.isLetterOrDigit(Infix.charAt(i+1))|| isOpenedPare(Infix.charAt(i+1))))
					throw new RuntimeException("Error,it is a wrong expression."); 
			}
		}
		
		return Infix ;
	}
	/**
	 * Check if the Character is an Operator .
	 * @param c
	 * an character.
	 * @return true if it is an Operator,otherwise return false.
	 */
	public boolean isOperator(char c)
	{
		if(c=='+'||c=='-'||c=='/'||c=='*')
			return true ;
		else
			return false ;
	}
	/**
	 * Check if the char is an opened parenthesis.
	 * @param c
	 * an character.
	 * @return true if it is ,otherwise return false.
	 */
	public boolean isOpenedPare(char c)
	{
		if( c=='(' || c=='{'||c=='[' ) 
			return true ;
		else
			return false ;
	}
	/**
	 * Check if the char is a closed parenthesis.
	 * @param c
	 * an character.
	 * @return true if it is ,otherwise return false.
	 */
	
	public boolean isClosedPare(char c)
	{
		if( c==')' || c=='}'||c==']' )
			return true ;
		else
			return false ;
	}
	/**
	 * check if the parentheses are matched or not.
	 * @param a
	 * the first parenthesis.
	 * @param b
	 * the second parenthesis.
	 * @return true if it is matched,otherwise return false.
	 */
	
	public  boolean match(char a,char b)
	{
		if(a == '(')
		{
			if(b==')')
				return true ;
			else
				return false ;
		}
		else if(a=='[')
		{
			if(b==']')
				return true ;
			else
				return false;
		}
		else 
		{
			if(b=='}')
				return true ;
			else
				return false;
		}
	}
	/**
	 * Check if an string is a valid parentheses. 
	 * @param str
	 * a string to be checked.
	 * @return true if they are balanced , otherwise return false.
	 */
	public boolean isValidParentheses(String str) {
        int i =0 ;
        stack obj = new stack();
		while(i<str.length())
		{
			if(isOpenedPare(str.charAt(i)))
			{
				obj.push(str.charAt(i));
			}
			else if(isClosedPare(str.charAt(i)))
			{
				if(obj.isEmpty())
						return false ;
					
				char a = (char) obj.pop();
				if( !match(a,str.charAt(i)))
					return false ;
			}
			i++;	
		}
		if(obj.isEmpty())
			return true ;
		else
			return false ;
    }
}
