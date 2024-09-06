import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[] s;
	static int[] numbers;
	static int k;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		sb = new StringBuilder();

		while (true) {
			st = new StringTokenizer(br.readLine());
			k = Integer.parseInt(st.nextToken()); // k개의 수 고르기
			if (k == 0)
				break;
			s = new int[k];
			numbers = new int[6];
			for (int i = 0; i < k; i++) {
				s[i] = Integer.parseInt(st.nextToken());
			}

			comb(0, 0);
			sb.append("\n");
		}
		System.out.print(sb);
	}

	private static void comb(int idx, int start) {
		// 기저조건
		if (idx == 6) {
			for (int num : numbers) {
				sb.append(num + " ");
			}
			sb.append("\n");
			return;
		}

		// 유도파트
		for (int i = start; i < k; i++) {
			numbers[idx] = s[i];
			comb(idx + 1, i + 1);
		}
	}
}
