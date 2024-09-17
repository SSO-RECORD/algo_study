import java.util.Scanner;

public class Solution {

	static int N, M, result;
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		for(int tc=1; tc<=10; tc++) {
			
			int T = sc.nextInt();
			N = sc.nextInt();
			M = sc.nextInt();
			
			recursion(M);
			System.out.println("#"+T+" "+result);
		}
	}

	private static int recursion(int m) {
		//기저조건
		if(m == 1) return N;
		
		//유도파트
		result = recursion(m-1) * N;
		
		return result;
	}

}
