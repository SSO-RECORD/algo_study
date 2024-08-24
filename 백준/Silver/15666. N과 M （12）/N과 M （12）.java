import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	private static int N, M;
	private static int[] input;
	private static int[] numbers;
	private static StringBuilder sb;
	private static Set<String> resultSet;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		resultSet = new HashSet<>();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		input = new int[N];
		numbers = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(input);
		comb(0, 0);
		System.out.print(sb);
	}

	private static void comb(int idx, int start) {
		// 기저조건
		if (idx == M) {
			StringBuilder temp = new StringBuilder();
			for (int num : numbers) {
				temp.append(num + " ");
			}
			String result = temp.toString().trim();
			if (!resultSet.contains(result)) {
				resultSet.add(result);
				sb.append(result + "\n");
			}
			return;
		}
		// 유도파트
		for (int i = start; i < N; i++) {
			numbers[idx] = input[i];
			comb(idx + 1, i);
		}
	}
}
