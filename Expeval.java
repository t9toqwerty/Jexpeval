import java.util.ArrayList;
import java.util.Stack;

public class Expeval {
	// function to tokenize the expression
	String[] tokenize(String expression) {
		boolean flag = false;
		String[] result = new String[expression.length()];
		int start_index = 0;
		int j = 0;
		int index = 0, pos = 0;
		if (expression.charAt(0) == '-') {
			flag = true;
			expression = expression.substring(1);
		}

		for (int i = j; i < expression.length(); i++) {
			if (expression.charAt(i) == '*' || expression.charAt(i) == '+'
					|| expression.charAt(i) == '-'
					|| expression.charAt(i) == '/') {
				String temp = expression.substring(start_index, i);
				result[index] = temp;
				index++;
				result[index] = String.valueOf(expression.charAt(i));
				start_index = i + 1;
				index++;
				pos = i;
			}
		}
		result[index] = expression.substring(pos + 1, expression.length());
		if (flag == true) {
			result[0] = "-" + result[0];
		}
		return result;

	}
//function to convert array of string infix to postfix
	String[] infixToPostfix(String infix[]) {
		Stack<String> s = new Stack<String>();
		int index = 0;
		String[] postfix = new String[infix.length];
		for (int i = 0; i < postfix.length; i++) {
			if (!isOperator(infix[i])) {
				postfix[index++] = infix[i];
			} else if (isOperator(infix[i])) {
				if (s.empty()
						|| precedenceOf(infix[i]) >= precedenceOf(String
								.valueOf(s.peek()))) {
					s.push(infix[i]);
				} else {
					while (!s.empty()
							&& precedenceOf(infix[i]) < precedenceOf(String
									.valueOf(s.peek()))) {
						postfix[index++] = s.pop();
					}

					s.push(infix[i]);
				}
			}
		}

		while (!s.empty()) {
			postfix[index++] = s.pop();
		}
		return postfix;
	}
	//function to evaluate postfixed array of string
	double evaluate(String[] postfix) {
		Stack<String> s1 = new Stack<String>();
		double result = 0;
		for (int i = 0; i < postfix.length; i++) {
			if (!isOperator(postfix[i])) {
				s1.push(postfix[i]);
			} else

			if (isOperator(postfix[i])) {
				Double Operand1 = Double.valueOf(s1.pop());
				Double Operand2 = Double.valueOf(s1.pop());
				if (postfix[i].equals("+")) {
					result = Operand2 + Operand1;
					s1.push(String.valueOf(result));
				} else if (postfix[i].equals("-")) {
					result = Operand2 - Operand1;
					s1.push(String.valueOf(result));
				} else if (postfix[i].equals("*")) {
					result = Operand2 * Operand1;
					s1.push(String.valueOf(result));
				} else if (postfix[i].equals("/")) {
					result = Operand2 / Operand1;
					s1.push(String.valueOf(result));
				}
			}
		}
		return result;
	}
//function to check precedence of operator
	int precedenceOf(String operator) {
		int pre = 0;
		if (operator.equals("/")) {
			pre = 4;
		}
		if (operator.equals("*")) {
			pre = 3;
		}
		if (operator.equals("-")) {
			pre = 2;
		}
		if (operator.equals("+")) {
			pre = 1;
		}
		return pre;
	}
//check if the given string is operator or not
	boolean isOperator(String operator) {
		if (operator.equals("+") || operator.equals("-")
				|| operator.equals("/") || operator.equals("*")) {
			return true;
		} else {
			return false;
		}
	}
//perform calculation of expression 
	double calculate(String expression) {
		String e1[] = tokenize(expression);
		ArrayList<String> list = new ArrayList<String>();

		for (String s : e1) {
			if (s != null && s.length() > 0) {
				list.add(s);
			}
		}

		e1 = list.toArray(new String[list.size()]);

		for (int i = 0; i < e1.length; i++) {
			//System.out.println(e1[i]);

		}
		//System.out.println();
		String[] e2 = infixToPostfix(e1);
		for (int i = 0; i < e2.length; i++) {
		}
		return evaluate(e2);
	}
//validate the given expression , supports only ( and )
	boolean isCorrectexpression(String s) {
		char sc[] = s.toCharArray();
		Stack<String> s2 = new Stack<String>();
		boolean flag = true;
		for (int i = 0; i < sc.length; i++) {
			if (sc[i] == '(') {
				s2.push("(");
			}
			if (sc[i] == ')') {
				try {
					s2.pop();
				} catch (Exception e) {
					e.printStackTrace();
					flag = false;
				}
			}

		}
		if (!s2.isEmpty()) {
			
			flag=false;
		}else{
		}
		return flag;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Expeval e = new Expeval();
		String exp = "2+2/2";
System.out.print(e.calculate(exp));
	}
}
