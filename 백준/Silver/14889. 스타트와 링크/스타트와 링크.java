import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int N, minValue;
	static int[][] arr;
	static List<Integer> number;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine()); // 총 사람의 수
		arr = new int[N][N];
		for (int row = 0; row < N; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < N; col++) {
				arr[row][col] = Integer.parseInt(st.nextToken());
			}
		}

		number = new ArrayList<>();
		minValue = Integer.MAX_VALUE;
		comb(0, 0);
		System.out.println(minValue);
	}

	private static void comb(int idx, int start) {
		// 기저조건 - 합 구해서 최솟값 찾기
		if (idx == N / 2) {

			List<Integer> remain = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				if (!number.contains(i)) {
					remain.add(i);
				}
			}

			int diff = sum(number, remain);
			if (diff < minValue) {
				minValue = diff;
			}

			return;
		}

		// 유도파트 - 조합하기
		for (int i = start; i < N; i++) {
			number.add(i);
			comb(idx + 1, i + 1);
			number.remove(number.size() - 1);
		}
	}

	private static int sum(List<Integer> list1, List<Integer> list2) {
		int group1Sum = 0;
		for (int i = 0; i < list1.size(); i++) {
			for (int j = 0; j < list1.size(); j++) {
				if (i != j) {
					group1Sum += arr[list1.get(i)][list1.get(j)];
				}
			}
		}

		int group2Sum = 0;
		for (int i = 0; i < list2.size(); i++) {
			for (int j = 0; j < list2.size(); j++) {
				if (i != j) {
					group2Sum += arr[list2.get(i)][list2.get(j)];
				}
			}
		}

		return Math.abs(group1Sum - group2Sum);

	}

}
