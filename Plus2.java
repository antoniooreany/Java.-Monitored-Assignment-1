package MonitoredAssignment1;

public class Plus2 {

	public static void main(String[] args) {

		// Entering The first number as a String
//		String x = Terminal.getString("Please Enter  Number 1: ");

		// Entering The second number as a String
//		String y = Terminal.getString("Please Enter  Number 2: ") ;

		// Calling the Method Plus
//		Terminal.put("The sum of the numbers is = " + plus(x, y));

//		System.out.println(isStringIntLike("2342131"));
//		String a = "0123456789";
//		int count = 0; 
//		for (int index = 0; index < 10; index++) {
//			System.out.println(isCharIntLike(a, index));
//			if (isCharIntLike(a, index)) {
//				++count;
//			}
//		}
//		System.out.println(count);

//		char a = '0';
//		Character.
//		
//		System.out.println(a);
//		System.out.println('A');
//		System.out.println('1');
//		System.out.println('2');
//		System.out.println('3');
//		System.out.println('4');
//		System.out.println('5');
//		System.out.println('6');
//		System.out.println('7');
//		System.out.println('8');
//		System.out.println('9');

//		String integerToString = integerToString(-1234567890);
//		String integerToString = intToString(0);
//		System.out.println(integerToString);
//		System.out.println(integerToString.length());
		
		System.out.println(stringToInt("987624"));

//		System.out.println(-123 % 10);
	}

	public static String plus(String opr1, String opr2) {

		int num1 = 0;
		int num2 = 0;

		// Analysing the First string if all the characters are numeric values
		// Analysing the Second string if all the characters are numeric values
		if (isStringIntLike(opr1) && isStringIntLike(opr2)) {
			num1 = stringToInt(opr1); // Parse string yourself
			num2 = Integer.parseInt(opr1); // Parse string yourself
		} else {
			throw new NumberFormatException("Illegal character(s)!");
		}

		// Computing the Sum of the two converted strings
		int sum1 = num1 + num2;

		// Convert the sum of the converted strings into a String
		String sum = intToString(sum1); // Parse integer yourself

		// Returns the sum of the converted strings as a String
		return sum;

	}

	private static int stringToInt(String opr) {

		int result = 0;
		int oprLength = opr.length();
		for (int index = oprLength - 1; index >= 0; index--) {
			int currentChar = opr.charAt(index);
			switch (currentChar) {
			case '0':
				result = (int) (result + Math.pow(10, oprLength - index - 1) * 0);
				break;
			case '1':
				result = (int) (result + Math.pow(10, oprLength - index - 1) * 1);
				break;
			case '2':
				result = (int) (result + Math.pow(10, oprLength - index - 1) * 2);
				break;
			case '3':
				result = (int) (result + Math.pow(10, oprLength - index - 1) * 3);
				break;
			case '4':
				result = (int) (result + Math.pow(10, oprLength - index - 1) * 4);
				break;
			case '5':
				result = (int) (result + Math.pow(10, oprLength - index - 1) * 5);
				break;
			case '6':
				result = (int) (result + Math.pow(10, oprLength - index - 1) * 6);
				break;
			case '7':
				result = (int) (result + Math.pow(10, oprLength - index - 1) * 7);
				break;
			case '8':
				result = (int) (result + Math.pow(10, oprLength - index - 1) * 8);
				break;
			case '9':
				result = (int) (result + Math.pow(10, oprLength - index - 1) * 9);
				break;
			default:
				break;
			}
		}
		return result;
	}

	private static String intToString(int number) {
		String result = "";
		String sign = "";

		if (number < 0) {
			sign = "-";
			number = -number;
		} else if (number == 0) {
			return "0";
		}
		return parseIntToString(number, result, sign);
	}

	private static String parseIntToString(int number, String result, String sign) {
		while (number != 0) {
			switch (number % 10) {
			case 0:
				result = "0" + result;
				break;
			case 1:
				result = "1" + result;
				break;
			case 2:
				result = "2" + result;
				break;
			case 3:
				result = "3" + result;
				break;
			case 4:
				result = "4" + result;
				break;
			case 5:
				result = "5" + result;
				break;
			case 6:
				result = "6" + result;
				break;
			case 7:
				result = "7" + result;
				break;
			case 8:
				result = "8" + result;
				break;
			case 9:
				result = "9" + result;
				break;
			default:
				throw new NumberFormatException("Illegal character(s)");
			}
			number /= 10;
		}
		return sign + result;
	}

	private static boolean isStringIntLike(String a) {

		for (int index = 0; index < a.length() - 1; ++index) // () -1
		{
			if (!isCharIntLike(a, index)) {
				return false;
			}
		}
		return true;
	}

	private static boolean isCharIntLike(String a, int index) {
		if (a.charAt(index) < '0' || a.charAt(index) > '9') {
			// in case the string has a non-numeric value, the method is terminated and a
			// NumberFormatException is thrown
			return false;
		} else {
			return true;
		}
	}
}