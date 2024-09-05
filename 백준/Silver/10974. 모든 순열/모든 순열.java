import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	private static int N;
	private static int[] number;
	private static boolean[] isSelected;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		number = new int[N];
		isSelected = new boolean[N];
		perm(0);
	}

	private static void perm(int idx) {
		//기저조건
		if(idx == N) {
			for(int num : number) {
				System.out.print(num + " ");
			}
			System.out.println();
			return;
		}
		
		//유도파트
		for(int i=0; i<N; i++) {
			if(isSelected[i]) continue;
			isSelected[i] = true;
			number[idx] = i+1;
			perm(idx+1);
			isSelected[i] = false;
		}
	}
}
