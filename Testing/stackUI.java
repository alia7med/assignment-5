package testing;

import java.util.InputMismatchException;
import java.util.Scanner;


import classes.stack;
import eg.edu.alexu.csd.datastructure.stack.cs.IStack;

public class stackUI {
	
	public static void print_options() {
		System.out.println("Please choose an action");
		System.out.println("-----------------------");
		System.out.println("1 - Push");
		System.out.println("2 - Pop");
		System.out.println("3 - Peek");
		System.out.println("4 - Get szie"); 
		System.out.println("5 - Check if Empty");
		System.out.println("6 - Close the program");
		System.out.println("====================================================================");
	}

	public static void main(String[] args) {
		IStack obj = new stack();
		Scanner in = new Scanner(System.in);
		int action = 0 ; 
		boolean validInput = false ;
		while(action!=6)
		{
			print_options() ;
			validInput =false ;
			while(!validInput)
			{
				System.out.println("Please , Enter your choice :");
				try {
					action = in.nextInt() ;
					if(action<1 || action>6)
						throw new InputMismatchException();
					validInput = true ;
				}
				catch(Exception e)
				{
					in.nextLine(); // clean the stream.
					System.out.println("Wrong input.Please,Enter a valid choice.");
				} 
			}
			if(action==1)
			{
				System.out.println("Please , Enter the elemnet :");
				String str = in.next();
				try //check the input if it  is int or str ;
				{
					Integer.parseInt(str);
					obj.push(Integer.parseInt(str));
				}
				catch (Exception e)
				{
					obj.push(str);
				}
			}
			else if(action == 2)
			{
				try {
				System.out.println("The element at the top of stack is : "+obj.pop());
				}
				catch(RuntimeException e)
				{
		            System.out.println(e.getMessage()); 
				}
			}
			else if(action == 3)
			{
				try {

					System.out.println("The element at the top of stack is : "+obj.peek());
					}
				catch(RuntimeException e)
				{
		            System.out.println(e.getMessage()); 
				}
			}
			else if(action == 4)
			{
				System.out.println(obj.size());
			}
			else if(action == 5)
			{
				System.out.println(obj.isEmpty());
			}
		System.out.println("====================================================================");
		}	
		System.out.println("\t\t< thanks for using my Stack implementation >");
		in.close();
	}

}
