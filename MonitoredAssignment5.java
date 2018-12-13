package MonitoredAssignment1;

public class MonitoredAssignment5 {
	static String strNumber1 = "-12550";
	static String strNumber2 = "-365";
	static String strNumber3 = "-3655";
	public static char sign = '+';

	public static void main(String[] args) {
		SignAndNumber signAndNumber1 = getSignAndNumber(strNumber1);
		char sign1 = signAndNumber1.getSign();
		strNumber1 = signAndNumber1.getNumber();

		SignAndNumber signAndNumber2 = getSignAndNumber(strNumber2);
		char sign2 = signAndNumber2.getSign();
		strNumber2 = signAndNumber2.getNumber();

		SignAndNumber signAndNumber3 = getSignAndNumber(strNumber3);
		char sign3 = signAndNumber3.getSign();
		strNumber3 = signAndNumber3.getNumber();

		int strNumber1Length = strNumber1.length();
		int strNumber2Length = strNumber2.length();
		if (strNumber1Length > strNumber2Length) {
			int zerosQuantity = strNumber1Length - strNumber2Length;
			strNumber2 = addZeros(strNumber2, zerosQuantity);
		} else if (strNumber1Length < strNumber2Length) {
			int zerosQuantity = strNumber2Length - strNumber1Length;
			strNumber1 = addZeros(strNumber1, zerosQuantity);
		}
		String unsignedResult = minus(sign1, strNumber1, sign2, strNumber2, sign);
		Terminal.put(MonitoredAssignment5.sign + unsignedResult);
//		Terminal.put(MonitoredAssignment4.sign + plus(sign1, strNumber1, sign2, strNumber2, sign3, strNumber3, sign));
//  	Terminal.put(minus(sign1, strNumber1, sign2, strNumber2, sign3, strNumber3, sign));
	}

	private static SignAndNumber getSignAndNumber(String strNumber) {
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
		return new SignAndNumber(sign, strNumber);
	}

	private static String addZeros(String strNumber, int zerosQuantity) {
		for (int index = 0; index < zerosQuantity; index++) {
			strNumber = '0' + strNumber;
		}
		return strNumber;
	}

	private static String plus(char sign1, String op1, char sign2, String op2, char sign) {
		if (sign1 == '-' && sign2 == '-') {
			return plus('+', op1, '+', op2, '-');
		} else if (sign1 == '-' && sign2 == '+') {
			return minus('+', op2, '+', op1, '+');
		} else if (sign1 == '+' && sign2 == '-') {
			return minus('+', op1, '+', op2, '+');
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
		MonitoredAssignment5.sign = sign;
		return result;
	}

	private static String minus(char sign1, String op1, char sign2, String op2, char sign) {
		if (sign1 == '-' && sign2 == '-') {
			return minus('+', op2, '+', op1, '+');
		} else if (sign1 == '-' && sign2 == '+') {
			return plus('+', op2, '+', op1, '-');
		} else if (sign1 == '+' && sign2 == '-') {
			return plus('+', op1, '+', op2, '+');
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
			if (sign == '-') {
				sign = '+';
			} else if (sign == '+') {
				sign = '-';
			}
			result = minus(sign2, op2, sign1, op1, sign);
		}
		MonitoredAssignment5.sign = sign;
		return result; // returns sign several times depending of how many times the minus() called.
		// Solve this!!!
	}

	private static String plus(char sign1, String op1, char sign2, String op2, char sign3, String op3, char sign) {
		return MonitoredAssignment5.sign + minus(sign, plus(sign1, op1, sign2, op2, sign), sign3, op3, sign);
	}

	private static String minus(char sign1, String op1, char sign2, String op2, char sign3, String op3, char sign) {
		return MonitoredAssignment5.sign + minus(sign, minus(sign1, op1, sign2, op2, sign), sign3, op3, sign);
	}
}

class SignAndNumber {
	private char sign = '+';
	private String strNumber = "";

	SignAndNumber(char sign, String strNumber) {
		this.sign = sign;
		this.strNumber = strNumber;
	}

	char getSign() {
		return this.sign;
	}

	String getNumber() {
		return this.strNumber;
	}
}
