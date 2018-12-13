package MonitoredAssignment1;

public class MonitoredAssignment1_Amjad {
	public static void main(String[] args) {

		String x = Terminal.getString("Please Enter a Numeric String:");
		String y = Terminal.getString("Please Enter a Numeric String:");
		String z = Terminal.getString("Please Enter a Numeric String:");
		
		boolean xValid = isStringValid(x);
		boolean yValid = isStringValid(y);
		boolean zValid = isStringValid(z);

		System.out.println(plus(x, y));
	}

	private static boolean isStringValid(String x) {

		//Checking if the first character of the string is legal input
		if ((x.charAt(0) != '+' && x.charAt(0) != '-') && (x.charAt(0) < '0' && x.charAt(0) > '9')) {
			throw new NumberFormatException("Illegal Input");
		}
		// Checking if the string contains an illegal input
		for (int index = 1; index < x.length(); index++) {
			if (x.charAt(index) < '0' || x.charAt(index) > '9') {
				throw new NumberFormatException("Illegal Input");
			}
		}
		return true;
	}

	public static String plus(String op1, String op2) {

		String result = "";
		
		char signOfOp1 = sign(op1);
		char signOfOp2 = sign(op2);
		char sign = 0;

		if (signOfOp1 != signOfOp2) {
			result = subtraction(op1, op2);
			sign = signForSubtraction(op1, op2);
		}
		if (signOfOp1 == signOfOp2) {
			result = addition(op1, op2);
			sign = signOfOp1;
		}

		return sign + result;
	}

	public static String addition(String op1, String op2) {

		String result = "";

		op1 = stringWithoutSign(op1);
		op2 = stringWithoutSign(op2);

		int lengthDifference = op1.length() - op2.length();

		if (op1.length() < op2.length()) {
			op1 = addZeros(op1, lengthDifference);
		}
		if (op1.length() > op2.length()) {
			op2 = addZeros(op2, lengthDifference);
		}

		int index = 0;
		int carryIn = 0;
		int carryOut = 0;
		int sum = 0;

		for (index = op1.length() - 1; index >= 0; --index)

			for (index = op2.length() - 1; index >= 0; --index) {

				carryIn = carryOut;

				int valueOfOp1 = op1.charAt(index) - '0';
				int valueOfOp2 = op2.charAt(index) - '0';

				sum = carryIn + valueOfOp1 + valueOfOp2;

				if (sum > 9) {
					sum = sum - 10;
					carryOut = 1;
				}

				if (index == 0 && ((op1.charAt(0) - '0') + (op2.charAt(0) - '0')) > 9) {
					result = "1" + sum + result;
				} else {
					result = sum + result;
				}
			}

		return result;
	}

	public static String subtraction(String op1, String op2) {

		String result = "";

		if (op1.length() >= op2.length()) {
			result = minus(op1, op2);
		}
		if (op1.length() < op2.length()) {
			result = minus(op2, op1);
		}
		return result;
	}

	public static String minus(String op1, String op2) {

		String result = "";

		op1 = stringWithoutSign(op1);
		op2 = stringWithoutSign(op2);

		int lengthDifference = op1.length() - op2.length();

		if (op1.length() < op2.length()) {
			op1 = addZeros(op1, lengthDifference);
		}
		if (op1.length() > op2.length()) {
			op2 = addZeros(op2, lengthDifference);
		}

		int index = 0;
		int carryIn = 0;
		int carryOut = 0;
		int sub = 0;

		for (index = op1.length() - 1; index >= 0; --index)

			for (index = op2.length() - 1; index >= 0; --index) {

				carryIn = carryOut;
				int valueOfOp1 = op1.charAt(index) - '0';
				int valueOfOp2 = op2.charAt(index) - '0';

				if (valueOfOp1 < valueOfOp2) {
					valueOfOp1 = valueOfOp1 + 10;
					carryOut = -1;
				} else {
					carryOut = 0;
				}
				sub = valueOfOp1 - valueOfOp2 + carryIn;

				result = sub + result;
			}
		return result;
	}

	public static String addZeros(String op, int lengthDifference) {

		if (lengthDifference < 0) {
			lengthDifference = -lengthDifference;
		}
		for (int index = 0; index < lengthDifference; ++index) {
			op = index * 0 + op;
		}
		return op;
	
	}

	public static String stringWithoutSign(String op) {

		char firstChar = op.charAt(0);

		if (firstChar == '-' || firstChar == '+') {
			op = op.substring(1, op.length());
		}

		return op;
	}

	public static char sign(String op) {

		char sign = op.charAt(0);

		if (sign == '+' || (sign >= '0' && sign <= '9')) {
			sign = '+';
		}
		if (sign == '-') {
			sign = '-';
		}

		return sign;
	}

	public static char signForSubtraction(String op1, String op2) {

		char result = 0;

		if (op1.length() > op2.length()) {
			result = sign(op1);
		}
		if (op1.length() < op2.length()) {
			result = sign(op2);
		}
		if (op1.length() == op2.length()) {

			for (int indexForSign = 0; indexForSign < op1.length();) {

				int m = op1.charAt(indexForSign);
				int n = op2.charAt(indexForSign);

				if (m > n) {
					result = sign(op1);
					break;
				}
				if (m < n) {
					result = sign(op2);
					break;
				}
				if (m == n) {
					++indexForSign;
				}
			}
		}
		return result;
	}
}