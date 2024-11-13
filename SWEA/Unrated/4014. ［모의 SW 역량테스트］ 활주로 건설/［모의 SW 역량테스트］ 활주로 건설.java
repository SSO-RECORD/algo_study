import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int N, X;
	static int[][] map;
	static int count;

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/SWEA4014_sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine().trim()); // 테스트 케이스 수
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 지형의 크기
			X = Integer.parseInt(st.nextToken()); // 경사로 길이

			map = new int[N][N];
			count = 0;

			for (int row = 0; row < N; row++) {
				st = new StringTokenizer(br.readLine());
				for (int col = 0; col < N; col++) {
					map[row][col] = Integer.parseInt(st.nextToken());
				}
			}

			// 행 검사
			for (int i = 0; i < N; i++) {
				if (isPossible(map[i])) {
					count++;
				}
			}

			// 열 검사
			for (int i = 0; i < N; i++) {
				int[] column = new int[N];
				for (int j = 0; j < N; j++) {
					column[j] = map[j][i];
				}
				if (isPossible(column)) {
					count++;
				}
			}

			sb.append("#").append(tc).append(" ").append(count).append("\n");
		}
		System.out.print(sb);
	}

	private static boolean isPossible(int[] line) {

		boolean[] used = new boolean[N];

		for (int i = 0; i < N - 1; i++) {
			if (line[i] == line[i + 1])
				continue;
			if (Math.abs(line[i] - line[i + 1]) > 1)
				return false;
			if (line[i] - line[i + 1] == 1) { // 내리막 설치
				for (int j = 1; j <= X; j++) {
					if (i + j >= N || line[i + j] != line[i + 1] || used[i + j]) {
						return false;
					}
					used[i + j] = true;
				}
			} else if (line[i] - line[i + 1] == -1) {
				for (int j = 0; j < X; j++) {
					if (i - j < 0 || line[i - j] != line[i] || used[i - j]) {
						return false;
					}
					used[i - j] = true;
				}
			}
		}

		return true;
	}

}