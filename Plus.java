package MonitoredAssignment1;

public class Plus {

	public static void main(String[] args) {

		// Entering The first number as a String 
		String x = Terminal.getString("Please Enter  Number 1: ");

		// Entering The second number as a String 
		String y = Terminal.getString("Please Enter  Number 2: ") ;

		// Calling the Method Plus
		Terminal.put("The sum of the numbers is = " + plus(x, y));

	}

	public static String plus (String a , String b) {

		// Analysing the First string if all the characters are numeric values  
		for (int index = 0; index < a.length() -1 ; ++index) // () -1

			if ((a.charAt(index) != '0' || a.charAt(index) != '1' || a.charAt(index) != '2' || a.charAt(index) != '3' || a.charAt(index) != '4' 
			|| a.charAt(index) != '5' || a.charAt(index) != '6' || a.charAt(index) != '7' || a.charAt(index) != '8' || a.charAt(index) != '9' )) {

				// in case the string has a non-numeric value, the method is terminated and a NumberFormatException is thrown
				break;
			}
		// Otherwise the string is Converted into integer type
		int aa =  Integer.parseInt(a);

		// Analysing the Second string if all the characters are numeric values  
		for (int index1 = 0; index1 < b.length() -1 ; ++index1) 

			if ((b.charAt(index1) != '0' || b.charAt(index1) != '1' || b.charAt(index1) != '2' || b.charAt(index1) != '3' || b.charAt(index1) != '4' 
			|| b.charAt(index1) != '5' || b.charAt(index1) != '6' || b.charAt(index1) != '7' || b.charAt(index1) != '8' || b.charAt(index1) != '9' )) {

				// in case the string has a non-numeric value, the method is terminated and a NumberFormatException is thrown
				break;
			}
		// Otherwise the string is Converted into integer type
		int bb =  Integer.parseInt(b);

		// Computing the Sum of the two converted strings
		int sum1= aa + bb;

		// Convert the sum of the converted strings into a String
		String sum =  Integer.toString(sum1);

		// Returns the sum of the converted strings as a String 
		return sum;

	}

}