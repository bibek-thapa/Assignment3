package com.example.crossreference;

import java.util.Scanner;

//This program computes the average and maximum of a set
//of input values.

public class DataAnalyzer
{  
public static void main(String[] args)
{  
   Scanner in = new Scanner(System.in);	// create a Scanner 
   DataSet data = new DataSet();		// create a DataSet

   boolean done = false;
   while (!done)				// get input from the user
   {  
      System.out.print("Enter value, Q to quit: ");
      String input = in.next(); 
      if (input.equalsIgnoreCase("Q"))
         done = true;
      else
      {  
         double x = Double.parseDouble(input);
         data.add(x);
      }
   }

	// output the average and maximum
   System.out.println("Average = " + data.getAverage());
   System.out.println("Maximum = " + data.getMaximum());
}
}
