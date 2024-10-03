import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int[][] arr;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine().trim());
		arr = new int[N][N];
		for(int row=0; row<N; row++) {
			st = new StringTokenizer(br.readLine());
			for(int col=0; col<N; col++) {
				arr[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(recursion(N, 0, 0));

	}
	private static int recursion(int n, int row, int col) {
		//기저조건
		if(n == 1) {
			return arr[row][col];
		}
		
		//유도파트
		int[] award = new int[] {
				recursion(n/2, row, col),
				recursion(n/2, row, col+n/2),
				recursion(n/2, row+n/2, col),
				recursion(n/2, row+n/2, col+n/2)
		};
		Arrays.sort(award);
		return award[1];
	}

}
