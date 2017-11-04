import javax.swing.*;

// This program is to test the Complex number class

public class ComplexTest {
   public static void main( String args[] )
   {
	 // create and intialize Complex objects
      Complex a, b;
      a = new Complex( 9.9, 7.7 );
      b = new Complex( 1.2, 3.1 );
 
	 // perform calculation on Complex numbers
      String result = "a = " + a;
      result += "\nb = " + b;
      result += "\na + b = " + a.add( b );
      result += "\na - b = " + a.subtract( b );
   
	 // display the result in a message dialog
      JOptionPane.showMessageDialog(
         null, result, "Complex Test",
         JOptionPane.INFORMATION_MESSAGE );
   }
}