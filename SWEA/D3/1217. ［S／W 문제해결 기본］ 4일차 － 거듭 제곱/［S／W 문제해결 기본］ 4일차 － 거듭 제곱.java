import java.util.Scanner;

public class Solution {

	static int N, M;
	
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/SWEA1217_input.txt"));
		Scanner sc = new Scanner(System.in);
		
		for(int tc=1; tc<=10; tc++) {
			
			int T = sc.nextInt();
			N = sc.nextInt();
			M = sc.nextInt();
			
			int result = recursion(M);
			System.out.println("#"+T+" "+result);
		}
	}

	private static int recursion(int m) {
		//기저조건
		if(m == 1) return N;
		
		//유도파트
		return recursion(m-1) * N;
		
	}

}
