import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[] arr;
	static boolean[] isSelected;
	static int[] numbers;
	static int maxSum, result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine().trim());// 입력받을 정수의 수

		arr = new int[N];
		isSelected = new boolean[N];
		numbers = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		maxSum = Integer.MIN_VALUE;
		perm(0);
		System.out.println(maxSum);
	}

	private static void perm(int idx) {
		// 기저조건
		if (idx == N) {
			int sum = 0;
			for (int n = 1; n < N; n++) {
				sum += Math.abs(numbers[n - 1] - numbers[n]);
			}
			maxSum = Math.max(sum, maxSum);
			return;
		}

		// 유도파트
		for (int i = 0; i < N; i++) {
			if (isSelected[i])
				continue;
			numbers[idx] = arr[i];
			isSelected[i] = true;
			perm(idx + 1);
			isSelected[i] = false;
		}
	}
}
