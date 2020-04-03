package UIApplication;

import java.util.Scanner;

import classes.stackApp;

public class ExpressionEvaluatoin {
	public static void print_options() {
		System.out.println("Please choose an action");
		System.out.println("-----------------------");
		System.out.println("1 - Convert an Infix Expression to Postfix expression.");
		System.out.println("2 - Evaluate a Postfix expression.");
		System.out.println("3 - Close the program.");
		System.out.println("====================================================================");
	}
	public static void main(String[] args) {
		stackApp obj = new stackApp();
		Scanner in = new Scanner(System.in);
		int action = 0 ;
		String expression ;
		boolean validInput = false ; 
		while(action!=3)
		{
			validInput = false ; 
			print_options();
			System.out.println("Please,Choose an action :  ");
			while(!validInput){
				try {
					action = in.nextInt() ;
					in.nextLine() ;
					if(action<1 ||action>3)
						throw new RuntimeException() ;
					validInput = true ;
				}
				catch(Exception e)
				{
					in.nextLine() ;
					System.out.println("Please,Enter a valid choice : ");
				}
			}
		  if(action==1)
			{
				validInput = false ;
				while(!validInput)
				{
					try {
						System.out.println("Please ,Enter an Infix expression :");
						expression = in.nextLine() ;
						System.out.println("the Postfix of this Infxi expressoin is : "+obj.infixToPostfix(expression));
						validInput = true ;
					}
					catch(Exception e)
					{
						System.out.println(e.getMessage());
					}
				}
				System.out.println("====================================================================");
			}
		  else if(action==2)
		  {
			  validInput = false ;
				System.out.println("Please ,Enter an Postfix expression with a single space seprator  :");
			  	while(!validInput)
				{
					try {
						expression = in.nextLine() ;
						System.out.println("the result of this Postfix is : "+obj.evaluate(expression));
						validInput = true ;
					}
					catch(Exception e)
					{
						System.out.println("Error,Please insert a valid Postfix :");
					}
				}
				System.out.println("====================================================================");
		  }
		  else
			  break ;
		}
		
		in.close();
	
	}

}
