package MonitoredAssignment1;

import Methods.Terminal;

public class MonitoredAssignment2 {

	public static void main(String[] args) {

		String strNumber1 = "+12550";
		String strNumber2 = "-365";

		char sign1 = strNumberCheck(strNumber1);
		char sign2 = strNumberCheck(strNumber2);

		int strNumber1Length = strNumber1.length();
		int strNumber2Length = strNumber2.length();
		if (strNumber1Length > strNumber2Length) {
			int zerosQuantity = strNumber1Length - strNumber2Length;
			strNumber2 = addZeros(strNumber2, zerosQuantity);
		} else if (strNumber1Length < strNumber2Length) {
			int zerosQuantity = strNumber2Length - strNumber1Length;
			strNumber1 = addZeros(strNumber1, zerosQuantity);
		}

		Terminal.put(plus(sign1, strNumber1, sign2, strNumber2));
		Terminal.put(minus(sign1, strNumber1, sign2, strNumber2));
		Terminal.put(minus(sign1, strNumber2, sign2, strNumber1));

	}

	private static char strNumberCheck(String strNumber) {
		char sign = '+';
		if (strNumber.charAt(0) == '-') {
			sign = '-';
			strNumber = strNumber.substring(1, strNumber.length());
		} else if (strNumber.charAt(0) == '+') {
			sign = '+';
			strNumber = strNumber.substring(1, strNumber.length());
		}
		for (int index = 0; index < strNumber.length(); index++) {
			if (strNumber.charAt(index) < '0' || strNumber.charAt(index) > '9') {
				throw new NumberFormatException("Illegal number!!!");
			}
		}
		return sign;
	}

	private static String addZeros(String strNumber, int zerosQuantity) {
		for (int index = 0; index < zerosQuantity; index++) {
			strNumber = '0' + strNumber;
		}
		return strNumber;
	}

	private static String plus(char sign1, String op1, char sign2, String op2) {

		if (sign1 == '-' && sign2 == '-') {
			return '-' + plus('+', op1, '+', op2);
		} else if (sign1 == '+' && sign2 == '-') {
			return '+' + minus('+', op1, '+', op2);
		} else if (sign1 == '-' && sign2 == '+') {
			return '+' + minus('+', op2, '+', op1);
		}

		String result = "";
		char carryIn = '0';
		char carryOut = '0';
		char c = '0';
		for (int index = op1.length() - 1; index >= 0; index--) {
			carryIn = carryOut;
			char c1 = op1.charAt(index);
			char c2 = op2.charAt(index);
			if (c1 + c2 + carryIn > '9' + '0' + '0') {
				c = (char) (c1 + c2 + carryIn - '0' - '9' - 1);
				carryOut = '1';
			} else {
				c = (char) (c1 + c2 + carryIn - '0' - '0');
				carryOut = '0';
			}
			result = c + result;

		}
		return result;
	}

	private static String minus(char sign1, String op1, char sign2, String op2) {

		if (sign1 == '-' && sign2 == '-') {
			return '-' + minus('+', op2, '+', op1);
		} else if (sign1 == '+' && sign2 == '-') {
			return '+' + plus('+', op1, '+', op2);
		} else if (sign1 == '-' && sign2 == '+') {
			return '-' + plus('+', op2, '+', op1);
		}

		String result = "";
		char carryIn = '0';
		char carryOut = '0';
		char c = '0';
		for (int index = op1.length() - 1; index >= 0; index--) {
			carryIn = carryOut;
			char c1 = op1.charAt(index);
			char c2 = op2.charAt(index);
			int i = c1 - c2 - carryIn;
			if (i < -'0') {
				c = (char) (i + '0' + '9' + 1);
				carryOut = '1';
			} else {
				c = (char) (i + '0' + '0');
				carryOut = '0';
			}
			result = c + result;
		}
		if (carryOut == '1') {
			result = '-' + minus(sign1 ,op2, sign2, op1);
		}
		return result;
	}

}