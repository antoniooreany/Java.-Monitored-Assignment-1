package MonitoredAssignment1;

public class MonitoredAssignment_Winny {

	public static void main(String[] args) {
		String s1 = Terminal.getString("Enter first number:");
		String s2 = Terminal.getString("Enter second number:");
		Terminal.put(plus(s1, s2));
		// String s3 = Terminal.getString("Enter third number:");
		// Terminal.put(plus(s3,plus(s1, s2)));
	}

	public static String plus(String op1, String op2) {
		String result = "";
		// Throw exception if there's something unexpected
		if (!isNumber(op1) || !isNumber(op2)) {
			throw new NumberFormatException();
		}

		if (isNegative(op1) && isNegative(op2)) {
			// Both numbers are negative
			op1 = op1.substring(1, op1.length());
			op2 = op2.substring(1, op2.length());
			result = "-" + sumPositive(op1, op2);
		} else if ((isNegative(op1) && !(isNegative(op2))) || (isNegative(op2) && !isNegative(op1))) {
			// One (and only one) number is negative
			if (isNegative(op1)) {
				result = sumNegative(op2, op1.substring(1, op1.length()));
			} else {
				result = sumNegative(op1, op2.substring(1, op2.length()));
			}
		} else {
			// Both numbers are positive
			result = sumPositive(op1, op2);
		}
		return result;
	}

	// Sum with a positive op1 and a negative op2
	private static String sumNegative(String op1, String op2) {
		String result = "";
		// We'll make sure the two strings are the same length.
		if (op1.length() > op2.length()) {
			op2 = addZeros(op2, op1.length() - op2.length());
		} else if (op2.length() > op1.length()) {
			op1 = addZeros(op1, op2.length() - op1.length());
		}

		if (isSmaller(op2, op1)) {
			// Negative number is smaller than positive number; subtraction
			result = subtract(op1, op2);
			// Remove leading zeroes
			result = subZeroes(result);
		} else {
			// Either the negative number is larger...
			if (isBigger(op2, op1)) {
				result = "-" + subtract(op2, op1);
				// Remove leading zeroes
				result = subZeroes(result);
			} else {
				// Or the values are equal
				result = "0";
			}
		}
		return result;
	}

	// Positive op1 and op2 sum
	// Code based on GeeksforGeeks DANISH_RAZA
	private static String sumPositive(String op1, String op2) {
		String result = "";
		int carry = 0;
		// We'll make sure the two strings are the same length.
		if (op1.length() > op2.length()) {
			op2 = addZeros(op2, op1.length() - op2.length());
		} else if (op2.length() > op1.length()) {
			op1 = addZeros(op1, op2.length() - op1.length());
		}

		for (int i = op1.length() - 1; i >= 0; i--) {
			// Compute sum of digits and carry
			int sum = digit(op1, i) + digit(op2, i) + carry;
			result = "" + result + (sum % 10);
			carry = sum / 10;
		}
		// Add remaining carry
		if (carry > 0) {
			result = "" + result + carry;
		}
		// Reverse string
		result = reverseString(result);
		// Return string
		return result;
	}

	// Subtract op2 from op1 (op1 must be >= op2)
	// Code based on GeeksforGeeks DANISH_RAZA
	private static String subtract(String op1, String op2) {
		String result = "";
		int carry = 0;
		op1 = reverseString(op1);
		op2 = reverseString(op2);
		for (int i = 0; i < op1.length(); i++) {
			// Compute difference of digits
			int sub = digit(op1, i) - digit(op2, i) - carry;
			if (sub < 0) {
				sub = sub + 10;
				carry = 1;
			} else {
				carry = 0;
			}
			result = result + sub;
		}
		// Reverse string
		result = reverseString(result);
		return result;
	}

	// Adds x 0's in front of String s
	private static String addZeros(String s, int x) {
		String result = s;
		for (int i = 0; i < x; i++) {
			result = '0' + result;
		}
		return result;
	}

	// Removes leading zeroes from Sting s (preserving - sign if it exists)
	private static String subZeroes(String s) {
		boolean isNegative = false;
		int i = 0;
		if (s.charAt(0) == '-') {
			isNegative = true;
			s = s.substring(1, s.length());
		}

		while (s.charAt(i) == '0') {
			i++;
		}

		s = s.substring(i, s.length());

		if (isNegative) {
			s = '-' + s;
		}
		return s;
	}

	// Returns true if op1 is smaller than op2
	private static boolean isSmaller(String op1, String op2) {

		if (op1.length() < op2.length()) {
			return true;
		}
		if (op2.length() < op1.length()) {
			return false;
		}

		for (int i = 0; i < op1.length(); i++) {
			if (op1.charAt(i) < op2.charAt(i)) {
				return true;
			} else if (op1.charAt(i) > op2.charAt(i)) {
				return false;
			}
		}
		return false;
	}

	// Returns true if op1 is bigger than op2
	private static boolean isBigger(String op1, String op2) {

		if (op1.length() > op2.length()) {
			return true;
		}
		if (op2.length() < op1.length()) {
			return false;
		}

		for (int i = 0; i < op1.length(); i++) {
			if (op1.charAt(i) > op2.charAt(i)) {
				return true;
			} else if (op1.charAt(i) < op2.charAt(i)) {
				return false;
			}
		}
		return false;
	}

	// Returns true if String s is negative
	private static boolean isNegative(String s) {
		if (s.charAt(0) == '-') {
			return true;
		}
		return false;
	}

	// Returns the digit value of s at index
	// Code based on Dr. Kratzer's
	private static int digit(String s, int index) {
		return (s.charAt(index) - '0');
	}

	// Returns true if s contains numbers (and maybe a sign at the beginning)
	private static boolean isNumber(String s) {
		if (s.charAt(0) == '+' || s.charAt(0) == '-') {
			s = s.substring(1, s.length() - 1);
		}
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) < '0' || s.charAt(i) > '9') {
				return false;
			}
		}
		return true;
	}

	// Reverses a string
	private static String reverseString(String s) {
		String reverse = "";
		for (int i = 0; i < s.length(); i++) {
			reverse = s.charAt(i) + reverse;
		}
		return reverse;
	}

}