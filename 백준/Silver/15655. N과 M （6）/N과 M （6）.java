import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	private static int N, M;
	private static int[] input;
	private static int[] numbers;
	private static StringBuilder sb;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		numbers = new int[M];
		input = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(input);
		
		comb(0, 0); //파라미터는 (현재 선택된 원소 개수, 배열의 시작 인덱스번호)
		
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
		for(int i=start; i<N; i++) {
			numbers[idx] = input[i];
			comb(idx+1, i+1);
		}
	}
}
