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
	private static boolean[] isSelected;
	private static StringBuilder sb;
	private static Set<String> resultSet; // 중복된 순열을 체크하기 위한 Set

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		resultSet = new HashSet<>(); // Set 초기화

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		input = new int[N];
		numbers = new int[M];
		isSelected = new boolean[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(input);
		perm(0); // 파라미터는 현재 선택된 원소의 개수
		System.out.print(sb);
	}

	private static void perm(int idx) {
		// 기저조건
		if (idx == M) {
			StringBuilder temp = new StringBuilder();
			for (int num : numbers) {
				temp.append(num + " ");
			}
			String result = temp.toString().trim();

			// 중복된 순열이 아니면 결과에 추가
			if (!resultSet.contains(result)) {
				resultSet.add(result);
				sb.append(result).append("\n");
			}
			return;
		}
		// 유도파트
		for (int i = 0; i < N; i++) {
			if (isSelected[i])
				continue;
			isSelected[i] = true;
			numbers[idx] = input[i];
			perm(idx + 1);
			isSelected[i] = false;
		}
	}
}
