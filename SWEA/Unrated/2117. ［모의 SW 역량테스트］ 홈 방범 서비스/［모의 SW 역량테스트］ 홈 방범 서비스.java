import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	private static int N, M;
	private static int[][] city;

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/SWEA2117_sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 도시의 크기
			M = Integer.parseInt(st.nextToken()); // 하나의 집이 지불하는 비용

			// 도시정보 입력
			city = new int[N][N];
			for (int row = 0; row < N; row++) {
				st = new StringTokenizer(br.readLine());
				for (int col = 0; col < N; col++) {
					city[row][col] = Integer.parseInt(st.nextToken());
				}
			}

			// 가능한 최대 집의 수를 계ㅆ산
			int result = 0;
			for (int row = 0; row < N; row++) {
				for (int col = 0; col < N; col++) {
					result = Math.max(result, fun1(row, col));
				}
			}
			sb.append("#" + tc + " " + result + "\n");
		}
		// 출력
		System.out.print(sb);
	}

	// (row, col)을 중심으로 k에 대해 가능한 최대 집 수 계산
	public static int fun1(int row, int col) {
		int maxHouse = 0;
		for (int k = 1; k < 2 * N - 1; k++) { // 가능한 모든 크기 k를 탐색
			int house = houseCnt(row, col, k); // 크기 k에서의 집 수 계산
			int cost = k * k + (k - 1) * (k - 1);
			int profit = house * M - cost;

			if (profit >= 0) {
				maxHouse = Math.max(house, maxHouse);
			}
		}
		return maxHouse;
	}

	// k값에 따라 포함되는 집의 수 count
	private static int houseCnt(int row, int col, int k) {
		int houseCnt = 0;

		for (int i = row - (k - 1); i <= row + (k - 1); i++) {
			for (int j = col - (k - 1); j <= col + (k - 1); j++) {
				if (i >= 0 && i < N && j >= 0 && j < N && Math.abs(row - i) + Math.abs(col - j) <= k - 1) {
					if (city[i][j] == 1) {
						houseCnt++;
					}
				}
			}
		}
		return houseCnt;
	}
}
