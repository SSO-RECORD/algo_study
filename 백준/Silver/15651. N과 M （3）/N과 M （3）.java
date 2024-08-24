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

		N = Integer.parseInt(st.nextToken()); //자연수 N 입력받기
		M = Integer.parseInt(st.nextToken()); //수열의 길이 M 입력받기
		
		numbers = new int[M]; //크기가 M인 배열 생성
		perm(0); //파라미터는 현재 선택된 원소의 개수
		
		System.out.print(sb);
	}

	private static void perm(int idx) {
		// 기저 조건
		if(idx == M) {
			for(int num : numbers) {
				sb.append(num + " ");
			}
			sb.append("\n");
			return;
		}
		// 유도 파트
		for(int i=1; i<=N; i++) {
			numbers[idx] = i;
			perm(idx+1);
		}	
	}
}
