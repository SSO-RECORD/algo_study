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
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		input = new int[N];
		numbers = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(input);

		comb(0, 0); // 파라미터는 (현재 선택된 원소 개수, 인덱스 번호)

		System.out.print(sb);
	}

	private static void comb(int idx, int start) {
		// 기저 조건
		if (idx == M) {
			for (int num : numbers) {
				sb.append(num + " ");
			}
			sb.append("\n");
			return;
		}
		// 유도 파트
		for (int i = start; i < N; i++) {
			numbers[idx] = input[i];
			comb(idx + 1, i);
		}
	}
}