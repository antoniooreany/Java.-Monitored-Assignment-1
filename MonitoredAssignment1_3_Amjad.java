// Group Members: Winifred Lu,	Amjad	Alissa Alkhalaf, Anton Gorshkov

package MonitoredAssignment1;

public class MonitoredAssignment1_3_Amjad {

	public static void main(String[] args) {

		String x = Terminal.getString("Please Enter a Numeric String:");
		String y = Terminal.getString("Please Enter a Numeric String:");
		String z = Terminal.getString("Please Enter a Numeric String:");

		Terminal.put("The sum of all Strings is = " + plus(plus(x, y), z));
	}

	// This method decides the operation and sign which need to be assigned to the
	// strings
	public static String plus(String op1, String op2) {

		String result = "";
		String signOfTheResult = "";

		// Checking for the validity of the string in case of illegal inputs
		boolean op1Valid = isValidString(op1);
		boolean op2Valid = isValidString(op2);

		if (!op1Valid || !op2Valid) {
			throw new NumberFormatException("Illegal Input(s)");
		}

		// The method sign returns the sign of the numeric string
		String signOfOp1 = sign(op1);
		String signOfOp2 = sign(op2);

		// if the signs are not the same then subtraction method is called directs the
		// subtraction operation.
		// The method signForSubtraction determines the sign for the result of the
		// subtraction operation.
		if (!signOfOp1.equals(signOfOp2)) {
			result = minus(op1, op2);
			signOfTheResult = signForSubtraction(op1, op2);
		}

		// if the signs are the same then the addition method is called, which implement
		// the addition operation.
		// the sign for the result of the addition is any sign of one of the strings
		if (signOfOp1.equals(signOfOp2)) {
			result = addition(op1, op2);
			signOfTheResult = signOfOp1;
		}

		// if the sign = "+", we don't need to show it as a result
		if (signOfTheResult.equals("+")) {
			signOfTheResult = "";
		}

		return signOfTheResult + result;
	}

	// This method implement the addition operation
	private static String addition(String op1, String op2) {

		String result = "";

		// If the any string has a sign, the method stringWithoutSign returns the string
		// without a sign
		String op1Modified = stringWithoutSign(op1);
		String op2Modified = stringWithoutSign(op2);

		// Here the method addZeros returns the shorter string being equal to the other
		// string by adding zeros to the left of the string
		int lengthDifference = op1Modified.length() - op2Modified.length();

		if (op1Modified.length() < op2Modified.length()) {
			op1Modified = addZeros(op1Modified, -lengthDifference);
		}
		if (op1Modified.length() > op2Modified.length()) {
			op2Modified = addZeros(op2Modified, lengthDifference);
		}

		int index = 0;
		int carryIn = 0;
		int sum = 0;

		for (index = op2Modified.length() - 1; index >= 0; --index) {

			int valueOfOp1 = op1Modified.charAt(index) - '0';
			int valueOfOp2 = op2Modified.charAt(index) - '0';

			sum = valueOfOp1 + valueOfOp2 + carryIn;

			if (sum > 9) {
				sum = sum - 10;
				carryIn = 1;
			}

			// if the sum of the last numbers is more than 9 we add extra 1 to the left of
			// the final result
			if (index == 0 && (sum > 9)) {
				result = "1" + sum + result;
			} else {
				result = sum + result;
			}
		}
		
		//delete leading zeros in the result
		for (int i = 0; i < result.length(); i++) {
			if (result.charAt(i) != '0') {
				result = result.substring(i);
				break;
			}
		}

		return result;
	}

	// This methods directs the subtraction operation
	private static String minus(String op1, String op2) {

		String result = "";
		// If the any string has a sign, the method stringWithoutSign returns the string
		// without a sign
		String op1Modified = stringWithoutSign(op1);
		String op2Modified = stringWithoutSign(op2);

		// We subtract the longest string which must have the numeric value from the
		// shorter string regardless of the sign which is determined in a separate
		// method
		if (op1Modified.length() > op2Modified.length()) {
			result = subtraction(op1Modified, op2Modified);
		}
		if (op1Modified.length() < op2Modified.length()) {
			result = subtraction(op2Modified, op1Modified);
		}

		// If the strings have the same sign then we look for (the sign of the
		// subtraction operation).
		// Because it is in a separate method and the method can determines the sign
		// before implementing the subtraction operation.
		if (op1Modified.length() == op2Modified.length()) {
			if (signForSubtraction(op1, op2).equals(sign(op1))) {
				result = subtraction(op1Modified, op2Modified);
			} else {
				result = subtraction(op2Modified, op1Modified);
			}
		}

		return result;
	}

	// This method implements the subtraction operation
	private static String subtraction(String op1, String op2) {

		String result = "";

		// The method addZeros returns the shorter string being equal to the other
		// string by adding zeros to the left of the string
		int lengthDifference = op1.length() - op2.length();

		if (op1.length() < op2.length()) {
			op1 = addZeros(op1, -lengthDifference);
		}
		if (op1.length() > op2.length()) {
			op2 = addZeros(op2, lengthDifference);
		}

		int carryIn = 0;
		int sub = 0;
		int index = 0;

		for (index = op1.length() - 1; index >= 0; --index) {

			int valueOfOp1 = op1.charAt(index) - '0';
			int valueOfOp2 = op2.charAt(index) - '0';

			sub = valueOfOp1 - valueOfOp2 - carryIn;

			// if the character's value of the first string is less than the character's
			// value of the second string we add 10 to the character of the first string and
			// carry out is -1.
			// it is like borrowing from the adjacent character...
			if (sub < 0) {
				sub = 10 + sub;
				carryIn = 1;
			} else {
				carryIn = 0;
			}

			result = sub + result;
		}

		return result;
	}

	// This methods checks for the sign of the number
	private static String sign(String op) {

		char sign = op.charAt(0);

		// If the string does not have a sign at the first character then the sign is +
		if (sign == '+' || (sign >= '0' && sign <= '9')) {
			sign = '+';
		}

		if (sign == '-') {
			sign = '-';
		}
		String result = Character.toString(sign);

		return result;
	}

	// The method signForSubtraction determines the sign of the result, if
	// implementing the subtraction operation
	private static String signForSubtraction(String op1, String op2) {

		String signOfOp1 = sign(op1);
		String signOfOp2 = sign(op2);

		op1 = stringWithoutSign(op1);
		op2 = stringWithoutSign(op2);

		String result = "";

		if (op1.length() > op2.length()) {
			result = signOfOp1;
		}

		if (op1.length() < op2.length()) {
			result = signOfOp2;
		}

		// If the strings are of the same length, we examine the number at the index 0
		// to determine the sign.
		// if the characters are of similar value then we examine the following
		// characters
		if (op1.length() == op2.length()) {

			for (int index = 0; index < op1.length();) {

				int valueOfOp1 = op1.charAt(index) - '0';
				int valueOfOp2 = op2.charAt(index) - '0';

				// Checking for Bigger string in value by comparing the digits starting form the
				// MSD
				if (valueOfOp1 > valueOfOp2) {
					return result = signOfOp1;
				}
				if (valueOfOp1 < valueOfOp2) {
					return result = signOfOp2;
				}

				if (valueOfOp1 == valueOfOp2) {
					++index;
				}
			}
		}

		return result;
	}

	// If the any string has a sign, the method stringWithoutSign returns the string
	// without a sign
	private static String stringWithoutSign(String op) {

		char firstChar = op.charAt(0);

		if (firstChar == '-' || firstChar == '+') {
			op = op.substring(1, op.length());
		}

		return op;
	}

	// Here the method addZeros returns the shorter string being equal to the other
	// string by adding zeros to the left of the string
	private static String addZeros(String op, int lengthDifference) {

		for (int index = 0; index < lengthDifference; ++index) {
			op = 0 + op;
		}

		return op;
	}

	// This method checks if the string has legal characters such as numbers and
	// signs '+' or '-'
	private static boolean isValidString(String x) {

		// Checking if the first character of the string is legal input, if not the
		// program throws an exception
		if ((x.charAt(0) != '+' && x.charAt(0) != '-') && (x.charAt(0) < '0' && x.charAt(0) > '9')) {
			return false;
		}

		// Checking if the string contains an illegal input, if not the program throws
		// an exception
		for (int index = 1; index < x.length(); index++) {
			if (x.charAt(index) < '0' || x.charAt(index) > '9') {
				return false;
			}
		}
		return true;
	}
}