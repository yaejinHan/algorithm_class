/// used lecture slide for a reference

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class reverse_polish_notation {
	
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws IOException {

		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String line;
		
		ArrayList<Character> operator = new ArrayList<>(Arrays.asList('*', '/', 'A', 'B', '+', '-'));
		String postFix = "";
		Stack<Character> stack = new Stack<Character>();

		
		while ((line = br.readLine()) != null) {
			sb.append(line);
			
			if (!br.ready()) 
				break;
			
		}
		
		line = br.readLine();
		
		sb.append(line);
		
	for (int i = 0; i < sb.length(); i++) {
			char ch = sb.charAt(i);
			
			if (Character.isDigit(ch)) {
				postFix += ch;
			}
			
			else if (ch == '(') {
				stack.push(ch);
				for (int k = 0; k < stack.size(); k++) {
				}
			}
			
			else if (operator.contains(ch)) {
				if (!stack.isEmpty()) {
					while (stack.peek() != '(' && (operator.indexOf(stack.peek()) - operator.indexOf(ch) <=1)) {
						postFix += stack.peek();
						stack.pop();
						if (stack.size() == 0) break;
						
						
					}
				}
				stack.push(ch);
			}
			
			else if (ch == ')') {
				while (!stack.isEmpty()) {
			
					if (stack.peek() != '(') {
						postFix += stack.peek();
						stack.pop();
					}
					else {
						stack.pop();
						break;
					}
				}
			}
		}
		
		while (!stack.isEmpty()) {
			postFix += stack.peek();
			stack.pop();
		}
		
		System.out.println(postFix);
	}
}
