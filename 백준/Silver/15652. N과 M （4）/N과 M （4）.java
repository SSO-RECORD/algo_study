import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	private static int N, M;
	private static int[] numbers;
	private static StringBuilder sb;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		numbers = new int[M];
		comb(0, 1); //파라미터는 현재 선택된 원소의 개수
		
		System.out.print(sb);
	}

	private static void comb(int idx, int start) {
		// 기저조건
		if(idx == M) {
			for(int num : numbers) {
				sb.append(num + " ");
			}
			sb.append("\n");
			return;
		}
		// 유도파트
		for(int i=start; i<=N; i++) {
			numbers[idx] = i;
			comb(idx+1, i);
		}
	}

}
