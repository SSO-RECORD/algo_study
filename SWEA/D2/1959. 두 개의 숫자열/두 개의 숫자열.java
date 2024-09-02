import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

	private static int sum;
	private static int maxSum;

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/SWEA1959_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine()); // 테스트케이스 수 입력
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // N 입력받기
			int M = Integer.parseInt(st.nextToken()); // M 입력받기

			List<Integer> A = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			for (int n = 0; n < N; n++) {
				A.add(Integer.parseInt(st.nextToken()));
			}

			List<Integer> B = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			for (int m = 0; m < M; m++) {
				B.add(Integer.parseInt(st.nextToken()));
			}

			int aSize = A.size();
			int bSize = B.size();
			int diff = Math.abs(A.size() - B.size());
			for (int i = 0; i < diff; i++) {
				if (A.size() > B.size()) {
					B.add(0);
				} else {
					A.add(0);
				}
			}

			sum = 0;
			maxSum = Integer.MIN_VALUE;
			for (int i = 0; i < diff + 1; i++) {
				calculate(A, B);
				maxSum = Math.max(sum, maxSum);
				if (aSize < bSize) {
					rotate(A);
				} else {
					rotate(B);
				}
			}
			sb.append("#" + t + " " + maxSum + "\n");
		}
		System.out.print(sb);
	}

	// 리스트 회전 메서드
	private static void rotate(List<Integer> a) {
		int lastVal = a.get(a.size() - 1);
		a.add(0, lastVal);
		a.remove(a.size() - 1);
	}

	// 두개의 리스트를 곱하는 메서드
	public static void calculate(List<Integer> a, List<Integer> b) {
		sum = 0;
		for (int i = 0; i < a.size(); i++) {
			sum += a.get(i) * b.get(i);
		}
	}
}
