import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		Stack<Character> stack = new Stack<>();
		String str = "";
		
		//System.out.println(str);
		for(int i=0; i<n; i++) {
			String s = br.readLine();
			
			for(int j=0; j<s.length(); j++) {
				//System.out.println(s.charAt(j));
				//System.out.println(stack.size());
				if(s.charAt(0) == ')' || (s.length() %2 != 0)) {
					//System.out.println("a");
					str = "NO";
					//System.out.println("NO");
					break;
				}else {
					if(s.charAt(j) == '(') {
						//System.out.println("b");
						stack.push('(');
					}else if((s.charAt(j) == ')') && (stack.size() == 0)) {
						//System.out.println("c");
						str = "NO";
						break;
					}else {
						//System.out.println("d");
						stack.pop();
					}
				}	
				
			}
			if(str=="NO") {
				//System.out.println("1");
				System.out.println("NO");
			} else if(stack.size() == 0) {
				//System.out.println("2");
				System.out.println("YES");
			} else {
				//System.out.println("3");
				System.out.println("NO");
			}
			
			str = "";
			//System.out.println(str);
			stack.clear();
		}
		

	}

}
