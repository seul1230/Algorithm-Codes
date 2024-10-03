package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution_1218_D4_괄호짝짓기_조예슬 {
	static int N;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for (int t=1;t<11;t++) {
			Stack<Character> stack = new Stack<>();
			N = Integer.parseInt(br.readLine());
			sb.append("#").append(t).append(" ");
			String s = br.readLine();
			for (int i=0;i<N;i++) {
				char now = s.charAt(i);
				if (now==')' && stack.peek()=='(') stack.pop();
				else if (now=='>' && stack.peek()=='<') stack.pop();
				else if (now=='}' && stack.peek()=='{') stack.pop();
				else if (now==']' && stack.peek()=='[') stack.pop();
				else stack.push(now);
			}
			if (stack.isEmpty())
				sb.append(1).append("\n");
			else
				sb.append(0).append("\n");
		}
		System.out.print(sb);
	}
}
