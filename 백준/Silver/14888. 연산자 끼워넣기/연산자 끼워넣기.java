import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static long[] arr;
	static char[] operArr;
	static char[] newOperArr;
	static boolean[] isSelected;
	static long minValue, maxValue;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine().trim()); // 수의 개수

		arr = new long[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		int plus = Integer.parseInt(st.nextToken()); // +연산자의 개수
		int minus = Integer.parseInt(st.nextToken()); // -연산자의 개수
		int multi = Integer.parseInt(st.nextToken()); // *연산자의 개수
		int divide = Integer.parseInt(st.nextToken()); // 나누기 연산자의 개수

		// int operator = plus + minus + multi + divide;

		operArr = new char[N - 1];
		newOperArr = new char[N - 1];
		isSelected = new boolean[N - 1];

		for (int i = 0; i < plus; i++) {
			operArr[i] = '+';
		}

		for (int i = plus; i < plus + minus; i++) {
			operArr[i] = '-';
		}

		for (int i = plus + minus; i < plus + minus + multi; i++) {
			operArr[i] = '*';
		}

		for (int i = plus + minus + multi; i < operArr.length; i++) {
			operArr[i] = '/';
		}

//		for(int i=0; i<operArr.length; i++) {
//			System.out.print(operArr[i] + " ");
//		}

		minValue = Long.MAX_VALUE;
		maxValue = Long.MIN_VALUE;
		perm(0);
		System.out.println(maxValue);
		System.out.println(minValue);

	}

	private static void perm(int idx) {
		// 기저조건
		if (idx == N - 1) {

			long result = arr[0];
			for (int i = 0; i < N - 1; i++) {
				if (newOperArr[i] == '+') {
					result += arr[i + 1];
				} else if (newOperArr[i] == '-') {
					result -= arr[i + 1];
				} else if (newOperArr[i] == '*') {
					result *= arr[i + 1];
				} else if (newOperArr[i] == '/') {
					if (result < 0) {
						result = (Math.abs(result) / arr[i+1]) * -1;
					} else {
						result /= arr[i + 1];
					}
				}
			}

			if (result < minValue) {
				minValue = result;
			}
			if (result > maxValue) {
				maxValue = result;
			}
			return;
		}

		// 유도파트
		for (int i = 0; i < N - 1; i++) {
			if (isSelected[i])
				continue;
			isSelected[i] = true;
			newOperArr[idx] = operArr[i];
			perm(idx + 1);
			isSelected[i] = false;
		}
	}

}
